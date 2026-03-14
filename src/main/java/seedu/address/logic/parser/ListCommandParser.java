package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ListCommand object
 */
public class ListCommandParser implements Parser<ListCommand> {


    /**
     * Parses the given {@code String} of arguments in the context of the ListCommand
     * and returns a ListCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListCommand parse(String args) throws ParseException {
        String trimmedFlag = args.trim();

        // Check for missing flag or extra arguments after the flag
        if (trimmedFlag.isEmpty() || trimmedFlag.contains(" ")) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
        }

        switch (trimmedFlag.toLowerCase()) { // Case-sensitive
        case "/contact":
            return new ListCommand(ListCommand.Flag.CONTACT);
        case "/itinerary":
            return new ListCommand(ListCommand.Flag.ITINERARY);
        case "/client":
            return new ListCommand(ListCommand.Flag.CLIENT);
        case "/vendor":
            return new ListCommand(ListCommand.Flag.VENDOR);
        default:
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
        }
    }
}
