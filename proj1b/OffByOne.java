public class OffByOne implements CharacterComparator {
    @Override
    public boolean equalChars(char x, char y) {
        int result = Math.abs(x - y);
        return result == 1;
    }
}
