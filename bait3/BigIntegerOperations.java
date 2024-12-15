import java.math.BigInteger;

public class BigIntegerOperations {
    //Task 2.1
    //Assumes n!=null
    //Returns the sum of positive integers smaller than n
    public static BigInteger sumSmaller(BigInteger n){
        BigInteger sum = null;
        // ---------------write your code BELOW this line only! ------------------

        // ---------------write your code ABOVE this line only! ------------------
        return sum;
    }

    //Task 2.2
    //Assumes n>=0
    //prints n pseudo-random numbers
    public static void printRandoms(int n){
        // ---------------write your code BELOW this line only! ------------------

        // ---------------write your code ABOVE this line only! ------------------
    }

    //Task 2.3
    // Assumes n!=null and n>=0
    //Returns true iff n is a prime number
    public static boolean isPrime(BigInteger n){
        boolean ans = true;
        // ---------------write your code BELOW this line only! ------------------

        // ---------------write your code ABOVE this line only! ------------------
        return ans;
    }

    //Task 2.4
    //Assumes n>1
    //Returns a randomly chosen prime number, smaller than 2^n
    public static BigInteger randomPrime(int n){
        BigInteger myRand = null;
        // ---------------write your code BELOW this line only! ------------------

        // ---------------write your code ABOVE this line only! ------------------
        return myRand;
    }

    //Task 2.5
    // No assumptions
    //Returns false if primes==null, or is not sorted, or includes duplicates, or includes a composite number
    public static boolean isValidPrimesArray(BigInteger[] primes) {
        boolean isValid = true; // Assume the array is valid initially
        // ---------------write your code BELOW this line only! ------------------

        // ---------------write your code ABOVE this line only! ------------------
        return isValid;
    }


    //Task 2.6
    //Assumes primes != null, n !=null, and n>1. 'primes' is sorted, and includes only unique prime numbers.
    //Returns true iff n can be expressed as a product of prime numbers from primes
    public static boolean canFactorizeToTarget(BigInteger[] primes, BigInteger n) {
        boolean ans = true;
        // ---------------write your code BELOW this line only! ------------------

        // ---------------write your code ABOVE this line only! ------------------
        return ans;
    }

    //Task 2.7
    //Assumes primes != null, n !=null, and n>1. 'primes' is sorted, and includes only unique prime numbers.
    //If n can be expressed as a product of prime numbers from primes, it prints the numbers in the factorization
    public static void printFactorization(BigInteger[] primes, BigInteger n) {
        // ---------------write your code BELOW this line only! ------------------

        // ---------------write your code ABOVE this line only! ------------------
    }

}
