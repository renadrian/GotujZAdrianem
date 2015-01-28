package com.example.ardian.gotujzadrianem.Data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Ardian on 2015-01-28.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Recipe implements Serializable, Comparable<Recipe>{
    public Integer id;
    public Integer ownerId;
    public String title;
    public String introduction;
    public String ingredients;
    public String steps;
    public String created;
    public Integer preparationMinutes;
    public Integer cookingMinutes;
    public Integer servings;
    public Date getCreatedDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try{
            return sdf.parse(created);
        }
        catch (Exception error){
            return new Date();
        }
    }
    @Override
    public int compareTo(Recipe recipe2) {
        Date date1 = getCreatedDate();
        Date date2 = recipe2.getCreatedDate();
        return -date1.compareTo(date2);
    }

}