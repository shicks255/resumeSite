package com.steven.hicks.TechHandling.logics;

import com.steven.hicks.entities.BTNode;
import com.steven.hicks.filters.LogFilter;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BinaryTreeGameLogic
{
    private static final Logger log = Logger.getLogger(BinaryTreeGameLogic.class.getName());

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
            log.error(e.getMessage());
        }

        return root;
    }

    public static void addNode(String animal, String question, BTNode currentNode, BTNode root)
    {
        BTNode<String> oldAnswer = new BTNode<String>((String)currentNode.getData(), null, null);

        currentNode.setData(question);
        currentNode.setLeft(new BTNode<String>(animal, null, null));
        currentNode.setRight(oldAnswer);

        try
        {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            File file = new File(classLoader.getResource("animalGuessingTree.xml").getFile());
            FileWriter fr = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fr);

            writer.write("<?xml version=\"1.0\"?>");
            writer.write("\r\n\t<questionTree xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"animalTreeSchema.xsd\">");
            writer.write("\r\n\t\t<node id=\"1   \" parentId=\"1   \" data=\"" + root.getData() + "\">");

            Set<Integer> usedIds = new HashSet<>();
            int currentId = 1;
            int currentParent = 1;
            usedIds.add(currentId);

            writeLeftAndRightNodes(writer, root, usedIds, currentId, currentParent);

            writer.write("\r\n\t\t</node>");
            writer.write("\r\n\t</questionTree>");

            writer.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }

    private static void writeLeftAndRightNodes(BufferedWriter writer, BTNode<String> currentNode, Set<Integer> usedIds, int currentId, int currentParent) throws IOException
    {
        BTNode<String> testNode = currentNode;
        if (testNode.getLeft() != null)
        {
            testNode = testNode.getLeft();
            while (!usedIds.add(currentId))
                currentId++;

            writer.write("\r\n\t\t\t<left id=\"" + currentId + "   \" parentId=\"" + currentParent + "   \" data=\"" + testNode.getData() + "\"/>");
            writeLeftAndRightNodes(writer, testNode, usedIds, currentId, currentId);
        }

        testNode = currentNode;
        if (testNode.getRight() != null)
        {
            testNode = testNode.getRight();
            while (!usedIds.add(currentId))
                currentId++;

            writer.write("\r\n\t\t\t<right id=\"" + currentId + "   \" parentId=\"" + currentParent + "   \" data=\"" + testNode.getData() + "\"/>");
            writeLeftAndRightNodes(writer, testNode, usedIds, currentId, currentId);
        }
    }
}
