package com.example.ardian.gotujzadrianem.Data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Ardian on 2015-01-28.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccPassword {
    // automatycznie konwertuje sie na format taki jak na serwerze, nazwy musza byc takie same
    public String email;
    public String password;
}