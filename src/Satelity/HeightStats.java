package Satelity;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class HeightStats {

    public static String url;

    public static void main(String[] args) {

        url = "https://www.satbeams.com/satellites?norad=25638";

        try {
            Connection.Response response = Jsoup.connect(url).execute();
            if (response.statusCode() == 200) {
                Document document = response.parse();

                Elements data1 = document.select("#table_wrap tbody tr:nth-child(2) td b");

                if (!data1.isEmpty()) {
                    for (int i = 0; i < (data1.size()) / 2; i++) {
                        Element data2 = data1.get(i).nextElementSibling();

                        // Sprawdzamy, czy następne rodzeństwo istnieje
                        if (data2 != null) {
                            System.out.println("Pobrany tekst: " + data2.text());
                        } else {
                            System.out.println("Brak następnego rodzeństwa po elemencie: " + data2.text());
                        }
                    }

                } else {
                    System.out.println("Nie znaleziono elementów dla selektora '#table_wrap tbody tr:nth-child(2) td b'.");
                }

            } else {
                System.out.println("Page download failed");
            }
        } catch (IOException e) {
            System.out.println("Error! Page download failed");
        }
    }
}