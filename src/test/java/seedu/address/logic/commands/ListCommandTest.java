package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void equals() {
        ListCommand listContactCommand = new ListCommand(ListCommand.Flag.CONTACT);
        ListCommand listClientCommand = new ListCommand(ListCommand.Flag.CLIENT);

        // same object -> returns true
        assertTrue(listContactCommand.equals(listContactCommand));

        // same flag -> returns true
        ListCommand listContactCommandCopy = new ListCommand(ListCommand.Flag.CONTACT);
        assertTrue(listContactCommand.equals(listContactCommandCopy));

        // different types -> returns false
        assertFalse(listContactCommand.equals(1));

        // null -> returns false
        assertFalse(listContactCommand.equals(null));

        // different flag -> returns false
        assertFalse(listContactCommand.equals(listClientCommand));
    }

    @Test
    public void execute_contactListIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(ListCommand.Flag.CONTACT), model,
                ListCommand.MESSAGE_SUCCESS_CONTACTS, expectedModel);
    }

    @Test
    public void execute_contactListIsFiltered_showsEverything() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        assertCommandSuccess(new ListCommand(ListCommand.Flag.CONTACT), model,
                ListCommand.MESSAGE_SUCCESS_CONTACTS, expectedModel);
    }

    @Test
    public void execute_clientListIsNotFiltered_showsClientsOnly() {
        expectedModel.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_CLIENTS);
        assertCommandSuccess(new ListCommand(ListCommand.Flag.CONTACT), model,
                ListCommand.MESSAGE_SUCCESS_CLIENTS, expectedModel);
    }

    @Test
    public void execute_clientListIsFiltered_showsClientsOnly() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        expectedModel.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_CLIENTS);
        assertCommandSuccess(new ListCommand(ListCommand.Flag.CONTACT), model,
                ListCommand.MESSAGE_SUCCESS_CLIENTS, expectedModel);
    }

    @Test
    public void execute_vendorListIsNotFiltered_showsVendorsOnly() {
        expectedModel.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_VENDORS);
        assertCommandSuccess(new ListCommand(ListCommand.Flag.VENDOR), model,
                ListCommand.MESSAGE_SUCCESS_VENDORS, expectedModel);
    }

    @Test
    public void execute_vendorListIsFiltered_showsVendorsOnly() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        expectedModel.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_VENDORS);
        assertCommandSuccess(new ListCommand(ListCommand.Flag.VENDOR), model,
                ListCommand.MESSAGE_SUCCESS_VENDORS, expectedModel);
    }

    @Test
    public void execute_itineraryList_throwsCommandException() {
        assertCommandFailure(new ListCommand(ListCommand.Flag.ITINERARY), model,
                "Unknown flag: " + ListCommand.Flag.ITINERARY);
        // throws an exception because list itinerary is not yet implemented
    }

    @Test
    public void toStringMethod() {
        ListCommand listCommand = new ListCommand(ListCommand.Flag.CONTACT);
        String expected = ListCommand.class.getCanonicalName() + "{flag=" + ListCommand.Flag.CONTACT + "}";
        assertEquals(expected, listCommand.toString());
    }
}
