package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.itinerary.Itinerary;
import seedu.address.model.person.Person;

/**
 * Deletes a contact or itinerary identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";
    public static final String CONTACT_FLAG = "/contact";
    public static final String ITINERARY_FLAG = "/itinerary";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the contact or itinerary identified by the index number used in the displayed contact or "
            + "itinerary list.\n"
            + "Parameters: FLAG (" + CONTACT_FLAG + " OR " + ITINERARY_FLAG + ") INDEX (must be a positive integer)\n"
            + "Contact Example: " + COMMAND_WORD + " " + CONTACT_FLAG + " 1  |  "
            + "Itinerary Example: " + COMMAND_WORD + " " + ITINERARY_FLAG + " 2";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";
    public static final String MESSAGE_DELETE_ITINERARY_SUCCESS = "Deleted Itinerary: %1$s";

    /**
     * Represents the possible flags that can be used to delete entries.
     */
    public enum DeleteType { CONTACT, ITINERARY }

    private final Index targetIndex;
    private final DeleteType flag;

    /**
     * Deletes a person or itinerary identified using it's displayed index from the address book.
     */
    public DeleteCommand(DeleteType flag, Index targetIndex) {
        this.flag = flag;
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (flag.equals(DeleteType.CONTACT)) {
            List<Person> lastShownContactList = model.getFilteredPersonList();

            if (targetIndex.getZeroBased() >= lastShownContactList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }

            Person personToDelete = lastShownContactList.get(targetIndex.getZeroBased());
            model.deletePerson(personToDelete);
            return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, Messages.format(personToDelete)));

        } else if (flag.equals(DeleteType.ITINERARY)) {
            List<Itinerary> lastShownItineraryList = model.getFilteredItineraryList();

            if (targetIndex.getZeroBased() >= lastShownItineraryList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_ITINERARY_DISPLAYED_INDEX);
            }

            Itinerary itineraryToDelete = lastShownItineraryList.get(targetIndex.getZeroBased());
            model.deleteItinerary(itineraryToDelete);
            return new CommandResult(String.format(MESSAGE_DELETE_ITINERARY_SUCCESS,
                    Messages.format(itineraryToDelete)));

        } else {
            throw new CommandException(Messages.MESSAGE_INVALID_COMMAND_FORMAT);
        }

    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteCommand)) {
            return false;
        }

        DeleteCommand otherDeleteCommand = (DeleteCommand) other;
        return targetIndex.equals(otherDeleteCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
