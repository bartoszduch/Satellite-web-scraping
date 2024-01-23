package Satelity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Satelity {
    final String satBeamsUrl = "https://www.satbeams.com/satellites";
    List<String[]> satellitesList = new ArrayList<>();
    List<String[]> transpondersList = new ArrayList<>();


    public String orbitalPosition;
    public String frequence;
    public String polarization;
    public String txp;
    public String beam;
    public String standard;
    public String modulation;
    public String srFec;
    public String networkBitrate;
    public String status;
    public String satelliteName;
    public String norad;
    public String cospar;
    public String model;
    public String operator;
    public String launchSite;
    public String launchMass;
    public String launchDate;

    public Satelity() {
        webScraper();
        // displayData();
    }

    void webScraper() {
        try {
            final Document document = Jsoup.connect(satBeamsUrl).get();
            System.out.println("Main Page Scraped Successfully");
            scrapMainPage(document);
        } catch (IOException e) {
            System.err.println("An error occurred while connecting to URL: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public List<String> scrapMainPage(Document document) {
        Elements rows = document.select(".class_tr");
        int satelliteCount = 0;

        List<String> noradList = new ArrayList<>();

        for (Element row : rows) {
            Elements cells = row.select("td");

            if (cells.size() >= 11) {
                orbitalPosition = cells.get(1).text();
                status = cells.get(2).text();
                satelliteName = cells.get(3).select("a").text();
                norad = cells.get(4).text();
                cospar = cells.get(5).text();
                model = cells.get(6).text();
                operator = cells.get(7).text();
                launchSite = cells.get(8).text();
                launchMass = cells.get(9).text();
                launchDate = cells.get(10).text();

                // Dodawanie NORAD do listy
                noradList.add(norad);

                satellitesList.add(new String[]{orbitalPosition, status, satelliteName, norad, cospar, model,
                        operator, launchSite, launchMass, launchDate});

                String hrefValue = cells.get(3).select("a").attr("href");
                satelliteCount++;
            }
        }

        // Zwracamy listę NORAD po zakończeniu pętli
        return noradList;
    }

    List<String[]> getSatellitesData() {
        return satellitesList;
    }
    List<String[]> geTransponderstData() {
        return transpondersList;
    }

    void displaySatellitesData() {
        for (String[] array : satellitesList) {
            for (String element : array) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }
    void displayTranspondersData() {
        for(String[] array : transpondersList) {
            for(String element : array) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }
}
