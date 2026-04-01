package seedu.address.model.list;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * A generic list that enforces uniqueness between its elements and does not allow nulls.
 * An element is considered unique by comparing using {@code UniqueList<T>#isSame(T, T)}. As such, adding and updating
 * of elements uses {@code UniqueList<T>#isSame(T, T)} for equality so as to ensure that the element being added or
 * updated is unique in terms of identity in the UniqueList. However, the removal of an element uses
 * {@code T#equals(Object)}.
 *
 * Supports a minimal set of list operations.
 *
 * @see UniqueList#isSame(T, T)
 */
public abstract class UniqueList<T> implements Iterable<T> {

    protected final ObservableList<T> internalList = FXCollections.observableArrayList();
    private final ObservableList<T> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the two elements are considered the same.
     */
    protected abstract boolean isSame(T e1, T e2);

    /**
     * Returns the exception to throw when trying to add a duplicate element into the list.
     */
    protected abstract RuntimeException duplicateException();

    /**
     * Returns the exception to throw when an element is not found in the list.
     */
    protected abstract RuntimeException notFoundException();

    /**
     * Returns true if the list contains an equivalent element as the given argument.
     */
    public boolean contains(T toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(item -> isSame(item, toCheck));
    }

    /**
     * Adds an element to the list.
     * The element must not already exist in the list.
     */
    public void add(T toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw duplicateException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces {@code target} in the list with {@code editedT}.
     * {@code target} must exist in the list.
     * The identity of {@code editedT} must not be the same as another existing element in the list.
     */
    public void set(T target, T editedT) {
        requireAllNonNull(target, editedT);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw notFoundException();
        }

        if (!isSame(target, editedT) && contains(editedT)) {
            throw duplicateException();
        }

        internalList.set(index, editedT);
    }

    /**
     * Removes the equivalent element from the list.
     * The element must exist in the list.
     */
    public void remove(T toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw notFoundException();
        }
    }

    public void setAll(UniqueList<T> replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code elements}.
     * {@code elements} must not contain duplicate elements.
     */
    public void setAll(List<T> elements) {
        requireAllNonNull(elements);
        if (!elementsAreUnique(elements)) {
            throw duplicateException();
        }

        internalList.setAll(elements);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<T> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<T> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof UniqueList)) {
            return false;
        }
        UniqueList<?> otherUniqueList = (UniqueList<?>) other;
        return internalList.equals(otherUniqueList.internalList);
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    @Override
    public String toString() {
        return internalList.toString();
    }

    /**
     * Returns true if {@code elements} contains only unique elements.
     */
    private boolean elementsAreUnique(List<T> elements) {
        for (int i = 0; i < elements.size() - 1; i++) {
            for (int j = i + 1; j < elements.size(); j++) {
                if (isSame(elements.get(i), elements.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
