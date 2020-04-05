import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Book {
    private String title;
    private double price;

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    Book(String title, double price) {
        this.title = title;
        this.price = price;
    }

    public static ArrayList<Book> searchBooks(String author) {
        String link = "https://www.bertrand.pt/pesquisa/";
        String search = author;
        ArrayList<Book> found = new ArrayList<Book>();
        boolean gotTitle = false;
        try {
            HttpRequestFunctions.httpRequest1(link, search, "Bertrand.html");
            String titleSelector = "title-lnk track";
            String title = ">.*<";
            String priceSelector = "active-price";
            String price = ">.*<";
            Pattern titleSelectorP = Pattern.compile(titleSelector);
            Pattern titleP = Pattern.compile(title);
            Pattern priceSelectorP = Pattern.compile(priceSelector);
            Pattern priceP = Pattern.compile(price);
            FileReader reader = new FileReader("Bertrand.html");

            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            String t = "";
            double p = 0;
            while((line = bufferedReader.readLine()) != null) {
                if(!gotTitle) {
                    Matcher m1 = titleSelectorP.matcher(line);
                    while (m1.find()) {
                        Matcher m2 = titleP.matcher(line);
                        while (m2.find()) {
                            String temp = m2.group();
                            temp = temp.substring(1, temp.length() - 1);
                            t = temp;
                            gotTitle = true;
                        }
                    }
                } else {
                    Matcher m3 = priceSelectorP.matcher(line);
                    while (m3.find()) {
                        Matcher m4 = priceP.matcher(line);
                        while (m4.find()) {
                            String temp = m4.group();
                            temp = temp.substring(1, temp.length() - 2);
                            NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
                            Number number = format.parse(temp);
                            p = number.doubleValue();

                            found.add(new Book(t, p));
                            gotTitle = false;
                        }
                    }
                }
            }
            reader.close();

            } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return found;
    }

    @Override
    public String toString() {
        return title + " / " + price + "€";
    }
}
