package ro.hanca.cristian.munichcity.models;

import org.joda.time.DateTime;

/**
 * Representation of Operating Times of a POI.
 */
public class OperationalTime {

    public enum Day {
        MON, TUE, WED, THU, FRI, SAT, SUN
    }

    private final Day day;
    private final DateTime from;
    private final DateTime to;

    public OperationalTime(Day day, DateTime from, DateTime to) {
        this.day = day;
        this.from = from;
        this.to = to;
    }

    public Day getDay() {
        return day;
    }

    public DateTime getFrom() {
        return from;
    }

    public DateTime getTo() {
        return to;
    }

    @Override
    public String toString() {
        return day + ": " + from + " - " + to;
    }
}
