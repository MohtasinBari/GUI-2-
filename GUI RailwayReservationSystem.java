package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Entity.*;
import File.*;

public class RailwayReservationSystem extends JFrame implements ActionListener {
    Font font15 = new Font("Consoles", Font.BOLD, 15);

    JLabel trainNoLabel, trainNameLabel, seatLabel, passengerLabel, priceLabel;
    JButton createTrainButton, bookTicketButton, removeTicketButton;
    JButton clearTrainButton, clearTicketButton, saveButton;

    JTextField trainNoField, trainNameField, seatField, passengerField, priceField;
    JTextArea screen;

    Train[] trains = new Train[10];

    public RailwayReservationSystem() {
        super("Railway Reservation System");
        this.setSize(800, 600);
        this.setLocation(200, 50);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);

        // ✅ Use image only for the window icon
        this.setIconImage(new ImageIcon("./images/train.png").getImage());

        FileIO.loadFromFile(trains);

        createLabel(10, 10, 300, 30, "Train Management Section");

        trainNoLabel = createLabel(10, 50, 150, 30, "Train No:");
        trainNoField = createTextField(160, 50, 150, 30, "");

        trainNameLabel = createLabel(10, 90, 150, 30, "Train Name:");
        trainNameField = createTextField(160, 90, 150, 30, "");

        createTrainButton = createButton(10, 130, 300, 30, "Create Train");
        clearTrainButton = createButton(10, 170, 300, 30, "Clear Train Fields");

        createLabel(10, 220, 300, 30, "Ticket Booking Section");

        seatLabel = createLabel(10, 260, 150, 30, "Seat No:");
        seatField = createTextField(160, 260, 150, 30, "");

        passengerLabel = createLabel(10, 300, 150, 30, "Passenger Name:");
        passengerField = createTextField(160, 300, 150, 30, "");

        priceLabel = createLabel(10, 340, 150, 30, "Ticket Price:");
        priceField = createTextField(160, 340, 150, 30, "");

        bookTicketButton = createButton(10, 380, 300, 30, "Book Ticket");
        removeTicketButton = createButton(10, 420, 300, 30, "Remove Ticket");
        clearTicketButton = createButton(10, 460, 300, 30, "Clear Ticket Fields");

        saveButton = createButton(10, 500, 300, 30, "Save Changes");
        saveButton.setBackground(Color.YELLOW);

        screen = new JTextArea();
        screen.setFont(font15);
        JScrollPane scrollPane = new JScrollPane(screen);
        scrollPane.setBounds(330, 20, 440, 510);
        this.add(scrollPane);

        updateScreen();
        this.setVisible(true);
    }

    JLabel createLabel(int x, int y, int w, int h, String text) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, w, h);
        label.setFont(font15);
        this.add(label);
        return label;
    }

    JTextField createTextField(int x, int y, int w, int h, String text) {
        JTextField tf = new JTextField(text);
        tf.setBounds(x, y, w, h);
        tf.setFont(font15);
        this.add(tf);
        return tf;
    }

    JButton createButton(int x, int y, int w, int h, String text) {
        JButton btn = new JButton(text);
        btn.setBounds(x, y, w, h);
        btn.setFont(font15);
        btn.setBackground(new Color(66, 245, 179));
        btn.addActionListener(this);
        this.add(btn);
        return btn;
    }

    public void updateScreen() {
        String data = "";
        for (int i = 0; i < trains.length; i++) {
            if (trains[i] != null) {
                data += i + ". " + trains[i].getTrain() + "\n";
            }
        }
        screen.setText(data);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createTrainButton) {
            String tno = trainNoField.getText();
            String tname = trainNameField.getText();
            if (!tno.isEmpty() && !tname.isEmpty()) {
                int idx = Integer.parseInt(tno);
                if (idx >= 0 && idx < trains.length) {
                    if (trains[idx] == null) {
                        trains[idx] = new Train(tname);
                        updateScreen();
                    } else {
                        JOptionPane.showMessageDialog(this, "Train already exists!", "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid Train Index!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        else if (e.getSource() == bookTicketButton) {
            String tno = trainNoField.getText();
            String seat = seatField.getText();
            String pname = passengerField.getText();
            String price = priceField.getText();

            if (!tno.isEmpty() && !seat.isEmpty() && !pname.isEmpty() && !price.isEmpty()) {
                try {
                    int tIndex = Integer.parseInt(tno);
                    int sIndex = Integer.parseInt(seat);

                    if (tIndex >= 0 && tIndex < trains.length && trains[tIndex] != null) {
                        if (sIndex >= 0 && sIndex < 100) {
                            if (trains[tIndex].getTicket(sIndex) == null) {
                                Ticket ticket = new Ticket(pname, sIndex, Double.parseDouble(price));
                                trains[tIndex].insertTicket(sIndex, ticket);
                                updateScreen();
                            } else {
                                JOptionPane.showMessageDialog(this, "Seat already booked!", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(this, "Invalid Seat Number!", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Train not found at index " + tIndex, "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid number format!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please fill all ticket fields.", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        }

        else if (e.getSource() == removeTicketButton) {
            String tno = trainNoField.getText();
            String seat = seatField.getText();
            if (!tno.isEmpty() && !seat.isEmpty()) {
                int tIndex = Integer.parseInt(tno);
                int sIndex = Integer.parseInt(seat);
                if (trains[tIndex] != null && trains[tIndex].getTicket(sIndex) != null) {
                    trains[tIndex].removeTicket(sIndex);
                    updateScreen();
                }
            }
        }

        else if (e.getSource() == clearTrainButton) {
            trainNoField.setText("");
            trainNameField.setText("");
        }

        else if (e.getSource() == clearTicketButton) {
            seatField.setText("");
            passengerField.setText("");
            priceField.setText("");
        }

        else if (e.getSource() == saveButton) {
            if (JOptionPane.showConfirmDialog(this, "Save Changes?") == JOptionPane.YES_OPTION) {
                FileIO.saveChangesInFile(trains);
            }
        }
    }

    public static void main(String[] args) {
        new RailwayReservationSystem();
    }
}
