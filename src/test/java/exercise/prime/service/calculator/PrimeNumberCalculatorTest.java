package exercise.prime.service.calculator;

import exercise.prime.service.calculator.primality.MillerRabin;
import exercise.prime.service.calculator.primality.TrialDivision;
import exercise.prime.service.error.ArgumentException;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class PrimeNumberCalculatorTest {
    private final PrimeNumberCalculator calculator = new PrimeNumberCalculator();

    @Test
    public void givenASmallNumberReturnsPrimes() throws Exception {

        List<Integer> primeNumbers = calculator.calculatePrimeNumbers(3);

        assertThat(primeNumbers, hasSize(2));
        assertThat(primeNumbers, hasItems(2, 3));
    }

    @Test
    public void givenALargeNumberReturnsPrimes() throws Exception {

        List<Integer> primeNumbers = calculator.calculatePrimeNumbers(1000000);

        assertThat(primeNumbers, hasSize(78498));
        assertThat(primeNumbers.get(0), is(2));
        assertThat(primeNumbers.get(1), is(3));
        assertThat(primeNumbers.get(78496), is(999979));
        assertThat(primeNumbers.get(78497), is(999983));
    }

    @Test
    public void givenTheFirstPrimeReturnsIt() throws Exception {

        List<Integer> primeNumbers = calculator.calculatePrimeNumbers(2);

        assertThat(primeNumbers, hasSize(1));
        assertThat(primeNumbers, hasItem(2));
    }

    @Test
    public void givenANumberSmallerThanTheFirstPrimeReturnsEmptyList() throws Exception {

        assertThat(calculator.calculatePrimeNumbers(1), is(empty()));
        assertThat(calculator.calculatePrimeNumbers(-10000), is(empty()));
    }

    @Test
    public void givenMultiplePrimalityTestsReturnsTheSameResult() throws Exception {

        List<Integer> trialdivision = calculator.calculatePrimeNumbers(TrialDivision.name, 1000);
        List<Integer> millerrabin = calculator.calculatePrimeNumbers(MillerRabin.name, 1000);

        assertThat(trialdivision, hasSize(168));
        assertThat(millerrabin, hasSize(168));
        assertEquals(millerrabin, trialdivision);
    }

    @Test(expected = ArgumentException.class)
    public void givenInvalidPrimalityTestThrowsException() throws Exception {

        calculator.calculatePrimeNumbers("sieveoferatosthenes", 10);
    }
}
