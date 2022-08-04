package com.company.musiccatalog.controller;

import com.company.musiccatalog.model.Label;
import com.company.musiccatalog.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/label")
public class LabelController {
    @Autowired
    CatalogService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Label createLabel(@RequestBody @Valid Label label) {
        label = service.createLabel(label);
        return label;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Label> getAllLabels() {
        List<Label> labelList = service.getAllLabels();
        return labelList;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Label getLabelById(@PathVariable("id") Integer id) {
        Label label = service.getLabel(id);
        if (label == null) {
            throw new IllegalArgumentException("Label could not be retrieved for id " + id);
        } else {
            return label;
        }
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateLabel(@RequestBody @Valid Label label) {
        if (label == null || label.getId() <1) {
            throw new IllegalArgumentException("Check id in model");
        } else if (label.getId() >0) {
            service.updateLabel(label);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletelabel(@PathVariable("id") Integer id) { service.deleteLabel(id);}
}
