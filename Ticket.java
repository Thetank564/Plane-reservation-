package airlines.reservation.system;

import java.util.*;
import java.util.Set;

/**
 *
 * @author antho
 */
public class Ticket extends Plane implements Comparable<Ticket> {

    protected int day, month, year;
    protected double time;
    String rowAndSeat = this.row + Integer.toString(this.seat);
    static Set<Ticket> allTks = new LinkedHashSet<Ticket>();
    static Stack<Ticket> allBoughtTickets = new Stack<Ticket>();

    public Ticket(int day, int month, int year, double time, String originalLocation, String FinalLocation, char row, int seat) {
        super(originalLocation, FinalLocation, row, seat);
        this.day = day;
        this.month = month;
        this.year = year;
        this.time = time;
    }

    public static void last15BoughtTickets(Stack<Ticket> s) {

        if (s.size() > 0 && s.size() >= 15) {
            System.out.println("The latest 15 tickets bought in order of latest bought are : ");
            for (int i = 0; i < 15; i++) {
                int j = i + 1;
                System.out.println(j + " " + Ticket.allBoughtTickets.get(i));
            }
        } else if (s.size() > 0) {
            System.out.println("The latest " + s.size() + " tickets bought in order of latest bought are : ");
            for (int i = 0; i < s.size(); i++) {
                int j = i + 1;
                System.out.println(j + " " + Ticket.allBoughtTickets.get(i));
            }
        } else {
            System.out.println("There are no Tickets.");
        }
    }

    public static void makeTickets() {
        int j;
        for (j = 65; j < 77; j++) {
            for (int i = 1; i < 10; i++) {
                Ticket.allTks.add(new Ticket(10, 12, 2022, 1200, "from Montreal", "to Miami", (char) j, i));
            }
        }
    }

    //Comparator
    public static void sortTickets(Set<Ticket> tlist) {
        List<Ticket> tl = new ArrayList<>();
        tl.addAll(tlist);
        Collections.sort(tl);
        tlist.removeAll(tl);
        tlist.addAll(tl);

        //empty tlist and re-add tl
    }

    public static void printTickets() {
        for (Ticket t : Ticket.allTks) {
            if(t.row > 64 && t.row < 77){
                System.out.println(String.format("%5s", ""));
                System.out.print(String.format("%5s %5s", "Section", t.row));
                if(t.seat > 0 && t.seat < 11){
                 System.out.print(String.format("%5s %5s", "Row", t.seat));
            }
            }
            System.out.println();
        }
    }

    @Override
    public String toString() {
        return " Ticket " + this.row + this.seat + " " + this.originalLocation + " " + this.FinalLocation + " " + day + "/" + month + "/" + year + " " + time;
    }

    public static Ticket findTicket(String userInput) {
        char charTicket = userInput.substring(0, 1).toUpperCase().charAt(0);
        String seat = userInput.substring(1, userInput.length());
        for (Ticket t : allTks) {
            if (t.row == charTicket && seat.equals("" + t.seat)) {
                return t;
            }
        }
        System.out.println("Ticket not found.");
        return null;
    }

    public static Ticket findTicket(String userInput, Stack<Ticket> s) {
        char charTicket = userInput.substring(0, 1).toUpperCase().charAt(0);
        String seat = userInput.substring(1, userInput.length());
        for (Ticket t : s) {
            if (t.row == charTicket && seat.equals("" + t.seat)) {
                return t;
            }
        }
        System.out.println("Ticket not found.");
        return null;
    }

    public static Ticket findTicket() {
        String chosenTicket = UserInput.getTicketString();
        char charTicket = chosenTicket.substring(0, 1).toUpperCase().charAt(0);
        String seat = chosenTicket.substring(1, chosenTicket.length());
        for (Ticket t : allTks) {
            if (t.row == charTicket && seat.equals("" + t.seat)) {
                return t;
            }
        }
        return findTicket();
    }

    public static void removeTicketFromAll(Ticket t) {
        if (allTks.size() != 0) {
            allTks.remove(t);
        } else {
            System.out.println("Invalid! there are no tickets to remove.");
        }
    }

    @Override
    public int compareTo(Ticket t) {
        return this.rowAndSeat.compareTo(t.rowAndSeat);
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public String getOriginalLocation() {
        return originalLocation;
    }

    public void setOriginalLocation(String originalLocation) {
        this.originalLocation = originalLocation;
    }

    public String getFinalLocation() {
        return FinalLocation;
    }

    public void setFinalLocation(String FinalLocation) {
        this.FinalLocation = FinalLocation;
    }

    public char getRow() {
        return row;
    }

    public void setRow(char row) {
        this.row = row;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

}
