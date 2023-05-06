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
import java.util.concurrent.TimeUnit;

public class Static1c implements UseCase
{
    @Benchmark
    public void BenchmarkLinkedList(final LinkedListState state)
    {
        var isContaining = state.collection.containsAll(state.someRandomNumbers);
    }

    @Benchmark
    public void BenchmarkLinkedListStream(final LinkedListState state)
    {
        var isContaining = state.collection.stream().allMatch(state.someRandomNumbers::contains);
    }

    @Benchmark
    public void BenchmarkLinkedListStreamParallel(final LinkedListState state)
    {
        var isContaining = state.collection.parallelStream().allMatch(state.someRandomNumbers::contains);
    }

    @Benchmark
    public void BenchmarkArrayList(final ArrayListState state)
    {
        var isContaining = state.collection.containsAll(state.someRandomNumbers);
    }

    @Benchmark
    public void BenchmarkVector(final VectorState state)
    {
        var isContaining = state.collection.containsAll(state.someRandomNumbers);
    }
    @Benchmark
    public void BenchmarkArrayDeque(final ArrayDequeState state)
    {
        var isContaining = state.collection.containsAll(state.someRandomNumbers);
    }

    @Benchmark
    public void BenchmarkTreeSet(final TreeSetState state)
    {
        var isContaining = state.collection.containsAll(state.someRandomNumbers);
    }

    @Benchmark
    public void BenchmarkHashSet(final HashSetState state)
    {
        var isContaining = state.collection.containsAll(state.someRandomNumbers);
    }

    @Benchmark
    public void BenchmarkLinkedHashSet(final LinkedHashSetState state)
    {
        var isContaining = state.collection.containsAll(state.someRandomNumbers);
    }

    @Override
    public Collection<RunResult> RunBenchmarks() throws InterruptedException, RunnerException, IOException
    {
        Options opt = new OptionsBuilder()
                .include(Static1c.class.getSimpleName())
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

