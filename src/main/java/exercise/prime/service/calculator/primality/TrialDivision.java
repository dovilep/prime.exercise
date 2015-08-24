package exercise.prime.service.calculator.primality;

public class TrialDivision implements PrimalityTest {

    public static final String name = "trialdivision";

    @Override
    public String name() {
        return name;
    }

    @Override
    public int nextPrime(int number) {
        int current = number;
        while (!isPrime(current))
            current++;
        return current;
    }

    private boolean isPrime(int number) {
        if (number <= 1) return false;
        for (int i = 2; i * i <= number; i++)
            if (number % i == 0) return false;
        return true;
    }
}
