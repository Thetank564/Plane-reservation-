package airlines.reservation.system;

/**
 *
 * @author antho
 */
public class Plane {
    String originalLocation;
    String FinalLocation;
    protected static final int MAX_SEATS = 108;
    protected char row;
    protected int seat;

    public Plane(String originalLocation, String FinalLocation, char row, int seat) {
        this.originalLocation = originalLocation;
        this.FinalLocation = FinalLocation;
        this.row = row;
        this.seat = seat;
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
