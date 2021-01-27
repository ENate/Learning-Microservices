package io.minejava.ratingsdataservice.resources;

import io.minejava.ratingsdataservice.models.Rating;
import io.minejava.ratingsdataservice.models.UserRating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("ratingsdata")
public class RatingsResource {

    @RequestMapping("/{movieId}")
    public Rating getRating(@PathVariable("movieId") String movieId){
        return new Rating("Frame of Mind", 6);
    }
    // userId controller API to return list of all movies
    @RequestMapping("users/{userId}")
    // Problem with objects and deserialization
    // If I want to add a field late with user full name: A List is a bad object
    // When enhancement if needed for a global contract, code might break!
    // We need to resort to object and extending will still be backward compatible

    /*public List<Rating> getUserRating(@PathVariable("userId") String userId){
        List<Rating> ratings = Arrays.asList(
                new Rating("1234", 4),
                new Rating("5678", 23));
        return ratings;
    };
     */
    public UserRating getUserRating(@PathVariable("userId") String userId){
        List<Rating> ratings = Arrays.asList(
                new Rating("1234", 4),
                new Rating("5678", 23));
        UserRating userRating = new UserRating();
        userRating.setUserRating(ratings);
        return userRating;
    }
}
