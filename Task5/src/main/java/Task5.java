import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task5 {

    public static Document addBook (BookTask5 book, Document doc) {
        Element root;
        if (doc == null) {
            root = new Element("catalogue");
            doc = new Document(root);
        } else {
            root = doc.getRootElement();
        }

        Element newBook = new Element("book");
        Attribute isbn = new Attribute("isbn",book.getIsbn());
        newBook.setAttribute(isbn);
        Element title = new Element("title").addContent(book.getTitle());
        Element author = new Element("author").addContent(book.getAuthor());
        Element price = new Element("price").addContent(String.valueOf(book.getPrice()));
        newBook.addContent(title);
        newBook.addContent(author);
        newBook.addContent(price);
        root.addContent(newBook);
        return doc;
    }

    public static void addBooksFromFile() {
        try {
            FileReader reader = new FileReader("livros.txt");
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            Document doc = XMLJDomFunctions.lerDocumentoXML("livros.xml");
            while((line = bufferedReader.readLine()) != null) {
                String[] data = line.split("\\*\\*\\*");
                BookTask5 book = new BookTask5(data[0], data[1], data[2], Double.parseDouble(data[3]));

                doc = addBook(book, doc);
                XMLJDomFunctions.escreverDocumentoParaFicheiro(doc, "livros.xml");

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Document removeBook (String searched, Document doc) {
        boolean found = false;
        Element root;
        if (doc == null) {
            System.out.println("No such file");
            return null;
        } else {
            root = doc.getRootElement();
        }

        List books = root.getChildren("book");
        for(int i = 0; i < books.size(); i++) {
            Element book = (Element)books.get(i);
            if(book.getChild("author").getText().contains(searched)) {
                book.getParent().removeContent(book);
                System.out.println("Book removed");
                found = true;
            }
        }
        if(!found) {
            System.out.println("No instances of " + searched + " found");
            return null;
        }

        return doc;
    }

    public static Document removeBookISBN(String isbn, Document doc) {
        boolean found = false;
        Element root;
        if (doc == null) {
            System.out.println("No such file");
            return null;
        } else {
            root = doc.getRootElement();
        }

        List books = root.getChildren("book");
        for(int i = 0; i < books.size(); i++) {
            Element book = (Element)books.get(i);
            if(book.getAttributeValue("isbn").equals(isbn)) {
                book.getParent().removeContent(book);
                found = true;
            }
        }
        if(!found) {
            System.out.println("No instances of " + isbn + " found");
            return null;
        }
        return doc;
    }

    public static Document changePrice (String isbn, double newPrice, Document doc) {
        boolean found = false;
        Element root;
        if (doc == null) {
            System.out.println("No such file");
            return null;
        } else {
            root = doc.getRootElement();
        }

        List books = root.getChildren("book");
        for(int i = 0; i < books.size(); i++) {
            Element book = (Element)books.get(i);
            if(book.getAttributeValue("isbn").equals(isbn)) {
                System.out.println(book.getChildText("title") + ", price: " + book.getChildText("price") + "€");

                book.getChild("price").setText(String.valueOf(newPrice));
                found = true;
            }
        }
        if(!found) {
            System.out.println("No instances of " + isbn + " found");
            return null;
        }
        return doc;
    }
}
