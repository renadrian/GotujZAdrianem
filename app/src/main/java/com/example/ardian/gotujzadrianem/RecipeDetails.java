package com.example.ardian.gotujzadrianem;

import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

import com.example.ardian.gotujzadrianem.Data.Recipe;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Ardian on 2015-01-28.
 */
@EActivity(R.layout.recipe_details)
public class RecipeDetails extends ActionBarActivity {
    @ViewById
    TextView title;
    @ViewById
    TextView introduction;
    @ViewById
    TextView ingredients;
    @ViewById
    TextView steps;
    @ViewById
    TextView created;
    @ViewById
    TextView preparationMinutes;
    @ViewById
    TextView cookingMinutes;
    @ViewById
    TextView servings;
    @Extra // pozwala przekazac dane miedzy aktywnosciami
            Recipe recipe; // do intencji sa 'doklejane' extra z innej aktywnosci
    @AfterViews
    void init() {
        title.setText(recipe.title);
        introduction.setText(recipe.introduction); //settext ustawia w layoucie argument, ktory podajemy w polu
        ingredients.setText(recipe.ingredients);
        steps.setText(recipe.steps);
        created.setText(recipe.created);
        if (recipe.preparationMinutes != null)
            preparationMinutes.setText(Integer.toString(recipe.preparationMinutes)); //integer.tostring konwertuje (przemienia) cyfry na tekst
        if (recipe.cookingMinutes != null)
            cookingMinutes.setText(Integer.toString(recipe.cookingMinutes));
        if (recipe.servings != null) servings.setText(Integer.toString(recipe.servings));
// if zeby nie wysypywalo przy pustych polach
    }
}