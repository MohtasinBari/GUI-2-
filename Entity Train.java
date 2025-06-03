package Entity;

public class Train {
	private String trainName;
	private Ticket[] tickets = new Ticket[100]; // assuming 100 seats per train

	public Train() {
		// System.out.println("E-Train Created.");
	}

	public Train(String trainName) {
		// System.out.println("P-Train Created.");
		this.trainName = trainName;
	}

	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}

	public String getTrainName() {
		return trainName;
	}

	public void insertTicket(int index, Ticket t) {
		tickets[index] = t;
	}

	public Ticket getTicket(int index) {
		return tickets[index];
	}

	public void removeTicket(int index) {
		tickets[index] = null;
	}

	public void showTrain() {
		System.out.println("-------- " + trainName + " --------");
		for (int i = 0; i < tickets.length; i++) {
			if (tickets[i] != null) {
				System.out.println("-------- " + i + " --------");
				tickets[i].showTicket();
			}
		}
		System.out.println("-------------------------\n");
	}

	public String getTrain() {
		String data = "";
		data += "******** " + trainName + " **********\n";
		for (int i = 0; i < tickets.length; i++) {
			if (tickets[i] != null) {
				data += "~~~~~~~~ Seat " + i + " ~~~~~~~~\n";
				data += tickets[i].getTicket();
			}
		}
		data += "*******************************\n";
		return data;
	}

	public Ticket[] getAllTickets() {
		return tickets;
	}
}
