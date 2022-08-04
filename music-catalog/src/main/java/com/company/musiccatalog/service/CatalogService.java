package com.company.musiccatalog.service;

import com.company.musiccatalog.model.Artist;
import com.company.musiccatalog.model.Label;
import com.company.musiccatalog.repository.ArtistRepository;
import com.company.musiccatalog.repository.LabelRepository;
import com.company.musiccatalog.viewModel.ArtistViewModel;
import com.company.musiccatalog.viewModel.LabelViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CatalogService {
    ArtistRepository artistRepo;
    LabelRepository labelRepo;

    @Autowired
    public CatalogService(
            ArtistRepository artistRepository,
            LabelRepository labelRepository
    ) {
        this.artistRepo = artistRepository;
        this.labelRepo = labelRepository;
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

    public List<ArtistViewModel> getAllArtists() {
        //take already seeded artists from database
        List<Artist> artistList = artistRepo.findAll();
        List<ArtistViewModel> artistViewModelList = new ArrayList<>();

        if (artistList == null) {
            return null;
        } else {
            //place each artist in vm
            artistList.stream().forEach(artist -> artistViewModelList.add(buildArtistViewModel(artist)));
        }
        return artistViewModelList;
    }

    public ArtistViewModel getArtist(Integer id) {
        Optional<Artist> artist = artistRepo.findById(id);
        if (artist == null) {
            return null;
        } else {
            return buildArtistViewModel(artist.get());
        }
    }

    public void updateArtist(ArtistViewModel artistViewModel) {
        //validate data
        if (artistViewModel==null) throw new IllegalArgumentException("No artist is passed! Artist object is null!");

        //make sure artist exists. If not, throw exception...
        if (this.getArtist(artistViewModel.getId()) == null) throw new IllegalArgumentException("No such artist to update");

        Artist artist = new Artist();
        artist.setId(artistViewModel.getId());
        artist.setName(artistViewModel.getName());
        artist.setInstagram(artistViewModel.getInstagram());
        artist.setTwitter(artistViewModel.getTwitter());

        artistRepo.save(artist);
    }


    public void deleteArtist(Integer id) {artistRepo.deleteById(id);}

    //label
    public LabelViewModel createLabel(LabelViewModel labelViewModel) {
        // Validate incoming Game Data in the view model.
        // All validations were done using JSR303
        if (labelViewModel==null) throw new IllegalArgumentException("No label is passed! Label object is null!");

        Label label = new Label();
        label.setName(labelViewModel.getName());
        label.setWebsite(labelViewModel.getWebsite());

        labelViewModel.setId(labelRepo.save(label).getId());
        return labelViewModel;
    }

    public List<LabelViewModel> getAllLabels() {
        List<Label> labelList = labelRepo.findAll();
        List<LabelViewModel> labelViewModelList = new ArrayList<>();

        if (labelList == null) {
            return null;
        } else {
            labelList.stream().forEach(label -> labelViewModelList.add(buildLabelViewModel(label)));
        }
        return labelViewModelList;
    }

    public LabelViewModel getLabel(Integer id) {
        Optional<Label> label = labelRepo.findById(id);
        if (label == null) {
            return null;
        } else {
            return buildLabelViewModel(label.get());
        }
    }

    public void updateLabel(LabelViewModel labelViewModel) {
        //validate data
        if (labelViewModel==null) throw new IllegalArgumentException("No label is passed! ");

        //make sure artist exists. If not, throw exception...
        if (this.getLabel(labelViewModel.getId()) == null) throw new IllegalArgumentException("No such label to update");

        Label label = new Label();
        label.setId(labelViewModel.getId());
        label.setName(labelViewModel.getName());
        label.setWebsite(labelViewModel.getWebsite());

        labelRepo.save(label);
    }

    public void deleteLabel(Integer id) {labelRepo.deleteById(id);}

    //helper methods
    public ArtistViewModel buildArtistViewModel(Artist artist) {
        ArtistViewModel artistViewModel = new ArtistViewModel();
        artistViewModel.setId(artist.getId());
        artistViewModel.setName(artist.getName());
        artistViewModel.setInstagram(artist.getInstagram());
        artistViewModel.setTwitter(artist.getTwitter());
        return artistViewModel;
    }

    public LabelViewModel buildLabelViewModel(Label label) {
        LabelViewModel labelViewModel = new LabelViewModel();
        labelViewModel.setId(label.getId());
        labelViewModel.setName(label.getName());
        labelViewModel.setWebsite(label.getWebsite());
        return labelViewModel;
    }
}
