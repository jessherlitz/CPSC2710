import java.util.*;
import java.time.LocalDate;



public class SeatReservation {

	private String flightDesignator;
	private java.time.LocalDate flightDate;
	private String firstName;
	private String lastName;


	public void setFlightDesignator(String fd) {
        if (fd == null || fd.length() < 4 || fd.length() > 6) {
            throw new IllegalArgumentException();
        }
        
        this.flightDesignator = fd;
    }


    public String getFlightDesignator() {
        return flightDesignator;
    }

    public LocalDate getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(LocalDate date) {
        this.flightDate = date;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String fn) {
        this.firstName = fn;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String ln) {
        this.lastName = ln;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SeatReservation{");
        sb.append("flightDesignator=");
        sb.append(flightDesignator != null ? flightDesignator : "null");
        sb.append(",flightDate=");
        sb.append(flightDate != null ? flightDate.toString() : "null");
        sb.append(",firstName=");
        sb.append(firstName != null ? firstName : "null");
        sb.append(",lastName=");
        sb.append(lastName != null ? lastName : "null");
        sb.append("}");
        return sb.toString();
    }
}
