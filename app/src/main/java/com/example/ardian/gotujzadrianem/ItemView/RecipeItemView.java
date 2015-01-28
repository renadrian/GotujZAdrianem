package com.example.ardian.gotujzadrianem.ItemView;

import android.content.Context;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ardian.gotujzadrianem.Data.Recipe;
import com.example.ardian.gotujzadrianem.R;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Ardian on 2015-01-28.
 */
@EViewGroup(R.layout.recipe_view)
public class RecipeItemView extends RelativeLayout {
    @ViewById
    TextView title;
    @ViewById
    TextView introduction;
    public RecipeItemView(Context context) {
        super(context);
    }
    public void bind(Recipe recipe) {
        title.setText(recipe.title);
        introduction.setText(recipe.introduction);
    }
}
