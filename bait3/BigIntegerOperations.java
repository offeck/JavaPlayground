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
        canFactorizeToTarget(primes, n, false);
        // ---------------write your code ABOVE this line only! ------------------
        return ans;
    }

    // Task 2.7
    // Assumes primes != null, n !=null, and n>1. 'primes' is sorted, and includes
    // only unique prime numbers.
    // If n can be expressed as a product of prime numbers from primes, it prints
    // the numbers in the factorization
    public static void printFactorization(BigInteger[] primes, BigInteger n) {
        // ---------------write your code BELOW this line only! ------------------
        canFactorizeToTarget(primes, n, true);
        // ---------------write your code ABOVE this line only! ------------------
    }

    public static boolean canFactorizeToTarget(BigInteger[] primes, BigInteger n, boolean verbose) {
        boolean ans = true;
        // ---------------write your code BELOW this line only! ------------------
        if (primes == null || n == null || n.compareTo(BigInteger.ONE) < 0) {
            throw new IllegalArgumentException("Invalid input");
        }
        String factorization = "";
        for (int i = 0; i < primes.length; i++) {
            if (n.mod(primes[i]).equals(BigInteger.ZERO)) {
                n = n.divide(primes[i]);
                if (verbose) {
                    factorization += primes[i].toString() + ",";
                }
                i--;
            }
        }
        if (verbose && factorization.length() > 0) {
            System.out.println(factorization.substring(0, factorization.length() - 1));
        }
        if (n.equals(BigInteger.ONE)) {
            ans = true;
        }
        // ---------------write your code ABOVE this line only! ------------------
        return ans;
    }

    public static void main(String[] args) {
        System.out.println("=== BigIntegerOperations Test Suite ===\n");

        // Test sumSmaller
        System.out.println("1. Testing sumSmaller:");
        System.out.println("--------------------------");
        BigInteger[] sumTestCases = { BigInteger.valueOf(5), BigInteger.valueOf(1), BigInteger.valueOf(10) };
        for (BigInteger n : sumTestCases) {
            System.out.println("Sum of numbers smaller than " + n + ": " + sumSmaller(n));
        }

        // Test printRandoms
        System.out.println("\n2. Testing printRandoms:");
        System.out.println("--------------------------");
        System.out.println("Generating 5 random numbers:");
        printRandoms(5);

        // Test isPrime
        System.out.println("\n3. Testing isPrime:");
        System.out.println("--------------------------");
        BigInteger[] primeTestCases = {
                BigInteger.valueOf(2), // smallest prime
                BigInteger.valueOf(17), // prime
                BigInteger.valueOf(4), // composite
                BigInteger.valueOf(97), // prime
                BigInteger.valueOf(100), // composite
                BigInteger.valueOf(1) // neither prime nor composite
        };
        for (BigInteger num : primeTestCases) {
            System.out.println(num + " is prime: " + isPrime(num));
        }

        // Test randomPrime
        System.out.println("\n4. Testing randomPrime:");
        System.out.println("--------------------------");
        int[] bitLengths = { 8, 10, 12 };
        for (int bits : bitLengths) {
            BigInteger randomPrimeNum = randomPrime(bits);
            System.out.println("Random prime less than 2^" + bits + ": " + randomPrimeNum);
            System.out.println("Verification - isPrime(" + randomPrimeNum + "): " + isPrime(randomPrimeNum));
        }

        // Test isValidPrimesArray
        System.out.println("\n5. Testing isValidPrimesArray:");
        System.out.println("--------------------------");
        BigInteger[] validPrimes = { BigInteger.valueOf(2), BigInteger.valueOf(3), BigInteger.valueOf(5),
                BigInteger.valueOf(7) };
        BigInteger[] invalidPrimes1 = { BigInteger.valueOf(2), BigInteger.valueOf(4), BigInteger.valueOf(5) }; // contains
                                                                                                               // composite
        BigInteger[] invalidPrimes2 = { BigInteger.valueOf(5), BigInteger.valueOf(3), BigInteger.valueOf(7) }; // not
                                                                                                               // sorted
        BigInteger[] invalidPrimes3 = { BigInteger.valueOf(2), BigInteger.valueOf(2), BigInteger.valueOf(3) }; // contains
                                                                                                               // duplicate

        System.out.println("Valid array test: " + isValidPrimesArray(validPrimes));
        System.out.println("Invalid array (composite) test: " + isValidPrimesArray(invalidPrimes1));
        System.out.println("Invalid array (unsorted) test: " + isValidPrimesArray(invalidPrimes2));
        System.out.println("Invalid array (duplicates) test: " + isValidPrimesArray(invalidPrimes3));

        // Test canFactorizeToTarget and printFactorization
        System.out.println("\n6. Testing Factorization:");
        System.out.println("--------------------------");
        BigInteger[] testTargets = {
                BigInteger.valueOf(30), // 2 * 3 * 5
                BigInteger.valueOf(100), // 2 * 2 * 5 * 5
                BigInteger.valueOf(17), // prime number
                BigInteger.valueOf(84) // 2 * 2 * 3 * 7
        };

        for (BigInteger target : testTargets) {
            System.out.println("\nTesting factorization of " + target + ":");
            System.out.println("Can factorize: " + canFactorizeToTarget(validPrimes, target));
            System.out.print("Factorization: ");
            printFactorization(validPrimes, target);
        }
    }

}
