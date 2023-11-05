import java.io.*;
import java.nio.ByteBuffer;

public class LZWCompressor {

    private static final int CODE_TABLE_SIZE = 4096;

    private int[] codeTable;
    private ByteBuffer compressedData;

    public LZWCompressor() {
        codeTable = new int[CODE_TABLE_SIZE];
        for (int i = 0; i < CODE_TABLE_SIZE; i++) {
            codeTable[i] = i;
        }

        compressedData = ByteBuffer.allocate(1024);
    }

    public void compress(String string) throws IOException {
        int previousCode = -1;

        for (char currentCharacter : string.toCharArray()) {
            int currentCode = codeTable[currentCharacter];

            if (previousCode != -1 && codeTable[previousCode + currentCode] != -1) {
                previousCode = previousCode + currentCode;
            } else {
                writeCode(previousCode);

                if (codeTable[currentCode] == -1) {
                    codeTable[codeTable.length] = previousCode + currentCode;
                }

                previousCode = currentCode;
            }
        }

        writeCode(previousCode);
        writeCode(256); // End of string code.
    }

    private void writeCode(int code) throws IOException {
        compressedData.putShort((short) code);

        if (compressedData.remaining() < 2) {
            flushCompressedData();
        }
    }

    private void flushCompressedData() throws IOException {
        byte[] bytes = compressedData.array();

        FileOutputStream fos = new FileOutputStream("compressed.bin", true);
        fos.write(bytes, 0, compressedData.position());
        fos.close();

        compressedData.clear();
    }

    public void saveToFile(String filename) throws IOException {
        flushCompressedData();
    }

    public static void main(String[] args) throws IOException {
        LZWCompressor compressor = new LZWCompressor();
        compressor.compress("ABAABABBAABAABAAAABABBBBBBBB");
        compressor.saveToFile("compressed.bin");
    }
}
