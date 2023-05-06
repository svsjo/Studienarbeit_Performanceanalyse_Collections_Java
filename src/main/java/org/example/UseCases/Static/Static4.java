package org.example.UseCases.Static;

import org.example.CollectionsInit.ConcurrentSkipListSetState;
import org.example.CollectionsInit.LinkedHashSetState;
import org.example.CollectionsInit.TreeSetState;
import org.example.UseCases.UseCase;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.results.RunResult;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import javax.swing.text.StyleContext;
import java.io.IOException;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

public class Static4 implements UseCase
{
    @Benchmark
    public void BenchmarkTreeSet(final TreeSetState state)
    {
        var element = state.someRandomNumbers.get(0);
        var replacedElement = state.collection.higher(element);
        var index = state.collection.headSet(element).size();
        var relativeIndex = index / state.collection.size() * 100;
    }

    @Benchmark
    public void BenchmarkConcurrentSkipListSet(final ConcurrentSkipListSetState state)
    {
        var element = state.someRandomNumbers.get(0);
        var replacedElement = state.collection.higher(element);
        var index = state.collection.headSet(element).size();
        var relativeIndex = index / state.collection.size() * 100;
    }

    @Benchmark
    public void BenchmarkLinkedHashSet(final LinkedHashSetState state)
    {
        var value = state.someRandomNumbers.get(0);
        int smallerElements = 0;
        for(var el : state.collection)
        {
            smallerElements++;
        }

        var index = smallerElements + 1;
        var relativeIndex = index / state.collection.size() * 100;
    }

    @Benchmark
    public void BenchmarkLinkedHashSetStream(final LinkedHashSetState state)
    {
        var value = state.someRandomNumbers.get(0);
        var index = state.collection.stream().filter(x -> x < value).count() + 1;
        var relativeIndex = index / state.collection.size() * 100;
    }

    @Benchmark
    public void BenchmarkLinkedHashSetStreamParallel(final LinkedHashSetState state)
    {
        var value = state.someRandomNumbers.get(0);
        var index = state.collection.parallelStream().filter(x -> x < value).count() + 1;
        var relativeIndex = index / state.collection.size() * 100;
    }

    @Benchmark
    public void BenchmarkHashSetStream(final LinkedHashSetState state)
    {
        var value = state.someRandomNumbers.get(0);
        var index = state.collection.stream().filter(x -> x < value).count() + 1;
        var relativeIndex = index / state.collection.size() * 100;
    }

    @Override
    public Collection<RunResult> RunBenchmarks() throws InterruptedException, RunnerException, IOException
    {
        Options opt = new OptionsBuilder()
                .include(Static4.class.getSimpleName())
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
