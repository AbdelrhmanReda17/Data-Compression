import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello world!");
        String fileName;
        Scanner in = new Scanner(System.in);
        System.out.print("Enter file name: ");
        fileName = in.next();
        _2D_Predictive_Coding.compress(8, fileName);
    }
}