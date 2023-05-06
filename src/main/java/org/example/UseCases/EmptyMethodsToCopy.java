package org.example.UseCases;

import org.example.CollectionsInit.*;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.results.RunResult;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class EmptyMethodsToCopy implements UseCase
{
    /******************************************************************************/
    /** BEGINN: Array und Listen **/

    @Benchmark
    public void BenchmarkArray(final ArrayState state)
    {

    }

    @Benchmark
    public void BenchmarkLinkedList(final LinkedListState state)
    {

    }

    @Benchmark
    public void BenchmarkArrayList(final ArrayListState state)
    {

    }

    @Benchmark
    public void BenchmarkVector(final VectorState state)
    {

    }

    @Benchmark
    public void BenchmarkCopyOnWriteArrayList(final CopyOnWriteArrayListState state)
    {

    }

    /** ENDE: Array und Listen **/
    /******************************************************************************/





    /******************************************************************************/
    /** BEGINN: Stack/Queue **/

    @Benchmark
    public void ArrayBlockingQueue(final ArrayBlockingQueueState state)
    {

    }

    @Benchmark
    public void BenchmarkArrayDeque(final ArrayDequeState state)
    {

    }

    @Benchmark
    public void BenchmarkConcurrentLinkedDeque(final ConcurrentLinkedDequeState state)
    {

    }

    @Benchmark
    public void BenchmarkConcurrentLinkedQueue(final ConcurrentLinkedQueueState state)
    {

    }

    @Benchmark
    public void BenchmarkLinkedBlockingDeque(final LinkedBlockingDequeState state)
    {

    }

    @Benchmark
    public void BenchmarkLinkedBlockingQueue(final LinkedBlockingQueueState state)
    {

    }

    @Benchmark
    public void BenchmarkLinkedTransferQueue(final LinkedTransferQueueState state)
    {

    }

    @Benchmark
    public void BenchmarkPriorityBlockingQueue(final PriorityBlockingQueueState state)
    {

    }

    @Benchmark
    public void BenchmarkPriorityQueue(final PriorityQueueState state)
    {

    }

    @Benchmark
    public void BenchmarkStack(final StackState state)
    {

    }

    /** ENDE: Stack/Queue **/
    /******************************************************************************/





    /******************************************************************************/
    /** BEGINN: Map **/

    @Benchmark
    public void BenchmarkConcurrentSkipListMap(final ConcurrentSkipListMapState state)
    {

    }

    @Benchmark
    public void BenchmarkHashMap(final HashMapState state)
    {

    }

    @Benchmark
    public void BenchmarkTreeMap(final TreeMapState state)
    {

    }

    @Benchmark
    public void BenchmarkLinkedHashMap(final LinkedHashMapState state)
    {

    }

    /** ENDE: Map **/
    /******************************************************************************/





    /******************************************************************************/
    /** BEGINN: Set **/

    @Benchmark
    public void BenchmarkTreeSet(final TreeSetState state)
    {

    }

    @Benchmark
    public void BenchmarkHashSet(final HashSetState state)
    {

    }

    @Benchmark
    public void BenchmarkLinkedHashSet(final LinkedHashSetState state)
    {

    }

    @Benchmark
    public void BenchmarkConcurrentSkipListSet(final ConcurrentSkipListSetState state)
    {

    }

    @Benchmark
    public void BenchmarkCopyOnWriteArraySet(final CopyOnWriteArraySetState state)
    {

    }

    /** ENDE: Set **/
    /******************************************************************************/





    @Override
    public Collection<RunResult> RunBenchmarks() throws InterruptedException, RunnerException, IOException
    {
        Options opt = new OptionsBuilder()
                .include(EmptyMethodsToCopy.class.getSimpleName())
                .forks(1)
                .warmupIterations(3)
                .measurementIterations(5)
                .mode(Mode.AverageTime)
                .timeUnit(TimeUnit.MILLISECONDS)
                .build();

        Collection<RunResult> runResults = new Runner(opt).run();

        return runResults;
    }
}

