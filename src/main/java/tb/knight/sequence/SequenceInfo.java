package tb.knight.sequence;

public class SequenceInfo {

    private final char code;
    private int length;
    private int vowels;
    private boolean active = true;

    public SequenceInfo(char code, int length, int vowels) {
        this.code = code;
        this.length = length;
        this.vowels = vowels;
    }

    public int getLength() {
        return length;
    }

    public int getVowels() {
        return vowels;
    }

    public void incrementLength() {
        length++;
    }

    public void incrementVowel() {
        vowels++;
    }

    public boolean active() {
        return active;
    }

    public void decrementLength() {
        length--;
    }

    public void decrementVowel() {
        vowels--;
    }

    public void deactivate() {
        active = false;
    }

    @Override
    public String toString() {
        return "SequenceInfo{" +
                "code=" + code +
                "length=" + length +
                ", vowels=" + vowels +
                ", active=" + active +
                '}';
    }
}
