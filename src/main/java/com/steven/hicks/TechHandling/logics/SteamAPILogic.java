package com.steven.hicks.TechHandling.logics;

import com.steven.hicks.entities.SteamGame;

import jakarta.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class SteamAPILogic
{
    public static ArrayList<SteamGame> doSteamApiCall(HttpServletRequest request)
    {
        String URLAddress = "http://api.steampowered.com/ISteamApps/GetAppList/v0002/?count=3&maxlength=300&format=xml";
        String inputString = null;
        List<SteamGame> allGameList = new ArrayList<>();

        try
        {
            URL url = new URL(URLAddress);
            try
            {
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                BufferedReader in = new BufferedReader(new InputStreamReader(
                        connection.getInputStream()));

                String name;
                String appId = "";
                Map<Integer,String> mapOfGamesList = new HashMap<Integer, String>();

                while ((inputString = in.readLine()) != null)
                {
                    if (inputString.length() > 10)
                    {
                        if (inputString.substring(0,10).equalsIgnoreCase("\t\t\t<appid>"))
                        {
                            int lengthOfInputString = inputString.length();
                            appId = inputString.substring(10, lengthOfInputString-8);
                        }

                        if (inputString.substring(0,9).equalsIgnoreCase("\t\t\t<name>"))
                        {
                            int lengthOfInputString = inputString.length();
                            name = inputString.substring(9, lengthOfInputString-7);

                            mapOfGamesList.put(Integer.valueOf(appId),name);
                        }
                    }
                }
                in.close();

                Iterator it = mapOfGamesList.keySet().iterator();
                while (it.hasNext())
                {
                    int key = (Integer)it.next();

                    SteamGame game = new SteamGame(key, mapOfGamesList.get(key));
                    allGameList.add(game);
                }

                allGameList.sort(Comparator.comparing(SteamGame::getAppId));

                String sortType = request.getParameter("sort");

                if (sortType.equalsIgnoreCase("reverseId"))
                    allGameList.sort(Comparator.comparing(SteamGame::getAppId).reversed());

                if (sortType.equalsIgnoreCase("alpha"))
                    allGameList.sort(Comparator.comparing(SteamGame::getName));

                if (sortType.equalsIgnoreCase("reverseAlpha"))
                    allGameList.sort(Comparator.comparing(SteamGame::getName).reversed());
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }

        return (ArrayList<SteamGame>) allGameList;
    }


}
