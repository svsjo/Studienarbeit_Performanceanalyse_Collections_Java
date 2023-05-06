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

public class Static5a implements UseCase
{
    @Benchmark
    public void BenchmarkArray(final ArrayState state)
    {
        var errorElements = new ArrayList<Integer>();
        var noErrors = true;
        var toCheck = state.someRandomNumbers.get(0); // > element ?

        for(int i = 0; i < state.collection.length; ++i)
        {
            if(state.collection[i] > toCheck)
            {
                errorElements.add(state.collection[i]);
                noErrors = false;
            }
        }
    }

    @Benchmark
    public void BenchmarkLinkedList(final LinkedListState state)
    {
        var errorElements = new ArrayList<Integer>();
        var noErrors = true;
        var toCheck = state.someRandomNumbers.get(0); // > element ?

        for(var el : state.collection)
        {
            if(el > toCheck)
            {
                errorElements.add(el);
                noErrors = false;
            }
        }
    }

    @Benchmark
    public void BenchmarkArrayList(final ArrayListState state)
    {
        var errorElements = new ArrayList<Integer>();
        var noErrors = true;
        var toCheck = state.someRandomNumbers.get(0); // > element ?

        for(var el : state.collection)
        {
            if(el > toCheck)
            {
                errorElements.add(el);
                noErrors = false;
            }
        }
    }

    @Benchmark
    public void BenchmarkArrayDeque(final ArrayDequeState state)
    {
        var errorElements = new ArrayList<Integer>();
        var noErrors = true;
        var toCheck = state.someRandomNumbers.get(0); // > element ?

        for(var el : state.collection)
        {
            if(el > toCheck)
            {
                errorElements.add(el);
                noErrors = false;
            }
        }
    }

    @Benchmark
    public void BenchmarkVector(final VectorState state)
    {
        var errorElements = new ArrayList<Integer>();
        var noErrors = true;
        var toCheck = state.someRandomNumbers.get(0); // > element ?

        for(var el : state.collection)
        {
            if(el > toCheck)
            {
                errorElements.add(el);
                noErrors = false;
            }
        }
    }

    @Benchmark
    public void BenchmarkArrayListStream(final ArrayListState state)
    {
        var toCheck = state.someRandomNumbers.get(0); // > element ?
        var errorElements = state.collection.stream().filter(x -> x > toCheck).collect(Collectors.toCollection(ArrayList<Integer>::new));
        var noErrors = errorElements.size() == 0;
    }

    @Benchmark
    public void BenchmarkArrayListStreamParallel(final ArrayListState state)
    {
        var toCheck = state.someRandomNumbers.get(0); // > element ?
        var errorElements = state.collection.parallelStream().filter(x -> x > toCheck).collect(Collectors.toCollection(ArrayList<Integer>::new));
        var noErrors = errorElements.size() == 0;
    }


    @Override
    public Collection<RunResult> RunBenchmarks() throws InterruptedException, RunnerException, IOException
    {
        Options opt = new OptionsBuilder()
                .include(Static5a.class.getSimpleName())
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

