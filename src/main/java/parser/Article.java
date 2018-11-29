package parser;

public class Article{
    private String url;
    private String name;
    private String portion;
    private String timer;
    private String ingredients;
    private String instruction;
    private String urlJpg;

    public Article(String url, String name, String portion, String timer, String ingredients, String instruction, String urlJpg) {
        this.url = url;
        this.name = name;
        this.portion = portion;
        this.timer = timer;
        this.ingredients = ingredients;
        this.instruction = instruction;
        this.urlJpg = urlJpg;
    }

    public String getUrlJpg() {
        return urlJpg;
    }

    public void setUrlJpg(String urlJpg) {
        this.urlJpg = urlJpg;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        instruction = instruction;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getTimer() {
        return timer;
    }

    public void setTimer(String timer) {
        this.timer = timer;
    }

    public String getPortion() {
        return portion;
    }

    public void setPortion(String portion) {
        this.portion = portion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return  "Название рецепта \t  " + name +
                " кол-во порций  " + portion +
                " время приготовления  " + timer + "\n"+
                "Ингредиенты  \t\t  " + ingredients + "\n" +
                "Инструкция к рецепту  " + instruction + "\n" +
                "Картинка  \t\t  " + urlJpg + "\n";
    }
}
