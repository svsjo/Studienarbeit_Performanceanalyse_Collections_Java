package org.example.Utils;
import org.example.DataClasses.CollectionBenchmarkRuns;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.*;


import java.io.File;
import java.io.IOException;
import java.util.Collection;

public class DiagramDrawer
{
    public void draw(Collection<CollectionBenchmarkRuns> allBenchmarks, String useCase) throws IOException
    {
        XYSeriesCollection dataset = new XYSeriesCollection();
        for(var benchmark : allBenchmarks)
        {
            var series = new XYSeries(benchmark.methodName);
            for(var result : benchmark.allRuns)
            {
                series.add(result.elementCount, result.score);
            }
            dataset.addSeries(series);
        }

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Benchmark: " + useCase,
                "Elementzahl n",  // x-Achsenbeschriftung
                "Laufzeit (ms)",  // y-Achsenbeschriftung
                dataset  // Datensätze
        );

        ChartFrame frame = new ChartFrame("Laufzeitentwicklung", chart);
        frame.pack();
        frame.setVisible(true);

        var targetFile = new File("./resultImages/" + useCase + ".png");

        try
        {
            ChartUtils.saveChartAsPNG(targetFile, chart, 1920, 1080);
        } catch (Exception ex)
        {
            System.out.println(ex.getLocalizedMessage());
            System.out.println(ex.getMessage());
            System.out.println(ex.getCause().getMessage());
        }

    }
}
