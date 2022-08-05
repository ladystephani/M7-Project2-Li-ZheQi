package com.company.musiccatalog.controller;

import com.company.musiccatalog.model.Album;
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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AlbumController.class)
public class AlbumControllerTest {
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
        Album album = new Album(1, "Greatest Work of All", 1, LocalDate.of(2022,07,04), 1, BigDecimal.valueOf(12.99));
        Album albumWithoutId = new Album("Greatest Work of All", 1, LocalDate.of(2022,07,04), 1, BigDecimal.valueOf(12.99));
        List<Album> albumList = Arrays.asList(album);

        doReturn(albumList).when(service).getAllAlbums();
        doReturn(album).when(service).getAlbum(1);
        doReturn(album).when(service).createAlbum(albumWithoutId);
        doNothing().when(service).updateAlbum(album);
        doNothing().when(service).deleteAlbum(1);
    }

    @Test
    public void getAllAlbumShouldReturnAListAnd200() throws Exception {
        Album album = new Album(1, "Greatest Work of All", 1, LocalDate.of(2022,07,04), 1, BigDecimal.valueOf(12.99));
        List<Album> albumList = Arrays.asList(album);
        String expectedJsonValue = mapper.writeValueAsString(albumList);

        mockMvc.perform(get("/album"))
                .andDo(print())
                .andExpect(status().isOk()) //assert
                .andExpect(content().json(expectedJsonValue));
    }

    @Test
    public void getAlbumByIdShouldReturnArtistAnd200() throws Exception {
        Album savedAlbum = new Album(1, "Greatest Work of All", 1, LocalDate.of(2022,07,04), 1, BigDecimal.valueOf(12.99));
        String savedAlbumJson = mapper.writeValueAsString(savedAlbum);
        mockMvc.perform(get("/album/{id}", 1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(savedAlbumJson));
    }

    @Test
    public void createAlbumShouldReturnNewAlbumAndStatus201() throws Exception {
        Album inputAlbum = new Album("Greatest Work of All", 1, LocalDate.of(2022,07,04), 1, BigDecimal.valueOf(12.99));
        Album outputAlbum = new Album(1, "Greatest Work of All", 1, LocalDate.of(2022,07,04), 1, BigDecimal.valueOf(12.99));

        String inputALbumJson = mapper.writeValueAsString(inputAlbum);
        String outputAlbumJson  = mapper.writeValueAsString(outputAlbum);

        mockMvc.perform(
                        post("/album")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(inputALbumJson)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputAlbumJson));
    }

    @Test
    public void updateAlbumShouldReturnUpdatedAlbumAndStatus204() throws Exception {
        Album inputAlbum = new Album(1,"Greatest Work of All", 1, LocalDate.of(2022,07,04), 1, BigDecimal.valueOf(12.99));
        Album outputAlbum = new Album(1, "Greatest Work Of All", 1, LocalDate.of(2022,07,04), 1, BigDecimal.valueOf(15.99));

        String inputALbumJson = mapper.writeValueAsString(inputAlbum);

        mockMvc.perform(
                        put("/album")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(inputALbumJson)
                )
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void deleteAlbumShouldDeleteAndReturnStatus204() throws Exception {
        Album inputAlbum = new Album(1,"Greatest Work of All", 1, LocalDate.of(2022,07,04), 1, BigDecimal.valueOf(12.99));
        mockMvc.perform(delete("/album/{id}", 1))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}