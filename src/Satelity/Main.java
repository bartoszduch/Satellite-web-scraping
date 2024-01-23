package Satelity;

import java.util.List;
public class Main {

    public static void main(String[] args) {
        Satelity satelity = new Satelity();
        List<String[]> satellitesList = satelity.getSatellitesData();

        if (satellitesList.isEmpty()) {
            System.out.println("No data retrieved.");
        } else {
            // Wydrukuj nagłówek tabeli z wyznaczoną szerokością dla każdej kolumny
            System.out.format("%-5s %-10s %-15s %-10s %-15s %-20s %-20s %-25s %-15s %-15s%n", "Orb.Pos.", "Status", "Satellite Name", "NORAD", "COSPAR", "Model", "Operator", "Launch Site", "Launch Mass", "Launch Date");

            // Dla każdego satelity w liście
            for (String[] satellite : satellitesList) {
                // Wydrukuj dane satelity, każdą kolumnę wyrównaną do lewej i z określoną szerokością
                System.out.format("%-5s %-10s %-15s %-10s %-15s %-20s %-20s %-25s %-15s %-15s%n", satellite[0], satellite[1], satellite[2], satellite[3], satellite[4], satellite[5], satellite[6], satellite[7], satellite[8], satellite[9]);
            }
        }
    }
}