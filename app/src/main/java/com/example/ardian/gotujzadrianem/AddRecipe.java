package com.example.ardian.gotujzadrianem;

import android.app.ProgressDialog;
import android.support.v7.app.ActionBarActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ardian.gotujzadrianem.Data.Recipe;
import com.example.ardian.gotujzadrianem.Data.User;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.NonConfigurationInstance;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Ardian on 2015-01-28.
 */

@EActivity(R.layout.add_recipe)
public class AddRecipe extends ActionBarActivity {
    @ViewById
    EditText title;
    @ViewById
    EditText introduction;
    @ViewById
    EditText ingredients;
    @ViewById
    EditText steps;
    @ViewById
    EditText preparationMinutes;
    @ViewById
    EditText cookingMinutes;
    @ViewById
    EditText servings;
    @Extra // po zalogowaniu przekazuje dane (id, sessionid) User do dodawania przepisu (jest przypisany do zmiennej)
            User user;
    @AfterViews
    void init(){
        ringProgressDialog=new ProgressDialog(this);
        ringProgressDialog.setMessage("Trwa dodawanie przepisu...");
        ringProgressDialog.setIndeterminate(true);
    }
    public void addSuccess(){ // void nic nie zwraca tylko wywoluje
        ringProgressDialog.dismiss();
        Toast.makeText(this, "Przepis został dodany", Toast.LENGTH_SHORT).show(); // wyswietli, ze dodawanie przepisu sie udalo
        MyActivity_.intent(this).user(user).start(); // user w klasie dodawanieprzepisu, uzytkownik funckji
// po dodaniu przepisu przekierowuje do firstactivity
    }
    public void addError (Exception error){
        ringProgressDialog.dismiss();
        Toast.makeText(this, "Wystąpił błąd w trakcie dodawania przepisu", Toast.LENGTH_LONG).show();
        error.printStackTrace();
    }
    // zeby wywolac metode dodawania przepisu
    @Bean
    @NonConfigurationInstance // nie wywali apki po obroceniu ekranu
            AddRecipeBackgroundTask addRecipeBackgroundTask;
    ProgressDialog ringProgressDialog; // koleczko ladowania
    @Click
    void buttonDodawania(){
        if (title.getText().toString().trim().length()==0){
            Toast.makeText(this, "Nie podano tytułu przepisu",Toast.LENGTH_LONG).show();
            return;
        }
        if (ingredients.getText().toString().trim().length()==0){
            Toast.makeText(this, "Nie podano składników przepisu",Toast.LENGTH_LONG).show();
            return;
        }
        if (steps.getText().toString().trim().length()==0){
            Toast.makeText(this, "Nie podano sposobu przygotowania przepisu",Toast.LENGTH_LONG).show();
            return;
        }
        if (servings.getText().toString().trim().length()==0){
            Toast.makeText(this, "Nie podano ilości porcji",Toast.LENGTH_LONG).show();
            return;
        }
        Recipe recipe=new Recipe();
        recipe.title=title.getText().toString();
        recipe.introduction=introduction.getText().toString();
        recipe.ingredients=ingredients.getText().toString();
        recipe.steps=steps.getText().toString();
        if (preparationMinutes.getText().toString().trim().length()>0) recipe.preparationMinutes=Integer.parseInt(preparationMinutes.getText().toString()); // trim usuwa biale znaki
        if (cookingMinutes.getText().toString().trim().length()>0)recipe.cookingMinutes=Integer.parseInt(cookingMinutes.getText().toString());
        if (servings.getText().toString().trim().length()>0)recipe.servings=Integer.parseInt((servings).getText().toString());
        recipe.ownerId=user.id; // wywolujemy id usera, zostanie przypisane do pola
        ringProgressDialog.show();
        addRecipeBackgroundTask.add(user, recipe);
    }
}