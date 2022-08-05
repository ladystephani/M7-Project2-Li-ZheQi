package com.company.musiccatalog.controller;

import com.company.musiccatalog.model.Artist;
import com.company.musiccatalog.service.CatalogService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@RunWith(SpringRunner.class)
@WebMvcTest(ArtistController.class)
public class ArtistControllerTest {
    @MockBean
    private CatalogService service;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp() throws Exception {
        setupCatalogServiceMock();
    }

    private void setupCatalogServiceMock() {
        Artist artist = new Artist(1,"Jay", "@jaychou", "@jaychou");
        Artist artistWithoutId = new Artist("Jay", "@jaychou", "@jaychou");
        List<Artist> artistList = Arrays.asList(artist);

        doReturn(artistList).when(service).getAllArtists();
        doReturn(artist).when(service).getArtist(1);
        doReturn(artist).when(service).createArtist(artistWithoutId);
        doNothing().when(service).updateArtist(artist);
        doNothing().when(service).deleteArtist(1);
    }

    @Test
    public void getAllArtistShouldReturnAListAnd200() throws Exception {
        //arrange
        Artist artist = new Artist(1,"Jay", "@jaychou", "@jaychou");
        List<Artist> artistList = Arrays.asList(artist);
        String expectedJsonValue = mapper.writeValueAsString(artistList);
        //act
        mockMvc.perform(get("/artist"))
                .andDo(print())
                .andExpect(status().isOk()) //assert
                .andExpect(content().json(expectedJsonValue));
    }

    @Test
    public void getArtistByIdShouldReturnArtistAnd200() throws Exception {
        //arrange
        Artist savedArtist = new Artist(1,"Jay", "@jaychou", "@jaychou");

        String savedArtistJson = mapper.writeValueAsString(savedArtist);
        mockMvc.perform(get("/artist/{id}", 1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(savedArtistJson));
    }

    @Test
    public void createArtistShouldReturnNewArtistAndStatus201() throws Exception {
        Artist outputArtist = new Artist(1,"Jay", "@jaychou", "@jaychou");
        Artist inputArtist = new Artist("Jay", "@jaychou", "@jaychou");
        String outputArtistJson = mapper.writeValueAsString(outputArtist);
        String inputArtistJson = mapper.writeValueAsString(inputArtist);

        mockMvc.perform(
                post("/artist")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputArtistJson)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputArtistJson));
    }

    @Test
    public void updateArtistShouldReturnUpdatedArtistAndStatus204() throws Exception {
        Artist inputArtist = new Artist(1,"Jay", "@jaychou", "@jaychou");
        Artist outputArtist = new Artist(1,"Jay Chou", "@jaychou", "@jaychou");

        String inputArtistJson = mapper.writeValueAsString(inputArtist);

        mockMvc.perform(
                put("/artist")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputArtistJson)
                )
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void deleteArtistShouldDeleteAndReturnStatus204() throws Exception {
        Artist inputArtist = new Artist(1,"Jay", "@jaychou", "@jaychou");

        mockMvc.perform(delete("/artist/{id}", 1))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}