public class OffByN implements CharacterComparator {
    public int step;
    public OffByN(int N) {
        step = N;
    }

    @Override
    public boolean equalChars(char x, char y) {
        int result = Math.abs(x - y);
        return result == step;
    }
}
