package com.company.musiccatalog.controller;

import com.company.musiccatalog.model.Track;
import com.company.musiccatalog.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/track")
public class TrackController {
    @Autowired
    CatalogService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Track createTrack(@RequestBody @Valid Track track) {
        track = service.createTrack(track);
        return track;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Track> getAllTracks() {
        List<Track> trackList = service.getAllTracks();
        return trackList;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Track getTrackById(@PathVariable("id") Integer id) {
        Track track = service.getTrack(id);
        if (track == null) {
            throw new IllegalArgumentException("Track could not be retrieved for id " + id);
        } else {
            return track;
        }
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTrack(@RequestBody @Valid Track track) {
        if (track == null || track.getId() <1) {
            throw new IllegalArgumentException("Check id in model");
        } else if (track.getId() > 0) {
            service.updateTrack(track);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTrack(@PathVariable("id") Integer id) {service.deleteTrack(id);}
}
