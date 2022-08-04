package com.company.musiccatalog.viewModel;

import javax.validation.constraints.NotEmpty;
import java.util.Objects;

public class TrackViewModel {
    private Integer id;
    @NotEmpty(message = "Album id cannot be empty.")
    private Integer albumId;
    @NotEmpty(message = "Track title cannot be empty.")
    private String title;
    @NotEmpty(message = "Run time cannot be empty.")
    private Integer runTime;

    public TrackViewModel() {
    }

    public TrackViewModel(Integer id, Integer albumId, String title, Integer runTime) {
        this.id = id;
        this.albumId = albumId;
        this.title = title;
        this.runTime = runTime;
    }

    public TrackViewModel(Integer albumId, String title, Integer runTime) {
        this.albumId = albumId;
        this.title = title;
        this.runTime = runTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getRunTime() {
        return runTime;
    }

    public void setRunTime(Integer runTime) {
        this.runTime = runTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrackViewModel that = (TrackViewModel) o;
        return Objects.equals(id, that.id) && Objects.equals(albumId, that.albumId) && Objects.equals(title, that.title) && Objects.equals(runTime, that.runTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, albumId, title, runTime);
    }

    @Override
    public String toString() {
        return "TrackViewModel{" +
                "id=" + id +
                ", albumId=" + albumId +
                ", title='" + title + '\'' +
                ", runTime=" + runTime +
                '}';
    }
}
