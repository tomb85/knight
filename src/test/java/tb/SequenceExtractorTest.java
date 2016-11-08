package tb;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(JUnitParamsRunner.class)
public class SequenceExtractorTest {

    private static MovementTable movementTable;

    @BeforeClass
    public static void setup() {
        movementTable = simpleMovementTable();
    }


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

    private static MovementTable simpleMovementTable() {
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

    @Test
    public void test() {
        shouldReturnCorrectNumberOfSequencesPerOrigin('G', 4, 2, 1);
    }

    @Test
    @FileParameters("classpath:simple_keypad_test_cases.csv")
    public void shouldReturnCorrectNumberOfSequencesPerOrigin(char origin, int length, int vowels, long expected) {
        SequenceExtractor extractor = SequenceExtractor.forMovementTable(movementTable).from(origin).length(length).vowels(vowels).create();
        long sequences = extractor.getNumberOfSequences();
        assertThat(sequences, is(equalTo(expected)));
    }
}