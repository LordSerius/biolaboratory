package hu.bioinformatics.biolaboratory.sequence;

import hu.bioinformatics.biolaboratory.utils.ArgumentValidator;
import hu.bioinformatics.biolaboratory.utils.SequenceUtils;
import hu.bioinformatics.biolaboratory.utils.datastructures.CountableOccurrenceMap;
import hu.bioinformatics.biolaboratory.utils.datastructures.OccurrenceMap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.google.common.base.Preconditions.checkArgument;
import static hu.bioinformatics.biolaboratory.utils.ArgumentValidator.checkNotBlankString;
import static hu.bioinformatics.biolaboratory.utils.ArgumentValidator.checkNotNegativeNumber;
import static hu.bioinformatics.biolaboratory.utils.ArgumentValidator.checkNotNullArgument;
import static hu.bioinformatics.biolaboratory.utils.ArgumentValidator.checkPositiveNumber;
import static hu.bioinformatics.biolaboratory.utils.ArgumentValidator.checkSmallerNumberTo;
import static hu.bioinformatics.biolaboratory.utils.ArgumentValidator.checkSmallerOrEqualNumberTo;

/**
 * Represents an immutable abstract biological sequence, which can be a DNA, RNA or a protein. Contains all of the common
 * operations, what are interpretable for all biological sequences.
 *
 * @author Attila Radi
 */
public abstract class BiologicalSequence<TYPE extends BiologicalSequence, ELEMENT extends SequenceElement> {

    protected final String sequence;
    protected final int sequenceLength;
    private final String name;

    private ELEMENT[] sequenceAsElements;
    private CountableOccurrenceMap<ELEMENT> elementOccurrences = null;

    /**
     * Validates the name is not null.
     *
     * @param name The name to validate.
     * @return The name, if it is valid.
     * @throws IllegalArgumentException If name is null.
     */
    protected static String validateName(final String name) {
        return checkNotNullArgument("Name", name).trim();
    }

    /**
     * Executes initial validation and format on the input sequence.
     *
     * @param sequence The sequence to validate and format.
     * @return The formatted sequence.
     * @throws IllegalArgumentException If sequence is blank.
     */
    protected static String formatSequence(final String sequence) {
        return checkNotBlankString("Biological sequence", sequence).trim().toUpperCase();
    }

    /**
     * Creates a biological sequence from its elements. The name will be empty.
     *
     * @param sequenceElements The elements of the biological sequence in array.
     */
    @SafeVarargs
    protected BiologicalSequence(final ELEMENT... sequenceElements) {
        this("", sequenceElements);
    }

    /**
     * Creates a biological sequence from its elements.
     *
     * @param name The name of the biological sequence.
     * @param sequenceElements The elements of the biological sequence in array.
     */
    @SafeVarargs
    protected BiologicalSequence(final String name, final ELEMENT... sequenceElements) {
        this(name, Arrays.asList(sequenceElements));
        this.sequenceAsElements = sequenceElements.clone();
    }

    /**
     * Creates a biological sequence from its elements. The name will be empty.
     *
     * @param sequenceElementList The elements of the biological sequence in {@link List} collection.
     */
    protected BiologicalSequence(final List<ELEMENT> sequenceElementList) {
        this("", sequenceElementList);
    }

    /**
     * Creates a biological sequence from its elements.
     *
     * @param name The name of the biological sequence.
     * @param sequenceElementList The elements of the biological sequence in {@link List} collection.
     */
    protected BiologicalSequence(final String name, final List<ELEMENT> sequenceElementList) {
        this.sequence = new String(createLetterList(sequenceElementList));
        this.sequenceLength = sequence.length();
        this.name = name;
    }

    /**
     * Creates a biological sequence from {@link String}. The name will be empty.
     *
     * @param sequence The biological sequence as {@link String}.
     */
    protected BiologicalSequence(final String sequence) {
        this("", sequence);
    }

    /**
     * Creates a biological sequence from {@link String}.
     *
     * @param name The name of the biological sequence.
     * @param sequence The biological sequence as {@link String}.
     */
    protected BiologicalSequence(final String name, final String sequence) {
        this.sequence = sequence;
        this.sequenceLength = sequence.length();
        this.name = name;
    }

    /**
     * Construct a TYPE {@link BiologicalSequence} from name and sequence element array.
     *
     * @param sequenceElements The sequence element array (varargs).
     * @return The TYPE {@link BiologicalSequence} from the sequence.
     */
    @SafeVarargs
    protected final TYPE construct(final ELEMENT... sequenceElements) {
        return construct("", sequenceElements);
    }

    /**
     * Construct a TYPE {@link BiologicalSequence} from name and sequence element array.
     *
     * @param name The name of the biological sequence.
     * @param sequenceElements The sequence element array (varargs).
     * @return The TYPE {@link BiologicalSequence} from the sequence.
     */
    @SafeVarargs
    protected final TYPE construct(final String name, final ELEMENT... sequenceElements) {
        return construct(name, Arrays.asList(sequenceElements));
    }

    /**
     * Construct a TYPE {@link BiologicalSequence} from sequence element {@link List}.
     *
     * @param sequenceElementList The sequence element {@link List}.
     * @return The TYPE {@link BiologicalSequence} from the sequence.
     */
    protected final TYPE construct(final List<ELEMENT> sequenceElementList) {
        return construct("", sequenceElementList);
    }

    /**
     * Construct a TYPE {@link BiologicalSequence} from name and sequence element {@link List}.
     *
     * @param name The name of the biological sequence.
     * @param sequenceElementList The sequence element {@link List}.
     * @return The TYPE {@link BiologicalSequence} from the sequence.
     */
    protected final TYPE construct(final String name, final List<ELEMENT> sequenceElementList) {
        return construct(name, new String(createLetterList(sequenceElementList)));
    }

    private char[] createLetterList(final List<ELEMENT> sequenceElementList) {
        int length = sequenceElementList.size();
        char[] letters = new char[length];

        int i = 0;
        for (ELEMENT element : sequenceElementList) {
            letters[i++] = element.getLetter();
        }
        return letters;
    }

    /**
     * Construct a TYPE {@link BiologicalSequence} from the given sequence {@link String}.
     *
     * @param sequence The sequence {@link String}.
     * @return The TYPE {@link BiologicalSequence} from the sequence.
     */
    protected final TYPE construct(final String sequence) {
        return construct("", sequence);
    }

    /**
     * Calls the constructor of the inherited class.
     *
     * @param name The name of the sequence.
     * @param sequence The String sequence of the biological sequence.
     * @return The instance of the inherited class
     */
    protected abstract TYPE construct(final String name, final String sequence);

    /**
     * Creates a copy of the {@link BiologicalSequence}.
     *
     * @return The copy of the {@link BiologicalSequence}.
     */
    public final TYPE copy() { return construct(name, sequence); }

    /**
     * Getter of the biological sequence.
     *
     * @return sequence
     */
    public final String getSequence() {
        return sequence;
    }

    /**
     * Getter of the name.
     *
     * @return The name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns with a new {@link BiologicalSequence} with the given name.
     *
     * @param name The new name.
     * @return Tha new {@link BiologicalSequence} with the given name.
     * @throws IllegalArgumentException If name is null.
     */
    public TYPE changeName(final String name) {
        checkNotNullArgument("Sequence", name);
        return construct(name.trim(), sequence);
    }

    /**
     * Get the ELEMENT from the target index.
     *
     * @param index The index which should smaller than length.
     * @return The ELEMENT at the target index.
     * @throws IllegalArgumentException If index is smaller than 0.
     * @throws IllegalArgumentException If index is greater than sequence length.
     */
    public final ELEMENT getElement(final int index) {
        checkNotNegativeNumber("Index", index);
        checkSmallerNumberTo("Index", index, "sequence length", sequenceLength);
        return loadSequenceAsElements()[index];
    }

    /**
     * Get the biological sequence as an immutable typed array of its elements. The method realizes a lazy loading method.
     *
     * @return An immutable map of typed elements.
     */
    public final ELEMENT[] getSequenceAsElements() {
        return Arrays.copyOf(loadSequenceAsElements(), sequenceLength);
    }

    private synchronized ELEMENT[] loadSequenceAsElements() {
        if (sequenceAsElements == null) {
            sequenceAsElements = createEmptyElementArray();
            IntStream.range(0, sequenceLength)
                    .forEach(index -> sequenceAsElements[index] = findSequenceElement(sequence.charAt(index)));
        }
        return sequenceAsElements;
    }

    /**
     * Creates an ELEMENT array with the appropriate size of the available {@link SequenceElement}s.
     *
     * @return An array with the appropriate size of {@link SequenceElement}s.
     */
    protected abstract ELEMENT[] createEmptyElementArray();

    /**
     * Getter of the biological sequence length.
     *
     * @return sequenceLength
     */
    public final int getSequenceLength() {
        return sequenceLength;
    }

    /**
     * Immutable getter of the element occurrences. It calculates the occurrences at the first call.
     *
     * @return The element occurrences.
     */
    public final CountableOccurrenceMap<ELEMENT> getElementOccurrences() {
        return collectSequenceElementOccurrences().copy();
    }

    /**
     * Get the ratio of the target element according to the full sequence.
     *
     * @param element The desirable element.
     * @return The ratio of the target element.
     * @throws IllegalArgumentException If element is null.
     */
    public final double getElementRatio(final ELEMENT element) {
        return collectSequenceElementOccurrences().occurrenceRatio(element);
    }

    /**
     * Get the ratio of the target elements according to the full sequence.
     *
     * @param elements The desirable elements.
     * @return The ratio of the target elements.
     * @throws IllegalArgumentException If elements are null.
     */
    @SafeVarargs
    public final double getElementsRatio(final ELEMENT... elements) {
        return collectSequenceElementOccurrences().accumulatedOccurrenceRatio(elements);
    }

    /**
     * Get the ratio of the target elements according to the full sequence.
     *
     * @param elementSet The desirable elements.
     * @return The ratio of the target elements.
     * @throws IllegalArgumentException If elementSet contains null value.
     */
    public final double getElementsRatio(final Set<ELEMENT> elementSet) {
        return collectSequenceElementOccurrences().accumulatedOccurrenceRatio(elementSet);
    }

    /**
     * Get the number of the target element.
     *
     * @param element The desired element.
     * @return The element number in the {@link BiologicalSequence}
     * @throws IllegalArgumentException If element is null.
     */
    public final int getElementNumber(final ELEMENT element) {
        return collectSequenceElementOccurrences().getOccurrence(element);
    }

    /**
     * Get the number of the target elements.
     *
     * @param elements The desired elements.
     * @return The sum of the target elements in the {@link BiologicalSequence}.
     * @throws IllegalArgumentException If elements are null.
     */
    @SafeVarargs
    public final int getElementsNumber(final ELEMENT... elements) {
        return collectSequenceElementOccurrences().sumOccurrences(elements);
    }

    /**
     * Get the number of the target elements.
     *
     * @param elementSet The desired elements.
     * @return The sum of the target elements in the {@link BiologicalSequence}.
     * @throws IllegalArgumentException If elementSet contains null value.
     */
    public final int getElementsNumber(final Set<ELEMENT> elementSet) {
        return collectSequenceElementOccurrences().sumOccurrences(elementSet);
    }

    /**
     * Create a {@link CountableOccurrenceMap} about the sequence elements.
     *
     * @return A {@link CountableOccurrenceMap} about the occurrences.
     */
    protected final synchronized CountableOccurrenceMap<ELEMENT> collectSequenceElementOccurrences() {
        if (elementOccurrences == null) {
            elementOccurrences = CountableOccurrenceMap.build(getElementSet());
            ELEMENT[] sequenceAsElements = loadSequenceAsElements();
            IntStream.range(0, sequenceLength)
                    .forEach(index -> elementOccurrences.increase(sequenceAsElements[index]));
        }
        return elementOccurrences;
    }

    /**
     * Return the possible {@link SequenceElement}s for this {@link BiologicalSequence} type.
     *
     * @return All possible {@link SequenceElement} for this {@link BiologicalSequence}.
     */
    protected abstract ELEMENT[] getElementArray();

    /**
     * Return the possible {@link SequenceElement}s for this {@link BiologicalSequence} type in a {@link Set}.
     *
     * @return All possible {@link SequenceElement} for this {@link BiologicalSequence} in a {@link Set}.
     */
    protected abstract Set<ELEMENT> getElementSet();

    /**
     * Return with the {@link SequenceElement} representation of the given letter.
     *
     * @param sequenceElementLetter A sequence element letter.
     * @return The {@link SequenceElement} representation of the given letter.
     */
    protected abstract ELEMENT findSequenceElement(final char sequenceElementLetter);

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || !obj.getClass().equals(getClass())) return false;
        BiologicalSequence rightHand = (BiologicalSequence) obj;
        return sequenceLength == rightHand.sequenceLength && sequence.equals(rightHand.sequence);
    }

    @Override
    public int hashCode() {
        return sequence.hashCode();
    }

    @Override
    public String toString() {
        return getBiologicalSequenceTypeName() + " = {" + sequence + "}";
    }

    /**
     * Get the name of the sequence type (e.g. DNA).
     *
     * @return The name of the sequence type.
     */
    protected abstract String getBiologicalSequenceTypeName();

    /**
     * Append the given biological sequence to the end.
     *
     * @param otherBiologicalSequence The biological sequence to append.
     * @return A new {@link BiologicalSequence} which stands from the sequence of the original {@link BiologicalSequence}
     *          and the sequence of the other {@link BiologicalSequence}.
     * @throws IllegalArgumentException If otherBiologicalSequence is null.
     * @throws IllegalArgumentException If otherBiological sequence type differs than the objects type.
     */
    public final TYPE append(final TYPE otherBiologicalSequence) {
        return construct(sequence + validateType(otherBiologicalSequence).getSequence());
    }

    /**
     * Append the given biological sequence element to the end.
     *
     * @param element The element to append.
     * @return A new {@link BiologicalSequence} which stands from the sequence of the original {@link BiologicalSequence}
     *          and the appended element.
     * @throws IllegalArgumentException If element is null.
     */
    public final TYPE append(final ELEMENT element) {
        return construct(sequence + validateElement(element).getLetter());
    }

    private ELEMENT validateElement(final ELEMENT element) {
        checkNotNullArgument("Element", element);
        checkArgument(getElementSet().contains(element), "Element has invalid type");
        return element;
    }

    /**
     * Cuts a {@link BiologicalSequence} part from the start position to the end of the {@link BiologicalSequence}'s length.
     *
     * @param startPosition The beginning element position in the {@link BiologicalSequence} inclusive.
     * @return The {@link BiologicalSequence} part from start position (inclusive) to the end.
     * @throws IllegalArgumentException If startPosition is negative number.
     * @throws IllegalArgumentException If startPosition is bigger or equals than sequence length.
     */
    public final TYPE cut(final int startPosition) {
        return cut(startPosition, sequenceLength);
    }

    /**
     * Cuts a {@link BiologicalSequence} part about the given index and return it to the caller. The cut operation includes the start
     * position, but excludes the end position.
     *
     * @param startPosition The beginning element position in the {@link BiologicalSequence} inclusive.
     * @param endPosition The end element position in the {@link BiologicalSequence} exclusive.
     * @return The {@link BiologicalSequence} part from start position (inclusive) to end position (exclusive).
     * @throws IllegalArgumentException If startPosition is negative number.
     * @throws IllegalArgumentException If endPosition is bigger or equals than sequence length.
     * @throws IllegalArgumentException If startPosition is greater or equal than endPosition.
     */
    public final TYPE cut(final int startPosition, final int endPosition) {
        checkNotNegativeNumber("Start position", startPosition);
        checkSmallerOrEqualNumberTo("End position", endPosition, "sequence length", sequenceLength);
        checkSmallerNumberTo("Start position", startPosition, "end position", endPosition);
        return construct(sequence.substring(startPosition, endPosition));
    }

    /**
     * Count the occurrences of the given pattern inside the {@link BiologicalSequence} sequence.
     * The sequence parts can overlap.
     *
     * @see BiologicalSequence#findPatternsWithMismatch(BiologicalSequence, int)
     * @param pattern The pattern {@link BiologicalSequence}.
     * @return The number of found patterns.
     * @throws IllegalArgumentException If pattern is null.
     */
    public int patternCount(final TYPE pattern) {
        return patternMatching(pattern).size();
    }

    /**
     * Count the occurrences of the given pattern inside the {@link BiologicalSequence} sequence with maximum
     * of <i>d</i> mismatch.
     * The sequence parts can overlap.
     *
     * @see BiologicalSequence#findPatternsWithMismatch(BiologicalSequence, int)
     * @param pattern The pattern {@link BiologicalSequence}.
     * @param d The maximum permitted mismatch.
     * @return The number of found patterns.
     * @throws IllegalArgumentException If pattern is null.
     * @throws IllegalArgumentException If <i>d</i> is negative number.
     */
    public int patternCountWithMismatches(final TYPE pattern, final int d) {
        return patternMatchingWithMismatches(pattern, d).size();
    }

    /**
     * Return the beginning index of all occurrences of the given pattern inside the {@link BiologicalSequence}
     * sequence. The sequence parts can overlap.
     *
     * @see BiologicalSequence#findPatternsWithMismatch(BiologicalSequence, int)
     * @param pattern The pattern {@link BiologicalSequence}.
     * @return The beginning indexes of found patterns.
     * @throws IllegalArgumentException If pattern is null.
     */
    public List<Integer> patternMatching(final TYPE pattern) {
        return patternMatchingWithMismatches(pattern, 0);
    }

    /**
     * Return the beginning index of all occurrences of the given pattern inside the {@link BiologicalSequence}
     * sequence with maximum of <i>d</i> mismatch. The sequence parts can overlap.
     *
     * @see BiologicalSequence#findPatternsWithMismatch(BiologicalSequence, int)
     * @param pattern he pattern {@link BiologicalSequence}.
     * @param d The maximum permitted mismatch.
     * @return The position of found patterns.
     * @throws IllegalArgumentException If pattern is null.
     * @throws IllegalArgumentException If <i>d</i> is negative number.
     */
    public List<Integer> patternMatchingWithMismatches(final TYPE pattern, final int d) {
        checkNotNegativeNumber("Maximum different value (d)", d);
        return findPatternsWithMismatch(validatePattern(pattern), d);
    }

    private TYPE validatePattern(final TYPE pattern) {
        validateType(pattern);
        checkSmallerOrEqualNumberTo("Pattern length", pattern.sequenceLength, "sequence length", sequenceLength);
        return pattern;
    }

    /**
     * Returns with the smallest mismatch number of pattern and subsequences.
     *
     * @param pattern A pattern for compare against the subsequences.
     * @return The minimum mismatches between pattern and the subsequences.
     * @throws IllegalArgumentException If pattern is null.
     */
    public int findMinimumMismatchSubSequenceNumber(final TYPE pattern) {
        validatePattern(pattern);
        final int patternLength = pattern.sequenceLength;
        return IntStream.rangeClosed(0, sequenceLength - patternLength)
                .parallel()
                .map(index -> SequenceUtils.hammingDistance(
                        sequence.substring(index, index + patternLength), pattern.sequence))
                .min()
                .orElse(Integer.MAX_VALUE);
    }

    /**
     * The work of the algorithm:
     * <ol>
     *     <li>Cut a template sequence from the beginning of the biological sequence which length equals to
     *     the pattern's length.</li>
     *     <li>Compares the template with the pattern.</li>
     *     <li>If the pattern against the template has at most <i>d</i> mismatches then add the
     *     first index of the template inside the biological sequence to the return values.</li>
     *     <li>The next template will be constructed from the second element to the end of
     *     the template concatenated with the next character of the biological sequence after this template.</li>
     *     <li>Returns to the 2. point until the loop variable reaches the difference of the
     *     biological sequence length and the pattern length.</li>
     * </ol>
     *
     * @param pattern The pattern to find.
     * @return The first indices of the occurrences inside the {@link TYPE} sequence.
     * @throws IllegalArgumentException If pattern is null.
     * @throws IllegalArgumentException If <i>d</i> is negative number.
     */
    private List<Integer> findPatternsWithMismatch(final TYPE pattern, final int d) {
        int lengthDiff = sequenceLength - pattern.sequenceLength;
        return IntStream.rangeClosed(0, lengthDiff)
                .parallel()
                .filter(index -> SequenceUtils.hammingDistanceMismatchComparator(
                        sequence.substring(index, index + pattern.sequenceLength), pattern.sequence, d) != SequenceUtils.GREATER)
                .sorted()
                .boxed()
                .collect(Collectors.toList());
    }

    /**
     * Get the most frequent <i>k</i> occurrences in the biological sequence.
     *
     * @param k The findable <i>k</i> long sequences.
     * @return The most frequent <i>k</i> long occurrences.
     * @throws IllegalArgumentException If <i>k</i> is smaller than 1.
     * @throws IllegalArgumentException If <i>k</i> is bigger than sequence length.
     */
    public Set<TYPE> findMostFrequentSubSequences(final int k) {
        return findMostFrequentMismatchSubSequences(k, 0);
    }

    /**
     * Get the most frequent <i>k</i> occurrences which has at most <i>d</i> different
     * elements in the biological sequence.
     *
     * @param k The findable <i>k</i> long sequences.
     * @param d The maximum permitted different elements.
     * @return The most frequent <i>k</i> long occurrences with at most <i>d</i> mismatches.
     * @throws IllegalArgumentException If <i>k</i> is smaller than 1.
     * @throws IllegalArgumentException If <i>k</i> is bigger than sequence length.
     * @throws IllegalArgumentException If <i>d</i> is negative number.
     */
    public Set<TYPE> findMostFrequentMismatchSubSequences(final int k, final int d) {
        return getMismatchOccurrenceMap(k, d).filterMostFrequentOccurrences();
    }

    /**
     * Get all <i>k</i> long subsequences of the DNA.
     *
     * @param k The size of the subsequnces.
     * @return All <i>k</i> long subsequences in a {@link Set}.
     * @throws IllegalArgumentException If <i>k</i> is smaller than 1.
     * @throws IllegalArgumentException If <i>k</i> is bigger than sequence length.
     */
    public Set<TYPE> getSubSequences(final int k) {
        return getMismatchSubSequences(k, 0);
    }

    /**
     * Get all <i>k</i> long subsequences with at most <i>d</i> mismatches of the DNA.
     *
     * @param k The size of the subsequnces.
     * @param d The maximum permitted different elements.
     * @return All <i>k</i> long subsequences with <i>d</i> mismatch in a {@link Set}.
     * @throws IllegalArgumentException If <i>k</i> is smaller than 1.
     * @throws IllegalArgumentException If <i>k</i> is bigger than sequence length.
     * @throws IllegalArgumentException If <i>d</i> is negative number.
     */
    public Set<TYPE> getMismatchSubSequences(final int k, final int d) {
        return findFrequentMismatchSubSequences(k, d, 1);
    }

    /**
     * Get all <i>k</i> long sequences which are greater or equals than <i>t</i> in the
     * DNA sequence.
     *
     * @param k The findable <i>k</i> long sequences.
     * @param t The threshold of the occurrences of the <i>k</i> long sequences in the DNA.
     * @return All <i>k</i> long sequences which occurrences are greater or equals than <i>t</i>.
     * @throws IllegalArgumentException If <i>k</i> is smaller than 1.
     * @throws IllegalArgumentException If <i>k</i> is bigger than sequence length.
     * @throws IllegalArgumentException If <i>t</i> is smaller than 1.
     */
    public Set<TYPE> findFrequentSubSequences(final int k, final int t) {
        return findFrequentMismatchSubSequences(k, 0, t);
    }

    /**
     * Get all <i>k</i> long sequences which has at most <i>d</i> different
     * elements and are greater or equals than <i>t</i> in the DNA sequence.
     *
     * @param k The findable <i>k</i> long sequences.
     * @param d The maximum permitted different elements.
     * @param t The threshold of the occurrences of the <i>k</i> long sequences in the DNA.
     * @return All <i>k</i> long sequences at most <i>d</i> mismatches which occurrences
     *             are greater or equals than <i>t</i>.
     * @throws IllegalArgumentException If <i>k</i> is smaller than 1.
     * @throws IllegalArgumentException If <i>k</i> is bigger than sequence length.
     * @throws IllegalArgumentException If <i>d</i> is negative number.
     * @throws IllegalArgumentException If <i>t</i> is smaller than 1.
     */
    public Set<TYPE> findFrequentMismatchSubSequences(final int k, final int d, final int t) {
        return getMismatchOccurrenceMap(k, d).filterGreaterOrEqualsOccurrences(t);
    }

    /**
     * This method finds the burst frequency of the DNA parts. The <i>L</i> parameter defines a
     * window where the method finds the occurrences of the <i>k</i> long DNA parts.
     * If the occurrence of a particular DNA part in this <i>L</i> window is greater or equals
     * than <i>t</i> the DNA part appears in the return set.
     * <p>
     * The work of the algorithm:
     * <ol>
     *     <li>Picks the window from the first element to <i>L</i> in the DNA.</li>
     *     <li>Determines the first window part from the first position to the <i>k</i> position.</li>
     *     <li>Determines the last window part from <i>L</i> - <i>k</i> to <i>L</i>.</li>
     *     <li>Calculates the occurrence map of the window.</li>
     *     <li>Adds all elements from the occurrence map to the return set which occurrence is
     *     greater or equals than <i>t</i>.</li>
     *     <li>Decrease or remove the first window part from the occurrence list.</li>
     *     <li>Shift the first window part with one position, the next first window part will be
     *     from the second element from the first window part concatenated with the next element
     *     of the DNA sequence.
     *     <li>Shift the last window part with one position, the next first window part will be
     *     from the second element from the last window part concatenated with the next element
     *     of the DNA sequence.</li>
     *     <li>Increase or put the last window part inside the occurrence map.</li>
     *     <li>Add the last window part to the return set if it's occurrence is greater or
     *     equal than <i>t</i></li>
     *     <li>Return to the 6. point until the window position reaches the difference of
     *     the DNA sequence and <i>L</i>.</li>
     * </ol>
     *
     * @param k The findable <i>k</i> long sequences inside the DNA sequence.
     * @param L The window size which determines the search area.
     * @param t The threshold of the occurrences.
     * @return A {@link Set} of <i>k</i> long sequence parts which occurrences are greater
     *          or equal than <i>t</i> in <i>L</i> clumps.
     * @throws IllegalArgumentException If <i>k</i> is smaller than 1.
     * @throws IllegalArgumentException If <i>k</i> is bigger than L.
     * @throws IllegalArgumentException If <i>L</i> is smaller than 1.
     * @throws IllegalArgumentException If <i>L</i> is bigger or equal than sequence length.
     * @throws IllegalArgumentException If <i>t</i> is smaller than 1.
     */
    @SuppressWarnings("unchecked")
    public Set<TYPE> findPatternsInClumps(final int k, final int L, final int t) {
        checkPositiveNumber("Clump length (L)", L);
        checkSmallerOrEqualNumberTo("Clump length (L)", L, "sequence length", sequenceLength);
        checkPositiveNumber("Findable subsequence length (k)", k);
        checkSmallerOrEqualNumberTo("Findable subsequence length (k)", k, "clump length", L);

        int lengthDiff = sequenceLength - L;
        TYPE window = construct(sequence.substring(0, L));
        String firstWindowPart = window.sequence.substring(0, k);
        String lastWindowPart = window.sequence.substring(L - k, L);
        OccurrenceMap<TYPE> occurrenceMap = window.getOccurrenceMap(k);
        Set<TYPE> patternSet = new HashSet<>();
        patternSet.addAll(occurrenceMap.filterGreaterOrEqualsOccurrences(t));

        for(int i = 0; i < lengthDiff; i++) {
            occurrenceMap.decrease(construct(firstWindowPart));
            firstWindowPart = firstWindowPart.substring(1) + sequence.charAt(i + k);
            lastWindowPart = lastWindowPart.substring(1) + sequence.charAt(i + L);

            TYPE lastWindowDna = construct(lastWindowPart);
            if (occurrenceMap.increase(lastWindowDna) >= t) {
                patternSet.add(lastWindowDna);
            }
        }
        return patternSet;
    }

    /**
     * Get the <i>k</i> long subsequences and their occurrence number in {@link OccurrenceMap}.
     *
     * @param k The parameter for the <i>k</i> long subsequences.
     * @return An {@link OccurrenceMap} with the subsequences.
     */
    protected final OccurrenceMap<TYPE> getOccurrenceMap(final int k) {
        return getMismatchOccurrenceMap(k, 0);
    }

    /**
     * The method returns with the occurrence of the <i>k</i> long patterns which has at most
     * <i>d</i> mismatches than an existing pattern in the sequence.
     * <p>
     * The work of the algorithm:
     * <ol>
     *     <li>Generates the maximum <i>d</i> mismatches for the first <i>k</i> long subsequence
     *     in a mismatch map.</li>
     *     <li>A sliding window separates the mismatch set's elements according to the first
     *     disappearing character is equals with the <i>i</i>. element in the sequence.
     *         <ul>
     *             <li>If the first element of the pattern equals with the <i>i</i>. element of
     *             the sequence, the Hamming-distance doesn't change, the new pattern will
     *             appear in the next run (new mismatch set).</li>
     *             <li>If the first element of the pattern is not equal than the <i>i</i>. element of
     *             the sequence, the Hamming-distance decreases, so the algorithm will have to
     *             generate mismatch instances from the new pattern (generator set).</li>
     *         </ul>
     *     The new element will be the pattern from the 1. position of the end, concatenated
     *     with the <i>i</i>. element of the sequence.
     *     </li>
     *     <li>If <i>d</i> greater than 0 add the subsequence from i + 1. to i + k. to the
     *     generator set.</li>
     *     <li>Generates the next mismatch pattern from the generator set. The algorithm
     *     iterates over every generator element and substitute every element at the last
     *     position in the pattern.</li>
     *     <li>Add these 4 element to the new mismatch set.</li>
     *     <li>Continue at 4. point until the last generator element.</li>
     *     <li>The new mismatch set will be the next mismatch map in the next run.</li>
     *     <li>Continue from 2. point until the sliding widow reaches the end of the sequence.</li>
     * </ol>
     *
     * @param k The <i>k</i> long subsequences.
     * @param d The maximum permitted different elements.
     * @return The occurrence map of the sequence elements and their mismatches.
     * @throws IllegalArgumentException If <i>k</i> is smaller than 1.
     * @throws IllegalArgumentException If <i>k</i> is bigger than sequence length.
     * @throws IllegalArgumentException If <i>d</i> is negative number.
     */
    @SuppressWarnings("unchecked")
    protected final OccurrenceMap<TYPE> getMismatchOccurrenceMap(final int k, final int d) {
        checkPositiveNumber("Findable subsequence (k)", k);
        checkSmallerOrEqualNumberTo("Findable subsequence (k)", k, "sequence length", sequenceLength);

        OccurrenceMap<TYPE> occurrenceMap = OccurrenceMap.build();
        int lengthDiff = sequenceLength - k;
        Set<TYPE> mismatchSet = construct(sequence.substring(0, k))
                                    .generateMismatches(d);

        for (int i = 0; i < lengthDiff; i++) {
            Set<TYPE> nextMismatchesSet = new HashSet<TYPE>();
            Set<TYPE> generatedMismatchesSet = new HashSet<TYPE>();

            for (TYPE mismatchPattern : mismatchSet) {
                occurrenceMap.increase(mismatchPattern);
                TYPE nextMismatchPattern = construct(mismatchPattern.sequence.substring(1) + sequence.charAt(i + k));
                if (sequence.charAt(i) == mismatchPattern.sequence.charAt(0))
                    nextMismatchesSet.add(nextMismatchPattern);
                else
                    generatedMismatchesSet.add(nextMismatchPattern);
            }

            if (d > 0) {
                generatedMismatchesSet.add(construct(sequence.substring(i + 1, i + k) + sequence.charAt(i + k)));
            }

            for (TYPE generatedMismatchPattern : generatedMismatchesSet) {
                char[] charArray = generatedMismatchPattern.sequence.toCharArray();
                ELEMENT[] elementArray = getElementArray();
                for (ELEMENT element : elementArray) {
                    charArray[k - 1] = element.getLetter();
                    nextMismatchesSet.add(construct(new String(charArray)));
                }
            }
            mismatchSet = nextMismatchesSet;
        }
        mismatchSet.forEach(occurrenceMap::increase);
        return occurrenceMap;
    }

    /**
     * Generate {@link BiologicalSequence}s from this sequence which have at most <i>d</i> different
     * elements.
     *
     * @param d The maximum permitted different elements.
     * @return All elements which are different at most <i>d</i> elements.
     * @throws IllegalArgumentException If <i>d</i> is negative number.
     */
    public Set<TYPE> generateMismatches(final int d) {
        checkNotNegativeNumber("Maximum mismatch number (d)", d);

        Map<String, Integer> mismatchMap = new HashMap<>();

        ELEMENT[] elementArray = getElementArray();
        for (ELEMENT element : elementArray) {
            char elementLetter = element.getLetter();
            if (sequence.charAt(0) == elementLetter) {
                mismatchMap.put(Character.toString(elementLetter), 0);
            } else if (d > 0) {
                mismatchMap.put(Character.toString(elementLetter), 1);
            }
        }

        for (int i = 1; i < sequenceLength; i++) {
            Map<String, Integer> newMismatchMap = new HashMap<>();
            for (String prefix : mismatchMap.keySet()){
                int mismatchCount = mismatchMap.get(prefix);
                for (ELEMENT element : elementArray) {
                    char elementLetter = element.getLetter();
                    if (sequence.charAt(i) == elementLetter) {
                        newMismatchMap.put(prefix + elementLetter, mismatchCount);
                    } else if (mismatchCount < d) {
                        newMismatchMap.put(prefix + elementLetter, mismatchCount + 1);
                    }
                }
            }
            mismatchMap = newMismatchMap;
        }
        return mismatchMap.keySet().stream().map(this::construct).collect(Collectors.toSet());
    }

    /**
     * Compares the {@link BiologicalSequence}'s sequence with the other {@link BiologicalSequence}'s sequence and calculates
     * the number of the different elements at the same sequence position.
     *
     * @param otherBiologicalSequence The other {@link BiologicalSequence} compare with.
     * @return The number of the different elements at the same positions.
     * @throws IllegalArgumentException If otherBiologicalSequence has different length.
     */
    public int getMismatchNumber(final TYPE otherBiologicalSequence) {
        validateType(otherBiologicalSequence);
        return SequenceUtils.hammingDistance(sequence, otherBiologicalSequence.sequence);
    }

    private TYPE validateType(final TYPE otherBiologicalSequence) {
        return ArgumentValidator.checkSameTypeTo("Other biological sequence", otherBiologicalSequence, getBiologicalSequenceTypeName(), this);
    }
}
