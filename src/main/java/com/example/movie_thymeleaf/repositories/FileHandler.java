package com.example.movie_thymeleaf.repositories;

import com.example.movie_thymeleaf.models.Movie;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class FileHandler
{
    private final File movieData = new File("src/main/resources/static/imdb-data.csv");
    private final ArrayList<Movie> listOfMovies = new ArrayList<>();

    public FileHandler()
    {
    }

    // Try to load the movie data from file @GH
    public ArrayList<Movie> loadMovies()
    {
        int year;
        int length;
        String title;
        String subject;
        int popularity;
        boolean awards;
        String line;

        try
        {
            Scanner input = new Scanner(movieData);
            input.nextLine(); // Step over meta data
            while (input.hasNext())
            {
                line = input.nextLine();
                String[] lineData = line.split(";");
                year = Integer.parseInt(lineData[0]);
                length = Integer.parseInt(lineData[1]);
                title = lineData[2];
                subject = lineData[3];
                popularity = Integer.parseInt(lineData[4]);
                awards = (Objects.equals(lineData[5].toLowerCase(), "yes"));
                Movie movie = new Movie(year, length, title, subject, popularity, awards);
                listOfMovies.add(movie);
            }
            System.out.println("Movie data loaded successfully.");
            System.out.println("For " + listOfMovies.size() + " movies.");
        } catch (FileNotFoundException e)
        {
            System.out.println(movieData + " not found.");
        }
        return listOfMovies;
    }
}
