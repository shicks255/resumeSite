package com.steven.hicks.TechHandling;

//import com.sun.org.apache.xerces.internal.parsers.SAXParser;

import com.steven.hicks.entities.Album;
import com.steven.hicks.entities.MusicArtist;
import com.steven.hicks.entities.SteamGame;
import com.steven.hicks.entities.TopArtistRecord;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
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

    public static List<Album> getAlbumsFromMethodCall(BufferedReader in)
    {
        List<Album> searchResults = new ArrayList<>();
        String inputString = null;
        Map<Integer, String> mapOfArtists = new HashMap<>();
        boolean startAlbumTag = false;
        boolean stopAlbumTag = false;
        String artistName = "";
        String urll = "";
        String albumName = "";
        Album album = new Album();

        try
        {
            while ((inputString = in.readLine()) != null)
            {
                if (inputString.contains("<album>"))
                {
                    startAlbumTag = true;
                    stopAlbumTag = false;
                }
                if (inputString.contains("</album>"))
                {
                    stopAlbumTag = true;
                    startAlbumTag = false;
                }

                if (startAlbumTag)
                {
                    album = new Album();

                    if (inputString.contains("<artist>") && inputString.contains("</artist>"))
                    {
                        artistName = inputString.substring(inputString.indexOf("<artist>") + 8, inputString.indexOf("</artist>"));
                        album.setArtist(artistName);
                    }

                    if (inputString.contains("<name>") && inputString.contains("</name>"))
                    {
                        albumName = inputString.substring(inputString.indexOf("<name>") + 6, inputString.indexOf("</name>"));
                        album.setAlbum(albumName);
                    }
                }

                if (stopAlbumTag)
                {
                    album.setArtist(artistName);
                    album.setAlbum(albumName);
                    searchResults.add(album);
                }
            }
            in.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return searchResults;
    }

    public static List<Album> searchForAlbums(HttpServletRequest request, String albumSearch) throws Exception
    {
        List<Album> searchResults = Collections.emptyList();

        albumSearch = albumSearch.replace(" ", "%20");

        String URLAddress = "http://ws.audioscrobbler.com/2.0/?method=album.search&album=" + albumSearch + "&api_key=c349ab1fcb6b132ffb8d842e982458db&page=1&limit=10&format=xml";
        String inputString = null;
        List<Album> albums = new ArrayList<>();

        URL url = null;
        url = new URL(URLAddress);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader in = null;
        in = new BufferedReader(new InputStreamReader(
                connection.getInputStream()));

        searchResults = getAlbumsFromMethodCall(in);

        return searchResults;
    }

    public static List<TopArtistRecord> searchForTopArtists(HttpServletRequest request, String userName)
    {
        List<TopArtistRecord> topArtistRecords = new ArrayList<>();

        boolean startTag = false;
        boolean stopTage = false;
        String artistName = "";
        int playCount;

        String URLAddress = "http://ws.audioscrobbler.com/2.0/?method=user.gettopartists&user=" + userName + "&api_key=c349ab1fcb6b132ffb8d842e982458db&period=overall&format=xml";

        try
        {
            URL url = null;
            url = new URL(URLAddress);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = null;
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));

            topArtistRecords = getTopArtistRecordsFromMethodCall(in);

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return topArtistRecords;
    }

    public static List<TopArtistRecord> getTopArtistRecordsFromMethodCall(BufferedReader in)
    {
        String inputString = null;
        List<TopArtistRecord> searchResults = new ArrayList<>();

        boolean startTag = false;
        boolean stopTag = false;
        String artistName = "";
        int rank = 0;
        int playCount = 0;
        MusicArtist musicArtist = new MusicArtist();
        TopArtistRecord record = new TopArtistRecord();

        try
        {
            while ((inputString = in.readLine()) != null)
            {
                if (inputString.contains("<artist"))
                {
                    startTag = true;
                    stopTag = false;
                }
                if (inputString.contains("</artist>"))
                {
                    stopTag = true;
                    startTag = false;
                }

                if (startTag)
                {
                    musicArtist = new MusicArtist();
                    record = new TopArtistRecord();

                    if (inputString.contains("rank=") && inputString.contains("<artist"))
                    {
                        int startOfRankNumber = inputString.indexOf("rank=\"") + 6;
                        String rankString = inputString.substring(startOfRankNumber);
                        rankString.replace("\"", "").trim();

                        rank = Integer.valueOf(rankString.substring(0, rankString.indexOf(">")-1));
                    }

                    if (inputString.contains("<name>") && inputString.contains("</name>"))
                    {
                        artistName = inputString.substring(inputString.indexOf("<name>") + 6, inputString.indexOf("</name"));
                    }

                    if (inputString.contains("<playcount>") && inputString.contains("</playcount>"))
                    {
                        playCount = Integer.valueOf(inputString.substring(inputString.indexOf("<playcount>") + 11, inputString.indexOf("</playcount>")));
                    }
                }

                if (stopTag)
                {
                    musicArtist.setArtistName(artistName);
                    record.setMusicArtist(musicArtist);
                    record.setPlayCount(playCount);
                    record.setRank(rank);
                    searchResults.add(record);
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return searchResults;
    }

    public static List<MusicArtist> getArtistsFromMethodCall(BufferedReader in)
    {
        String inputString = null;
        List<MusicArtist> searchResults = new ArrayList<>();

        boolean startArtistTag = false;
        boolean stopArtistTag = false;
        String artistName = "";
        String urll = "";
        String imageUrl = "";
        MusicArtist musicArtist = new MusicArtist();

        try
        {
            while ((inputString = in.readLine()) != null)
            {

                if (inputString.contains("<artist>"))
                {
                    startArtistTag = true;
                    stopArtistTag = false;
                }
                if (inputString.contains("</artist>"))
                {
                    stopArtistTag = true;
                    startArtistTag = false;
                }

                if (startArtistTag)
                {
                    musicArtist = new MusicArtist();

                    if (inputString.contains("<name>") && inputString.contains("</name>"))
                    {
                        artistName = inputString.substring(inputString.indexOf("<name>") + 6, inputString.indexOf("</name>"));
    //                        musicArtist.setArtistName(artistName);
                    }

                    if (inputString.contains("<url>") && inputString.contains("</url>"))
                    {
                        urll = inputString.substring(inputString.indexOf("<url>") + 5, inputString.indexOf("</url>"));
    //                        musicArtist.setUrl(urll);
                    }

                    if (inputString.contains("<image>") && inputString.contains("</image>"))
                    {
                        imageUrl = inputString.substring(inputString.indexOf("<image>") + 5, inputString.indexOf("</image>"));
    //                        musicArtist.setImageURL(imageUrl);
                    }
                }

                if (stopArtistTag)
                {
                    musicArtist.setArtistName(artistName);
                    musicArtist.setUrl(urll);
                    searchResults.add(musicArtist);
                }
            }
            in.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return searchResults;
    }

    public static List<MusicArtist> searchForArtists(HttpServletRequest request, String artistSearch) throws Exception
    {
        String URLAddress = "http://ws.audioscrobbler.com/2.0/?method=artist.search&artist=" + artistSearch + "&api_key=c349ab1fcb6b132ffb8d842e982458db&limit=10&format=xml&callback=?";
        String inputString = null;
        List<MusicArtist> musicArtists = new ArrayList<>();

        URL url = new URL(URLAddress);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(
                connection.getInputStream()));

        musicArtists = getArtistsFromMethodCall(in);

        in.close();

        return musicArtists;
    }

    public static ArrayList<MusicArtist> getMusicArtistsFromLast_FM(HttpServletRequest request)
    {
        String URLAddress = "http://ws.audioscrobbler.com/2.0/?method=user.getTopArtists&user=test&api_key=c349ab1fcb6b132ffb8d842e982458db&limit=10&format=xml&callback=?";
        String inputString = null;
        List<MusicArtist> musicArtists = new ArrayList<>();

        try
        {
            URL url = new URL(URLAddress);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));

            String artistName = "";
            Map<Integer, String> mapOfMusicArtists = new HashMap<>();

            while ((inputString = in.readLine()) != null)
            {
                if (inputString.contains("<name>") && inputString.contains("</name>"))
                {
                    int lengthOfInputString = inputString.length();
                    artistName = inputString.substring(inputString.indexOf("<name>")+5, inputString.indexOf("</name>"));
                    MusicArtist musicArtist = new MusicArtist(artistName);
                    musicArtists.add(musicArtist);
                }
            }
            in.close();


        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return (ArrayList<MusicArtist>) musicArtists;
    }


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
