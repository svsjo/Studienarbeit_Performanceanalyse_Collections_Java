package org.example.DataClasses;

import org.openjdk.jmh.annotations.Mode;

import java.util.ArrayList;
import java.util.List;

/**
 * Enthält alle Benchmarks einer Collection, also beispielsweise für die Parameter 10, 20, 50 als Größe.
 */
public class CollectionBenchmarkRuns
{
    public String methodName;
    public long iterationCount;
    public int warmupCount = 3;
    public Mode TimeMode;
    public List<BenchmarkRun> allRuns = new ArrayList<>();
}
