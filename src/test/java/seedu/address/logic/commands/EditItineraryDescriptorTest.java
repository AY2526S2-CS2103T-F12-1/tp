package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BALI;
import static seedu.address.logic.commands.CommandTestUtil.DESC_FRANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITINERARY_DEST_BALI;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITINERARY_END_DATE_BALI;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITINERARY_NAME_BALI;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITINERARY_START_DATE_BALI;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditItineraryCommand.EditItineraryDescriptor;
import seedu.address.testutil.EditItineraryDescriptorBuilder;


public class EditItineraryDescriptorTest {
    @Test
    public void equals() {
        // same values -> returns true
        EditItineraryDescriptor descriptorWithSameValues =
                new EditItineraryCommand.EditItineraryDescriptor(DESC_FRANCE);
        assertTrue(DESC_FRANCE.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_FRANCE.equals(DESC_FRANCE));

        // null -> returns false
        assertFalse(DESC_FRANCE.equals(null));

        // different types -> returns false
        assertFalse(DESC_FRANCE.equals(5));

        // different values -> returns false
        assertFalse(DESC_FRANCE.equals(DESC_BALI));

        // different name -> returns false
        EditItineraryDescriptor editedFrance = new EditItineraryDescriptorBuilder(DESC_FRANCE)
                .withName(VALID_ITINERARY_NAME_BALI).build();
        assertFalse(DESC_FRANCE.equals(editedFrance));

        // different destination -> returns false
        editedFrance = new EditItineraryDescriptorBuilder(DESC_FRANCE)
                .withDestination(VALID_ITINERARY_DEST_BALI).build();
        assertFalse(DESC_FRANCE.equals(editedFrance));

        // different start date -> returns false
        editedFrance = new EditItineraryDescriptorBuilder(DESC_FRANCE)
                .withStartDate(LocalDate.parse(VALID_ITINERARY_START_DATE_BALI)).build();
        assertFalse(DESC_FRANCE.equals(editedFrance));

        // different end date -> returns false
        editedFrance = new EditItineraryDescriptorBuilder(DESC_FRANCE)
                .withEndDate(LocalDate.parse(VALID_ITINERARY_END_DATE_BALI)).build();
        assertFalse(DESC_FRANCE.equals(editedFrance));
    }

    @Test
    public void toStringMethod() {
        EditItineraryDescriptor editItineraryDescriptor =
                new EditItineraryCommand.EditItineraryDescriptor();
        String expected = EditItineraryDescriptor.class.getCanonicalName()
                + "{itineraryName=" + editItineraryDescriptor.getItineraryName().orElse(null)
                + ", destination=" + editItineraryDescriptor.getDestination().orElse(null)
                + ", startDate=" + editItineraryDescriptor.getStartDate().orElse(null)
                + ", endDate=" + editItineraryDescriptor.getEndDate().orElse(null)
                + "}";
        assertEquals(expected, editItineraryDescriptor.toString());
    }
}
