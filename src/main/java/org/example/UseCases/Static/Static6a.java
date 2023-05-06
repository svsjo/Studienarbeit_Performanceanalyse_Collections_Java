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
import java.util.HashSet;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Static6a implements UseCase
{
    @Benchmark
    public void BenchmarkTreeSet(final TreeSetState state)
    {
        var value = state.someRandomNumbers.get(0);
        var lesser = state.collection.headSet(value);
        var higher = state.collection.tailSet(value);
    }

    @Benchmark
    public void BenchmarkHashSet(final HashSetState state)
    {
        var value = state.someRandomNumbers.get(0);
        var lesser = new HashSet<Integer>();
        var higher = new HashSet<Integer>();

        for(var el : state.collection)
        {
            if(el < value)
            {
                lesser.add(el);
            }
            else
            {
                higher.add(el);
            }
        }
    }

    @Benchmark
    public void BenchmarkHashSetStream(final HashSetState state)
    {
        var value = state.someRandomNumbers.get(0);
        var lesser = state.collection.stream().filter(x -> x < value).collect(Collectors.toCollection(HashSet<Integer>::new));
        var higher = state.collection.stream().filter(x -> x > value).collect(Collectors.toCollection(HashSet<Integer>::new));
    }

    @Benchmark
    public void BenchmarkHashSetStreamParallel(final HashSetState state)
    {
        var value = state.someRandomNumbers.get(0);
        var lesser = state.collection.parallelStream().filter(x -> x < value).collect(Collectors.toCollection(HashSet<Integer>::new));
        var higher = state.collection.parallelStream().filter(x -> x > value).collect(Collectors.toCollection(HashSet<Integer>::new));
    }


    @Benchmark
    public void BenchmarkLinkedHashSet(final LinkedHashSetState state)
    {
        var value = state.someRandomNumbers.get(0);
        var lesser = new HashSet<Integer>();
        var higher = new HashSet<Integer>();

        for(var el : state.collection)
        {
            if(el < value)
            {
                lesser.add(el);
            }
            else
            {
                higher.add(el);
            }
        }
    }

    @Benchmark
    public void BenchmarkConcurrentSkipListSet(final ConcurrentSkipListSetState state)
    {
        var value = state.someRandomNumbers.get(0);
        var lesser = state.collection.headSet(value);
        var higher = state.collection.tailSet(value);
    }

    @Override
    public Collection<RunResult> RunBenchmarks() throws InterruptedException, RunnerException, IOException
    {
        Options opt = new OptionsBuilder()
                .include(Static6a.class.getSimpleName())
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
