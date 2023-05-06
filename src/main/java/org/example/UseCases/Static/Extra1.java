package org.example.UseCases.Static;

import org.example.CollectionsInit.*;
import org.example.UseCases.UseCase;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.results.RunResult;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

public class Extra1 implements UseCase
{
    @Benchmark
    public void BenchmarkTreeSet(final TreeSetState state)
    {
        var list = new ArrayList<>(state.collection);
    }

    @Benchmark
    public void BenchmarkHashSet(final HashSetState state)
    {
        var list = new ArrayList<>(state.collection);
    }

    @Benchmark
    public void BenchmarkConcurrentSkipListSet(final ConcurrentSkipListSetState state)
    {
        var list = new ArrayList<>(state.collection);
    }

    @Benchmark
    public void BenchmarkLinkedHashSet(final LinkedHashSetState state)
    {
        var list = new ArrayList<>(state.collection);
    }

    @Override
    public Collection<RunResult> RunBenchmarks() throws InterruptedException, RunnerException, IOException
    {
        Options opt = new OptionsBuilder()
                .include(Extra1.class.getSimpleName())
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
