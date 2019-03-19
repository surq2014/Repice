package cook.cooking;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

//Класс для упрощения связывания данных с элементов управдения
class RecipeAdapter extends BaseAdapter {
    private Activity activity;
    private ArrayList<Recipe> recipeArrayList;
    private LayoutInflater inflater;

    RecipeAdapter(Activity activity, ArrayList<Recipe> recipeArrayList) {
        this.activity = activity;
        this.recipeArrayList = recipeArrayList;
    }

    @Override
    public int getCount() {
        return this.recipeArrayList.size();
    }

    @Override
    public Recipe getItem(int position) {
        return this.recipeArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
            convertView = inflater.inflate(R.layout.recipe_list_item, null);

        TextView name = (TextView) convertView.findViewById(R.id.nameTextView);
        TextView description = (TextView) convertView.findViewById(R.id.descriptionTextView);
        TextView recipes = (TextView) convertView.findViewById(R.id.recipesTextView);
        ImageView image = (ImageView) convertView.findViewById(R.id.imageView);

        final Recipe recipe = getItem(position);
        name.setText(recipe.getName());
        if (recipe.getDescription().length() <= 100) {
            description.setText(recipe.getDescription());
        } else {
            description.setText(recipe.getDescription().substring(0, 100) + "...");
        }
        if (recipe.getRecipes().length() <= 50) {
            recipes.setText(recipe.getRecipes());
        } else {
            recipes.setText(recipe.getRecipes().substring(0, 50) + "...");
        }

        Picasso.get().load(recipe.getImage()).into(image);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, RecipeActivity.class);
                intent.putExtra("name", recipe.getName());
                intent.putExtra("description", recipe.getDescription());
                intent.putExtra("recipes", recipe.getRecipes());
                intent.putExtra("image", recipe.getImage());
                intent.putExtra("url", recipe.getUrl());

                activity.startActivity(intent);
            }
        });

        return convertView;
    }
}
