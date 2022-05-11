package airlines.reservation.system;

/**
 *
 * @author antho
 */
public interface Iaccount {

    public void printAllCurrentTickets(Account a);

    public void printTransactionHistory(Account a);

    public void removeTicketFromCurrentTickets(String ticket, Account a);

    public void downloadTicket(Ticket t) throws Exception;

    public void printAccountInfo();
}
