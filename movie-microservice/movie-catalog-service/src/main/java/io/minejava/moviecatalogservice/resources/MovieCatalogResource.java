package io.minejava.moviecatalogservice.resources;

import io.minejava.moviecatalogservice.models.CatalogItem;
import io.minejava.moviecatalogservice.models.Movie;
import io.minejava.moviecatalogservice.models.Rating;
import io.minejava.moviecatalogservice.models.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
    // To use bean, create property of same type
    @Autowired // tells bean to inject bean of type rest
    private RestTemplate restTemplate;

    /*@Autowired
    private WebClient.Builder webClientBuilder;
     */
    // singleton class used accross several calls


    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable String userId){
        // Define rest and pass url which can populate a created property class using its payload
        // create a bean and have only one instance for calls. Move this to the main. Use at bean annotation
        // RestTemplate restTemplate = new RestTemplate();

        // webClient.Builder builder = new webClient.Builder();
        //get all rated movie IDs
        UserRating ratings = restTemplate.getForObject("http://localhost:8083/ratingsdata/users/" + userId, UserRating.class);
        /* List<Rating> ratings =
                Arrays.asList(
               new Rating("1234", 4),
               new Rating("5678", 23)
        );
         */
        return ratings.getUserRating().stream().map(rating -> {
            // Deprecated
            // For each movie ID, call movie info service and get details
            Movie movie = restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId(), Movie.class);
            // Pt all them together
            return new CatalogItem(movie.getName(), "This entailes robbery movie", rating.getRating());
    }).collect(Collectors.toList());
    }
}
/* Movie movie = webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8082/movies/" + rating.getRating())
                    .retrieve()
                    .bodyToMono(Movie.class)
                    .block(); // blocking execution until return mono: movie object
             */
