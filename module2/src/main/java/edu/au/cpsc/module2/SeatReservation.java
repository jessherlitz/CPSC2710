package edu.au.cpsc.module2;

import java.util.*;
import java.time.LocalDate;

public class SeatReservation {

    private String flightDesignator;
    private LocalDate flightDate;
    private String firstName;
    private String lastName;
    private int numberOfBags;
    private boolean flyingWithInfant;
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

    public int getNumberOfBags() {
        return numberOfBags;
    }

    public void setNumberOfBags(int num) {
        this.numberOfBags = num;
    }

    public boolean isFlyingWithInfant() {
        return flyingWithInfant;
    }

    public void makeFlyingWithInfant() {
        this.flyingWithInfant = true;
    }

    public void makeNotFlyingWithInfant() {
        this.flyingWithInfant = false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SeatReservation{");
        sb.append("flightDesignator=");
        sb.append(getFlightDesignator() != null ? getFlightDesignator() : "null");
        sb.append(",flightDate=");
        sb.append(getFlightDate() != null ? getFlightDate().toString() : "null");
        sb.append(",firstName=");
        sb.append(getFirstName() != null ? getFirstName() : "null");
        sb.append(",lastName=");
        sb.append(getLastName() != null ? getLastName() : "null");
        sb.append(",numberOfBags=" + getNumberOfBags());
        sb.append(",flyingWithInfant=" + isFlyingWithInfant());
        sb.append("}");
        return sb.toString();
    }
}
