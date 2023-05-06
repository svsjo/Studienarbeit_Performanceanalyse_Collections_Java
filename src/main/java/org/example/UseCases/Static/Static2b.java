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

public class Static2b implements UseCase
{
    @Benchmark
    public void BenchmarkLinkedList(final LinkedListState state)
    {
        double sum = 0;
        for(var el : state.collection)
            sum += el;

        var avg = sum/ state.collection.size();
    }

    @Benchmark
    public void BenchmarkArrayList(final ArrayListState state)
    {
        double sum = 0;
        for(var el : state.collection)
            sum += el;

        var avg = sum/ state.collection.size();
    }

    @Benchmark
    public void BenchmarkVector(final VectorState state)
    {
        double sum = 0;
        for(var el : state.collection)
            sum += el;

        var avg = sum/ state.collection.size();
    }

    @Benchmark
    public void BenchmarkArrayDeque(final ArrayDequeState state)
    {
        double sum = 0;
        for(var el : state.collection)
            sum += el;

        var avg = sum/ state.collection.size();
    }

    @Benchmark
    public void BenchmarkVectorStream(final VectorState state)
    {
        var sum = state.collection.stream().mapToInt(x -> x).sum();
        var average = state.collection.stream().mapToInt(x -> x).average();
    }

    @Benchmark
    public void BenchmarkVectorStreamParallel(final VectorState state)
    {
        var sum = state.collection.parallelStream().mapToInt(x -> x).sum();
        var average = state.collection.parallelStream().mapToInt(x -> x).average();
    }

    @Benchmark
    public void BenchmarkTreeSet(final TreeSetState state)
    {
        double sum = 0;
        for(var el : state.collection)
            sum += el;

        var avg = sum/ state.collection.size();
    }

    @Benchmark
    public void BenchmarkHashSet(final HashSetState state)
    {
        double sum = 0;
        for(var el : state.collection)
            sum += el;

        var avg = sum/ state.collection.size();
    }

    @Benchmark
    public void BenchmarkConcurrentSkipListSet(final ConcurrentSkipListSetState state)
    {
        double sum = 0;
        for(var el : state.collection)
            sum += el;

        var avg = sum/ state.collection.size();
    }

    @Override
    public Collection<RunResult> RunBenchmarks() throws InterruptedException, RunnerException, IOException
    {
        Options opt = new OptionsBuilder()
                .include(Static2b.class.getSimpleName())
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

