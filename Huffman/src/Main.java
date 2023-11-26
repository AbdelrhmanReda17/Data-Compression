import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Huffman huffman = new Huffman();
        huffman.compress("input.txt", "compressed.bin");
        huffman.decompress("compressed.bin", "decompress.txt");
    }

}