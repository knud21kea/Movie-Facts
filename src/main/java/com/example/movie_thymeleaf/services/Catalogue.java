package com.example.movie_thymeleaf.services;

import com.example.movie_thymeleaf.models.Movie;
import com.example.movie_thymeleaf.repositories.FileHandler;

import java.util.*;


// Service to maintain and interrogate a list of Movie objects @GH
// The list is loaded from CSV file
// Implements a Comparator class to sort by popularity field
public class Catalogue
{
    private final ArrayList<Movie> listOfMovies;
    private final Random rand = new Random();

    public Catalogue()
    {
        FileHandler fh = new FileHandler();
        listOfMovies = fh.loadMovies();
    }

    public int getSize()
    {
        return listOfMovies.size();
    }

    public String getFirstMovieName()
    {
        return listOfMovies.get(0).getTitle();
    }

    public String getRandomMovieName()
    {
        return listOfMovies.get(rand.nextInt(listOfMovies.size())).getTitle();
    }

    public ArrayList<Movie> getTenRandomMoviesSortedByPopularity()
    {
        Set<Movie> tenUniqueMovies = new HashSet<>();
        while (tenUniqueMovies.size() < 10)
        {
            Movie currentMovie = listOfMovies.get(rand.nextInt(listOfMovies.size()));
            tenUniqueMovies.add(currentMovie);
        }
        ArrayList<Movie> tenMovies = new ArrayList<>(tenUniqueMovies); // now contains unique movies
        tenMovies.sort(new SortByPopularity()); // implements Comparator
        return tenMovies;
    }

    public int countMoviesWithAwards()
    {
        int count = 0;
        for (Movie m : listOfMovies)
        {
            if (m.hasAwards())
            {
                count++;
            }
        }
        return count; // could also be done with hashmap
    }

    public ArrayList<String> getMatchingTitles(char character, int amount)
    {
        ArrayList<String> list = new ArrayList<>();
        int count;
        String title;
        for (Movie m : listOfMovies)
        {
            count = 0;
            title = m.getTitle();
            for (int i = 0; i < title.length(); i++)
            {
                if (title.charAt(i) == character)
                {
                    count++;
                }
            }
            if (count == amount)
            {
                list.add(title);
            }
        }
        return list;
    }

    public Set<String> getAllGenres()
    {
        Set<String> setOfSubjects = new HashSet<>();
        for (Movie m : listOfMovies)
        {
            setOfSubjects.add(m.getSubject());
        }
        return setOfSubjects;
    }

    public int averageRuntime(String genre)
    {
        int runTime = 0;
        int count = 0;
        for (Movie m : listOfMovies)
        {
            if (Objects.equals(m.getSubject(), genre))
            {
                runTime += m.getLength();
                count++;
            }
        }
        if (count != 0)
        {
            runTime = runTime / count; // average runtime if not null
        }
        return runTime;
    }

    class SortByPopularity implements Comparator<Movie>
    {
        public int compare(Movie a, Movie b)
        {
            return (a.getPopularity() - b.getPopularity());
        }
    }
}
