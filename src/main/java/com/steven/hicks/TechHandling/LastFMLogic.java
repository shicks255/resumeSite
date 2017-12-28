package com.steven.hicks.TechHandling;

import com.steven.hicks.entities.*;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public class LastFMLogic
{
    public static List<Album> searchForAlbums(String albumSearch) throws Exception
    {
        List<Album> searchResults;

        albumSearch = albumSearch.replace(" ", "%20");

        String URLAddress = "http://ws.audioscrobbler.com/2.0/?method=album.search&album=" + albumSearch + "&api_key=c349ab1fcb6b132ffb8d842e982458db&page=1&limit=10&format=xml";

        URL url = new URL(URLAddress);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        searchResults = parseAlbumFromXML(in);
        in.close();

        return searchResults;
    }

    private static List<Album> parseAlbumFromXML(BufferedReader in)
    {
        List<Album> searchResults = new ArrayList<>();
        String inputString;
        boolean startAlbumTag = false;
        boolean stopAlbumTag = false;
        String artistName = "";
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
                    stopAlbumTag = false;
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

    public static List<TopArtistRecord> searchForTopArtists(String userName, String selectedTimePeriod)
    {
        List<TopArtistRecord> topArtistRecords = new ArrayList<>();

        String URLAddress = "http://ws.audioscrobbler.com/2.0/?method=user.gettopartists&user=" + userName + "&api_key=c349ab1fcb6b132ffb8d842e982458db&limit=250&format=xml&period=" + selectedTimePeriod;
        try
        {
            URL url = new URL(URLAddress);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            topArtistRecords = parseTopArtistRecordsFromXML(in);
            in.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return topArtistRecords;
    }
    private static List<TopArtistRecord> parseTopArtistRecordsFromXML(BufferedReader in)
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
                    stopTag = false;
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return searchResults;
    }

    public static List<TopAlbumRecord> searchForTopAlbums(String userName, String selectedTimePeriod)
    {
        List<TopAlbumRecord> topAlbumRecords = new ArrayList<>();

        String URLAddress = "http://ws.audioscrobbler.com/2.0/?method=user.gettopalbums&user=" + userName + "&api_key=c349ab1fcb6b132ffb8d842e982458db&limit=250&format=xml&period=" + selectedTimePeriod;
        try
        {
            URL url = new URL(URLAddress);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            topAlbumRecords = parseTopAlbumRecordsFromXML(in);
            in.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return topAlbumRecords;
    }
    private static List<TopAlbumRecord> parseTopAlbumRecordsFromXML(BufferedReader in)
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
                    stopTag = false;
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return searchResults;
    }

    public static List<TopSongRecord> searchForTopSongs(String userName, String selectedTimePeriod)
    {
        List<TopSongRecord> topSongRecords = new ArrayList<>();

        String URLAddress = "http://ws.audioscrobbler.com/2.0/?method=user.gettoptracks&user=" + userName + "&api_key=c349ab1fcb6b132ffb8d842e982458db&limit=250&format=xml&period=" + selectedTimePeriod;
        try
        {
            URL url = new URL(URLAddress);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            topSongRecords = parseTopSongRecordsFromXML(in);
            in.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return topSongRecords;
    }
    private static List<TopSongRecord> parseTopSongRecordsFromXML(BufferedReader in)
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
                    stopTag = false;
                }
            }
        }
        catch (IOException e)
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

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        musicArtists = parseSearchForArtistsXML(in);
        in.close();

        return musicArtists;
    }
    private static List<MusicArtist> parseSearchForArtistsXML(BufferedReader in)
    {
        String inputString = null;
        List<MusicArtist> searchResults = new ArrayList<>();

        boolean startArtistTag = false;
        boolean stopArtistTag = false;
        String artistName = "";
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
                        artistName = inputString.substring(inputString.indexOf("<name>") + 6, inputString.indexOf("</name>"));
                }

                if (stopArtistTag)
                {
                    musicArtist.setArtistName(artistName);
                    searchResults.add(musicArtist);
                    stopArtistTag = false;
                }
            }
            in.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return searchResults;
    }

    public static ArrayList<MusicArtist> getMusicArtistsFromLast_FM()
    {
        String URLAddress = "http://ws.audioscrobbler.com/2.0/?method=user.getTopArtists&user=test&api_key=c349ab1fcb6b132ffb8d842e982458db&limit=10&format=xml&callback=?";
        String inputString = null;
        List<MusicArtist> musicArtists = new ArrayList<>();

        try
        {
            URL url = new URL(URLAddress);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String artistName;
            while ((inputString = in.readLine()) != null)
            {
                if (inputString.contains("<name>") && inputString.contains("</name>"))
                {
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
}
