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

public class Static1a implements UseCase
{
    @Benchmark
    public void BenchmarkLinkedList(final LinkedListState state)
    {
        for(int i = 0; i < 10; i++)
        {
            var isContaining = state.collection.contains(state.someRandomNumbers.get(i));
        }
    }

    @Benchmark
    public void BenchmarkArrayList(final ArrayListState state)
    {
        for(int i = 0; i < 10; i++)
        {
            var isContaining = state.collection.contains(state.someRandomNumbers.get(i));
        }
    }

    @Benchmark
    public void BenchmarkVector(final VectorState state)
    {
        for(int i = 0; i < 10; i++)
        {
            var isContaining = state.collection.contains(state.someRandomNumbers.get(i));
        }
    }

    @Benchmark
    public void BenchmarkTreeSet(final TreeSetState state)
    {
        for(int i = 0; i < 10; i++)
        {
            var isContaining = state.collection.contains(state.someRandomNumbers.get(i));
        }
    }

    @Benchmark
    public void BenchmarkHashSet(final HashSetState state)
    {
        for(int i = 0; i < 10; i++)
        {
            var isContaining = state.collection.contains(state.someRandomNumbers.get(i));
        }
    }

    @Benchmark
    public void BenchmarkLinkedHashSet(final LinkedHashSetState state)
    {
        for(int i = 0; i < 10; i++)
        {
            var isContaining = state.collection.contains(state.someRandomNumbers.get(i));
        }
    }

    @Benchmark
    public void BenchmarkArrayDeque(final ArrayDequeState state)
    {
        for(int i = 0; i < 10; i++)
        {
            var isContaining = state.collection.contains(state.someRandomNumbers.get(i));
        }
    }

    @Benchmark
    public void BenchmarkHashMap(final HashMapState state)
    {
        for(int i = 0; i < 10; i++)
        {
            var isContaining = state.collection.containsValue(state.someRandomNumbers.get(i));
        }
    }

    @Override
    public Collection<RunResult> RunBenchmarks() throws InterruptedException, RunnerException, IOException
    {
        Options opt = new OptionsBuilder()
                .include(Static1a.class.getSimpleName())
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

