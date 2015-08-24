package exercise.prime.service.calculator.primality;

import org.apache.commons.math3.primes.Primes;

public class MillerRabin implements PrimalityTest {

    public static final String name = "millerrabin";

    @Override
    public String name() {
        return name;
    }

    @Override
    public int nextPrime(int number) {
        return Primes.nextPrime(number);
    }
}
