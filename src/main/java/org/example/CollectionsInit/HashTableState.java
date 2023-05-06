package org.example.CollectionsInit;

import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

@State(Scope.Benchmark)
public class HashTableState implements MinimumStateObject
{
    @Param({"100", "200", "500", "1000", "2000", "5000", "10000", "20000", "50000", "100000", "200000", "500000", "1000000", "2000000", "5000000", "10000000", "20000000", "50000000", "100000000"})
    public int elements;

    public Hashtable<Integer, Integer> collection;

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

    public Hashtable<Integer, Integer> GetRandomNumbers(int count)
    {
        Hashtable<Integer, Integer> collection = new Hashtable<Integer, Integer>();
        Random random = new Random();

        for (int i = 0; i < count; i++)
        {
            var key = random.nextInt(count*2);
            var value = random.nextInt(count*2);
            var retVal = collection.put(key, value);
            while (retVal != null) /* null == Key existiert noch nicht */
            {
                key = random.nextInt(count*2);
                retVal = collection.put(key, value);
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
