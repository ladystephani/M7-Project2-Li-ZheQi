package com.company.musiccatalog.service;

import com.company.musiccatalog.model.Album;
import com.company.musiccatalog.model.Artist;
import com.company.musiccatalog.model.Label;
import com.company.musiccatalog.model.Track;
import com.company.musiccatalog.repository.AlbumRepository;
import com.company.musiccatalog.repository.ArtistRepository;
import com.company.musiccatalog.repository.LabelRepository;
import com.company.musiccatalog.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CatalogService {
    ArtistRepository artistRepo;
    LabelRepository labelRepo;
    AlbumRepository albumRepo;
    TrackRepository trackRepo;
    @Autowired
    public CatalogService(
            ArtistRepository artistRepository,
            LabelRepository labelRepository,
            AlbumRepository albumRepository,
            TrackRepository trackRepository
    ) {
        this.artistRepo = artistRepository;
        this.labelRepo = labelRepository;
        this.albumRepo = albumRepository;
        this.trackRepo = trackRepository;
    }

    //Artist
    public Artist createArtist(Artist artist) {
        // Validate incoming Data in the view model.
        // All validations were done using JSR303
        if (artist==null) throw new IllegalArgumentException("No artist is passed! Artist object is null!");

        artist.setName(artist.getName());
        artist.setInstagram(artist.getInstagram());
        artist.setTwitter(artist.getTwitter());

        artist.setId(artistRepo.save(artist).getId());
        return artist;
    }

    public List<Artist> getAllArtists() {
        //take already seeded artists from database
        List<Artist> artistList = artistRepo.findAll();
        List<Artist> artistArrList = new ArrayList<>();

        if (artistList == null) {
            return null;
        } else {
            //place each artist in vm
            artistList.stream().forEach(artist -> artistArrList.add(buildArtist(artist)));
        }
        return artistArrList;
    }

    public Artist getArtist(Integer id) {
        Optional<Artist> artist = artistRepo.findById(id);
        if (artist == null) {
            return null;
        } else {
            return buildArtist(artist.get());
        }
    }

    public void updateArtist(Artist artist) {
        //validate data
        if (artist==null) throw new IllegalArgumentException("No artist is passed! Artist object is null!");

        //make sure artist exists. If not, throw exception...
        if (this.getArtist(artist.getId()) == null) throw new IllegalArgumentException("No such artist to update");

        artist.setId(artist.getId());
        artist.setName(artist.getName());
        artist.setInstagram(artist.getInstagram());
        artist.setTwitter(artist.getTwitter());

        artistRepo.save(artist);
    }


    public void deleteArtist(Integer id) {artistRepo.deleteById(id);}

    //label
    public Label createLabel(Label label) {
        // Validate incoming Data in the view model.
        // All validations were done using JSR303
        if (label==null) throw new IllegalArgumentException("No label is passed! Label object is null!");


        label.setName(label.getName());
        label.setWebsite(label.getWebsite());

        label.setId(labelRepo.save(label).getId());
        return label;
    }


    public List<Label> getAllLabels() {
        List<Label> labelList = labelRepo.findAll();
        List<Label> labelArrList = new ArrayList<>();

        if (labelList == null) {
            return null;
        } else {
            labelList.stream().forEach(label -> labelArrList.add(buildLabel(label)));
        }
        return labelArrList;
    }

    public Label getLabel(Integer id) {
        Optional<Label> label = labelRepo.findById(id);
        if (label == null) {
            return null;
        } else {
            return buildLabel(label.get());
        }
    }

    public void updateLabel(Label label) {
        //validate data
        if (label==null) throw new IllegalArgumentException("No label is passed! ");

        //make sure artist exists. If not, throw exception...
        if (this.getLabel(label.getId()) == null) throw new IllegalArgumentException("No such label to update");

        label.setId(label.getId());
        label.setName(label.getName());
        label.setWebsite(label.getWebsite());

        labelRepo.save(label);
    }

    public void deleteLabel(Integer id) {labelRepo.deleteById(id);}

    //album
    public Album createAlbum(Album album) {
        // Validate incoming Data in the view model.
        // All validations were done using JSR303
        if (album==null) throw new IllegalArgumentException("No album is passed! ");

        album.setTitle(album.getTitle());
        album.setArtistId(album.getArtistId());
        album.setReleaseDate(album.getReleaseDate());
        album.setLabelId(album.getLabelId());
        album.setListPrice(album.getListPrice());

        album.setId(albumRepo.save(album).getId());

        return album;
    }

    public List<Album> getAllAlbums() {
        List<Album> albumList = albumRepo.findAll();
        List<Album> albumArrList = new ArrayList<>();

        if (albumList == null) {
            return null;
        } else {
            albumList.stream().forEach(album -> albumArrList.add(buildAlbum(album)));
        }
        return albumArrList;
    }

    public Album getAlbum(Integer id) {
        Optional<Album> album = albumRepo.findById(id);
        if (album == null) {
            return null;
        } else {
            return buildAlbum(album.get());
        }
    }

    public void updateAlbum(Album album) {
        //validate data
        if (album==null) throw new IllegalArgumentException("No album is passed! ");

        //make sure artist exists. If not, throw exception...
        if (this.getAlbum(album.getId()) == null) throw new IllegalArgumentException("No such album to update");

        album.setId(album.getId());
        album.setTitle(album.getTitle());
        album.setArtistId(album.getArtistId());
        album.setReleaseDate(album.getReleaseDate());
        album.setLabelId(album.getLabelId());
        album.setListPrice(album.getListPrice());

        albumRepo.save(album);
    }


    public void deleteAlbum(Integer id) {albumRepo.deleteById(id);}

    //track
    public Track createTrack(Track track) {
        // Validate incoming Data in the view model.
        // All validations were done using JSR303
        if (track==null) throw new IllegalArgumentException("No track is passed! ");

        track.setAlbumId(track.getAlbumId());
        track.setTitle(track.getTitle());
        track.setRunTime(track.getRunTime());

        track.setId(trackRepo.save(track).getId());
        return track;
    }

    public List<Track> getAllTracks() {
        List<Track> trackList = trackRepo.findAll();
        List<Track> trackArrList = new ArrayList<>();

        if (trackList == null) {
            return null;
        } else {
            trackList.stream().forEach(track -> trackArrList.add(buildTrack(track)));
        }
        return trackArrList;
    }

    public Track getTrack(Integer id) {
        Optional<Track> track = trackRepo.findById(id);
        if (track == null) {
            return null;
        } else {
            return buildTrack(track.get());
        }
    }

    public void updateTrack(Track track) {
        //validate data
        if (track==null) throw new IllegalArgumentException("No track is passed! ");

        //make sure artist exists. If not, throw exception...
        if (this.getTrack(track.getId()) == null) throw new IllegalArgumentException("No such track to update");

        track.setId(track.getId());
        track.setAlbumId(track.getAlbumId());
        track.setTitle(track.getTitle());
        track.setRunTime(track.getRunTime());

        trackRepo.save(track);
    }

    public void deleteTrack(Integer id) {trackRepo.deleteById(id);}

    //helper methods
    public Artist buildArtist(Artist artist) {

        artist.setId(artist.getId());
        artist.setName(artist.getName());
        artist.setInstagram(artist.getInstagram());
        artist.setTwitter(artist.getTwitter());
        return artist;
    }

    public Label buildLabel(Label label) {
        ;
        label.setId(label.getId());
        label.setName(label.getName());
        label.setWebsite(label.getWebsite());
        return label;
    }

    public Album buildAlbum(Album album) {
        album.setId(album.getId());
        album.setTitle(album.getTitle());
        album.setArtistId(album.getArtistId());
        album.setReleaseDate(album.getReleaseDate());
        album.setLabelId(album.getLabelId());
        album.setListPrice(album.getListPrice());
        return album;
    }

    public Track buildTrack(Track track) {
        track.setId(track.getId());
        track.setAlbumId(track.getAlbumId());
        track.setTitle(track.getTitle());
        track.setRunTime(track.getRunTime());
        return track;
    }

}
