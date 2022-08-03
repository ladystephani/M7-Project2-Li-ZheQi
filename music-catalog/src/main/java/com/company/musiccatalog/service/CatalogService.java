package com.company.musiccatalog.service;

import com.company.musiccatalog.model.Artist;
import com.company.musiccatalog.repository.ArtistRepository;
import com.company.musiccatalog.viewModel.ArtistViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CatalogService {
    ArtistRepository artistRepo;

    @Autowired
    public CatalogService(
            ArtistRepository artistRepository
    ) {
        this.artistRepo = artistRepository;
    }

    //Artist
    public ArtistViewModel createArtist(ArtistViewModel artistViewModel) {
        // Validate incoming Game Data in the view model.
        // All validations were done using JSR303
        if (artistViewModel==null) throw new IllegalArgumentException("No artist is passed! Artist object is null!");

        Artist artist = new Artist();
        artist.setName(artistViewModel.getName());
        artist.setInstagram(artistViewModel.getInstagram());
        artist.setTwitter(artistViewModel.getTwitter());

        artistViewModel.setId(artistRepo.save(artist).getId());
        return artistViewModel;
    }

}
