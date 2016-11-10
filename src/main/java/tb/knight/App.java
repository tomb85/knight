package tb.knight;

import tb.knight.model.MovementTable;
import tb.knight.model.MovementTableFactory;
import tb.knight.sequence.SequenceAggregator;
import tb.knight.sequence.SequenceExtractor;

import java.util.concurrent.ExecutionException;

public class App {

    private static MovementTable movementTable = MovementTableFactory.movementTable();

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        if (args.length != 1) {
            System.err.println("Expecting exactly one argument!");
            System.exit(-1);
        }
        int length = Integer.parseInt(args[0]);
        SequenceAggregator aggregator = new SequenceAggregator();
        movementTable.getKeys().forEach(k -> aggregator.add(extractorForKey(k, length, 2)));
        long total = aggregator.getTotal();
        System.out.println(total);
    }

    private static SequenceExtractor extractorForKey(Character code, int length, int vowels) {
        return SequenceExtractor.forMovementTable(movementTable).from(code).length(length).vowels(vowels).create();
    }
}
