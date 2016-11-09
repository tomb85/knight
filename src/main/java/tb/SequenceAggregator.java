package tb;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.*;

public class SequenceAggregator {

    private List<SequenceExtractor> extractors = Lists.newArrayList();

    public void add(SequenceExtractor extractor) {
        extractors.add(extractor);
    }

    public long getTotal() throws InterruptedException, ExecutionException {
        long total = 0;
        ExecutorService service = Executors.newFixedThreadPool(extractors.size());
        List<Callable<Long>> tasks = Lists.newArrayList();
        extractors.forEach(e -> tasks.add(new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                return e.getNumberOfSequences();
            }
        }));
        List<Future<Long>> futures = service.invokeAll(tasks);
        for (Future<Long> future : futures) {
            total += future.get();
        }
        service.shutdown();
        return total;
    }
}
