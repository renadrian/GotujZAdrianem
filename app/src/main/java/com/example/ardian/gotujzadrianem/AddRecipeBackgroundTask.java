package com.example.ardian.gotujzadrianem;

import com.example.ardian.gotujzadrianem.Data.Recipe;
import com.example.ardian.gotujzadrianem.Data.User;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.rest.RestService;

/**
 * Created by Ardian on 2015-01-28.
 */
@EBean
public class AddRecipeBackgroundTask {
    @RootContext
    AddRecipe activity; // w ktorej aktywnosci background zostanie wywolany
    @RestService
    RecipeRestClient restClient; // komunikacja z serwerem
    @Background
    void add(User user, Recipe recipe) {
        try {
            restClient.setHeader("X-Dreamfactory-Application-Name", "cookbook");
            restClient.setHeader("X-Dreamfactory-Session-Token", user.sessionId); // POST wymaga bycia zalogowanym, robimy to przez dodanie naglowka
            restClient.addCookBookEntry(recipe);
            publishResult();
        } catch (Exception error) {
            publishError(error);
        }
    }
    @UiThread
    void publishResult() {
        activity.addSuccess();
    }
    @UiThread
    void publishError(Exception e) {
        activity.addError(e);
    }
}