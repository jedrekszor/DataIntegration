import org.jdom2.Document;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import static java.lang.System.exit;

public class Main {
    final static JTextArea ta = new JTextArea(38, 90);

    public static void main(String[] args) {
/////////////////////////////////////////TASK 3////////////////////////////////////////
//        3.a)
//        Task3.class3a();

//        3.b)
//        Task3.class3b("dates.txt", "out.txt");

//        3.c)
//        for(Object record : Task3.search_names("Ana")) {
//            System.out.println(record.toString());
//        }

//        3.d)
//        for(Object record : Task3.search_names_using_city("Coimbra")) {
//            System.out.println(record.toString());
//        }

//        3.e)
//        for(Object record : Task3.search_names_for_job("Professor")) {
//            System.out.println(record.toString());
//        }

//        3.f)
//        System.out.println(Task3.search_name("5555"));

//        3.g)
//        System.out.println(Task3.search_age("33"));

//        3.h)
//        System.out.println(Task3.search_city("333"));

//        3.i)
//        System.out.println(Task3.search_job("222"));




/////////////////////////////////////////TASK 4////////////////////////////////////////
//        4.a)
//        Task4.class4a();

//        4.b)
//        for(Object record : Book.searchBooks("Paul Auster")) {
//            System.out.println(record.toString());
//        }

//        4.c)
//        for(Object record : Book.searchBooks("J. K. Rowling")) {
//            System.out.println(record.toString());
//        }



/////////////////////////////////////////TASK 5////////////////////////////////////////
//        5.b)
//        BookTask5 liv = new BookTask5("1111", "Os maias", "Eça de queiroz", 15.90);
//        Document doc = XMLJDomFunctions.lerDocumentoXML("livro.xml");
//        doc = Task5.addBook(liv, doc);
//        XMLJDomFunctions.escreverDocumentoParaFicheiro(doc, "livro.xml");

//        5.d)
//        Task5.addBooksFromFile();

//        5.e)
//        Document doc = XMLJDomFunctions.lerDocumentoXML("livros.xml");
//        doc = Task5.removeBook("Auster", doc);
//        if (doc != null)
//            XMLJDomFunctions.escreverDocumentoParaFicheiro(doc, "livros.xml");

//        5.f)
//        Document doc = XMLJDomFunctions.lerDocumentoXML("livros.xml");
//        doc = Task5.removeBookISBN("978-972-0-04645-1 ", doc);
//        if (doc != null)
//            XMLJDomFunctions.escreverDocumentoParaFicheiro(doc, "livros.xml");

//        5.g)
//        Document doc = XMLJDomFunctions.lerDocumentoXML("livros.xml");
//        doc = Task5.changePrice("978-972-2-05418-8 ", 22.22, doc);
//        if (doc != null)
//            XMLJDomFunctions.escreverDocumentoParaFicheiro(doc, "livros.xml");

//        5.h)
        gui();
    }



    static void gui() {
        JFrame frame = new JFrame();
        frame.setSize(1000, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        JFrame.setDefaultLookAndFeelDecorated(true);


        JScrollPane scroll = new JScrollPane(ta);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        ta.setEditable(false);
        frame.getContentPane().add(scroll);

        JMenuBar bar = new JMenuBar();
        JMenu main = new JMenu("Options");
        bar.add(main);

        JMenuItem view = new JMenuItem("View file");
        final JMenuItem addBook = new JMenuItem("Add a book");
        JMenuItem addBookTXT = new JMenuItem("Add books from txt");
        final JMenuItem removeBookAuthor = new JMenuItem("Remove books by author");
        final JMenuItem removeBookIsbn = new JMenuItem("Remove book by isbn");
        JMenuItem changePrice = new JMenuItem("Change price by isbn");
        JMenuItem exit = new JMenuItem("Exit");

        main.add(view);
        main.add(addBook);
        main.add(addBookTXT);
        main.add(removeBookAuthor);
        main.add(removeBookIsbn);
        main.add(changePrice);
        main.add(exit);

        view.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                view();
            }
        });

        addBook.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addBook();
            }
        });

        addBookTXT.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Task5.addBooksFromFile();
            }
        });

        removeBookAuthor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeBookAuthor();
            }
        });

        removeBookIsbn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeBookIsbn();
            }
        });

        changePrice.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                changePrice();
            }
        });

        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exit(0);
            }
        });

        frame.setJMenuBar(bar);
        frame.setVisible(true);
    }

    static void view() {
        Document doc = XMLJDomFunctions.lerDocumentoXML("livros.xml");
        String text = XMLJDomFunctions.escreverDocumentoString(doc);
        ta.setText(text);
    }

    static void addBook() {
        final JFrame next = new JFrame("Add a book");
        JFrame.setDefaultLookAndFeelDecorated(true);
        next.setSize(400, 200);
        next.setLayout(new FlowLayout());
        Box panel = Box.createVerticalBox();
        Box row = Box.createHorizontalBox();
        next.add(panel);

        JLabel main = new JLabel("Data of the new book: ");
        main.setHorizontalAlignment(JLabel.LEFT);
        panel.add(main);

        JLabel isbn = new JLabel("ISBN:    ");
        isbn.setHorizontalAlignment(JLabel.LEFT);
        final JTextField isbnTF = new JTextField();
        row.add(isbn);
        row.add(isbnTF);
        panel.add(row);

        JLabel title = new JLabel("Title:      ");
        title.setHorizontalAlignment(JLabel.LEFT);
        final JTextField titleTF = new JTextField();
        row = Box.createHorizontalBox();
        row.add(title);
        row.add(titleTF);
        panel.add(row);

        JLabel author = new JLabel("Author: ");
        author.setHorizontalAlignment(JLabel.LEFT);
        final JTextField authorTF = new JTextField();
        row = Box.createHorizontalBox();
        row.add(author);
        row.add(authorTF);
        panel.add(row);

        JLabel price = new JLabel("Price:    ");
        price.setHorizontalAlignment(JLabel.LEFT);
        final JTextField priceTF = new JTextField();
        row = Box.createHorizontalBox();
        row.add(price);
        row.add(priceTF);
        panel.add(row);

        JButton button = new JButton("Add the book");
        panel.add(button);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                BookTask5 book = new BookTask5(isbnTF.getText(), titleTF.getText(), authorTF.getText(), Double.parseDouble(priceTF.getText()));
                Document doc = XMLJDomFunctions.lerDocumentoXML("livros.xml");
                doc = Task5.addBook(book, doc);
                XMLJDomFunctions.escreverDocumentoParaFicheiro(doc, "livros.xml");
                next.dispatchEvent(new WindowEvent(next, WindowEvent.WINDOW_CLOSING));
                view();
            }
        });

        next.setVisible(true);
    }

    static void removeBookAuthor() {
        final JFrame next = new JFrame("Remove books of author");
        JFrame.setDefaultLookAndFeelDecorated(true);
        next.setSize(400, 200);
        next.setLayout(new FlowLayout());
        Box panel = Box.createVerticalBox();
        Box row = Box.createHorizontalBox();
        next.add(panel);

        JLabel main = new JLabel("Name of the author: ");
        panel.add(main);

        final JTextField authorTF = new JTextField();
        JButton button = new JButton("Remove the books");
        row.add(authorTF);
        row.add(button);
        panel.add(row);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Document doc = XMLJDomFunctions.lerDocumentoXML("livros.xml");
                doc = Task5.removeBook(authorTF.getText(), doc);
                if (doc != null)
                    XMLJDomFunctions.escreverDocumentoParaFicheiro(doc, "livros.xml");
                next.dispatchEvent(new WindowEvent(next, WindowEvent.WINDOW_CLOSING));
                view();
            }
        });

        next.setVisible(true);
    }

    static void removeBookIsbn() {
        final JFrame next = new JFrame("Remove book with ISBN");
        JFrame.setDefaultLookAndFeelDecorated(true);
        next.setSize(400, 200);
        next.setLayout(new FlowLayout());
        Box panel = Box.createVerticalBox();
        Box row = Box.createHorizontalBox();
        next.add(panel);

        JLabel main = new JLabel("ISBN number: ");
        main.setHorizontalAlignment(JLabel.LEFT);
        panel.add(main);

        final JTextField authorTF = new JTextField();
        JButton button = new JButton("Remove the book");
        row.add(authorTF);
        row.add(button);
        panel.add(row);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Document doc = XMLJDomFunctions.lerDocumentoXML("livros.xml");
                doc = Task5.removeBookISBN(authorTF.getText(), doc);
                if (doc != null)
                    XMLJDomFunctions.escreverDocumentoParaFicheiro(doc, "livros.xml");
                next.dispatchEvent(new WindowEvent(next, WindowEvent.WINDOW_CLOSING));
                view();
            }
        });

        next.setVisible(true);
    }

    static void changePrice() {
        final JFrame next = new JFrame("Change the price of a book");
        JFrame.setDefaultLookAndFeelDecorated(true);
        next.setSize(400, 200);
        next.setLayout(new FlowLayout());
        Box panel = Box.createVerticalBox();
        Box row = Box.createHorizontalBox();
        next.add(panel);

        JLabel isbn = new JLabel("ISBN:       ");
        isbn.setHorizontalAlignment(JLabel.LEFT);
        final JTextField isbnTF = new JTextField();
        row.add(isbn);
        row.add(isbnTF);
        panel.add(row);

        JLabel price = new JLabel("New price:");
        price.setHorizontalAlignment(JLabel.LEFT);
        final JTextField priceTF = new JTextField();
        row = Box.createHorizontalBox();
        row.add(price);
        row.add(priceTF);
        panel.add(row);

        JButton button = new JButton("Change the price");
        panel.add(button);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Document doc = XMLJDomFunctions.lerDocumentoXML("livros.xml");
                doc = Task5.changePrice(isbnTF.getText(), Double.parseDouble(priceTF.getText()), doc);
                if (doc != null)
                    XMLJDomFunctions.escreverDocumentoParaFicheiro(doc, "livros.xml");
                next.dispatchEvent(new WindowEvent(next, WindowEvent.WINDOW_CLOSING));
                view();
            }
        });

        next.setVisible(true);
    }
}
