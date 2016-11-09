package tb;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class AcceptanceTest {

    private static MovementTable movementTable;

    @BeforeClass
    public static void setup() {
        movementTable = TestUtils.simpleMovementTable();
    }

    @Test
    public void endToEndWithSimpleKeypad() {
        SequenceAggregator aggregator = new SequenceAggregator();
        movementTable.getKeys().forEach(k -> aggregator.add(extractorForKey(k)));
        long total = aggregator.getTotal();
        assertThat(total, is(equalTo(100l)));
    }

    private SequenceExtractor extractorForKey(Character code) {
        return SequenceExtractor.forMovementTable(movementTable).from(code).length(4).vowels(2).create();
    }

}
