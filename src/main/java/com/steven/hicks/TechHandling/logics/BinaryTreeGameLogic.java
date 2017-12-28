package com.steven.hicks.TechHandling.logics;

import com.steven.hicks.entities.BTNode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class BinaryTreeGameLogic
{
    public static BTNode<String> getNodesFromXML()
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
