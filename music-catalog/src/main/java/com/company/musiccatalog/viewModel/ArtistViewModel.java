package com.company.musiccatalog.viewModel;

import javax.validation.constraints.NotEmpty;
import java.util.Objects;

public class ArtistViewModel {
    private Integer id;

    @NotEmpty(message = "Artist name cannot be empty.")
    private String name;
    @NotEmpty(message = "Artist instagram cannot be empty.")
    private String instagram;
    @NotEmpty(message = "Artist twitter cannot be empty.")
    private String twitter;

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

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArtistViewModel that = (ArtistViewModel) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(instagram, that.instagram) && Objects.equals(twitter, that.twitter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, instagram, twitter);
    }

    @Override
    public String toString() {
        return "ArtistViewModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", instagram='" + instagram + '\'' +
                ", twitter='" + twitter + '\'' +
                '}';
    }
}
