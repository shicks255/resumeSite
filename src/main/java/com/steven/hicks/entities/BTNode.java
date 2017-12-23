package com.steven.hicks.entities;

public class BTNode<T>
{
    private T data;
    private BTNode<T> right;
    private BTNode<T> left;

    public BTNode(T data, BTNode<T> right, BTNode<T> left)
    {
        this.data = data;
        this.right = right;
        this.left = left;
    }

    public BTNode()
    {
    }

    public static <T> BTNode<T> treeCopy(BTNode<T> source)
    {
        BTNode<T> leftCopy, rightCopy;

        if (source == null)
            return null;
        else
        {
            leftCopy = treeCopy(source.left);
            rightCopy = treeCopy(source.right);
            return new BTNode<T>(source.data, leftCopy, rightCopy);
        }
    }


    //Binary Tree specific methods...
    public boolean isLeaf()
    {
        return left == null && right == null;
    }

    public T getLeftmostData()
    {
        if (left == null)
            return data;
        else
            return left.getLeftmostData();
    }

    public T getRightmostData()
    {
        if (right == null)
            return data;
        else
            return right.getRightmostData();
    }

    public BTNode<T> removeLeftmost()
    {
        if (left == null)
            return right;
        else
        {
            left = left.removeLeftmost();
            return this;
        }
    }

    public BTNode<T> removeRightmost()
    {
        if (right == null)
            return left;
        else
        {
            right = right.removeRightmost();
            return this;
        }
    }

    //------Getters & Setters

    public T getData()
    {
        return data;
    }

    public void setData(T data)
    {
        this.data = data;
    }

    public BTNode<T> getRight()
    {
        return right;
    }

    public void setRight(BTNode<T> right)
    {
        this.right = right;
    }

    public BTNode<T> getLeft()
    {
        return left;
    }

    public void setLeft(BTNode<T> left)
    {
        this.left = left;
    }
}

