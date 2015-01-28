package com.example.ardian.gotujzadrianem.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.ardian.gotujzadrianem.Data.Recipe;
import com.example.ardian.gotujzadrianem.Data.RecipeList;
import com.example.ardian.gotujzadrianem.ItemView.RecipeItemView;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Ardian on 2015-01-28.
 */
@EBean
public class RecipeListAdapter extends BaseAdapter {
    @RootContext
    Context context;
    List<Recipe> recipes = new ArrayList<Recipe>();
    public RecipeListAdapter() {
// recipes.add(new Recipe("pierogi", "smakowite"));
// recipes.add(new Recipe("nalesniki", "malo slodkie"));
    }
    public void update(RecipeList recipeList) {
        recipes.clear(); // czyscimy najpierw liste
        recipes.addAll(recipeList.records); // dodajemy wszystkie obiekty do adaptera, ktore pobralismy z serwera
        Collections.sort(recipes); // sortowanie w adapterze przepisow
        notifyDataSetChanged(); // odswieza
    }
    @Override
    public int getCount() {
        return recipes.size();
    }
    @Override
    public Recipe getItem(int i) {
        return recipes.get(i);
    }
    @Override
    public long getItemId(int i) {
        return i;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RecipeItemView recipeItemView;
        if (convertView == null) {
            recipeItemView = RecipeItemView_.build(context);
        } else {
            recipeItemView = (RecipeItemView) convertView;
        }
        recipeItemView.bind(getItem(position));
        return recipeItemView;
    }
// bind odpowiednie pole z recipe przypisuje do odpowiednich pol widoku
}