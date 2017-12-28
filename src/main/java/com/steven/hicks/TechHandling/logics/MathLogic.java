package com.steven.hicks.TechHandling.logics;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MathLogic
{
    public static int getLargestPrimeNumberUnderAMillion()
    {
        int answer = 0;
        for (int i = 1; i <=100_000; i++)
        {
            boolean isPrime = isPrime(i);
            if (isPrime)
                answer = i;
        }
        return answer;
    }

    public static int getLargestPalindromeNumber()
    {
        int answer = 0;
        for (int i = 1; i <= 9999; i++)
            for (int j = 1; j <= 9999; j++)
            {
                int product = i * j;
                String productString = ""+product;
                if (productString.equals(new StringBuilder(productString).reverse().toString()))
                    if (product > answer)
                        answer = product;
            }
        return answer;
    }

    public static long getLargestPrimeFactor()
    {
        long answer = 0;
        long bigNum = 100851475L;
        for (long i = 2; i < bigNum; i++)
            if (bigNum % i == 0 && isPrime(i))
                if (i > answer)
                    answer = i;
        return answer;
    }

    public static int doRaceCondition()
    {
        int[] answer = new int[1];
//        ReentrantLock lock = new ReentrantLock();
        answer[0] = 1;

        Thread t1 = new Thread(() ->
        {
//            lock.lock();
//            try
//            {
            for (int i = 1; i <= 1000000; i++)
                answer[0] = answer[0] + 1;
//            }
//            finally
//            {
//                lock.unlock();
//            }

        });

        Thread t2 = new Thread(() ->
        {
//            lock.lock();
//            try
//            {
            for (int i = 0; i < 1000000; i++)
                answer[0] = answer[0] + 1;
//            }
//            finally
//            {
//                lock.unlock();
//            }
        });

        ExecutorService executorService = Executors.newFixedThreadPool(4);
        Future f1 = executorService.submit(t1);
        Future f2 = executorService.submit(t2);
        executorService.shutdown();

        while (!f1.isDone() || !f2.isDone())
        {}

        System.out.println(answer[0]);
        return answer[0];
    }

    public static boolean isPrime(long number)
    {
        for (long i = 2; i < number; i++)
            if (number % i == 0)
                return false;
        return true;
    }


}
