package parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    public static void Parse(){
        ToDB db = new ToDB();
        List<Article> articleList = new ArrayList<>();
        int j = 1;

        for (Integer i = new Integer(1);i < 2; i++){

            Document doc = null;
            try {
                doc = Jsoup.connect("https://eda.ru/recepty?page=" + i.toString()).get();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Elements h1Elements = doc.getElementsByAttributeValue("class", "horizontal-tile__item-title item-title" );

            h1Elements.forEach(h1Element ->{
                Element aElement = h1Element.child(0);
                String url = aElement.attr("href");
                String title = aElement.child(0).text();

                Document recipePage = new Document("");
                try {
                    recipePage = Jsoup.connect( "https://eda.ru" + url).get();
                } catch (IOException e) {
                    System.out.println("Can not connect to " + url + ".");
                    e.printStackTrace();
                }

                String portion = recipePage.getElementsByAttributeValue("class", "info-text js-portions-count-print").text();
                String timer = recipePage.getElementsByAttributeValue("class", "info-text").text();
                if (!(timer.contains("мин")|| timer.contains("час")))
                    timer = "-";

                /*String ingredients = "";
                Elements bElements = recipePage.getElementsByAttributeValue("class", "ingredients-list layout__content-col").first().getElementsByAttributeValue("class", "ingredients-list__content");
                for(Element element : bElements) {
                    ingredients += element.text() + " ";
                }*/

                //String ingredients = "";
                Elements bElements = recipePage.getElementsByAttributeValue("class", "js-tooltip js-tooltip-ingredient");
                for(Element element : bElements) {
                    db.PutToDB(element.text());
                }

                String instruction = "";
                Elements iElements = recipePage.getElementsByAttributeValue("class", "recipe__steps");
                for(Element element : iElements){
                    instruction += element.getElementsByAttributeValue("style", "white-space: pre-line").text();
                }

                String urlJpg = recipePage.getElementsByTag("image").attr("xlink:href");


                //articleList.add(new Article(url,title, portion,timer,ingredients, instruction, urlJpg));
                db.PutToDB(title, instruction, urlJpg);
                //dbi.PutToDB(ingredients);

            });
            //if (i % 2 == 0){ articleList.forEach(System.out::println); }
        }
        articleList.forEach(System.out::println);


        db.CloseConnection();
    }
}

