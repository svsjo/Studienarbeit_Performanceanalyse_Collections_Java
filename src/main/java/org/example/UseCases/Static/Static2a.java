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

public class Static2a implements UseCase
{
    @Benchmark
    public void BenchmarkArrayListStream(final ArrayListState state)
    {
        var min = state.collection.stream().min(Integer::compare).get();
        var max = state.collection.stream().max(Integer::compare).get();
    }

    @Benchmark
    public void BenchmarkArrayListStreamParallel(final ArrayListState state)
    {
        var min = state.collection.parallelStream().min(Integer::compare).get();
        var max = state.collection.parallelStream().max(Integer::compare).get();
    }

    @Benchmark
    public void BenchmarkVectorStream(final VectorState state)
    {
        var min = state.collection.stream().min(Integer::compare).get();
        var max = state.collection.stream().max(Integer::compare).get();
    }

    @Benchmark
    public void BenchmarkLinkedListStream(final LinkedListState state)
    {
        var min = state.collection.stream().min(Integer::compare).get();
        var max = state.collection.stream().max(Integer::compare).get();
    }

    @Benchmark
    public void BenchmarkArrayDeque(final ArrayDequeState state)
    {
        var min = state.collection.stream().min(Integer::compare).get();
        var max = state.collection.stream().max(Integer::compare).get();
    }

    @Benchmark
    public void BenchmarkArrayListIteration(final ArrayListState state)
    {
        int min = state.collection.get(0);
        int max = min;

        for(var el : state.collection)
        {
            if(el > max) max = el;
            if(el < min) min = el;
        }
    }

    @Benchmark
    public void BenchmarkTreeSet(final TreeSetState state)
    {
        var min = state.collection.first();
        var max = state.collection.last();
    }

    @Benchmark
    public void BenchmarkHashSetStream(final HashSetState state)
    {
        var min = state.collection.stream().min(Integer::compare).get();
        var max = state.collection.stream().max(Integer::compare).get();
    }

    @Benchmark
    public void BenchmarkConcurrentSkipListSet(final ConcurrentSkipListSetState state)
    {
        var min = state.collection.first();
        var max = state.collection.last();
    }

    @Override
    public Collection<RunResult> RunBenchmarks() throws InterruptedException, RunnerException, IOException
    {
        Options opt = new OptionsBuilder()
                .include(Static2a.class.getSimpleName())
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

