package com.company.musiccatalog.controller;

import com.company.musiccatalog.model.Label;
import com.company.musiccatalog.service.CatalogService;
import com.company.musiccatalog.viewModel.LabelViewModel;
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
    public LabelViewModel createLabel(@RequestBody @Valid LabelViewModel labelViewModel) {
        labelViewModel = service.createLabel(labelViewModel);
        return labelViewModel;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<LabelViewModel> getAllLabels() {
        List<LabelViewModel> labelViewModelList = service.getAllLabels();
        return labelViewModelList;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public LabelViewModel getLabelById(@PathVariable("id") Integer id) {
        LabelViewModel labelViewModel = service.getLabel(id);
        if (labelViewModel == null) {
            throw new IllegalArgumentException("Label could not be retrieved for id " + id);
        } else {
            return labelViewModel;
        }
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateLabel(@RequestBody @Valid LabelViewModel labelViewModel) {
        if (labelViewModel == null || labelViewModel.getId() <1) {
            throw new IllegalArgumentException("Check id in view model");
        } else if (labelViewModel.getId() >0) {
            service.updateLabel(labelViewModel);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletelabel(@PathVariable("id") Integer id) { service.deleteLabel(id);}
}
