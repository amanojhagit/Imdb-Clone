package com.example.moviedirector.Model;

import java.io.Serializable;

public class model implements Serializable {
    private static final long id=1L;

    private String title;
    private String bio;
    private String posterpath;
    private String backgroundpath;
        private String releasedate;
        private String originalLang;

    public static long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getPosterpath() {
        return posterpath;
    }

    public void setPosterpath(String posterpath) {
        this.posterpath = posterpath;
    }

    public String getBackgroundpath() {
        return backgroundpath;
    }

    public void setBackgroundpath(String backgroundpath) {
        this.backgroundpath = backgroundpath;
    }

    public String getReleasedate() {
        return releasedate;
    }

    public void setReleasedate(String releasedate) {
        this.releasedate = releasedate;
    }

    public String getOriginalLang() {
        return originalLang;
    }

    public void setOriginalLang(String originalLang) {
        this.originalLang = originalLang;
    }

    public model() {
    }
}
