import java.io.File;

public class App {
    public static void main(String[] args) throws Exception {
        LZ77 lz77 = new LZ77();
        lz77.compress(new File("input.txt"), new File("output.txt"));
        // lz77.decompress(new File("output.txt") , new File("input.txt"));
    }
}
