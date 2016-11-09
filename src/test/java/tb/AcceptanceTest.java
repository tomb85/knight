package tb;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.ExecutionException;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(JUnitParamsRunner.class)
public class AcceptanceTest {

    private static MovementTable movementTable;

    @BeforeClass
    public static void setup() {
        movementTable = TestUtils.simpleMovementTable();
    }

    @Test
    @FileParameters("classpath:simple_keypad_end_to_end_test_cases.csv")
    public void endToEndForSimpleKeypad(int length, int vowels, long expected) throws ExecutionException, InterruptedException {
        SequenceAggregator aggregator = new SequenceAggregator();
        movementTable.getKeys().forEach(k -> aggregator.add(extractorForKey(k, length, vowels)));
        long total = aggregator.getTotal();
        assertThat(total, is(equalTo(expected)));
    }

    private SequenceExtractor extractorForKey(Character code, int length, int vowels) {
        return SequenceExtractor.forMovementTable(movementTable).from(code).length(length).vowels(vowels).create();
    }
}
