package exercise.prime.service.calculator;

import exercise.prime.service.calculator.primality.MillerRabin;
import exercise.prime.service.calculator.primality.PrimalityTest;
import exercise.prime.service.calculator.primality.TrialDivision;
import exercise.prime.service.error.ArgumentException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrimeNumberCalculator {
    private final Map<String, PrimalityTest> tests = new HashMap<>();

    public PrimeNumberCalculator() {
        registerPrimalityTest(new MillerRabin());
        registerPrimalityTest(new TrialDivision());
    }

    private void registerPrimalityTest(PrimalityTest test) {
        tests.put(test.name(), test);
    }

    private PrimalityTest testwithName(String test) {
        if (!tests.containsKey(test))
            throw new ArgumentException(String.format("Primality test not supported: %s. Valid options: %s.", test, tests.keySet()));

        return tests.get(test);
    }

    public List<Integer> calculatePrimeNumbers(int upTo) {
        return calculatePrimeNumbers(MillerRabin.name, upTo);
    }

    public List<Integer> calculatePrimeNumbers(String test, int upTo) {
        return calculatePrimeNumbers(testwithName(test), upTo);
    }

    private List<Integer> calculatePrimeNumbers(PrimalityTest test, int upTo) {
        List<Integer> primeNumbers = new ArrayList<>();

        int current = 2;
        while ((current = test.nextPrime(current)) <= upTo) {
            primeNumbers.add(current);
            current++;
        }

        return primeNumbers;
    }
}