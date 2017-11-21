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

    public static List<TopArtistRecord> searchForTopArtists(HttpServletRequest request, String userName, String selectedTimePeriod)
    {
        List<TopArtistRecord> topArtistRecords = new ArrayList<>();

        String URLAddress = "http://ws.audioscrobbler.com/2.0/?method=user.gettopartists&user=" + userName + "&api_key=c349ab1fcb6b132ffb8d842e982458db&limit=250&format=xml&period=" + selectedTimePeriod;
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

    public static List<TopAlbumRecord> getTopAlbumRecordsFromMethodCall(BufferedReader in)
    {
        String inputString = null;
        List<TopAlbumRecord> searchResults = new ArrayList<>();

        boolean startTag = false;
        boolean stopTag = false;

        boolean startArtistTag = false;
        boolean stopArtistTag = false;

        String artistName = "";
        String albumName = "";
        String smallImage = "";
        String medImage = "";
        int rank = 0;
        int playCount = 0;

        Album album = new Album();
        TopAlbumRecord record = new TopAlbumRecord();

        try
        {
            while ((inputString = in.readLine()) != null)
            {
                if (inputString.contains("<album"))
                {
                    startTag = true;
                    stopTag = false;
                }
                if (inputString.contains("</album>"))
                {
                    startTag = false;
                    stopTag = true;
                }
                if (inputString.contains("<artist>"))
                {
                    startArtistTag = true;
                    stopArtistTag = false;
                }
                if (inputString.contains("</artist>"))
                {
                    startArtistTag = false;
                    stopArtistTag = true;
                }

                if (startTag)
                {
                    album = new Album();
                    record = new TopAlbumRecord();

                    if (inputString.contains("rank="))
                    {
                        int startOfRankNumber = inputString.indexOf("rank=\"") + 6;
                        String rankString = inputString.substring(startOfRankNumber);
                        rankString.replace("\"", "").trim();

                        rank = Integer.valueOf(rankString.substring(0, rankString.indexOf(">")-1));
                    }

                    if (inputString.contains("<name>") && inputString.contains("</name>") && !startArtistTag)
                    {
                        albumName = inputString.substring(inputString.indexOf("<name>") + 6, inputString.indexOf("</name"));

                    }
                    if (inputString.contains("<name>") && inputString.contains("</name>") && startArtistTag)
                    {
                        artistName = inputString.substring(inputString.indexOf("<name>") + 6, inputString.indexOf("</name"));
                    }

                    if (inputString.contains("<playcount>") && inputString.contains("</playcount>"))
                    {
                        playCount = Integer.valueOf(inputString.substring(inputString.indexOf("<playcount>") + 11, inputString.indexOf("</playcount>")));
                    }
                    if (inputString.contains("<image size="))
                    {
                        if (inputString.contains("size=\"small\""))
                            smallImage = inputString.substring(inputString.indexOf("size=\"small\"") + 13, inputString.indexOf("</image>"));
                        if (inputString.contains("size=\"medium\""))
                            medImage = inputString.substring(inputString.indexOf("size=\"medium\"") + 14, inputString.indexOf("</image>"));
                    }
                }

                if (stopTag)
                {
                    album.setArtist(artistName);
                    album.setAlbum(albumName);
                    album.setSmallImageUrl(smallImage);
                    album.setMedImageUrl(medImage);
                    record.setAlbum(album);
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

    public static List<TopArtistRecord> getTopArtistRecordsFromMethodCall(BufferedReader in)
    {
        String inputString = null;
        List<TopArtistRecord> searchResults = new ArrayList<>();

        boolean startTag = false;
        boolean stopTag = false;
        String artistName = "";
        int rank = 0;
        int playCount = 0;
        String smallImage = "";
        String medImage = "";
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
                    if (inputString.contains("<image size="))
                    {
                        if (inputString.contains("size=\"small\""))
                            smallImage = inputString.substring(inputString.indexOf("size=\"small\"") + 13, inputString.indexOf("</image>"));
                        if (inputString.contains("size=\"medium\""))
                            medImage = inputString.substring(inputString.indexOf("size=\"medium\"") + 14, inputString.indexOf("</image>"));
                    }
                }

                if (stopTag)
                {
                    musicArtist.setArtistName(artistName);
                    musicArtist.setSmallImageUrl(smallImage);
                    musicArtist.setMedImageUrl(medImage);
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

    public static List<TopAlbumRecord> searchForTopAlbums(HttpServletRequest request, String userName, String selectedTimePeriod)
    {
        List<TopAlbumRecord> topAlbumRecords = new ArrayList<>();

        String URLAddress = "http://ws.audioscrobbler.com/2.0/?method=user.gettopalbums&user=" + userName + "&api_key=c349ab1fcb6b132ffb8d842e982458db&limit=250&format=xml&period=" + selectedTimePeriod;
        try
        {
            URL url = null;
            url = new URL(URLAddress);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = null;
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            topAlbumRecords = getTopAlbumRecordsFromMethodCall(in);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return topAlbumRecords;
    }

    public static List<TopSongRecord> searchForTopSongs(HttpServletRequest request, String userName, String selectedTimePeriod)
    {
        List<TopSongRecord> topSongRecords = new ArrayList<>();

        String URLAddress = "http://ws.audioscrobbler.com/2.0/?method=user.gettoptracks&user=" + userName + "&api_key=c349ab1fcb6b132ffb8d842e982458db&limit=250&format=xml&period=" + selectedTimePeriod;
        try
        {
            URL url = null;
            url = new URL(URLAddress);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = null;
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            topSongRecords = getTopSongRecordsFromMethodCall(in);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return topSongRecords;
    }

    public static List<TopSongRecord> getTopSongRecordsFromMethodCall(BufferedReader in)
    {
        String inputString = null;
        List<TopSongRecord> searchResults = new ArrayList<>();

        boolean startTag = false;
        boolean stopTag = false;

        boolean startArtistTag = false;
        boolean stopArtistTag = false;

        String artistName = "";
        String songName = "";
        String smallImage = "";
        String medImage = "";
        int rank = 0;
        int playCount = 0;

        Song song = new Song();
        TopSongRecord record = new TopSongRecord();

        try
        {
            while ((inputString = in.readLine()) != null)
            {
                if (inputString.contains("<track"))
                {
                    startTag = true;
                    stopTag = false;
                }
                if (inputString.contains("</track>"))
                {
                    startTag = false;
                    stopTag = true;
                }
                if (inputString.contains("<artist>"))
                {
                    startArtistTag = true;
                    stopArtistTag = false;
                }
                if (inputString.contains("</artist>"))
                {
                    startArtistTag = false;
                    stopArtistTag = true;
                }

                if (startTag)
                {
                    song = new Song();
                    record = new TopSongRecord();

                    if (inputString.contains("rank="))
                    {
                        int startOfRankNumber = inputString.indexOf("rank=\"") + 6;
                        String rankString = inputString.substring(startOfRankNumber);
                        rankString.replace("\"", "").trim();

                        rank = Integer.valueOf(rankString.substring(0, rankString.indexOf(">")-1));
                    }
                    if (inputString.contains("<name>") && inputString.contains("</name>") && !startArtistTag)
                    {
                        songName = inputString.substring(inputString.indexOf("<name>") + 6, inputString.indexOf("</name"));

                    }
                    if (inputString.contains("<name>") && inputString.contains("</name>") && startArtistTag)
                    {
                        artistName = inputString.substring(inputString.indexOf("<name>") + 6, inputString.indexOf("</name"));
                    }
                    if (inputString.contains("<playcount>") && inputString.contains("</playcount>"))
                    {
                        playCount = Integer.valueOf(inputString.substring(inputString.indexOf("<playcount>") + 11, inputString.indexOf("</playcount>")));
                    }
                    if (inputString.contains("<image size="))
                    {
                        if (inputString.contains("size=\"small\""))
                            smallImage = inputString.substring(inputString.indexOf("size=\"small\"") + 13, inputString.indexOf("</image>"));
                        if (inputString.contains("size=\"medium\""))
                            medImage = inputString.substring(inputString.indexOf("size=\"medium\"") + 14, inputString.indexOf("</image>"));
                    }
                }

                if (stopTag)
                {
                    song.setArtist(artistName);
                    song.setTitle(songName);
                    song.setSmallImageUrl(smallImage);
                    song.setMedImageUrl(medImage);
                    record.setSong(song);
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
//        String urll = "";??
//        String imageUrl = "";
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

//                    if (inputString.contains("<url>") && inputString.contains("</url>"))
//                    {
//                        urll = inputString.substring(inputString.indexOf("<url>") + 5, inputString.indexOf("</url>"));
//                            musicArtist.setUrl(urll);
//                    }
//
//                    if (inputString.contains("<image>") && inputString.contains("</image>"))
//                    {
//                        imageUrl = inputString.substring(inputString.indexOf("<image>") + 5, inputString.indexOf("</image>"));
//                            musicArtist.setImageURL(imageUrl);
//                    }
                }

                if (stopArtistTag)
                {
                    musicArtist.setArtistName(artistName);
//                    musicArtist.setUrl(urll);
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

    public static int getLargestPrimeNumberUnderAMillion()
    {
        int answer = 0;
        for (int i = 1; i <=200000; i++)
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
        long bigNum = 1008514756L;
        for (long i = 2; i < bigNum; i++)
            if (bigNum % i == 0 && isPrime(i))
                if (i > answer)
                    answer = i;
        return answer;
    }

    public static int doRaceCondition()
    {
        int[] answer = new int[1];
        ReentrantLock lock = new ReentrantLock();
        answer[0] = 1;

        Thread t1 = new Thread(() ->
        {
            lock.lock();
            try
            {
                for (int i = 1; i <= 1000000; i++)
                    answer[0] = answer[0] + 1;
            }
            finally
            {
                lock.unlock();
            }

        });

        Thread t2 = new Thread(() ->
        {
            lock.lock();
            try
            {
                for (int i = 0; i < 1000000; i++)
                    answer[0] = answer[0] + 1;
            }
            finally
            {
                lock.unlock();
            }
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
