import java.math.BigInteger;

public class TestBigIntegerOperations {
    public static void main(String[] args) {
        System.out.println("=== BigIntegerOperations Test Suite ===\n");

        // Test sumSmaller
        System.out.println("1. Testing sumSmaller:");
        System.out.println("--------------------------");
        BigInteger[] sumTestCases = { BigInteger.valueOf(5), BigInteger.valueOf(1), BigInteger.valueOf(10) };
        for (BigInteger n : sumTestCases) {
            System.out.println("Sum of numbers smaller than " + n + ": " + BigIntegerOperations.sumSmaller(n));
        }

        // Test printRandoms
        System.out.println("\n2. Testing printRandoms:");
        System.out.println("--------------------------");
        System.out.println("Generating 5 random numbers:");
        BigIntegerOperations.printRandoms(5);

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
            System.out.println(num + " is prime: " + BigIntegerOperations.isPrime(num));
        }

        // Test randomPrime
        System.out.println("\n4. Testing randomPrime:");
        System.out.println("--------------------------");
        int[] bitLengths = { 8, 10, 12 };
        for (int bits : bitLengths) {
            BigInteger randomPrimeNum = BigIntegerOperations.randomPrime(bits);
            System.out.println("Random prime less than 2^" + bits + ": " + randomPrimeNum);
            System.out.println("Verification - isPrime(" + randomPrimeNum + "): " + BigIntegerOperations.isPrime(randomPrimeNum));
        }

        // Test isValidPrimesArray
        System.out.println("\n5. Testing isValidPrimesArray:");
        System.out.println("--------------------------");
        BigInteger[] validPrimes = { BigInteger.valueOf(2), BigInteger.valueOf(3), BigInteger.valueOf(5),
                BigInteger.valueOf(7), BigInteger.valueOf(17) };
        BigInteger[] invalidPrimes1 = { BigInteger.valueOf(2), BigInteger.valueOf(4), BigInteger.valueOf(5) }; // contains
                                                                                                               // composite
        BigInteger[] invalidPrimes2 = { BigInteger.valueOf(5), BigInteger.valueOf(3), BigInteger.valueOf(7) }; // not
                                                                                                               // sorted
        BigInteger[] invalidPrimes3 = { BigInteger.valueOf(2), BigInteger.valueOf(2), BigInteger.valueOf(3) }; // contains
                                                                                                               // duplicate

        System.out.println("Valid array test: " + BigIntegerOperations.isValidPrimesArray(validPrimes));
        System.out.println("Invalid array (composite) test: " + BigIntegerOperations.isValidPrimesArray(invalidPrimes1));
        System.out.println("Invalid array (unsorted) test: " + BigIntegerOperations.isValidPrimesArray(invalidPrimes2));
        System.out.println("Invalid array (duplicates) test: " + BigIntegerOperations.isValidPrimesArray(invalidPrimes3));

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
            System.out.println("Can factorize: " + BigIntegerOperations.canFactorizeToTarget(validPrimes, target));
            System.out.print("Factorization: ");
            BigIntegerOperations.printFactorization(validPrimes, target);
        }
    }
}
