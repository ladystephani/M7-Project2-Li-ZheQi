package com.company.musiccatalog.controller;

import com.company.musiccatalog.model.Album;
import com.company.musiccatalog.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/album")
public class AlbumController {
    @Autowired
    CatalogService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Album createAlbum(@RequestBody @Valid Album album) {
        album = service.createAlbum(album);
        return album;
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Album> getAllAlbums() {
        List<Album> albumList = service.getAllAlbums();
        return albumList;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Album getAlbumById(@PathVariable("id") Integer id) {
        Album album = service.getAlbum(id);
        if (album == null) {
            throw new IllegalArgumentException("Album could not be retrieved for id " + id);
        } else {
            return album;
        }
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateAlbum(@RequestBody @Valid Album album) {
        if (album==null || album.getId()<1){
            throw new IllegalArgumentException("Check id in model");
        } else if (album.getId() >0) {
            service.updateAlbum(album);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAlbum(@PathVariable("id") Integer id) { service.deleteAlbum(id);}
}
