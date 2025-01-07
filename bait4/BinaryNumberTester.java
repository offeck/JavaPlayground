public class BinaryNumberTester {

    public static void main(String[] args) {
        BinaryNumber a = new BinaryNumber("25");
        System.out.println(a.toString());
        System.out.println(a.toInt());
        BinaryNumber b = new BinaryNumber("100");
        System.out.println(b.toString());
        System.out.println(b.toInt());
        // Check negative numbers
        BinaryNumber c = new BinaryNumber("-25");
        System.out.println(c.toString());
        System.out.println(c.toInt());
        BinaryNumber d = new BinaryNumber("-100");
        System.out.println(d.toString());
        System.out.println(d.toInt());
    }
}
