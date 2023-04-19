package hw3.hash;

public class Hint {
    public static void main(String[] args) {
        System.out.println("The powers of 256 in Java are:");
        int x = 1;
        for (int i = 0; i < 10; i += 1) {
           // System.out.println(i + "th power: " + x);
            System.out.printf("%dth power: %32x\n", i, x);
            x = x * 256;
        }
    }
} 
