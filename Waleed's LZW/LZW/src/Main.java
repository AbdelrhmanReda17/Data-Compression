import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {

        LZW compressor = new LZW();
        //String data = "ABAABABBAABAABAAAABABBBBBBBB";
        //65 66 65 128 128 129 131 134 130 129 66 138 139 138
        //0->127
        Scanner input = new Scanner(System.in);
        System.out.print("Enter output File name: ");
        String filename = input.nextLine();
        File destination = new File(filename);
//        compressor.compress(new File("lorem.txt"), destination);
        compressor.decompress(new File("input.txt"), destination);

    }
}