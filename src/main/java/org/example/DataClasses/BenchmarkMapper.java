package org.example.DataClasses;

import org.openjdk.jmh.results.RunResult;

import java.util.ArrayList;
import java.util.Collection;

public class BenchmarkMapper
{
    public Collection<CollectionBenchmarkRuns> Map(Collection<RunResult> runResults)
    {
        var allBenchmarks = new ArrayList<CollectionBenchmarkRuns>();

        for (var run : runResults)
        {
            var runResult = run.getBenchmarkResults();

            for (var res : runResult)
            {
                var primaryResult = res.getPrimaryResult();
                var methodName = primaryResult.getLabel();

                var belongingBenchmark = allBenchmarks.stream().filter(x -> x.methodName == methodName).findFirst();
                CollectionBenchmarkRuns collectionBenchmark;

                if(belongingBenchmark.isEmpty())
                {
                    collectionBenchmark = new CollectionBenchmarkRuns();
                    collectionBenchmark.methodName = primaryResult.getLabel();
                    collectionBenchmark.TimeMode = res.getParams().getMode();
                    collectionBenchmark.iterationCount = primaryResult.getSampleCount();
                }
                else
                {
                    collectionBenchmark = belongingBenchmark.get();
                    allBenchmarks.remove(collectionBenchmark);
                }

                var benchmarkRun = new BenchmarkRun();
                benchmarkRun.elementCount = Integer.parseInt(res.getParams().getParam("elements"));
                benchmarkRun.score = primaryResult.getScore();
                benchmarkRun.scoreUnit = primaryResult.getScoreUnit();

                collectionBenchmark.allRuns.add(benchmarkRun);

                allBenchmarks.add(collectionBenchmark);
            }
        }

        return allBenchmarks;
    }
}
