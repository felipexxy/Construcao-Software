package com.applyandgrowth.util;

import jakarta.validation.constraints.NotBlank;

public class DietProfile {
    @NotBlank
    private String height;
    @NotBlank
    private String weight;

    public String getHeight() {
        return height;
    }
    public void setHeight(String height) {
        this.height = height;
    }
    public String getWeight() {
        return weight;
    }
    public void setWeight(String weight) {
        this.weight = weight;
    }
}
