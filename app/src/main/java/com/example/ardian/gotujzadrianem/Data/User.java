package com.example.ardian.gotujzadrianem.Data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by Ardian on 2015-01-28.
 */
@JsonIgnoreProperties(ignoreUnknown = true) // ignoruje pola, ktorych nie wymienilismy, pobiera id i session_id
public class User implements Serializable { // implements - przesylamy jako extra dane User
    public Integer id; // pobieramy id
    @JsonProperty("session_id") // automatyczna konwersja dziala wtedy gdy nasze pola nazywaja sie dokladnie tak jak na serwerze
    public String sessionId; // zeby widzial ze sessionId to to samo co session_id na serwerze
// dane z serwera na temat zalogowanego uzytkownika
}