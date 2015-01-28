package com.example.ardian.gotujzadrianem.Data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Ardian on 2015-01-28.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Registration {
    public String first_name;
    public String last_name;
    public String email;
    public String new_password;
}