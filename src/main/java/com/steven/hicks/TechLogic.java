package com.steven.hicks;

//import com.sun.org.apache.xerces.internal.parsers.SAXParser;
import com.steven.hicks.entities.SteamGame;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.SAXParser;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

/**
 * Created by Steven on 7/26/2016.
 */
public class TechLogic
{
    public static ArrayList<SteamGame> doSteamApiCall()
    {
        String URLAddress = "http://api.steampowered.com/ISteamApps/GetAppList/v0002/?count=3&maxlength=300&format=xml";
        String inputString = null;
        int responseCode = 0;

        List<SteamGame> steamGames = new ArrayList<>();

        try
        {
            URL url = new URL(URLAddress);
            try
            {
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

//                if (!parsed)
//                {

//                    responseCode = connection.getResponseCode();
//                    detailsList.add("Connecting to " + URLAddress + ", Connection Method: GET, Response Code is: " + responseCode);
//                    detailsList.add("----[ URL DETAILS]-------");
//                    detailsList.add("URL Protocol...: " + url.getProtocol());
//                    detailsList.add("URL Host.......: " + url.getHost());
//                    detailsList.add("URL Port.......: " + url.getPort());
//                    detailsList.add("URL Authority..: " + url.getAuthority());
//                    detailsList.add("URL Path.......: " + url.getPath());
//                    detailsList.add("URL User Info..: " + url.getUserInfo());
//                    detailsList.add("URL Query Info.: " + url.getQuery());
//                }

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

                                Map<Integer, String> mapOfGames = new HashMap<>();
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
                    steamGames.add(game);
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

        return (ArrayList<SteamGame>) steamGames;
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
//                String game = "";
//                game = new SteamGame();
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
            {
                gameList.add(game.toString());
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException
        {
            if (bGameId)
            {
                game.append(new String(ch, start, length));
//                game.setAppId(new String(ch, start, length));
                bGameId = false;
            }

            if (bGameName)
            {
                game.append(new String(ch, start, length));
//                game.setGameName(new String(ch, start, length));
                bGameName = false;
            }
        }
    }
}
