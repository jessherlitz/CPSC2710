package edu.au.cpsc.module4.data;

import edu.au.cpsc.module4.model.ScheduledFlight;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Database implements Serializable {

    private List<ScheduledFlight> flights;

    public Database() {
        flights = new ArrayList<>();
    }

    public List<ScheduledFlight> getScheduledFlights() {
        return flights;
    }

    public void addScheduledFlight(ScheduledFlight scheduledFlight) {
        flights.add(scheduledFlight);
    }

    public void removeScheduledFlight(ScheduledFlight scheduledFlight) {
        flights.remove(scheduledFlight);
    }

    public void updateScheduledFlight(ScheduledFlight scheduledFlight) {
        int index = flights.indexOf(scheduledFlight);
        if (index != -1) {
            flights.set(index, scheduledFlight);
        }
    }
}
