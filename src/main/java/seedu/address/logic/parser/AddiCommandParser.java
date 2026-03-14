package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITINERARY_DESTINATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITINERARY_END;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITINERARY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITINERARY_START;

import java.time.LocalDate;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddiCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.itinerary.Itinerary;

/**
 * Parses input arguments and creates a new AddiCommand object
 */
public class AddiCommandParser implements Parser<AddiCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddiCommand
     * and returns an AddiCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddiCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ITINERARY_NAME, PREFIX_ITINERARY_DESTINATION,
                                           PREFIX_ITINERARY_START, PREFIX_ITINERARY_END);

        if (!arePrefixesPresent(argMultimap, PREFIX_ITINERARY_NAME, PREFIX_ITINERARY_DESTINATION,
                                PREFIX_ITINERARY_START, PREFIX_ITINERARY_END)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddiCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_ITINERARY_NAME, PREFIX_ITINERARY_DESTINATION,
                                                 PREFIX_ITINERARY_START, PREFIX_ITINERARY_END);
        String name = ParserUtil.parseItineraryName(argMultimap.getValue(PREFIX_ITINERARY_NAME).get());
        String destination = ParserUtil.parseDestination(argMultimap.getValue(PREFIX_ITINERARY_DESTINATION).get());
        LocalDate startDate = ParserUtil.parseDate(argMultimap.getValue(PREFIX_ITINERARY_START).get());
        LocalDate endDate = ParserUtil.parseDate(argMultimap.getValue(PREFIX_ITINERARY_END).get());

        Itinerary itinerary = new Itinerary(name, destination, startDate, endDate);

        return new AddiCommand(itinerary);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
