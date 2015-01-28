package com.example.ardian.gotujzadrianem;

import android.app.ProgressDialog;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ardian.gotujzadrianem.Adapter.RecipeListAdapter;
import com.example.ardian.gotujzadrianem.Data.Recipe;
import com.example.ardian.gotujzadrianem.Data.RecipeList;
import com.example.ardian.gotujzadrianem.Data.User;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.NonConfigurationInstance;
import org.androidannotations.annotations.ViewById;


@EActivity(R.layout.my_activity)
public class MyActivity extends ActionBarActivity { // dziedziczy po bardziej ogolnym typie aktywosci
    @Click(R.id.AddRecipe)
    void dodajClicked(){
//startActivity(new Intent(this, Logowanie_.class));
        LogActivity_.intent(this).user(user).start(); //inna wersja dla aktywnosci adnotowanych
    }
    @ViewById
    ListView lista;
    @Bean
    RecipeListAdapter adapter;
    @AfterViews
    void init() {
        lista.setAdapter(adapter);
        ringProgressDialog = new ProgressDialog(this);
        ringProgressDialog.setMessage("Trwa ładowanie, proszę czekać...");
        ringProgressDialog.setIndeterminate(true);
        ringProgressDialog.show();
        restBackgroundTask.getRecipeList();
    }
    // dla danej listy ustalamy adapter, ktory przypisze odpowiednio elementy; ring... okreslaja koleczko ladowania
// 2 ost wersy do ladowania automatycznie listy na starcie; getrecipe list wysyla do funkcji w recipelistbackgroundtask
    @Bean
    @NonConfigurationInstance
    RecipeListBackgroundTask restBackgroundTask;
    ProgressDialog ringProgressDialog;
    // bean nasza wlasna annotowana klasa
// nonconf... oznacza, ze jak obrocimy ekran nie zabije procesu, optymalizacja
// progressdialog to alert o ladowaniu aplikacji
    public void updateRecipeList(RecipeList recipeList) { // przekazuje z tla do aktywnosci, w ramach aktywnosci przekazujemy do adaptera
        ringProgressDialog.dismiss();
        adapter.update(recipeList);
    }
    public void showError(Exception e) { // glowny error wyswietla
        ringProgressDialog.dismiss();
        Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show(); // toast wyswietla komunikat chwile
        e.printStackTrace();
    }
    @ItemClick
    void listaItemClicked(Recipe recipe){ // obiekt klasy, nazwa zmiennej
        RecipeDetails_.intent(this).recipe(recipe).start(); //intencja przenosi do nowej aktywnosci i dolacza przepis
    }
    @Extra
    User user; // szuka user w intencji, ktora przeszla do firstactivity
}