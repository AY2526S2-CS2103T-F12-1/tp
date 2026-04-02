package seedu.address.model.itinerary;

import java.util.List;

import seedu.address.model.id.Id;
import seedu.address.model.itinerary.exceptions.DuplicateItineraryException;
import seedu.address.model.itinerary.exceptions.ItineraryNotFoundException;
import seedu.address.model.list.UniqueList;

/**
 * A list of itineraries that enforces uniqueness between its elements and does not allow nulls.
 * An itinerary is considered unique by comparing using {@code Itinerary#isSameItinerary(Itinerary)}.
 * As such, adding and updating of itineraries uses Itinerary#isSameItinerary(Itinerary) for equality
 * so as to ensure that the itinerary being added or updated is unique in terms of identity in the
 * UniqueItineraryList. However, the removal of an itinerary uses Itinerary#equals(Object) so as to
 * ensure that the itinerary with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Itinerary#isSameItinerary(Itinerary)
 */
public class UniqueItineraryList extends UniqueList<Itinerary> {

    @Override
    protected boolean isSame(Itinerary i1, Itinerary i2) {
        return i1.isSameItinerary(i2);
    }

    @Override
    protected RuntimeException duplicateException() {
        return new DuplicateItineraryException();
    }

    @Override
    protected RuntimeException notFoundException() {
        return new ItineraryNotFoundException();
    }

    public void removePerson(Id id) {
        internalList.forEach(itinerary -> itinerary.removePersonId(id));
    }

    public void setItinerary(Itinerary target, Itinerary editedItinerary) {
        set(target, editedItinerary);
    }

    public void setItineraries(UniqueItineraryList replacement) {
        setAll(replacement);
    }

    public void setItineraries(List<Itinerary> itineraries) {
        setAll(itineraries);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls and other subclasses of UniqueList
        if (!(other instanceof UniqueItineraryList)) {
            return false;
        }

        return super.equals(other);
    }
}
