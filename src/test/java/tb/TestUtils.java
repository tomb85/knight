package tb;

public class TestUtils {

 /*

    Simple keypad

    x---x---x
    | A | B |
    x---x---x---x
    | C | D | E |
    x---x---x---x
    | F | G | H |
    x---x---x---x

    */

    public static MovementTable simpleMovementTable() {
        MovementTable table = new MovementTable();
        table.addMovement('A', 'E');
        table.addMovement('A', 'G');
        table.addMovement('B', 'F');
        table.addMovement('B', 'H');
        table.addMovement('C', 'H');
        table.addMovement('E', 'A');
        table.addMovement('E', 'F');
        table.addMovement('F', 'B');
        table.addMovement('F', 'E');
        table.addMovement('G', 'A');
        table.addMovement('H', 'B');
        table.addMovement('H', 'C');
        return table;
    }
}
