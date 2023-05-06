package org.example.UseCases;
import org.openjdk.jmh.results.RunResult;
import org.openjdk.jmh.runner.RunnerException;
import java.io.IOException;
import java.util.Collection;

public interface UseCase
{
    public Collection<RunResult> RunBenchmarks() throws RunnerException, IOException, InterruptedException;
}
