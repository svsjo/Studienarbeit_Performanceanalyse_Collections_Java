package org.example.CollectionsInit;

import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;

@State(Scope.Benchmark)
public class LinkedHashSetState implements MinimumStateObject
{
    @Param({"100", "200", "500", "1000", "2000", "5000", "10000", "20000", "50000", "100000", "200000", "500000", "1000000", "2000000", "5000000", "10000000", "20000000", "50000000", "100000000" })
    public int elements;

    public LinkedHashSet<Integer> collection;

    public ArrayList<Integer> someRandomNumbers = new ArrayList<>();

    @Setup(Level.Iteration)
    public void SetUp()
    {
        if(this.collection == null || this.collection.size() == 0)
        {
            this.collection = GetRandomNumbers(elements);
        }

        someRandomNumbers.clear();
        this.InitHelperCollection(elements);
    }

    @TearDown(Level.Trial)
    public void TearDown()
    {
        this.collection.clear();
        this.someRandomNumbers.clear();
    }

    public LinkedHashSet<Integer> GetRandomNumbers(int count)
    {
        LinkedHashSet<Integer> collection = new LinkedHashSet<Integer>();
        Random random = new Random();

        for (int i = 0; i < count; i++)
        {
            var successAdding = collection.add(random.nextInt(count*2));
            while (!successAdding)
            {
                successAdding = collection.add(random.nextInt(count*2));
            }
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
