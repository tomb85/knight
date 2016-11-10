package tb.knight.model;

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

        x---x---x---x---x---x
      3 | A | B | C | D | E |
        x---x---x---x---x---x
      2 | F | G | H | I | J |
        x---x---x---x---x---x
      1 | K | L | M | N | O |
        x---x---x---x---x---x
      0     | 1 | 2 | 3 |
            x---x---x---x
          0   1   2   3   4

    */

    public static MovementTable movementTable() {
        Keypad keypad = new Keypad();

        keypad.addKey('1', 1, 0);
        keypad.addKey('2', 2, 0);
        keypad.addKey('3', 3, 0);

        keypad.addKey('K', 0, 1);
        keypad.addKey('L', 1, 1);
        keypad.addKey('M', 2, 1);
        keypad.addKey('N', 3, 1);
        keypad.addKey('O', 4, 1);

        keypad.addKey('F', 0, 2);
        keypad.addKey('G', 1, 2);
        keypad.addKey('H', 2, 2);
        keypad.addKey('I', 3, 2);
        keypad.addKey('J', 4, 2);

        keypad.addKey('A', 0, 3);
        keypad.addKey('B', 1, 3);
        keypad.addKey('C', 2, 3);
        keypad.addKey('D', 3, 3);
        keypad.addKey('E', 4, 3);

        return keypad.asMovementTable();
    }

    public static MovementTable verySimpleMovementTable() {
        MovementTable table = new MovementTable();
        table.addMovement('A', 'B');
        table.addMovement('A', 'E');

        table.addMovement('B', 'A');
        table.addMovement('B', 'C');

        table.addMovement('C', 'B');
        table.addMovement('C', 'E');

        table.addMovement('E', 'A');
        table.addMovement('E', 'C');
        return table;
    }
}
