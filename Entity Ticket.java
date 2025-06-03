package Entity;

public class Ticket {
    private String passengerName;
    private int seatNo;
    private double price;

    public Ticket() {
        System.out.println("E-Ticket Created.");
    }

    public Ticket(String passengerName, int seatNo, double price) {
        System.out.println("P-Ticket Created.");
        setPassengerName(passengerName);
        setSeatNo(seatNo);
        setPrice(price);
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setSeatNo(int seatNo) {
        this.seatNo = seatNo;
    }

    public int getSeatNo() {
        return seatNo;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void showTicket() {
        System.out.println("Passenger Name: " + passengerName);
        System.out.println("Seat No: " + seatNo);
        System.out.println("Price: " + price + " USD");
    }

    public String getTicket() {
        return "Passenger Name: " + passengerName + "\n" +
               "Seat No: " + seatNo + "\n" +
               "Price: " + price + " USD\n";
    }
}
