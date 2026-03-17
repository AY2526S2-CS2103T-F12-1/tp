package seedu.address.model.itinerary;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITINERARY_DEST_BALI;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITINERARY_END_DATE_BALI;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITINERARY_NAME_BALI;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITINERARY_NAME_FRANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITINERARY_START_DATE_BALI;
import static seedu.address.testutil.TypicalItineraries.FRANCE_TRIP;

import java.util.Set;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ItineraryBuilder;

public class ItineraryTest {

    @Test
    public void isSameItinerary() {
        // same object -> returns true
        assertTrue(FRANCE_TRIP.isSameItinerary(FRANCE_TRIP));

        // null -> returns false
        assertFalse(FRANCE_TRIP.isSameItinerary(null));

        // same name, all other attributes different -> returns true
        Itinerary editedItinerary = new ItineraryBuilder(FRANCE_TRIP).withDestination(VALID_ITINERARY_DEST_BALI)
                .withDateRange(VALID_ITINERARY_START_DATE_BALI, VALID_ITINERARY_END_DATE_BALI).build();
        assertTrue(FRANCE_TRIP.isSameItinerary(editedItinerary));

        // different name, all other attributes same -> returns false
        editedItinerary = new ItineraryBuilder().withName(VALID_ITINERARY_NAME_BALI).build();
        assertFalse(FRANCE_TRIP.isSameItinerary(editedItinerary));

        // name differs in case, all other attributes same -> returns true
        Itinerary editedtripToFrance = new ItineraryBuilder(FRANCE_TRIP)
                .withName(VALID_ITINERARY_NAME_FRANCE.toLowerCase()).build();
        assertTrue(FRANCE_TRIP.isSameItinerary(editedtripToFrance));

        // name has trailing spaces, all other attributes same -> returns true
        String nameWithTrailingSpaces = VALID_ITINERARY_NAME_FRANCE + " ";
        editedtripToFrance = new ItineraryBuilder(FRANCE_TRIP).withName(nameWithTrailingSpaces).build();
        assertFalse(FRANCE_TRIP.isSameItinerary(editedtripToFrance));
    }

    public void equals() {
        // same values -> returns true
        Itinerary tripToFranceCopy = new ItineraryBuilder(FRANCE_TRIP).build();
        assertTrue(FRANCE_TRIP.equals(tripToFranceCopy));

        // same object -> returns true
        assertTrue(FRANCE_TRIP.equals(FRANCE_TRIP));

        // null -> returns false
        assertFalse(FRANCE_TRIP.equals(null));

        // different type -> returns false
        assertFalse(FRANCE_TRIP.equals(5));

        // different name -> returns false
        Itinerary editedItinerary = new ItineraryBuilder(FRANCE_TRIP).withName(VALID_ITINERARY_NAME_BALI).build();
        assertFalse(FRANCE_TRIP.equals(editedItinerary));

        // different destination -> returns false
        editedItinerary = new ItineraryBuilder(FRANCE_TRIP).withDestination(VALID_ITINERARY_DEST_BALI).build();
        assertFalse(FRANCE_TRIP.equals(editedItinerary));

        // different date range -> returns false
        editedItinerary = new ItineraryBuilder(FRANCE_TRIP).withDateRange(VALID_ITINERARY_START_DATE_BALI,
                VALID_ITINERARY_END_DATE_BALI).build();
        assertFalse(FRANCE_TRIP.equals(editedItinerary));

        // different clientIds -> returns false
        editedItinerary = new ItineraryBuilder(FRANCE_TRIP).withClientIds(Set.of(UUID.randomUUID())).build();
        assertFalse(FRANCE_TRIP.equals(editedItinerary));

        // different vendorIds -> returns false
        editedItinerary = new ItineraryBuilder(FRANCE_TRIP).withVendorIds(Set.of(UUID.randomUUID())).build();
        assertFalse(FRANCE_TRIP.equals(editedItinerary));
    }

    @Test
    public void toStringMethod() {
        String expected = Itinerary.class.getCanonicalName() + "{itineraryName=" + FRANCE_TRIP.getName()
                + ", destination=" + FRANCE_TRIP.getDestination()
                + ", date range=" + FRANCE_TRIP.getDateRange()
                + ", clientIds=" + FRANCE_TRIP.getClientIds()
                + ", vendorIds=" + FRANCE_TRIP.getVendorIds() + "}";
        assertEquals(expected, FRANCE_TRIP.toString());
    }
}
