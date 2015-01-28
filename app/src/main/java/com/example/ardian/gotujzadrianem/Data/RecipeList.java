package com.example.ardian.gotujzadrianem.Data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ardian on 2015-01-28.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RecipeList {
    // lista przepisów do wyświetlenia, przechowuje dane
    @JsonProperty("record")
    public List<Recipe> records = new ArrayList<Recipe>();
}