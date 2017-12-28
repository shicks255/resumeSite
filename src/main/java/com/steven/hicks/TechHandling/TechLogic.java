package com.steven.hicks.TechHandling;

//import com.sun.org.apache.xerces.internal.parsers.SAXParser;

import com.steven.hicks.entities.*;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class TechLogic
{
    public static int getSessionAccessAcount(HttpSession session, HttpServletRequest request)
    {
        Integer accessCount;
        synchronized(session)
        {
            if (!request.isRequestedSessionIdValid())
                session = request.getSession();

            accessCount = (Integer)session.getAttribute("accessCountSession");
            if (accessCount == null)
                accessCount = 1;   // autobox int to Integer
            else
                accessCount = new Integer(accessCount + 1 + "");
        }

        session.setAttribute("accessCountSession", accessCount);

        return accessCount;
    }



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

    public static BTNode<String>  getNodesFromXML()
    {
        InputStream inputStream =
                Thread.currentThread().getContextClassLoader().getResourceAsStream("animalGuessingTree.xml");

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        BTNode<String> root = new BTNode<String>();

        Map<Integer, BTNode> allNodes = new HashMap<>();

        try
        {
            while (reader.ready())
            {
                String line = reader.readLine();

                if (line.contains("<node"))
                {
                    root.setData(line.substring(line.indexOf("data=\"")+6, line.indexOf(">")-1));
                    String id = line.substring(line.indexOf("id=\"")+4, line.indexOf("id=\"")+8);
                    int theId = Integer.parseInt(id.trim());
                    String parent = line.substring(line.indexOf("parentId=\"")+10, line.indexOf("parentId=\"") + 14);
                    int theParent = Integer.parseInt(parent.trim());
                    allNodes.put(theId, root);
                }

                if (line.contains("<left"))
                {
                    BTNode<String> newLeft = new BTNode<String>();
                    newLeft.setData(line.substring(line.indexOf("data=\"")+6, line.indexOf(">")-2));
                    String id = line.substring(line.indexOf("id=\"")+4, line.indexOf("id=\"")+8);
                    int theId = Integer.parseInt(id.trim());
                    String parent = line.substring(line.indexOf("parentId=\"")+10, line.indexOf("parentId=\"")+14);
                    int theParent = Integer.parseInt(parent.trim());
                    BTNode<String> parentNode = allNodes.get(theParent);
                    if (parentNode != null)
                    {
                        parentNode.setLeft(newLeft);
                        allNodes.put(theId, newLeft);
                    }
                }

                if (line.contains("<right"))
                {
                    BTNode<String> newRight = new BTNode<String>();
                    newRight.setData(line.substring(line.indexOf("data=\"")+6, line.indexOf(">")-2));
                    String id = line.substring(line.indexOf("id=\"")+4, line.indexOf("id=\"")+8);
                    int theId = Integer.parseInt(id.trim());
                    String parent = line.substring(line.indexOf("parentId=\"")+10, line.indexOf("parentId=\"")+14);
                    int theParent = Integer.parseInt(parent.trim());
                    BTNode<String> parentNode = allNodes.get(theParent);
                    if (parentNode != null)
                    {
                        parentNode.setRight(newRight);
                        allNodes.put(theId, newRight);
                    }
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return root;


    }

}
