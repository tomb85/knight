package tb;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import tb.knight.model.MovementTable;
import tb.knight.model.MovementTableFactory;
import tb.knight.sequence.SequenceExtractor;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(JUnitParamsRunner.class)
public class SequenceExtractorTest {

    private static MovementTable simpleMovementTable;

    @BeforeClass
    public static void setup() {
        simpleMovementTable = MovementTableFactory.simpleMovementTable();
    }

    @Test
    @FileParameters("classpath:simple_keypad_extractor_test_cases.csv")
    public void shouldReturnCorrectNumberOfSequences(char origin, int length, int vowels, long expected) {
        SequenceExtractor extractor = SequenceExtractor.forMovementTable(simpleMovementTable).from(origin).length(length).vowels(vowels).create();
        long sequences = extractor.getNumberOfSequences();
        assertThat(sequences, is(equalTo(expected)));
    }
}