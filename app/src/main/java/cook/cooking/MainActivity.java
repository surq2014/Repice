package cook.cooking;

import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ArrayList<Recipe> recipeArrayList;
    RecipeAdapter adapter;

    //Создаем новый объект и вносим в него данные для приложения
    Recipe[] recipes = new Recipe[] {
            new Recipe("Запечённая рыба по-гречески",
                    "Не знаете, как вкусно и интересно приготовить рыбу? Тогда это то, что вам нужно! Рецепт очень простой и доступный, а запечённая рыба \"под шубой\" получается просто шик!",
                    "Филе любой белой рыбы - 700 грамм; томаты - 2 штуки; твердый сыр - 150 грамм; сметана - 200 грамм; зелень; чеснок по вкусу; соль, перец.",
                    "https://img1.russianfood.com/dycontent/images_upl/271/big_270466.jpg",
                    "O5u5BOuTikE"),
            new Recipe("Горбуша, запеченная в духовке",
                    "Горбушу, запеченную в духовке, готовить несложно, но в результате она получается вкусной, сочной и нежной! Попробуйте приготовить!",
                    "Горбуша - 1 шт.\n" +
                            "Сыр твёрдый - 100-150 г\n" +
                            "Томаты - 2 шт.\n" +
                            "Сметана - 200 г\n" +
                            "Чеснок - 2 зубчика (или по вкусу)\n" +
                            "Соль - по вкусу\n" +
                            "Перец черный молотый - по вкусу\n" +
                            "Приправы - по вкусу",
                    "https://img1.russianfood.com/dycontent/images_upl/283/big_282423.jpg",
                    "xqdzVIX_hJY"),
            new Recipe("Печенье \"Творожные сердечки\"",
                    "Рецепт творожного печенья в форме сердечек. Лимонная цедра придает приятный вкус, а сами печеньки получаются мягкие и пышные. Простой и быстрый рецепт, простая выпечка!",
                    "Творог - 200 г\n" +
                            "Масло сливочное - 100 г\n" +
                            "Мука - 1 стакан\n" +
                            "Сахар - 100 г\n" +
                            "Яйцо - 1 шт.\n" +
                            "Лимон - 0,5 шт.\n" +
                            "Сода - 0,5 ч. л.\n" +
                            "Ванилин - на кончике ножа\n" +
                            "Сахарная пудра - 2 ч. л.",
                    "https://img1.russianfood.com/dycontent/images_upl/249/big_248845.jpg",
                            "hJrVLmSGtK0"),
            new Recipe("Соус бешамель",
                    "Главный секрет соуса бешамель вовсе не в том, что он прост в приготовлении или одинаково хорошо подходит к мясным, рыбным и овощным блюдам. Главное, что соус бешамель является базовым для многих соусов. Соус бешамель может быть отличной заправкой для низкокалорийных салатов или необходимым ингредиентом в приготовлении, например, супов и других блюд.",
                    "Молоко - 900 мл\n" +
                            "Масло сливочное - 75 г\n" +
                            "Мука пшеничная - 75 г\n" +
                            "Перец черный молотый - 1/3 ч. л.\n" +
                            "Орех мускатный молотый - 1/3 ч. л.\n" +
                            "Соль - 1/2 ч. л.",
                    "https://img1.russianfood.com/dycontent/images_upl/265/big_264576.jpg",
                            "7dW6hubD5FM"),
            new Recipe("Маринованные грибы на зиму",
                    "Предлагаю проверенный рецепт маринованных грибочков. Уже открыла и попробовала - грибы получились просто отменные! Для консервации взяла белые грибы и подберезовики в равных частях.",
                    "Грибы (белые и подберезовики) - 1 кг\n" +
                            "Чеснок - 3 зубчика\n" +
                            "*\n" +
                            "Для маринада:\n" +
                            "Вода - 1 л\n" +
                            "Соль - 1 ст. л.\n" +
                            "Сахар - 1 ч. л.\n" +
                            "Уксус 9% - 2,5 ст. л.\n" +
                            "Перец горошком - 5-8 шт.\n" +
                            "Гвоздика (по желанию) - 2 шт.\n" +
                            "Лавровый лист - 3 шт.\n" +
                            "Листья смородины - 4 шт.\n" +
                            "Укроп - 2 зонтика",
                    "https://img1.russianfood.com/dycontent/images_upl/274/big_273143.jpg",
                            "rNSCHx83q_U"),
            new Recipe("Закусочный торт на праздничный стол",
                    "Такой закуски на моём столе ещё не было! Давно хотела попробовать этот рецепт закусочного торта и удивить своих гостей! И у меня получилось всё, как задумала, – вкусно и красиво! Как оказалось, рыбный закусочный торт готовится легко, а продукты все доступны. Готовьте на здоровье и порадуйте всех за праздничным столом!",
                    "Тесто слоёное - 500 г (2 пласта)\n" +
                            "*\n" +
                            "Для начинки №1:\n" +
                            "Рыба красная солёная - 200 г\n" +
                            "Огурцы маринованные (корнишоны) - 8 шт.\n" +
                            "Яйца отварные - 2 шт.\n" +
                            "Сыр российский - 70 г\n" +
                            "Укроп свежий рубленый - 1 ст. ложка\n" +
                            "Майонез - по вкусу\n" +
                            "Соль - по вкусу\n" +
                            "Перец чёрный молотый - по вкусу\n" +
                            "*\n" +
                            "Для начинки №2:\n" +
                            "Скумбрия горячего копчения - 200 г\n" +
                            "Сыр творожный - 150 г\n" +
                            "Сыр плавленый сливочный - 100 г\n" +
                            "Хрен (не крепкий) - 3 ст. ложки\n" +
                            "*\n" +
                            "Для покрытия:\n" +
                            "Сыр плавленый сливочный - 100 г\n" +
                            "Сыр творожный - 150 г\n" +
                            "*\n" +
                            "Для украшения:\n" +
                            "Рыба красная - 50-100 г\n" +
                            "Огурцы свежие - 1-2 шт.\n" +
                            "Лук зелёный - 1 небольшой пучок\n" +
                            "Горошек зелёный",
                    "https://img1.russianfood.com/dycontent/images_upl/296/big_295680.jpg",
                            "7mW7S32YhWE"),
            new Recipe("Домашняя лапша с томатным соусом",
                    "Домашняя лапша – это очень вкусное блюдо. Домашнюю лапшу подают с разными соусами. Предлагаем рецепт домашней лапши на желтках, с помидорным соусом.",
                    "Для теста:\n" +
                            "Мука - 250 г\n" +
                            "Желтки - 6 шт.\n" +
                            "Яйца - 1 шт.\n" +
                            "Масло растительное - 2 ст. ложки\n" +
                            "*\n" +
                            "Для соуса:\n" +
                            "Помидоры - 1 кг\n" +
                            "Лук - 1 шт.\n" +
                            "Чеснок - 1 зубок\n" +
                            "Масло оливковое или другое растительное - 50 г\n" +
                            "Соль - 1 ч. ложка\n" +
                            "Перец черный молотый - 0,5 ч. ложки",
                    "https://img1.russianfood.com/dycontent/images_upl/208/big_207211.jpg",
                            "ve9M-x46of8"),
            new Recipe("Макароны по-летнему",
                    "Простой рецепт спагетти с соусом из летних овощей - паста (макароны) с кабачками.",
                    "Макароны (спагетти) - 450 г\n" +
                            "Помидоры - 1 кг\n" +
                            "Кабачки (цукини) - 2 шт.\n" +
                            "Морковь - 1 шт.\n" +
                            "Лук репчатый - 1 шт.\n" +
                            "Чеснок - 3 зубчика\n" +
                            "Томатный соус - 2 ст. ложки\n" +
                            "Яйца - 3 шт.\n" +
                            "Сыр пармезан натертый - 1 стакан + для подачи\n" +
                            "Итальянские травы - 2 ч. ложки\n" +
                            "Хлопья перца чили - 1/3 ч. ложки\n" +
                            "Базилик свежий - по вкусу\n" +
                            "Масло оливковое - 2-3 ст. ложки\n" +
                            "Соль - по вкусу\n" +
                            "Перец черный молотый (по желанию) - по вкусу",
                    "https://img1.russianfood.com/dycontent/images_upl/273/big_272026.jpg",
                            "Zetz-Ai-T0c"),
    };

    //Связываем данные
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayUseLogoEnabled(true);
            actionBar.setIcon(R.drawable.ic_action_logo);
            actionBar.setTitle("    " + actionBar.getTitle());
        }

        recipeArrayList = new ArrayList<>(Arrays.asList(recipes));
        adapter = new RecipeAdapter(this, recipeArrayList);

        ListView movieListView = (ListView) findViewById(R.id.movieListView);
        movieListView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
//        SearchView searchView = searchItem.getActionView() as SearchView;
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d("Поиск", newText);

                recipeArrayList.clear();

                if (newText.equals("")) {
                    recipeArrayList.addAll(Arrays.asList(recipes));
                } else {
                    for (Recipe recipe : recipes) {
                        if (recipe.getName().toLowerCase().contains(newText.toLowerCase())) {
                            recipeArrayList.add(recipe);
                        }
                    }
                }

                adapter.notifyDataSetChanged();

                return false;
            }
        });
            return super.onCreateOptionsMenu(menu);
    }

}
