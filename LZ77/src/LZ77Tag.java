public class LZ77Tag {
    private int position;
    private int length;
    private char nextSymbol;

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
