import java.io.IOException;

public class Task4 {

    public static void class4a(){
        String link = "https://eden.dei.uc.pt/~abs/ID/pessoas.html";
        String search ="";
        try {
            HttpRequestFunctions.httpRequest1(link, search, "task4_output.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
