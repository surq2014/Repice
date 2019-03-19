package cook.cooking;

//Класс для записи и вывода данных
class Recipe {
    private String name, description, image, url, recipes;

    Recipe(String name, String description, String recipes, String image, String url) {
        super();
        this.name = name;
        this.description = description;
        this.recipes = recipes;
        this.image = image;
        this.url = url;
    }

    String getName() {
        return this.name;
    }

    String getDescription() {
        return this.description;
    }

    String getRecipes() {
        return this.recipes;
    }

    String getImage() {
        return this.image;
    }

    String getUrl() {
        return this.url;
    }
}
