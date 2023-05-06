package org.example.CollectionsInit;

import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@State(Scope.Benchmark)
public class ArrayState implements MinimumStateObject
{
    @Param({"100", "200", "500", "1000", "2000", "5000", "10000", "20000", "50000", "100000", "200000", "500000", "1000000", "2000000", "5000000", "10000000", "20000000", "50000000", "100000000" })
    public int elements;

    public int[] collection;

    public ArrayList<Integer> someRandomNumbers = new ArrayList<>();

    @Setup(Level.Iteration)
    public void SetUp()
    {
        if(this.collection == null || this.collection.length == 0)
        {
            this.collection = GetRandomNumbers(elements);
        }

        someRandomNumbers.clear();
        this.InitHelperCollection(elements);
    }

    @TearDown(Level.Trial)
    public void TearDown()
    {
        for (int i = 0; i < collection.length; ++i)
        {
            collection[i] = 0;
        }

        this.someRandomNumbers.clear();
    }

    public int[] GetRandomNumbers(int count)
    {
        int[] collection = new int[count];
        Random random = new Random();

        for (int i = 0; i < count; i++)
        {
            collection[i] = random.nextInt(count*2);
        }

        return collection;
    }

    public void InitHelperCollection(int count)
    {
        Random random = new Random();
        for(int i = 0; i < (count / 4); i++)
        {
            someRandomNumbers.add(random.nextInt(count));
        }
    }
}
