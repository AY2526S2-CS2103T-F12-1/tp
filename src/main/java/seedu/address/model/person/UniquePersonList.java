package seedu.address.model.person;

import java.util.List;

import seedu.address.model.list.UniqueList;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;

/**
 * A list of persons that enforces uniqueness between its elements and does not allow nulls.
 * A person is considered unique by comparing using {@code Person#isSamePerson(Person)}. As such, adding and updating of
 * persons uses Person#isSamePerson(Person) for equality so as to ensure that the person being added or updated is
 * unique in terms of identity in the UniquePersonList. However, the removal of a person uses Person#equals(Object) so
 * as to ensure that the person with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Person#isSamePerson(Person)
 */
public class UniquePersonList extends UniqueList<Person> {

    @Override
    protected boolean isSame(Person p1, Person p2) {
        return p1.isSamePerson(p2);
    }

    @Override
    protected RuntimeException duplicateException() {
        return new DuplicatePersonException();
    }

    @Override
    protected RuntimeException notFoundException() {
        return new PersonNotFoundException();
    }

    public void setPerson(Person target, Person editedPerson) {
        set(target, editedPerson);
    }

    public void setPersons(UniquePersonList replacement) {
        setAll(replacement);
    }

    public void setPersons(List<Person> persons) {
        setAll(persons);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls and other subclasses of UniqueList
        if (!(other instanceof UniquePersonList)) {
            return false;
        }

        return super.equals(other);
    }
}
