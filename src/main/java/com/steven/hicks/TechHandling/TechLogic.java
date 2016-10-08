package com.steven.hicks.TechHandling;

//import com.sun.org.apache.xerces.internal.parsers.SAXParser;

import com.steven.hicks.entities.SteamGame;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Steven on 7/26/2016.
 */
public class TechLogic
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

                String name = "";
                String appid = "";
                Map<Integer,String> mapOfGamesList = new HashMap<Integer, String>();

                while ((inputString = in.readLine()) != null)
                    {
                        if (inputString.length() > 10)
                        {
                            if (inputString.substring(0, 10) != null && inputString.substring(0,10).equalsIgnoreCase("\t\t\t<appid>"))
                            {
                                int lengthOfInputString = inputString.length();
                                appid = inputString.substring(10, lengthOfInputString-8);
                            }

                            if (inputString.substring(0, 9) != null && inputString.substring(0,9).equalsIgnoreCase("\t\t\t<name>"))
                            {
                                int lengthOfInputString = inputString.length();
                                name = inputString.substring(9, lengthOfInputString-7);

                                mapOfGamesList.put(Integer.valueOf(appid),name);
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

                Collections.sort(allGameList, new Comparator<SteamGame>()
                {
                    @Override
                    public int compare(SteamGame o1, SteamGame o2)
                    {
                        int returnValue = 0;
                        if (o1.getAppId() > o2.getAppId())
                            returnValue = 1;
                        if (o1.getAppId() < o2.getAppId())
                            returnValue = -1;

                        return returnValue;
                    }
                });

                String sortType = request.getParameter("sort");

                if (sortType.equalsIgnoreCase("reverseId"))
                {
                    Collections.sort(allGameList, new Comparator<SteamGame>()
                    {
                        @Override
                        public int compare(SteamGame o1, SteamGame o2)
                        {
                            int returnValue = 0;
                            if (o1.getAppId() > o2.getAppId())
                                returnValue = -1;
                            if (o1.getAppId() < o2.getAppId())
                                returnValue = 1;

                            return returnValue;
                        }

                    });
                }

                if (sortType.equalsIgnoreCase("alpha"))
                {
                    allGameList = allGameList.stream()
                            .sorted( (o1, o2) -> o1.getName().compareTo(o2.getName()))
                            .collect(Collectors.toList());
                }

                if (sortType.equalsIgnoreCase("reverseAlpha"))
                {
                    allGameList = allGameList.stream()
                            .sorted( (o1, o2) -> -o1.getName().compareTo(o2.getName()))
                            .collect(Collectors.toList());
                }

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

    private static class MyHandler extends DefaultHandler
    {
        private List<String> gameList = null;

        public List<String> getList()
        {
            return gameList;
        }

        boolean bGameName = false;
        boolean bGameId = false;

        StringBuilder game = new StringBuilder();

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
        {
            if (qName.equalsIgnoreCase("app"))
            {
                if (gameList == null)
                    gameList = new ArrayList<>();
            }
            else if (qName.equalsIgnoreCase("appid"))
            {
                bGameId = true;
            }
            else if (qName.equalsIgnoreCase("name"))
            {
                bGameName = true;
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName)throws SAXException
        {
            if (qName.equalsIgnoreCase("app"))
                gameList.add(game.toString());
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException
        {
            if (bGameId)
            {
                game.append(new String(ch, start, length));
                bGameId = false;
            }

            if (bGameName)
            {
                game.append(new String(ch, start, length));
                bGameName = false;
            }
        }
    }
}
