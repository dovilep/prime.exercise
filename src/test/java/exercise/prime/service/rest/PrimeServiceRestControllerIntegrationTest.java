package exercise.prime.service.rest;

import com.jayway.restassured.RestAssured;
import exercise.prime.service.PrimeServiceApp;
import exercise.prime.service.calculator.primality.TrialDivision;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsEmptyCollection.empty;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PrimeServiceApp.class)
@WebAppConfiguration
@IntegrationTest(value = "server.port:0")
public class PrimeServiceRestControllerIntegrationTest {
    @Value("${local.server.port}")
    int restPort;

    @Before
    public void setUp() throws Exception {
        RestAssured.port = restPort;
    }

    @Test
    public void givenASmallNumberReturnsPrimes() throws Exception {
        RestAssured
                .when()
                .get("/primes/{initial}", 10)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("initial", is(equalTo(10)))
                .body("primes", is(asList(2, 3, 5, 7)));
    }

    @Test
    public void givenALargeNumberReturnsPrimes() throws Exception {
        RestAssured
                .when()
                .get("/primes/{initial}", 1000000)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("initial", is(equalTo(1000000)))
                .body("primes", hasSize(78498))
                .body("primes", hasItems(2, 3, 999979, 999983));
    }

    @Test
    public void givenANumberLessThan2ReturnsNoPrimes() throws Exception {
        RestAssured
                .when()
                .get("/primes/{initial}", -25)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("initial", is(equalTo(-25)))
                .body("primes", is(empty()));
    }

    @Test
    public void givenAnAlgorithmParameterReturnsPrimes() throws Exception {
        RestAssured
                .when()
                .get("/primes/{initial}?algorithm={name}", 1000000, TrialDivision.name)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("initial", is(equalTo(1000000)))
                .body("primes", hasSize(78498));
    }

    @Test
    public void givenInvalidAlgorithmParameterReturnsError() throws Exception {
        RestAssured
                .when()
                .get("/primes/{initial}?algorithm={name}", 1000000, "sieveoferatosthenes")
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void givenNaNReturnsBadRequest() throws Exception {
        RestAssured
                .when()
                .get("/primes/{initial}", "abc")
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void givenNoInputReturnsNotFound() throws Exception {
        RestAssured
                .when()
                .get("/primes/{initial}", "")
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }
}