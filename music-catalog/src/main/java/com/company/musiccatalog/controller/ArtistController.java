package com.company.musiccatalog.controller;

import com.company.musiccatalog.service.CatalogService;
import com.company.musiccatalog.viewModel.ArtistViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/artist")
public class ArtistController {
    @Autowired
    CatalogService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ArtistViewModel createArtist(@RequestBody ArtistViewModel artistViewModel) {
        artistViewModel = service.createArtist(artistViewModel);
        return artistViewModel;
    }

}
