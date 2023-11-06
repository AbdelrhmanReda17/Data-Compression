import java.io.File;

public class App {
    public static void main(String[] args) throws Exception {
        LZW LZW = new LZW();
        LZW.compress(new File("input.txt"), new File("output.txt"));
        LZW.decompress(new File("output.txt"), new File("input.txt"));
    }
}
