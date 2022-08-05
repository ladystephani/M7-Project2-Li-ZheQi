package com.company.musiccatalog.controller;

import com.company.musiccatalog.model.Label;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@WebMvcTest(LabelController.class)
public class LabelControllerTest {
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
        Label label = new Label(3, "Fiona", "www.physit.com");
        Label labelWithoutId = new Label("Fiona", "www.physit.com");
        List<Label> labelList = Arrays.asList(label);

        doReturn(labelList).when(service).getAllLabels();
        doReturn(label).when(service).getLabel(3);
        doReturn(label).when(service).createLabel(labelWithoutId);
        doNothing().when(service).updateLabel(label);
        doNothing().when(service).deleteLabel(3);
    }

    @Test
    public void getAllLabelsShouldReturnAListAnd200() throws Exception {
        Label label = new Label(3, "Fiona", "www.physit.com");
        List<Label> labelList = Arrays.asList(label);
        String expectedJsonValue = mapper.writeValueAsString(labelList);

        mockMvc.perform(get("/label"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJsonValue));
    }

    @Test
    public void getLabelByIdShouldReturnLabelAnd200() throws Exception {
        Label savedLabel = new Label(3, "Fiona", "www.physit.com");
        String savedLabelJson = mapper.writeValueAsString(savedLabel);

        mockMvc.perform(get("/label/{id}", 3))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(savedLabelJson));
    }

    @Test
    public void createArtistShouldReturnNewArtistAndStatus201() throws Exception {
        Label inputLabel = new Label("Fiona", "www.physit.com");
        Label outputLabel = new Label(3, "Fiona", "www.physit.com");
        String inputLabelJson = mapper.writeValueAsString(inputLabel);
        String outputLabelJson  = mapper.writeValueAsString(outputLabel);

        mockMvc.perform(
                        post("/label")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(inputLabelJson)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputLabelJson));
    }

    @Test
    public void updateLabelShouldReturnUpdatedLabelAndStatus204() throws Exception {
        Label inputLabel = new Label(3,"Fiona", "www.physit.com");
        Label outputLabel = new Label(3, "Fiona Sit", "www.physit.com");
        String inputLabelJson = mapper.writeValueAsString(inputLabel);

        mockMvc.perform(
                        put("/label")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(inputLabelJson)
                )
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void deleteLabelShouldDeleteAndReturnStatus204() throws Exception {
        Label inputLabel = new Label(3,"Fiona", "www.physit.com");

        mockMvc.perform(delete("/label/{id}", 3))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
    }