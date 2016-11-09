package tb;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.ExecutionException;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(JUnitParamsRunner.class)
public class AcceptanceTest {

    private static MovementTable simpleMovementTable;
    private static MovementTable movementTable;

    @BeforeClass
    public static void setup() {
        simpleMovementTable = MovementTableFactory.simpleMovementTable();
        movementTable = MovementTableFactory.movementTable();
    }

    @Test
    public void endToEndTestForMainKeypad() throws ExecutionException, InterruptedException {
        SequenceAggregator aggregator = new SequenceAggregator();
        movementTable.getKeys().forEach(k -> aggregator.add(extractorForKey(k, 10, 2, false)));
        long total = aggregator.getTotal();
        verify(total);
    }

    private void verify(long total) {
        String text = String.valueOf(total);
        if (!text.contains("1")) {
            Assert.fail("Expected at least one 1");
        }
        if (!text.contains("3")) {
            Assert.fail("Expected at least one 3");
        }
        if (!text.contains("8")) {
            Assert.fail("Expected at least one 8");
        }
        if (!text.contains("9")) {
            Assert.fail("Expected at least one 9");
        }

    }

    @Test
    @FileParameters("classpath:simple_keypad_end_to_end_test_cases.csv")
    public void endToEndForSimpleKeypad(int length, int vowels, long expected) throws ExecutionException, InterruptedException {
        SequenceAggregator aggregator = new SequenceAggregator();
        simpleMovementTable.getKeys().forEach(k -> aggregator.add(extractorForKey(k, length, vowels, true)));
        long total = aggregator.getTotal();
        assertThat(total, is(equalTo(expected)));
    }

    private SequenceExtractor extractorForKey(Character code, int length, int vowels, boolean simple) {
        return SequenceExtractor.forMovementTable(simple ? simpleMovementTable : movementTable).from(code).length(length).vowels(vowels).create();
    }
}
