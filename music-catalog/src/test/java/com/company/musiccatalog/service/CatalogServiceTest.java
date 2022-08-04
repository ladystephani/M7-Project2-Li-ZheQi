package com.company.musiccatalog.service;

import com.company.musiccatalog.model.Artist;
import com.company.musiccatalog.repository.AlbumRepository;
import com.company.musiccatalog.repository.ArtistRepository;
import com.company.musiccatalog.repository.LabelRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class CatalogServiceTest {
    private CatalogService service;
    private ArtistRepository artistRepo;
    private LabelRepository labelRepo;
    private AlbumRepository albumRepo;
    private TrackRepository trackRepo;
    @Before
    public void setUp() {
        setUpArtistRepositoryMock();

        this.service = new CatalogService(artistRepo, labelRepo, albumRepo, trackRepo);
    }

    private void setUpArtistRepositoryMock() {
        this.artistRepo = mock(ArtistRepository.class);

        Artist artistIn = new Artist("Jay", "@jaychou", "@jaychou");
        //make the mock data equal single artist:
        Artist artistOut = new Artist(1,"Jay", "@jaychou", "@jaychou");

        //all artists
        List<Artist> artistList = Arrays.asList(artistOut);

        //by id
        Optional<Artist> findByIdResult = Optional.of(artistOut);

        doReturn(artistOut).when(artistRepo).save(artistIn); //when passing in artistIn, will only give artistOut -with same values
        doReturn(artistList).when(artistRepo).findAll();
        doReturn(findByIdResult).when(artistRepo).findById(1);

    }


    @Test
    public void createArtistShouldReturnArtistWithIdWhenSaving() {
        //arrange
        Artist artistToSave = new Artist("Jay", "@jaychou", "@jaychou");
        Artist expectedResult = new Artist(1, "Jay", "@jaychou", "@jaychou");
        //act
        Artist actualResult = service.createArtist(artistToSave);

        assertEquals(expectedResult, actualResult);
    }
}