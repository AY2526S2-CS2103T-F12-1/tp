package seedu.address.model.itinerary;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;

// This is currently a base implementation to test addi functionality
// TODO update class

/**
 * Represents an itinerary in TripScribe
 */
public class Itinerary {

    public static final String ITINERARY_DATE_CONSTRAINTS = "Dates should be in the format yyyy-MM-dd.";

    private String itineraryName;
    private String itineraryDestination;
    private LocalDate itineraryStart;
    private LocalDate itineraryEnd;

    /**
     * Creates an Itinerary
     */
    public Itinerary(String itineraryName, String itineraryDestination, LocalDate itineraryStart,
                     LocalDate itineraryEnd) {
        requireAllNonNull(itineraryName, itineraryDestination, itineraryStart, itineraryEnd);
        this.itineraryName = itineraryName;
        this.itineraryDestination = itineraryDestination;
        this.itineraryStart = itineraryStart;
        this.itineraryEnd = itineraryEnd;
    }

    /**
     * Returns the name of the itinerary
     * @return String name of the itinerary
     */
    public String getName() {
        return itineraryName;
    }

    /**
     * Returns the destination of the itinerary
     * @return String name of the destination
     */
    public String getDestination() {
        return itineraryDestination;
    }

    /**
     * Returns the start date of the itinerary
     * @return String start date of itinerary
     */
    public String getStartDate() {
        return itineraryStart.toString();
    }

    /**
     * Returns the end date of the itinerary
     * @return String end date of itinerary
     */
    public String getEndDate() {
        return itineraryEnd.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof Itinerary)) {
            return false;
        }

        Itinerary otherItinerary = (Itinerary) other;
        return itineraryName.equals(otherItinerary.itineraryName)
                && itineraryDestination.equals(otherItinerary.itineraryDestination)
                && itineraryStart.equals(otherItinerary.itineraryStart)
                && itineraryEnd.equals(otherItinerary.itineraryEnd);
    }


}
