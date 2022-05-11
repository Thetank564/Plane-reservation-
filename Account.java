package airlines.reservation.system;

import java.io.*;
import java.util.*;

/**
 *
 * @author antho
 */
public class Account implements Iaccount, Comparable<Account> {

    private String firstName, lastName;
    private int age;
    private long accountID;
    Stack<Transactions> TransactionHistory = new Stack<Transactions>();
    Stack<Ticket> CurrentTickets = new Stack<Ticket>();
    static HashMap<Long, Account> accountMap = new HashMap<Long, Account>();

    @Override
    public void printAllCurrentTickets(Account a) {
        a.CurrentTickets.forEach((t) -> System.out.println(t.row + "" + t.seat));
    }

    @Override
    public void printTransactionHistory(Account a) {
        
       while(!a.TransactionHistory.isEmpty()){
            System.out.println(UserInput.ANSI_BLUE + a.TransactionHistory.pop() + UserInput.ANSI_BLACK);
    }
    }

    @Override
    public void removeTicketFromCurrentTickets(String ticket, Account a) {
        char charTicket = ticket.substring(0, 1).toUpperCase().charAt(0);
        String seat = ticket.substring(1, ticket.length());
        for (Ticket t : CurrentTickets) {
            if (t.row == charTicket && seat.equals("" + t.seat)) {
                Ticket.allTks.add(t);
                CurrentTickets.remove(t);
                a.TransactionHistory.add(new Transactions("Removed", t));
                Ticket.allBoughtTickets.remove(t);
                return;
            }
        }
        System.out.println("Ticket not found, therfor none are removed.");
    }

    @Override
    public void downloadTicket(Ticket t) throws Exception {
        try {
            String fileName = "c:\\AirlinesTicket.txt";
            FileOutputStream fout = new FileOutputStream(fileName);
            BufferedOutputStream bout = new BufferedOutputStream(fout);
            String output = this.getFirstName() + " " + this.getLastName() + " with the account ID : " + this.accountID + " has purchased the " + t.toString();
            byte b[] = output.getBytes();
            bout.write(b);
            bout.flush();
            bout.close();
            fout.close();
        } catch (IOException e) {
            System.out.println(UserInput.ANSI_RED + "Ticket receipt did not download." + UserInput.ANSI_BLACK);
        }
    }

    @Override
    public void printAccountInfo() {
        System.out.println("The information tied to the account ID " + this.accountID + "\nYour name is  " + this.getFirstName() + " " + this.getLastName());
        System.out.println("You are " + this.getAge() + " years old");
    }

    public static void makeAccount() {
        Account a = new Account();
        System.out.println(UserInput.ANSI_PURPLE + "To create an account, you will have to input your first and last name. \n" + UserInput.ANSI_PURPLE + "Enter your first name:" + UserInput.ANSI_BLACK);
        a.setFirstName(UserInput.getString());
        System.out.println(UserInput.ANSI_PURPLE + "Enter last name:" + UserInput.ANSI_BLACK);
        a.setLastName(UserInput.getString());
        a.setAge(UserInput.getAge());
        a.setAccountID(UserInput.randomAccountNumber());
        Account.accountMap.put(a.getAccountID(), a);
        System.out.println(UserInput.ANSI_GREEN + "Thank you for creating an account at our airlines!" + UserInput.ANSI_GREEN + "\nYour accountID which is the only way to access your account is " + UserInput.ANSI_YELLOW + a.getAccountID() + ". " + "\n" + UserInput.ANSI_GREEN + "Keep it safe and sound!");
    }

    public static Account signIn() {
        long tempID = UserInput.getAccountID();
        if (Account.accountMap.containsKey(tempID)) {
            return Account.accountMap.get(tempID);
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        return "Account " + firstName + " " + lastName + " is " + age + " years old, and accountID  = " + accountID;
    }

    public Account() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getAccountID() {
        return accountID;
    }

    public void setAccountID(long accountID) {
        this.accountID = accountID;
    }

    @Override
    public int compareTo(Account a) {
        return this.getFirstName().compareTo(a.getFirstName());
    }

}

class comparatorAge implements Comparator<Account> {

    @Override
    public int compare(Account a1, Account a2) {
        return a1.getAge() - a2.getAge();
    }
}

