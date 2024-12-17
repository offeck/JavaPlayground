import java.math.BigInteger;
import java.util.Random;

public class BigIntegerOperations {
    // Task 2.1
    // Assumes n!=null
    // Returns the sum of positive integers smaller than n
    public static BigInteger sumSmaller(BigInteger n) {
        BigInteger sum = null;
        // ---------------write your code BELOW this line only! ------------------
        if (n == null) {
            throw new IllegalArgumentException("n is null");
        }
        sum = BigInteger.ZERO;
        for (BigInteger i = BigInteger.ZERO; i.compareTo(n) < 0; i = i.add(BigInteger.ONE)) {
            sum = sum.add(i);
        }
        // ---------------write your code ABOVE this line only! ------------------
        return sum;
    }

    // Task 2.2
    // Assumes n>=0
    // prints n pseudo-random numbers
    public static void printRandoms(int n) {
        // ---------------write your code BELOW this line only! ------------------
        if (n < 0) {
            throw new IllegalArgumentException("n is negative");
        }
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            System.out.println(rand.nextInt());
        }
        // ---------------write your code ABOVE this line only! ------------------
    }

    // Task 2.3
    // Assumes n!=null and n>=0
    // Returns true iff n is a prime number
    public static boolean isPrime(BigInteger n) {
        boolean ans = true;
        // ---------------write your code BELOW this line only! ------------------
        if (n == null || n.compareTo(BigInteger.ONE) < 0) {
            throw new IllegalArgumentException("n is null or less than 1");
        }
        if (n.equals(BigInteger.ONE)) {
            return false;
        }
        for (BigInteger i = BigInteger.TWO; i.multiply(i).compareTo(n) <= 0; i = i.add(BigInteger.ONE)) {
            if (n.mod(i).equals(BigInteger.ZERO)) {
                return false;
            }
        }
        // ---------------write your code ABOVE this line only! ------------------
        return ans;
    }

    // Task 2.4
    // Assumes n>1
    // Returns a randomly chosen prime number, smaller than 2^n
    public static BigInteger randomPrime(int n) {
        BigInteger myRand = null;
        // ---------------write your code BELOW this line only! ------------------
        if (n <= 1) {
            throw new IllegalArgumentException("n is less than 1");
        }
        Random rand = new Random();
        while (true) {
            BigInteger candidate = new BigInteger(n, rand);
            if (isPrime(candidate)) {
                myRand = candidate;
                break;
            }
        }
        // ---------------write your code ABOVE this line only! ------------------
        return myRand;
    }

    // Task 2.5
    // No assumptions
    // Returns false if primes==null, or is not sorted, or includes duplicates, or
    // includes a composite number
    public static boolean isValidPrimesArray(BigInteger[] primes) {
        boolean isValid = true; // Assume the array is valid initially
        // ---------------write your code BELOW this line only! ------------------
        if (primes == null) {
            return false;
        }
        for (int i = 0; i < primes.length; i++) {
            if (primes[i] == null) {
                return false;
            }
            if (i > 0 && primes[i].compareTo(primes[i - 1]) <= 0) {
                return false;
            }
            if (!isPrime(primes[i])) {
                return false;
            }
        }
        // ---------------write your code ABOVE this line only! ------------------
        return isValid;
    }

    // Task 2.6
    // Assumes primes != null, n !=null, and n>1. 'primes' is sorted, and includes
    // only unique prime numbers.
    // Returns true iff n can be expressed as a product of prime numbers from primes
    public static boolean canFactorizeToTarget(BigInteger[] primes, BigInteger n) {
        boolean ans = true;
        // ---------------write your code BELOW this line only! ------------------
        if (primes == null || n == null || n.compareTo(BigInteger.ONE) < 0) {
            throw new IllegalArgumentException("Invalid input");
        }
        String result = canFactorizeToTarget(primes, n, 0);
        if (result != "") {
            ans = true;
        } else {
            ans = false;
        }

        // ---------------write your code ABOVE this line only! ------------------
        return ans;
    }

    public static String canFactorizeToTarget(BigInteger[] primes, BigInteger n, int i) {
        if (i == primes.length) {
            return "";
        }
        if (n.mod(primes[i]).equals(BigInteger.ZERO)) {
            return primes[i] + "," + canFactorizeToTarget(primes, n.divide(primes[i]), i);
        }
        return canFactorizeToTarget(primes, n, i + 1);
    }

    // Task 2.7
    // Assumes primes != null, n !=null, and n>1. 'primes' is sorted, and includes
    // only unique prime numbers.
    // If n can be expressed as a product of prime numbers from primes, it prints
    // the numbers in the factorization
    public static void printFactorization(BigInteger[] primes, BigInteger n) {
        // ---------------write your code BELOW this line only! ------------------
        String result = canFactorizeToTarget(primes, n, 0);
        if (result != "") {
            System.out.println(result.substring(0, result.length() - 1));
        }
        // ---------------write your code ABOVE this line only! ------------------
    }

}
