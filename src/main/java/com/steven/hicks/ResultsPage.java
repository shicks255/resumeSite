package com.steven.hicks;

import java.util.ArrayList;
import java.util.List;

public class ResultsPage<T>
{
    private List<T> results;
    private int pageNumber;
    private int resultsPerPage;
    private boolean lastPage;

    public ResultsPage()
    {
        results = new ArrayList<>();
        resultsPerPage = 10;
    }

    public List<T> getResults()
    {
        return results;
    }

    public void setResults(List<T> results)
    {
        this.results = results;
    }

    public int getPageNumber()
    {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber)
    {
        this.pageNumber = pageNumber;
    }

    public int getResultsPerPage()
    {
        return resultsPerPage;
    }

    public void setResultsPerPage(int resultsPerPage)
    {
        this.resultsPerPage = resultsPerPage;
    }

    public boolean isLastPage()
    {
        return lastPage;
    }

    public void setLastPage(boolean lastPage)
    {
        this.lastPage = lastPage;
    }
}
