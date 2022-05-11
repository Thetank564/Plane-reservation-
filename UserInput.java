package airlines.reservation.system;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author antho
 */
public class UserInput {
    
    public static int getInt() throws InputMismatchException {
        try {
            Scanner sc = new Scanner(System.in);
            return sc.nextInt();
        } catch (Exception e) {
            System.out.println(UserInput.ANSI_RED + "Not a number!" + UserInput.ANSI_BLACK);
        }
        return getInt();
    }

    public static char getRow() throws InputMismatchException {
        try {
            System.out.println(UserInput.ANSI_PURPLE + "Please enter the letter of the row." + UserInput.ANSI_BLACK);
            Scanner sc = new Scanner(System.in);
            String row = sc.next();
            if (row.length() == 1) {
                char newRow = row.toUpperCase().charAt(0);
                return newRow;
            } else {
                System.out.println(UserInput.ANSI_RED + "Not the character of a row." + UserInput.ANSI_BLACK);
                return getRow();
            }
        } catch (Exception E) {
            System.out.println(UserInput.ANSI_RED + "Not the character of a row." + UserInput.ANSI_BLACK);
            return getRow();
        }
    }

    public static String getPurchaseTicket() {
        System.out.println(UserInput.ANSI_PURPLE + "Type in the exact ticket you would like to purchase." + UserInput.ANSI_BLACK);
        Scanner sc = new Scanner(System.in);
        String userTicket = sc.next();
        return userTicket;
    }

    public static String getTicketString() {
        System.out.println(UserInput.ANSI_PURPLE + "Type in the exact ticket you would like." + UserInput.ANSI_BLACK);
        Scanner sc = new Scanner(System.in);
        String userTicket = sc.next();
        return userTicket;
    }

    public static long getAccountID() throws InputMismatchException {
        try {
            System.out.println(UserInput.ANSI_GREEN + "Please enter your account ID" + UserInput.ANSI_BLACK);
            Scanner sc = new Scanner(System.in);
            long accountID = sc.nextLong();
            if (Account.accountMap.containsKey(accountID)) {
                return accountID;
            } else {
                System.out.println(ANSI_RED + "Invalid account ID. It is either not created or not the right size!" + UserInput.ANSI_BLACK);
                return getAccountID();
            }
        } catch (Exception e) {
            System.out.println(UserInput.ANSI_RED + "Not a number." + UserInput.ANSI_BLACK);
            return getAccountID();
        }
    }

    public static int getAge() throws InputMismatchException {
        try {

            Scanner sc = new Scanner(System.in);
            System.out.println(UserInput.ANSI_PURPLE + "Please enter your age." + UserInput.ANSI_BLACK);
            int userAge = sc.nextInt();
            if (userAge < 18) {
                System.out.println(ANSI_RED + "Sorry you are too young to reserve tickets." + ANSI_BLACK);
                return getAge();
            }
            if (userAge > 123) {
                System.out.println(ANSI_RED + "Invalid Age." + ANSI_BLACK);
                return getAge();
            }
            return userAge;
        } catch (Exception e) {
            System.out.println(UserInput.ANSI_RED + "Not a number!" + UserInput.ANSI_BLACK);
            return getAge();
        }

    }

    public static String getString() {
        Scanner sc = new Scanner(System.in);
        return sc.next();
    }

    public static long randomAccountNumber() {
        long max = 999999999;
        long min = 100000000;
        long randomNumber = (int) Math.floor(Math.random() * (max - min + 1) + min);
        return randomNumber;
    }
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
}
