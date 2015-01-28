package com.example.ardian.gotujzadrianem;

import com.example.ardian.gotujzadrianem.Data.AccPassword;
import com.example.ardian.gotujzadrianem.Data.Recipe;
import com.example.ardian.gotujzadrianem.Data.RecipeList;
import com.example.ardian.gotujzadrianem.Data.Registration;
import com.example.ardian.gotujzadrianem.Data.User;

import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Post;
import org.androidannotations.annotations.rest.RequiresHeader;
import org.androidannotations.annotations.rest.Rest;
import org.androidannotations.api.rest.RestClientHeaders;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

/**
 * Created by Ardian on 2015-01-28.
 */
@Rest(rootUrl = "http://pegaz.wzr.ug.edu.pl:8080/rest", converters = { MappingJackson2HttpMessageConverter.class })
@RequiresHeader({"X-Dreamfactory-Application-Name"})
public interface RecipeRestClient extends RestClientHeaders {
    // Rest definiuje polaczenie z restem, zwraca liste, converter przerabia to co otrzymujemy z serwera na jsona, zeby zrozumiec, naglowek, zeby serwer odpowiedzial na zadanie wyslania listy
    @Get("/db/recipes")
    RecipeList getRecipeList();
    @Post("/db/recipes")
    @RequiresHeader({"X-Dreamfactory-Session-Token","X-Dreamfactory-Application-Name" })
    void addCookBookEntry(Recipe recipe);
    // przy poscie podajemy znowu naglowek, bo wymaga zalogowania; user dostaje id sesji
    @Post("/user/session") // bierze email i haslo z serwera i podpisuje pod adres /user/session
    User login(AccPassword accPassword); // przypisuje userowi dane
    @Post("/user/register/?login=true") // wysyłamy dane zarejestrowanego usera, a otrzymujemy zalogowanego usera
    User register(Registration registration);
}