package com.example.ardian.gotujzadrianem;

import com.example.ardian.gotujzadrianem.Data.RecipeList;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.rest.RestService;

/**
 * Created by Ardian on 2015-01-28.
 */
@EBean
public class RecipeListBackgroundTask {
    @RootContext
    MyActivity activity;
    // aktywnosc w ktorej sie uruchamia liste i wyswietla
    @RestService
    RecipeRestClient restClient;
    // skad pobiera dane, lacznik miedzy apka a serwerem z baza danych
    @Background
    void getRecipeList() {
        try {
            restClient.setHeader("X-Dreamfactory-Application-Name", "cookbook"); // nadaje naglowek zadaniu http
            RecipeList recipeList = restClient.getRecipeList(); // zwraca obiekt klasy recipe
            publishResult(recipeList); // przekazujemy teraz dalej liste
        } catch (Exception e) {
            publishError(e);
        }
// try pozwala na wykonanie kodu, ale jak czegos nie pobierze lub cos sie nie uda to zwraca blad, exception to zbior ogolnych bledow
    }
    @UiThread
    void publishResult(RecipeList recipeList) { // uithread (user interface) dziala w glownym watku, ktory odpowiada za wyswietlanie wszystkiego, zeby przepisy nie krazyly w tle ciagle
        activity.updateRecipeList(recipeList); // przyjmuje i aktualizuje liste
    }
    @UiThread
    void publishError(Exception e) {
        activity.showError(e);
    }
}