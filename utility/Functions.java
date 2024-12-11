package utility;

enum Algo {
    FIVERANDOMFERMAT
}

public class Functions {
    public boolean isPrime(int num, Algo alg) {
        if (num < 2) {
            return false;
        }
        for (int i = 0; i < 5; i++) {
            int a = ((int) (Math.random() * (num - 2))) + 1;
            if (Math.pow(a, num - 1) % num != 1) {
                return false;
            }
        }
        return true;
    }
}
