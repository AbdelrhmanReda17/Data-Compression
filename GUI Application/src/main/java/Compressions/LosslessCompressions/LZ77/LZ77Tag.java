package Compressions.LosslessCompressions.LZ77;

public class LZ77Tag {
    private final int position;
    private final int length;
    private final char nextSymbol;

    public LZ77Tag(int position, int length, char nextSymbol) {
        this.position = position;
        this.length = length;
        this.nextSymbol = nextSymbol;
    }
    public int getPosition() {
        return position;
    }
    public int getLength() {
        return length;
    }
    public char getNextSymbol() {
        return nextSymbol;
    }
}
