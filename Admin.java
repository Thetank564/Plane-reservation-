package airlines.reservation.system;

import java.util.Collection;
import java.util.Collections;
import java.util.*;

/**
 *
 * @author 2161743
 */
public class Admin extends Aadmin {

    @Override
    public void TicketsBoughtInRow(char row) {
        Ticket.allBoughtTickets.stream().filter(t -> t.row == row).forEach(System.out::println);
    }

    public void printAccountByAge() {
        List<Account> accountsByAge = new ArrayList<>();
        accountsByAge.addAll(Account.accountMap.values());
        Collections.sort(accountsByAge, new comparatorAge());
        for(Account a : accountsByAge){
            System.out.println(a);
        }
    }

    @Override
    public void printAccountsByFirstName() {
        List<Account> l = new ArrayList<>();
        l.addAll(Account.accountMap.values());
        Collections.sort(l);
        for (Account a : l) {
            System.out.println(UserInput.ANSI_BLUE + a.toString() + UserInput.ANSI_BLACK);
        }
    }

    //User has to enter first name,last name and their age, If all correct they can get their ID from admin
    @Override
    public long recoverAccountID(String firstName, String lastName, int age) {
        for (Account a : Account.accountMap.values()) {
            if (a.getFirstName().equals(firstName) && a.getLastName().equals(lastName) && a.getAge() == age) {
                return a.getAccountID();
            }
        }
        return -1;
    }

    @Override
    //make it re-add all the tickets this account currently owns.
    public void deleteAccount(Account a) {
        if (Account.accountMap.remove(a.getAccountID(), a)) {
            Ticket.allTks.addAll(a.CurrentTickets);
            System.out.println(UserInput.ANSI_GREEN + "Account deleted." + UserInput.ANSI_BLACK);
            Ticket.sortTickets(Ticket.allTks);
            return;
        }
        System.out.println(UserInput.ANSI_RED + "Account did not get removed" + UserInput.ANSI_BLACK);
    }

    public Admin() {

    }

}
