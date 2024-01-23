package Satelity;

import Satelity.KingOfSat;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static ArrayList<String> norad1 = new ArrayList<>();

    public static void main(String[] args) {
        Satelity satelity = new Satelity();
        KingOfSat kingOfSat = new KingOfSat();
        List<String[]> kingOfSatList = kingOfSat.getSatellitesData();
        List<String[]> satellitesList = satelity.getSatellitesData();
        System.out.println("Satelity ze strony SATBEAM:\n");
        if (satellitesList.isEmpty()) {
            System.out.println("No data retrieved.");
        } else {
            System.out.format("%-5s %-10s %-15s %-10s %-15s %-20s %-20s %-25s %-15s %-15s%n", "Orb.Pos.", "Status", "Satellite Name", "NORAD", "COSPAR", "Model", "Operator", "Launch Site", "Launch Mass", "Launch Date");

            for (String[] satellite : satellitesList) {
                System.out.println(String.join("  ", satellite));
                norad1.add(satellite[3]);
            }
        }

        if (kingOfSatList.isEmpty()) {
            System.out.println("No data retrieved.");
        } else {
            System.out.printf("%-5s %-10s %-15s %-10s %-15s %-20s %-20s %-25s %-15s", "Orb.Pos.", "Status", "Satellite Name", "satelliteName", "orbitalPosition", "channels", "longitude", "maxDeclination", "norad");

            for (String[] satellite : kingOfSatList) {
                if(norad1.contains(satellite[5])){
                    System.out.println(satellite[0] + " - Ta satelita znajduje się również na stronie SATBEAM");
                    continue;
                }
                System.out.println("\nPoniższa satelita nie znajduje się na stronie SATBEAM:\n");
                System.out.println(String.join("  ", satellite));

            }
        }


    }
}