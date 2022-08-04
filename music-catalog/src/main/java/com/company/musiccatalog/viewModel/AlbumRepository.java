package com.company.musiccatalog.viewModel;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class AlbumRepository {
    private Integer id;
    @NotEmpty(message = "Album title cannot be empty.")
    private String title;
    @NotEmpty(message = "Artist id cannot be empty.")
    private Integer artistId;
    @NotEmpty(message = "Release date cannot be empty.")
    private LocalDate releaseDate;
    @NotEmpty(message = "Label id cannot be empty.")
    private Integer labelId;
    @NotEmpty(message = "List price cannot be empty.")
    private BigDecimal listPrice;

    public AlbumRepository() {
    }

    public AlbumRepository(Integer id, String title, Integer artistId, LocalDate releaseDate, Integer labelId, BigDecimal listPrice) {
        this.id = id;
        this.title = title;
        this.artistId = artistId;
        this.releaseDate = releaseDate;
        this.labelId = labelId;
        this.listPrice = listPrice;
    }

    public AlbumRepository(String title, Integer artistId, LocalDate releaseDate, Integer labelId, BigDecimal listPrice) {
        this.title = title;
        this.artistId = artistId;
        this.releaseDate = releaseDate;
        this.labelId = labelId;
        this.listPrice = listPrice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getArtistId() {
        return artistId;
    }

    public void setArtistId(Integer artistId) {
        this.artistId = artistId;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getLabelId() {
        return labelId;
    }

    public void setLabelId(Integer labelId) {
        this.labelId = labelId;
    }

    public BigDecimal getListPrice() {
        return listPrice;
    }

    public void setListPrice(BigDecimal listPrice) {
        this.listPrice = listPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlbumRepository that = (AlbumRepository) o;
        return Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(artistId, that.artistId) && Objects.equals(releaseDate, that.releaseDate) && Objects.equals(labelId, that.labelId) && Objects.equals(listPrice, that.listPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, artistId, releaseDate, labelId, listPrice);
    }

    @Override
    public String toString() {
        return "AlbumRepository{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", artistId=" + artistId +
                ", releaseDate=" + releaseDate +
                ", labelId=" + labelId +
                ", listPrice=" + listPrice +
                '}';
    }
}
