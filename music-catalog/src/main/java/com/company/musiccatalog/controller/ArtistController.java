package com.company.musiccatalog.controller;

import com.company.musiccatalog.model.Artist;
import com.company.musiccatalog.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/artist")
public class ArtistController {
    @Autowired
    CatalogService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Artist createArtist(@RequestBody @Valid Artist artist) {
        artist = service.createArtist(artist);
        return artist;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Artist> getAllArtists() {
        List<Artist> artistList = service.getAllArtists();
        return artistList;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Artist getArtistById(@PathVariable("id") Integer id) {
        Artist artist = service.getArtist(id);
        if (artist == null) {
            throw new IllegalArgumentException("Artist could not be retrieved for id " + id);
        } else {
            return artist;
        }
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateArtist(@RequestBody @Valid Artist artist) {
        if (artist == null || artist.getId()<1) {
            throw new IllegalArgumentException("Check id in model");
        } else if (artist.getId() > 0) {
            service.updateArtist(artist);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteArtist(@PathVariable("id") Integer id) {service.deleteArtist(id);}
}
