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
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

public class Static5b implements UseCase
{
    @Benchmark
    public void BenchmarkLinkedList(final LinkedListState state)
    {
        var borderTenth = state.collection.stream().mapToInt(x -> x).sum() / state.collection.size() / 5;
        var borders = new ArrayList<Integer>();
        for(int i = 1; i < 10; i++)
        {
            borders.add(borderTenth * i);
        }
        borders.add(100000000 * 2);

        Collections.sort(state.collection);

        var results = new ArrayList<ArrayList<Integer>>(10);
        for(int i = 0; i < 10; i++)
        {
            results.add(new ArrayList<>());
        }

        var border = borders.get(0);
        var index = 0;

        for(var el : state.collection)
        {
            if(el <= border)
            {
                results.get(index).add(el);
            }
            else
            {
                index++;
                border = borders.get(index);
            }
        }

        var sums = results.stream().mapToInt(x -> x.size());
    }

    @Benchmark
    public void BenchmarkArrayList(final ArrayListState state)
    {
        var borderTenth = state.collection.stream().mapToInt(x -> x).sum() / state.collection.size() / 5;
        var borders = new ArrayList<Integer>();
        for(int i = 1; i < 10; i++)
        {
            borders.add(borderTenth * i);
        }
        borders.add(100000000 * 2);

        Collections.sort(state.collection);

        var results = new ArrayList<ArrayList<Integer>>(10);
        for(int i = 0; i < 10; i++)
        {
            results.add(new ArrayList<>());
        }

        var border = borders.get(0);
        var index = 0;

        for(var el : state.collection)
        {
            if(el <= border)
            {
                results.get(index).add(el);
            }
            else
            {
                index++;
                border = borders.get(index);
            }
        }

        var sums = results.stream().mapToInt(x -> x.size());
    }

    @Benchmark
    public void BenchmarkArrayDeque(final ArrayDequeState state)
    {
        var borderTenth = state.collection.stream().mapToInt(x -> x).sum() / state.collection.size() / 5;
        var borders = new ArrayList<Integer>();
        for(int i = 1; i < 10; i++)
        {
            borders.add(borderTenth * i);
        }
        borders.add(100000000 * 2);

        state.collection = state.collection.stream().sorted().collect(Collectors.toCollection(ArrayDeque<Integer>::new));

        var results = new ArrayList<ArrayList<Integer>>(10);
        for(int i = 0; i < 10; i++)
        {
            results.add(new ArrayList<>());
        }

        var border = borders.get(0);
        var index = 0;

        for(var el : state.collection)
        {
            if(el <= border)
            {
                results.get(index).add(el);
            }
            else
            {
                index++;
                border = borders.get(index);
            }
        }

        var sums = results.stream().mapToInt(x -> x.size());
    }

    @Benchmark
    public void BenchmarkVector(final VectorState state)
    {
        var borderTenth = state.collection.stream().mapToInt(x -> x).sum() / state.collection.size() / 5;
        var borders = new ArrayList<Integer>();
        for(int i = 1; i < 10; i++)
        {
            borders.add(borderTenth * i);
        }
        borders.add(100000000 * 2);

        Collections.sort(state.collection);

        var results = new ArrayList<ArrayList<Integer>>(10);
        for(int i = 0; i < 10; i++)
        {
            results.add(new ArrayList<>());
        }

        var border = borders.get(0);
        var index = 0;

        for(var el : state.collection)
        {
            if(el <= border)
            {
                results.get(index).add(el);
            }
            else
            {
                index++;
                border = borders.get(index);
            }
        }

        var sums = results.stream().mapToInt(x -> x.size());
    }

    @Override
    public Collection<RunResult> RunBenchmarks() throws InterruptedException, RunnerException, IOException
    {
        Options opt = new OptionsBuilder()
                .include(Static5b.class.getSimpleName())
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
