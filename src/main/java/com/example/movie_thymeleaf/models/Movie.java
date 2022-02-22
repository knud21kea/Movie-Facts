package com.example.movie_thymeleaf.models;

public class Movie
{
    private final int year;
    private final int length;
    private final String title;
    private final String subject;
    private final int popularity;
    private final boolean awards;

    public Movie(int year, int length, String title, String subject, int popularity, boolean awards)
    {
        this.year = year;
        this.length = length;
        this.title = title;
        this.subject = subject;
        this.popularity = popularity;
        this.awards = awards;
    }

    public int getLength()
    {
        return length;
    }

    public String getTitle()
    {
        return title;
    }

    public String getSubject()
    {
        return subject;
    }

    public int getPopularity()
    {
        return popularity;
    }

    public boolean hasAwards()
    {
        return awards;
    }

    @Override
    public String toString()
    {
        String award = awards ? "Yes" : "No";
        return title + ", " + year + ", " + length + ", " + subject + ", " + popularity + ", " + award;
    }
}
