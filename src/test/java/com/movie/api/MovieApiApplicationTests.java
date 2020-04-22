package com.movie.api;

import com.movie.api.model.Movie;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MovieApiApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getAllMoviesReturns() {
        ResponseEntity<Movie[]> response = restTemplate.getForEntity(getServerUrl(), Movie[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotEquals(0, response.getBody().length);
    }

    @Test
    public void getMovieByIdReturns() {
        ResponseEntity<Movie> response = restTemplate.getForEntity(getServerUrl() + "/1", Movie.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    public void getNotFoundMovieByIdThrows() {
        ResponseEntity<Movie> response = restTemplate.getForEntity(getServerUrl() + "/100", Movie.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void addMovieReturns() {
        Movie movie = Movie.builder()
                .title("Contagion")
                .category("Science")
                .rating(3)
                .build();

        ResponseEntity<String> response = restTemplate.postForEntity(getServerUrl(), movie, String.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

    }

    @Test
    public void addMovieWithoutTitleThrows() {
        Movie movie = Movie.builder()
                .category("Science")
                .rating(3)
                .build();

        ResponseEntity<String> response = restTemplate.postForEntity(getServerUrl(), movie, String.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void updateMovieReturns() {
        Movie movie = Movie.builder()
                .id(1)
                .title("Brave Heart")
                .category("Action")
                .rating(5.0)
                .build();

        restTemplate.put(getServerUrl(), movie);
        ResponseEntity<Movie> response = restTemplate.getForEntity(getServerUrl() + "/1", Movie.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(5, response.getBody().getRating(), 0);
    }


    @Test
    public void deleteMovieByIdReturns() {
        restTemplate.delete(getServerUrl() + "/1");
        ResponseEntity<Movie> response = restTemplate.getForEntity(getServerUrl() + "/1", Movie.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    private String getServerUrl() {
        return "http://localhost:" + port + "/api/v1/movies";
    }


}
