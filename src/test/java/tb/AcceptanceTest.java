package tb;


import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import tb.knight.model.MovementTable;
import tb.knight.model.MovementTableFactory;
import tb.knight.sequence.SequenceAggregator;
import tb.knight.sequence.SequenceExtractor;

import java.util.concurrent.ExecutionException;

public class AcceptanceTest {

    private static MovementTable movementTable;

    @BeforeClass
    public static void setup() {
        movementTable = MovementTableFactory.movementTable();
    }

    @Test
    public void endToEndTestForMainKeypad() throws ExecutionException, InterruptedException {
        SequenceAggregator aggregator = new SequenceAggregator();
        movementTable.getKeys().forEach(k -> aggregator.add(extractorForKey(k, 10, 2)));
        long total = aggregator.getTotal();
        verify(total);
    }

    private void verify(long total) {
        String text = String.valueOf(total);
        if (!text.contains("1")) {
            Assert.fail(total + ": Expected at least one 1");
        }
        if (!text.contains("3")) {
            Assert.fail(total + ": Expected at least one 3");
        }
        if (!text.contains("8")) {
            Assert.fail(total + ": Expected at least one 8");
        }
        if (!text.contains("9")) {
            Assert.fail(total + ": Expected at least one 9");
        }
    }

    private SequenceExtractor extractorForKey(Character code, int length, int vowels) {
        return SequenceExtractor.forMovementTable(movementTable).from(code).length(length).vowels(vowels).create();
    }
}