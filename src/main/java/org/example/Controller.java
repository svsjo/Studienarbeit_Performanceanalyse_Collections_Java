package org.example;

import org.apache.commons.math3.genetics.RandomKeyMutation;
import org.example.DataClasses.BenchmarkMapper;
import org.example.DataClasses.CollectionBenchmarkRuns;
import org.example.UseCases.*;
import org.example.UseCases.Static.*;
import org.example.Utils.DiagramDrawer;
import org.example.Utils.ExcelTableGenerator;
import org.openjdk.jmh.runner.RunnerException;

import java.io.IOException;
import java.util.*;

public class Controller
{
    public static List<UseCase> allUseCases = new ArrayList<>();
    public static Map<UseCase, Collection<CollectionBenchmarkRuns>> allResults = new HashMap<>();
    public static DiagramDrawer Drawer = new DiagramDrawer();
    public static BenchmarkMapper Mapper = new BenchmarkMapper();
    public static ExcelTableGenerator TableGenerator = new ExcelTableGenerator();

    public static void main(String[] args) throws RunnerException, IOException, InterruptedException
    {
        /* Statische Use Cases -> Auskommentieren welche verwendet werden sollen */
        allUseCases.add(new Static1a());
        allUseCases.add(new Static1b());
        allUseCases.add(new Static1c());

        // allUseCases.add(new Static2a());
        // allUseCases.add(new Static2b());

        // allUseCases.add(new Static3a());
        // allUseCases.add(new Static3b());

        // allUseCases.add(new Static4());

        // allUseCases.add(new Static5a());
        // allUseCases.add(new Static5b());

        // allUseCases.add(new Static6a());
        // allUseCases.add(new Static6b());

        // allUseCases.add(new Extra1());

        /* Auswertung */
        RunAllUseCases();
        System.out.println("----------------------- Ende -----------------------");
    }

    public static void RunAllUseCases() throws RunnerException, IOException, InterruptedException
    {
        for(var useCase : allUseCases)
        {
            var results = useCase.RunBenchmarks();
            var standardizedResults = Mapper.Map(results);
            allResults.put(useCase, standardizedResults);

            Drawer.draw(allResults.get(useCase), useCase.getClass().getSimpleName());
            TableGenerator.generate(allResults.get(useCase), useCase.getClass().getSimpleName());
        }
    }
}

