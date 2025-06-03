package File;

import Entity.*;
import java.io.*;
import java.util.*;

public class FileIO {
    public static void loadFromFile(Train[] trains) {
        try {
            Scanner sc = new Scanner(new File("./File/Trains.txt"));
            while (sc.hasNextLine()) {
                String[] data = sc.nextLine().split(";");
                trains[Integer.parseInt(data[0])] = new Train(data[1]);
            }
            sc.close();

            sc = new Scanner(new File("./File/Tickets.txt"));
            while (sc.hasNextLine()) {
                String[] data = sc.nextLine().split(";");
                int trainNo = Integer.parseInt(data[0]);
                int seatNo = Integer.parseInt(data[1]);
                String name = data[2];
                double price = Double.parseDouble(data[3]);
                trains[trainNo].insertTicket(seatNo, new Ticket(name, seatNo, price));
            }
            sc.close();
        } catch (Exception e) {
            System.out.println("Error loading files: " + e.getMessage());
        }
    }

    public static void saveChangesInFile(Train[] trains) {
        try {
            FileWriter trainWriter = new FileWriter("./File/Trains.txt");
            FileWriter ticketWriter = new FileWriter("./File/Tickets.txt");

            for (int i = 0; i < trains.length; i++) {
                if (trains[i] != null) {
                    trainWriter.write(i + ";" + trains[i].getTrainName() + "\n");
                    Ticket[] tickets = trains[i].getAllTickets();
                    for (int j = 0; j < tickets.length; j++) {
                        if (tickets[j] != null) {
                            ticketWriter.write(i + ";" + j + ";" +
                                tickets[j].getPassengerName() + ";" +
                                tickets[j].getPrice() + "\n");
                        }
                    }
                }
            }
            trainWriter.close();
            ticketWriter.close();
        } catch (IOException e) {
            System.out.println("Error saving files: " + e.getMessage());
        }
    }
}
