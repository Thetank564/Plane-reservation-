package airlines.reservation.system;

import java.util.*;

/**
 *
 * @author antho
 */
public class AirlinesReservationSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        //Menu
        Ticket.makeTickets();
        int input = 0;
        Admin admin = new Admin();
        do {
            System.out.println(UserInput.ANSI_PURPLE + "Welcome to the Airlines Reservation System. Please choose one of the following: " + UserInput.ANSI_BLACK);
            System.out.println("[1]Make an account\n[2]Choose a ticket\n[3]See All Account's Tickets\n[4]Manage Tickets\n[5]Display Transaction History \n[6] Last 15 Tickets Bought \n[7]Admin Sign in\n[8]Get Account Info\n[9]Exit");
            input = UserInput.getInt();
            //make 
            if (input == 1) {
                Account.makeAccount();
            }
            //sign into account and pick ticket.
            if (input == 2) {
                Account a = Account.signIn();
                Ticket.printTickets();
                Ticket foundTicket = Ticket.findTicket();
                Ticket.removeTicketFromAll(foundTicket);
                a.TransactionHistory.add(new Transactions("Added", foundTicket));
                a.CurrentTickets.add(foundTicket);
                Ticket.allBoughtTickets.add(foundTicket);
                a.downloadTicket(foundTicket);
                System.out.println(UserInput.ANSI_GREEN + "Ticket added to your account, Enjoy the flight!" + UserInput.ANSI_BLACK);
            }

            //see currentTickets
            if (input == 3) {
                Account a = Account.signIn();
                System.out.println(a.CurrentTickets);
            }
            if (input == 4) {
                Account a = Account.signIn();
                System.out.println(UserInput.ANSI_PURPLE + "Would you like to [1] Remove a ticket, [2] Replace a ticket " + UserInput.ANSI_BLACK);
                int userRR = UserInput.getInt();
                if (userRR == 1) {
                    if (a.CurrentTickets.size() > 0) {
                        System.out.println(UserInput.ANSI_PURPLE + "Which of your following tickets would you like to remove." + UserInput.ANSI_BLACK);
                        a.printAllCurrentTickets(a);
                        String removingTicket = UserInput.getPurchaseTicket();
                        a.removeTicketFromCurrentTickets(removingTicket, a);
                        System.out.println(UserInput.ANSI_GREEN + "Ticket Removed.");
                        Ticket.sortTickets(Ticket.allTks);

                    } else {
                        System.out.println(UserInput.ANSI_RED + "You currently own no tickets." + UserInput.ANSI_BLACK);
                    }

                }
                if (userRR == 2) {
                    if (a.CurrentTickets.size() > 0) {
                        System.out.println(UserInput.ANSI_PURPLE + "Here are ur tickets." + UserInput.ANSI_BLACK);
                        a.printAllCurrentTickets(a);
                        String usersReplaceTicket = UserInput.getTicketString();
                        Ticket t = Ticket.findTicket(usersReplaceTicket, a.CurrentTickets);
                        if (a.CurrentTickets.contains(t)) {
                            System.out.println(UserInput.ANSI_PURPLE + "Which of the available tickets would you like to replace it with?" + UserInput.ANSI_BLACK);
                            Ticket.printTickets();
                            Ticket ticketFromAll = Ticket.findTicket();
                            System.out.println(UserInput.ANSI_GREEN + "Replacing..." + UserInput.ANSI_BLACK);
                            a.removeTicketFromCurrentTickets(usersReplaceTicket, a);
                            a.CurrentTickets.add(ticketFromAll);
                            Ticket.allBoughtTickets.add(ticketFromAll);
                            Ticket.removeTicketFromAll(ticketFromAll);
                            a.TransactionHistory.add(new Transactions("Added", ticketFromAll));
                            System.out.println(UserInput.ANSI_GREEN + "You have replace your original ticket for the ticket " + ticketFromAll);
                            Ticket.sortTickets(Ticket.allTks);
                        } else {
                            System.out.println(UserInput.ANSI_RED + "You do not own this ticket." + UserInput.ANSI_BLACK);
                        }
                    } else {
                        System.out.println(UserInput.ANSI_RED + "You do not own any tickets." + UserInput.ANSI_BLACK);

                    }
                }

                if (userRR != 1 && userRR != 2) {
                    System.out.println(UserInput.ANSI_GREEN + "Returning to menu.");
                }
            }

            if (input == 5) {
                Account a = Account.signIn();
                System.out.println(UserInput.ANSI_BLUE + "Here is your accounts last 25 transaction." + UserInput.ANSI_BLACK);
                a.printTransactionHistory(a);
            }
            if (input == 6) {
                Ticket.last15BoughtTickets(Ticket.allBoughtTickets);
            }
            if (input == 7) {

                System.out.println(UserInput.ANSI_PURPLE + "Please enter admin ID" + UserInput.ANSI_BLACK);
                int userInt = UserInput.getInt();
                //Check To enter admin menu
                if (userInt == admin.getAdminNumber()) {
                    //Admin menu
                    System.out.println(UserInput.ANSI_PURPLE + "[1] Check seats bought in a specific row [2] Retrieve a users account ID [3] Delete an account [4] Compare all accounts based on First Name [5]Compare Accounts Based on Age" + UserInput.ANSI_BLACK);
                    int adminSelection = UserInput.getInt();
                    if (adminSelection == 1) {
                        char charRow = UserInput.getRow();
                        admin.TicketsBoughtInRow(charRow);
                    }
                    if (adminSelection == 2) {
                        System.out.println(UserInput.ANSI_PURPLE + "Enter first name linked to account." + UserInput.ANSI_BLACK);
                        String firstName = UserInput.getString();
                        System.out.println(UserInput.ANSI_PURPLE + "Enter last name linked to the account." + UserInput.ANSI_BLACK);
                        String lastName = UserInput.getString();
                        int age = UserInput.getAge();
                        if (admin.recoverAccountID(firstName, lastName, age) == -1) {
                            System.out.println(UserInput.ANSI_RED + "Account ID not found. Either the information you entered is incorrect(Capitalization) or your account does not exist." + UserInput.ANSI_BLACK);
                        } else {
                            System.out.println("Your Account ID tied to the information given is " + admin.recoverAccountID(firstName, lastName, age));
                        }
                    }
                    if (adminSelection == 3) {
                        System.out.println(UserInput.ANSI_PURPLE + "For account to be deleted, you need to put the Account ID." + UserInput.ANSI_BLACK);
                        Account a = Account.signIn();
                        admin.deleteAccount(a);
                    }
                    if (adminSelection == 4) {
                        System.out.println(UserInput.ANSI_PURPLE + "Displaying all first names of all accounts." + UserInput.ANSI_BLACK);
                        admin.printAccountsByFirstName();
                    }
                    if (adminSelection == 5) {
                        System.out.println(UserInput.ANSI_PURPLE + "Displaying all accounts by their age." + UserInput.ANSI_BLACK);
                        admin.printAccountByAge();
                    }
                } else {
                    System.out.println(UserInput.ANSI_RED + "Incorrect admin ID" + UserInput.ANSI_BLACK);
                }
            }
            if (input == 8) {
                Account a = Account.signIn();
                a.printAccountInfo();
            }
            if (input > 9 || input < 1) {
                System.out.println(UserInput.ANSI_RED + "Not in the menu." + UserInput.ANSI_BLACK);
            }
        } while (input != 9);
    }
}
