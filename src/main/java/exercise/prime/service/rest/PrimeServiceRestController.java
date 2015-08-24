package exercise.prime.service.rest;


import exercise.prime.service.calculator.PrimeNumberCalculator;
import exercise.prime.service.calculator.primality.MillerRabin;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PrimeServiceRestController {
    private final PrimeNumberCalculator calculator = new PrimeNumberCalculator();

    @RequestMapping(value = "/primes/{initial}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response getPrimes(
            @PathVariable(value = "initial") int initial,
            @RequestParam(value = "algorithm", required = false, defaultValue = MillerRabin.name) String algorithm) {

        List<Integer> primes = calculator.calculatePrimeNumbers(algorithm, initial);
        return new Response(initial, primes);
    }
}
