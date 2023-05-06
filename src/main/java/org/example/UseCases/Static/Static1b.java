package org.example.UseCases.Static;

import org.example.CollectionsInit.*;
import org.example.UseCases.UseCase;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.results.RunResult;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class Static1b implements UseCase
{
    @Benchmark
    public void BenchmarkLinkedList(final LinkedListState state)
    {
        var freq = Collections.frequency(state.collection, state.someRandomNumbers.get(0));
    }

    @Benchmark
    public void BenchmarkArrayList(final ArrayListState state)
    {
        var freq = Collections.frequency(state.collection, state.someRandomNumbers.get(0));
    }

    @Benchmark
    public void BenchmarkVector(final VectorState state)
    {
        var freq = Collections.frequency(state.collection, state.someRandomNumbers.get(0));
    }

    @Benchmark
    public void BenchmarkArrayDeque(final ArrayDequeState state)
    {
        var freq = Collections.frequency(state.collection, state.someRandomNumbers.get(0));
    }

    @Benchmark
    public void BenchmarkLinkedListStreamSingle(final LinkedListState state)
    {
        var searched = state.collection.get(0);
        var freq = state.collection.stream().filter(e -> e.equals(searched)).count();
    }

    @Benchmark
    public void BenchmarkLinkedListStreamParallel(final LinkedListState state)
    {
        var searched = state.collection.get(0);
        var freq = state.collection.parallelStream().filter(e -> e.equals(searched)).count();
    }

    @Override
    public Collection<RunResult> RunBenchmarks() throws InterruptedException, RunnerException, IOException
    {
        Options opt = new OptionsBuilder()
                .include(Static1b.class.getSimpleName())
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
