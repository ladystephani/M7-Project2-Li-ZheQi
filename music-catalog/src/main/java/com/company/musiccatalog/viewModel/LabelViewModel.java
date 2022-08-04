package com.company.musiccatalog.viewModel;

import javax.validation.constraints.NotEmpty;
import java.util.Objects;

public class LabelViewModel {
    private Integer id;
    @NotEmpty(message = "Label name cannot be empty.")
    private String name;
    @NotEmpty(message = "Label website cannot be empty.")
    private String website;

    public LabelViewModel() {
    }

    public LabelViewModel(Integer id, String name, String website) {
        this.id = id;
        this.name = name;
        this.website = website;
    }

    public LabelViewModel(String name, String website) {
        this.name = name;
        this.website = website;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LabelViewModel that = (LabelViewModel) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(website, that.website);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, website);
    }

    @Override
    public String toString() {
        return "LabelViewModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", website='" + website + '\'' +
                '}';
    }
}
