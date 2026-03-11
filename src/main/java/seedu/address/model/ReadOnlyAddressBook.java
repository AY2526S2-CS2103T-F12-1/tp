package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.person.Person;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

    /*  Temporary, to review again after adding Itinerary class
     /**
     * Returns an unmodifiable view of the itineraries list.
     * This list will not contain any duplicate itineraries.

    ObservableList<Itinerary> getItineraryList();
     */

}
