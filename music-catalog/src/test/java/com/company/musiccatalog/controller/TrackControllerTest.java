package com.company.musiccatalog.controller;

import com.company.musiccatalog.model.Album;
import com.company.musiccatalog.model.Track;
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
@WebMvcTest(TrackController.class)
public class TrackControllerTest {
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
        Track track = new Track(1, 1, "You are the firework I missed", 4);
        Track trackWithoutId = new Track(1, "You are the firework I missed", 4);
        List<Track> trackList = Arrays.asList(track);

        doReturn(trackList).when(service).getAllTracks();
        doReturn(track).when(service).getTrack(1);
        doReturn(track).when(service).createTrack(trackWithoutId);
        doNothing().when(service).updateTrack(track);
        doNothing().when(service).deleteTrack(1);
    }

    @Test
    public void getAllTracksShouldReturnAListAnd200() throws Exception {
        Track track = new Track(1, 1, "You are the firework I missed", 4);
        List<Track> trackList = Arrays.asList(track);
        String expectedJsonValue = mapper.writeValueAsString(trackList);

        mockMvc.perform(get("/track"))
                .andDo(print())
                .andExpect(status().isOk()) //assert
                .andExpect(content().json(expectedJsonValue));
    }

    @Test
    public void getTrackByIdShouldReturnArtistAnd200() throws Exception {
        Track savedTrack = new Track(1, 1, "You are the firework I missed", 4);
        String savedTrackJson = mapper.writeValueAsString(savedTrack);

        mockMvc.perform(get("/track/{id}", 1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(savedTrackJson));
    }

    @Test
    public void createTrackShouldReturnNewTrackAndStatus201() throws Exception {
        Track inputTrack = new Track(1, "You are the firework I missed", 4);
        Track outputTrack = new Track(1, 1, "You are the firework I missed", 4);
        String inputTrackJson = mapper.writeValueAsString(inputTrack);
        String outputTrackJson  = mapper.writeValueAsString(outputTrack);

        mockMvc.perform(
                        post("/track")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(inputTrackJson)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputTrackJson));
    }

    @Test
    public void updateTrackShouldReturnUpdatedTrackAndStatus204() throws Exception {
        Track inputTrack = new Track(1,1, "You are the firework I missed", 4);
        Track outputTrack = new Track(1, 1, "You are the firework I missed", 5);
        String inputTrackJson = mapper.writeValueAsString(inputTrack);

        mockMvc.perform(
                        put("/track")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(inputTrackJson)
                )
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void deleteTrackShouldDeleteAndReturnStatus204() throws Exception {
        Track inputTrack = new Track(1,1, "You are the firework I missed", 4);
        mockMvc.perform(delete("/track/{id}", 1))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}