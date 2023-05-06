package org.example.UseCases.Static;

import com.sun.source.tree.Tree;
import org.example.CollectionsInit.ConcurrentSkipListSetState;
import org.example.CollectionsInit.HashSetState;
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

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Static6b implements UseCase
{
    @Benchmark
    public void BenchmarkTreeSet(final TreeSetState state)
    {
        TreeSet<Integer> same = new TreeSet<>();
        TreeSet<Integer> different = new TreeSet<>();

        same.addAll(state.collection);
        different.addAll(state.collection);

        different.removeAll(state.someRandomNumbers);
        same.retainAll(state.someRandomNumbers);
    }

    @Benchmark
    public void BenchmarkHashSet(final HashSetState state)
    {
        HashSet<Integer> same = new HashSet<>(state.collection);
        HashSet<Integer> different = new HashSet<>(state.collection);

        different.removeAll(state.someRandomNumbers);
        same.retainAll(state.someRandomNumbers);
    }

    @Benchmark
    public void BenchmarkLinkedHashSet(final LinkedHashSetState state)
    {
        LinkedHashSet<Integer> same = new LinkedHashSet<>(state.collection);
        LinkedHashSet<Integer> different = new LinkedHashSet<>(state.collection);

        different.removeAll(state.someRandomNumbers);
        same.retainAll(state.someRandomNumbers);
    }

    @Benchmark
    public void BenchmarkLinkedHashSetStream(final LinkedHashSetState state)
    {
        var difference = state.collection.stream().filter(x -> !state.someRandomNumbers.contains(x)).collect(Collectors.toCollection(LinkedHashSet::new));
        var same = state.collection.stream().filter(x -> state.someRandomNumbers.contains(x)).collect(Collectors.toCollection(LinkedHashSet::new));
    }

    @Benchmark
    public void BenchmarkLinkedHashSetStreamParallel(final LinkedHashSetState state)
    {
        var difference = state.collection.parallelStream().filter(x -> !state.someRandomNumbers.contains(x)).collect(Collectors.toCollection(LinkedHashSet::new));
        var same = state.collection.parallelStream().filter(x -> state.someRandomNumbers.contains(x)).collect(Collectors.toCollection(LinkedHashSet::new));
    }

    @Benchmark
    public void BenchmarkConcurrentSkipListSet(final ConcurrentSkipListSetState state)
    {
        ConcurrentSkipListSet<Integer> same = new ConcurrentSkipListSet<>(state.collection);
        ConcurrentSkipListSet<Integer> different = new ConcurrentSkipListSet<>(state.collection);

        different.removeAll(state.someRandomNumbers);
        same.retainAll(state.someRandomNumbers);
    }

    @Override
    public Collection<RunResult> RunBenchmarks() throws InterruptedException, RunnerException, IOException
    {
        Options opt = new OptionsBuilder()
                .include(Static6b.class.getSimpleName())
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

