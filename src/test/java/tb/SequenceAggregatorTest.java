package tb;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import tb.knight.model.MovementTable;
import tb.knight.model.MovementTableFactory;
import tb.knight.sequence.SequenceAggregator;
import tb.knight.sequence.SequenceExtractor;

import java.util.concurrent.ExecutionException;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(JUnitParamsRunner.class)
public class SequenceAggregatorTest {

    private static MovementTable simpleMovementTable;

    @BeforeClass
    public static void setup() {
        simpleMovementTable = MovementTableFactory.simpleMovementTable();
    }

    @Test
    @FileParameters("classpath:simple_keypad_end_to_end_test_cases.csv")
    public void shouldAggregateNumberOfSequences(int length, int vowels, long expected) throws ExecutionException, InterruptedException {
        SequenceAggregator aggregator = new SequenceAggregator();
        simpleMovementTable.getKeys().forEach(k -> aggregator.add(extractorForKey(k, length, vowels)));
        long total = aggregator.getTotal();
        assertThat(total, is(equalTo(expected)));
    }

    private SequenceExtractor extractorForKey(Character code, int length, int vowels) {
        return SequenceExtractor.forMovementTable(simpleMovementTable).from(code).length(length).vowels(vowels).create();
    }
}
