package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ListCommand;

public class ListCommandParserTest {

    private ListCommandParser parser = new ListCommandParser();

    @Test
    public void parse_noFlag_throwsParseException() {
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidFlag_throwsParseException() {
        assertParseFailure(parser, "/unknown",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_extraArgumentsAfterFlag_throwsParseException() {
        assertParseFailure(parser, "/contact extraArg",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_contactFlag_returnsListCommand() {
        assertParseSuccess(parser, "/contact",
                new ListCommand(ListCommand.Flag.CONTACT));
    }

    @Test
    public void parse_clientFlag_returnsListCommand() {
        assertParseSuccess(parser, "/client",
                new ListCommand(ListCommand.Flag.CLIENT));
    }

    @Test
    public void parse_vendorFlag_returnsListCommand() {
        assertParseSuccess(parser, "/vendor",
                new ListCommand(ListCommand.Flag.VENDOR));
    }
}
