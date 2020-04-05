import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task3 {

    static final int ID_OFFSET = 0;
    static final int NAME_OFFSET = 1;
    static final int AGE_OFFSET = 2;
    static final int CITY_OFFSET = 3;
    static final int JOB_OFFSET = 4;

    static void class3a() {
        String phones="919191919 929992221 91111111111 239494582 9199999999 967779999";
        String er = "9[1236]\\d{7}\\b";
        Pattern p = Pattern.compile(er);
        Matcher m = p.matcher(phones);
        while(m.find()) {
            System.out.println(m.group());
        }
    }

    static void class3b(String fileIn, String fileOut) {
        String er = "\\d{2}-\\d{2}-\\d{4}|\\d{2}/\\d{2}/\\d{4}";
        Pattern p = Pattern.compile(er);
        try {
            FileReader reader = new FileReader(fileIn);
            FileWriter writer = new FileWriter(fileOut, true);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while((line = bufferedReader.readLine()) != null) {
                Matcher m = p.matcher(line);
                while(m.find()) {
                    writer.write(m.group() + "\n");
                }
            }
            reader.close();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static ArrayList search_names(String search) {
        ArrayList<String> found = new ArrayList<String>();
        String er = ">[a-zA-Z\\s]*" + search + "[a-zA-Z\\s]*<";
        Pattern p = Pattern.compile(er);
        String link = "https://eden.dei.uc.pt/~abs/ID/pessoas.html";
        String searcher ="";
        try {
            HttpRequestFunctions.httpRequest1(link, searcher, "people.html");
            FileReader reader = new FileReader("people.html");
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while((line = bufferedReader.readLine()) != null) {
                Matcher m = p.matcher(line);
                while(m.find()) {
                    String temp = m.group();
                    temp = temp.substring(1, temp.length() - 1);
                    found.add(temp + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return found;
    }

    static ArrayList search_names_using_city(String search) {
        ArrayList<String> found = new ArrayList<String>();
        String er1 = ".*" + search + ".*";
        String er2 = ">[A-Z][a-zA-Z]*\\s[A-Z][a-zA-Z]*<";
        Pattern p1 = Pattern.compile(er1, Pattern.DOTALL);
        Pattern p2 = Pattern.compile(er2);
        String link = "https://eden.dei.uc.pt/~abs/ID/pessoas.html";
        String searcher ="";
        try {
            HttpRequestFunctions.httpRequest1(link, searcher, "people.html");
            FileReader reader = new FileReader("people.html");
            BufferedReader bufferedReader = new BufferedReader(reader);
            StringBuffer sb = new StringBuffer();
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) break;
                sb.append(line).append("\n");
            }
            String[] blocks = sb.toString().split("<tr>");
            for (int i = 2; i < blocks.length; i++) {
                blocks[i] = blocks[i].trim();
                String[] data = blocks[i].split("/td>");
                Matcher m1 = p1.matcher(data[CITY_OFFSET]);
                while(m1.find()) {
                    Matcher m2 = p2.matcher(data[NAME_OFFSET]);
                    while(m2.find()) {
                        String temp = m2.group();
                        temp = temp.substring(1, temp.length() - 1);
                        found.add(temp + "\n");
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return found;
    }

    static ArrayList search_names_for_job(String search) {
        ArrayList<String> found = new ArrayList<String>();
        String er1 = ".*" + search + ".*";
        String er2 = ">[A-Z][a-zA-Z]*\\s[A-Z][a-zA-Z]*<";
        Pattern p1 = Pattern.compile(er1, Pattern.DOTALL);
        Pattern p2 = Pattern.compile(er2);
        String link = "https://eden.dei.uc.pt/~abs/ID/pessoas.html";
        String searcher ="";
        try {
            HttpRequestFunctions.httpRequest1(link, searcher, "people.html");
            FileReader reader = new FileReader("people.html");
            BufferedReader bufferedReader = new BufferedReader(reader);
            StringBuffer sb = new StringBuffer();
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) break;
                sb.append(line).append("\n");
            }
            String[] blocks = sb.toString().split("<tr>");
            for (int i = 2; i < blocks.length; i++) {
                blocks[i] = blocks[i].trim();

                String[] data = blocks[i].split("/td>");

                Matcher m1 = p1.matcher(data[JOB_OFFSET]);
                while(m1.find()) {
                    Matcher m2 = p2.matcher(data[NAME_OFFSET]);
                    while(m2.find()) {
                        String temp = m2.group();
                        temp = temp.substring(1, temp.length() - 1);
                        found.add(temp + "\n");
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return found;
    }

    static String search_name(String search) {
        String found = null;
        String er1 = ".*" + search + ".*";
        String er2 = ">[A-Z][a-zA-Z]*\\s[A-Z][a-zA-Z]*<";
        Pattern p1 = Pattern.compile(er1, Pattern.DOTALL);
        Pattern p2 = Pattern.compile(er2);
        String link = "https://eden.dei.uc.pt/~abs/ID/pessoas.html";
        String searcher ="";
        try {
            HttpRequestFunctions.httpRequest1(link, searcher, "people.html");
            FileReader reader = new FileReader("people.html");
            BufferedReader bufferedReader = new BufferedReader(reader);
            StringBuffer sb = new StringBuffer();
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) break;
                sb.append(line).append("\n");
            }
            String[] blocks = sb.toString().split("<tr>");
            for (int i = 2; i < blocks.length; i++) {
                blocks[i] = blocks[i].trim();

                String[] data = blocks[i].split("/td>");

                Matcher m1 = p1.matcher(data[ID_OFFSET]);
                while(m1.find()) {
                    Matcher m2 = p2.matcher(data[NAME_OFFSET]);
                    while(m2.find()) {
                        String temp = m2.group();
                        temp = temp.substring(1, temp.length() - 1);
                        found = temp;
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        if(found == null)
            return null;
        return found;
    }

    static int search_age(String search) {
        int found = -1;
        String er1 = ".*>" + search + "<.*";
        String er2 = ">[0-9]{1,3}<";
        Pattern p1 = Pattern.compile(er1, Pattern.DOTALL);
        Pattern p2 = Pattern.compile(er2);
        String link = "https://eden.dei.uc.pt/~abs/ID/pessoas.html";
        String searcher ="";
        try {
            HttpRequestFunctions.httpRequest1(link, searcher, "people.html");
            FileReader reader = new FileReader("people.html");
            BufferedReader bufferedReader = new BufferedReader(reader);
            StringBuffer sb = new StringBuffer();
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) break;
                sb.append(line).append("\n");
            }
            String[] blocks = sb.toString().split("<tr>");
            for (int i = 2; i < blocks.length; i++) {
                blocks[i] = blocks[i].trim();

                String[] data = blocks[i].split("/td>");

                Matcher m1 = p1.matcher(data[ID_OFFSET]);
                while(m1.find()) {
                    Matcher m2 = p2.matcher(data[AGE_OFFSET]);
                    while(m2.find()) {
                        String temp = m2.group();
                        temp = temp.substring(1, temp.length() - 1);
                        found = Integer.parseInt(temp);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        if(found == -1)
            return -1;
        return found;
    }

    static String search_city(String search) {
        String found = null;
        String er1 = ".*>" + search + "<.*";
        String er2 = ">[A-Z][a-zA-Z\\s,]*<";
        Pattern p1 = Pattern.compile(er1, Pattern.DOTALL);
        Pattern p2 = Pattern.compile(er2);
        String link = "https://eden.dei.uc.pt/~abs/ID/pessoas.html";
        String searcher ="";
        try {
            HttpRequestFunctions.httpRequest1(link, searcher, "people.html");
            FileReader reader = new FileReader("people.html");
            BufferedReader bufferedReader = new BufferedReader(reader);
            StringBuffer sb = new StringBuffer();
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) break;
                sb.append(line).append("\n");
            }
            String[] blocks = sb.toString().split("<tr>");
            for (int i = 2; i < blocks.length; i++) {
                blocks[i] = blocks[i].trim();

                String[] data = blocks[i].split("/td>");

                Matcher m1 = p1.matcher(data[ID_OFFSET]);
                while(m1.find()) {
                    Matcher m2 = p2.matcher(data[CITY_OFFSET]);
                    while(m2.find()) {
                        String temp = m2.group();
                        temp = temp.substring(1, temp.length() - 1);
                        found = temp;
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        if(found == null)
            return null;
        return found;
    }

    static String search_job(String search) {
        String found = null;
        String er1 = ".*>" + search + "<.*";
        String er2 = ">[A-Z][a-zA-Z\\s,]*<";
        Pattern p1 = Pattern.compile(er1, Pattern.DOTALL);
        Pattern p2 = Pattern.compile(er2);
        String link = "https://eden.dei.uc.pt/~abs/ID/pessoas.html";
        String searcher ="";
        try {
            HttpRequestFunctions.httpRequest1(link, searcher, "people.html");
            FileReader reader = new FileReader("people.html");
            BufferedReader bufferedReader = new BufferedReader(reader);
            StringBuffer sb = new StringBuffer();
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) break;
                sb.append(line).append("\n");
            }
            String[] blocks = sb.toString().split("<tr>");
            for (int i = 2; i < blocks.length; i++) {
                blocks[i] = blocks[i].trim();

                String[] data = blocks[i].split("/td>");

                Matcher m1 = p1.matcher(data[ID_OFFSET]);
                while(m1.find()) {
                    Matcher m2 = p2.matcher(data[JOB_OFFSET]);
                    while(m2.find()) {
                        String temp = m2.group();
                        temp = temp.substring(1, temp.length() - 1);
                        found = temp;
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        if(found == null)
            return null;
        return found;
    }
}





