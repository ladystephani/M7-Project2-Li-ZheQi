package com.company.musiccatalog.repository;

import com.company.musiccatalog.model.Artist;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArtistRepositoryTest {
    @Autowired
    ArtistRepository artistRepo;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void shouldInteractWithArtistTableInDatabase() {
        // Arrange
        //add an artist
        Artist artist = new Artist("Jay", "@jaychou", "@jaychou");
        Artist expectedArtist = new Artist("Jay", "@jaychou", "@jaychou");
        //act
        artistRepo.save(artist);
        expectedArtist.setId(artist.getId());

        //assert
        assertEquals(expectedArtist, artist);

        //act on all artists
        List<Artist> artistList = artistRepo.findAll();
        //assert
        assertEquals(1, artistList.size());

        //act by deleting
        artistRepo.deleteById(artist.getId());
        artistList = artistRepo.findAll();
        assertEquals(0, artistList.size());
    }
}