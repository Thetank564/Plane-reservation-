package airlines.reservation.system;

/**
 *
 * @author antho
 */
public abstract class Aadmin {

    private static final int ADMIN_NUMBER = 192837465;

    public abstract void TicketsBoughtInRow(char row);

    public abstract void printAccountsByFirstName();

    public abstract long recoverAccountID(String firstName, String lastName, int age);
    
    public abstract void deleteAccount(Account a);

    public int getAdminNumber() {
        return ADMIN_NUMBER;
    }
}
