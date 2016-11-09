package tb;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import org.junit.BeforeClass;
import org.junit.Ignore;
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
        movementTable = MovementTableFactory.simpleMovementTable();
    }

    @Test
    @Ignore
    public void checkFromSingleSource() {
        shouldReturnCorrectNumberOfSequences('B', 32, 2, 1);
    }

    @Test
    @FileParameters("classpath:simple_keypad_extractor_test_cases.csv")
    public void shouldReturnCorrectNumberOfSequences(char origin, int length, int vowels, long expected) {
        SequenceExtractor extractor = SequenceExtractor.forMovementTable(movementTable).from(origin).length(length).vowels(vowels).create();
        long sequences = extractor.getNumberOfSequences();
        assertThat(sequences, is(equalTo(expected)));
    }
}