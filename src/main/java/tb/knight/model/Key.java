package tb.knight.model;

public enum Key {

    A(true, 'A'),
    B(false, 'B'),
    C(false, 'C'),
    D(false, 'D'),
    E(false, 'E'),
    F(false, 'F'),
    G(false, 'G'),
    H(false, 'H'),
    I(false, 'I'),
    J(false, 'J'),
    K(false, 'K'),
    L(false, 'L'),
    M(false, 'M'),
    N(false, 'N'),
    O(true, 'O'),
    ONE(false, '1'),
    TWO(false, '2'),
    THREE(false, '3');

    private boolean isVowel;
    private char code;

    Key(boolean isVowel, char code) {
        this.isVowel = isVowel;
        this.code = code;
    }

    public boolean isVowel() {
        return isVowel;
    }

    public char getCode() {
        return code;
    }
}
