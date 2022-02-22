package com.example.movie_thymeleaf.controllers;

import com.example.movie_thymeleaf.models.Movie;
import com.example.movie_thymeleaf.services.Catalogue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Set;

@Controller
public class HomeController
{
    Catalogue cat = new Catalogue();
    @GetMapping("/")
    public String welcome()
    {
        return "index";
    }

    @GetMapping("/first")
    public String getFirst(Model model)
    {
        model.addAttribute("firstTitle",cat.getFirstMovieName());
        return "first";
    }

    @GetMapping("/random")
    public String getRandom(Model model)
    {
        String random = cat.getRandomMovieName();
        model.addAttribute("randomTitle",random);
        return "random";
    }

    @RequestMapping("/ten")
    public String getTenRandom(Model model)
    {
        ArrayList<Movie> tenMovies = cat.getTenRandomMoviesSortedByPopularity();
        model.addAttribute("sortedMovies",tenMovies);
        return "10sorted";
    }

    @GetMapping("/awards")
    public String howManyAwards(Model model)
    {
        int received = cat.countMoviesWithAwards();
        int total = cat.getSize();
        model.addAttribute("awards",received);
        model.addAttribute("movies",total);
        model.addAttribute("percent",100*received/total);
        return "awards";
    }

    @GetMapping("/filter") // can't seem to use char as parameter name
    public String filterMovies(@RequestParam char character, int amount, Model model)
    {
        ArrayList<String> listOfMatchingTitles = cat.getMatchingTitles(character, amount);
        model.addAttribute("titles",listOfMatchingTitles);
        return "filter";
    }

    @GetMapping("/longest")
    public String compareRuntimes(@RequestParam String g1, String g2, Model model)
    {
        int running1 = cat.averageRuntime(g1);
        int running2 = cat.averageRuntime(g2);
        String longest = g1;
        if (running2 > running1)
        {
            longest = g2;
        }
        Set<String> setOfSubjects = cat.getAllGenres();
        model.addAttribute("genre1",g1);
        model.addAttribute("genre2",g2);
        model.addAttribute("genre3",longest);
        model.addAttribute("runtime1",running1);
        model.addAttribute("runtime2",running2);
        model.addAttribute("subjects",setOfSubjects);
        return "longest";
    }
}
