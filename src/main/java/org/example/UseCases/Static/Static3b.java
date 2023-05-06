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
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Static3b implements UseCase
{
    @Benchmark
    public void BenchmarkArrayList(final ArrayListState state)
    {
        var result = new ArrayList<Integer>();
        for(var el : state.someRandomNumbers)
        {
            result.add(state.collection.get(el));
        }
    }

    @Benchmark
    public void BenchmarkArrayListStream(final ArrayListState state)
    {
        var result = state.someRandomNumbers.stream().map(x -> state.collection.get(x)).collect(Collectors.toCollection(ArrayList<Integer>::new));
    }

    @Benchmark
    public void BenchmarkArrayListStreamParallel(final ArrayListState state)
    {
        var result = state.someRandomNumbers.parallelStream().map(x -> state.collection.get(x)).collect(Collectors.toCollection(ArrayList<Integer>::new));
    }

    @Override
    public Collection<RunResult> RunBenchmarks() throws InterruptedException, RunnerException, IOException
    {
        Options opt = new OptionsBuilder()
                .include(Static3b.class.getSimpleName())
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
