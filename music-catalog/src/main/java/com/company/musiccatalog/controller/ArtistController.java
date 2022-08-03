package com.company.musiccatalog.controller;

import com.company.musiccatalog.model.Artist;
import com.company.musiccatalog.service.CatalogService;
import com.company.musiccatalog.viewModel.ArtistViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/artist")
public class ArtistController {
    @Autowired
    CatalogService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ArtistViewModel createArtist(@RequestBody @Valid ArtistViewModel artistViewModel) {
        artistViewModel = service.createArtist(artistViewModel);
        return artistViewModel;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ArtistViewModel> getAllArtists() {
        List<ArtistViewModel> artistViewModelList = service.getAllArtists();
        return artistViewModelList;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ArtistViewModel getArtistById(@PathVariable("id") Integer id) {
        ArtistViewModel artistViewModel = service.getArtist(id);
        if (artistViewModel == null) {
            throw new IllegalArgumentException("Artist could not be retrieved for id " + id);
        } else {
            return artistViewModel;
        }
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateArtist(@RequestBody @Valid ArtistViewModel artistViewModel) {
        if (artistViewModel == null || artistViewModel.getId()<1) {
            throw new IllegalArgumentException("Check id in view model");
        } else if (artistViewModel.getId() > 0) {
            service.updateArtist(artistViewModel);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteArtist(@PathVariable("id") Integer id) {service.deleteArtist(id);}
}
