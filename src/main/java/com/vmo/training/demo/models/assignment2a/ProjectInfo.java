package com.vmo.training.demo.models.assignment2a;

public class ProjectInfo {
    Integer id;
    String name;
    Integer name1;
    Integer color;
    boolean favorite;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getName1() {
        return name1;
    }
    public void setName1(Integer name1) {
        this.name1 = name1;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getColor() {
        return color;
    }

    public void setColor(Integer color) {
        this.color = color;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
}
