package tb;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class SequenceExtractorTest {

    private static MovementTable movementTable;

    @BeforeClass
    public static void setup() {
        movementTable = simpleMovementTable();
    }

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
        SequenceExtractor extractor = SequenceExtractor.forMovementTable(movementTable).from('A').length(4).vowels(2).create();
        long sequences = extractor.getNumberOfSequences();
        assertThat(sequences, is(equalTo(3l)));
    }


}