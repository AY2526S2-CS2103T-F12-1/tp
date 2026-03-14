package seedu.address.model.itinerary;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

public class NameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Name(null));
    }

    @Test void constructor_invalidName_throwsIllegalArgumentException() {
        String emptyName = "";
        assertThrows(IllegalArgumentException.class, () -> new Name(emptyName));

        String spacesOnlyName = "   ";
        assertThrows(IllegalArgumentException.class, () -> new Name(spacesOnlyName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> Name.isValidName(null));

        // invalid name
        assertFalse(Name.isValidName("")); // empty string
        assertFalse(Name.isValidName(" ")); // spaces only

        // valid name
        assertTrue(Name.isValidName("Paris Trip     ")); // alphabets and spaces
        assertTrue(Name.isValidName("Island time: Bali!!!")); // alphabets and punctuations
        assertTrue(Name.isValidName("12345")); // numbers only
        assertTrue(Name.isValidName("5D4N Trip to France")); // alphanumeric characters
    }

    @Test
    public void equals() {
        Name name = new Name("Valid Itinerary Name");

        // same values -> returns true
        assertTrue(name.equals(new Name("Valid Itinerary Name")));

        // case-insensitive
        assertTrue(name.equals(new Name("valid itiNerarY naMe")));

        // same object -> returns true
        assertTrue(name.equals(name));

        // null -> returns false
        assertFalse(name.equals(null));

        // different types -> returns false
        assertFalse(name.equals(5.0f));

        // different values -> returns false
        assertFalse(name.equals(new Name("Other Valid Itinerary Name")));
    }
}
