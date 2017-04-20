package com.steven.hicks.entities;

public class SteamGame
{
    private int appId;
    private String name = "";

    public SteamGame(int appId, String name)
    {
        this.appId = appId;
        this.name = name;
    }

    @Override
    public String toString()
    {
        return "SteamGame{" +
                "appId=" + appId +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SteamGame steamGame = (SteamGame) o;

        return appId == steamGame.appId;
    }

    @Override
    public int hashCode()
    {
        return appId;
    }

    public int getAppId()
    {
        return appId;
    }

    public void setAppId(int appId)
    {
        this.appId = appId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
