package tb;

public class MovementTableFactory {

 /*

    Simple keypad

        x---x---x
      2 | A | B |
        x---x---x---x
      1 | C | D | E |
        x---x---x---x
      0 | F | G | H |
        x---x---x---x
          0   1   2

    */

    public static MovementTable simpleMovementTable() {
        Keypad keypad = new Keypad();
        keypad.addKey('F', 0, 0);
        keypad.addKey('G', 1, 0);
        keypad.addKey('H', 2, 0);
        keypad.addKey('C', 0, 1);
        keypad.addKey('D', 1, 1);
        keypad.addKey('E', 2, 1);
        keypad.addKey('A', 0, 2);
        keypad.addKey('B', 1, 2);
        return keypad.asMovementTable();
    }

    /*

    Simple keypad

        x---x---x
      2 | A | B |
        x---x---x---x
      1 | C | D | E |
        x---x---x---x
      0 | F | G | H |
        x---x---x---x
          0   1   2

    */

    public static MovementTable movementTable() {
        Keypad keypad = new Keypad();
        keypad.addKey('F', 0, 0);
        keypad.addKey('G', 1, 0);
        keypad.addKey('H', 2, 0);
        keypad.addKey('C', 0, 1);
        keypad.addKey('D', 1, 1);
        keypad.addKey('E', 2, 1);
        keypad.addKey('A', 0, 2);
        keypad.addKey('B', 1, 2);
        return keypad.asMovementTable();
    }

}
