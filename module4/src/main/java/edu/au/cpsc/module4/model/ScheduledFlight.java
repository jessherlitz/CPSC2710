package edu.au.cpsc.module4.model;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Set;

public class ScheduledFlight implements Serializable {

    private String flightDesignator;
    private String departureAirportIdent;
    private LocalTime departureTime;
    private String arrivalAirportIdent;
    private LocalTime arrivalTime;
    private Set<String> daysOfWeek;

    public ScheduledFlight() {

    }

    public String getFlightDesignator() {
        return flightDesignator;
    }

    public void setFlightDesignator(String flightDesignator) {
        if (flightDesignator == null) {
            throw new IllegalArgumentException("Flight designator can't be null.");
        }
        this.flightDesignator = flightDesignator;
    }

    public String getDepartureAirportIdent() {
        return departureAirportIdent;
    }

    public void setDepartureAirportIdent(String departureAirportIdent) {
        if (departureAirportIdent == null) {
            throw new IllegalArgumentException("Departure airport ident can't be null.");
        }
        this.departureAirportIdent = departureAirportIdent;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        if (departureTime == null) {
            throw new IllegalArgumentException("Departure time can't be null.");
        }
        this.departureTime = departureTime;
    }

    public String getArrivalAirportIdent() {
        return arrivalAirportIdent;
    }

    public void setArrivalAirportIdent(String arrivalAirportIdent) {
        if (arrivalAirportIdent == null) {
            throw new IllegalArgumentException("Arrival airport ident can't be null.");
        }
        this.arrivalAirportIdent = arrivalAirportIdent;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        if (arrivalTime == null) {
            throw new IllegalArgumentException("Arrival time can't null.");
        }
        this.arrivalTime = arrivalTime;
    }

    public Set<String> getDaysOfWeek() {
        return daysOfWeek;
    }

    public String getDaysOfWeekFormatted() {

        if (daysOfWeek == null) {
            return "";
        }

        return daysOfWeek.toString().substring(1, daysOfWeek.toString().length() - 1);
    }

    public void setDaysOfWeek(Set<String> daysOfWeek) {
        if (daysOfWeek == null) {
            throw new IllegalArgumentException("Days of week can't be null.");
        }
        this.daysOfWeek = daysOfWeek;
    }

    public void changeDaysOfWeek(String dayOfWeek) {
        if (daysOfWeek.contains(dayOfWeek)) {
            daysOfWeek.remove(dayOfWeek);
        } else {
            daysOfWeek.add(dayOfWeek);
        }
    }

    @Override
    public String toString() {
        return "ScheduledFlight{" +
                "flightDesignator='" + flightDesignator + '\'' +
                ", departureAirportIdent='" + departureAirportIdent + '\'' +
                ", departureTime=" + departureTime +
                ", arrivalAirportIdent='" + arrivalAirportIdent + '\'' +
                ", arrivalTime=" + arrivalTime +
                ", daysOfWeek=" + daysOfWeek +
                '}';
    }
}
