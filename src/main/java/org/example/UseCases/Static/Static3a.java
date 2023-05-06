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
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class Static3a implements UseCase
{
    @Benchmark
    public void BenchmarkArray(final ArrayState state)
    {
        for(int i = 0; i < 10; i++)
        {
            var result = state.collection[state.someRandomNumbers.get(i)];
        }
    }

    @Benchmark
    public void BenchmarkLinkedList(final LinkedListState state)
    {
        for(int i = 0; i < 10; i++)
        {
            var result = state.collection.get(state.someRandomNumbers.get(0));
        }
    }

    @Benchmark
    public void BenchmarkArrayList(final ArrayListState state)
    {
        for(int i = 0; i < 10; i++)
        {
            var result = state.collection.get(state.someRandomNumbers.get(0));
        }
    }

    @Benchmark
    public void BenchmarkVector(final VectorState state)
    {
        for(int i = 0; i < 10; i++)
        {
            var result = state.collection.get(state.someRandomNumbers.get(0));
        }
    }

    @Benchmark
    public void BenchmarkConcurrentSkipListMap(final ConcurrentSkipListMapState state)
    {
        for(int i = 0; i < 10; i++)
        {
            var result = state.collection.get(state.someRandomNumbers.get(0));
        }
    }

    @Benchmark
    public void BenchmarkHashMap(final HashMapState state)
    {
        for(int i = 0; i < 10; i++)
        {
            var result = state.collection.get(state.someRandomNumbers.get(0));
        }
    }

    @Benchmark
    public void BenchmarkTreeMap(final TreeMapState state)
    {
        for(int i = 0; i < 10; i++)
        {
            var result = state.collection.get(state.someRandomNumbers.get(0));
        }
    }

    @Benchmark
    public void BenchmarkHashTable(final HashTableState state)
    {
        for(int i = 0; i < 10; i++)
        {
            var result = state.collection.get(state.someRandomNumbers.get(0));
        }
    }

    @Benchmark
    public void BenchmarkConcurrentHashMap(final ConcurrentHashMapState state)
    {
        for(int i = 0; i < 10; i++)
        {
            var result = state.collection.get(state.someRandomNumbers.get(0));
        }
    }

    @Override
    public Collection<RunResult> RunBenchmarks() throws InterruptedException, RunnerException, IOException
    {
        Options opt = new OptionsBuilder()
                .include(Static3a.class.getSimpleName())
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

