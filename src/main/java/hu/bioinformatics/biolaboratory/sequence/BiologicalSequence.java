package hu.bioinformatics.biolaboratory.sequence;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.sun.javafx.binding.StringFormatter;
import hu.bioinformatics.biolaboratory.utils.datastructures.CountableOccurrenceMap;
import hu.bioinformatics.biolaboratory.utils.datastructures.OccurrenceMap;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Represents an abstract biological sequence, which can be a DNA, RNA or a protein. Contains all of the common
 * operations, what are interpretable for all biological sequences.
 *
 * @author Attila Radi
 */
public abstract class BiologicalSequence<TYPE extends BiologicalSequence, PART extends SequenceElement> {

    protected final String sequence;
    protected final int sequenceLength;

    private CountableOccurrenceMap<PART> elementOccurrences = null;

    protected BiologicalSequence(final String sequence) {
        this.sequence = sequence;
        this.sequenceLength = sequence.length();
    }

    /**
     * Calls the constructor of the inherited class.
     *
     * @param sequence The String sequence of the biological sequence.
     * @return The instance of the inherited class
     */
    protected abstract TYPE construct(String sequence);

    /**
     * Creates a copy of the {@link BiologicalSequence}.
     *
     * @return The copy of the {@link BiologicalSequence}.
     */
    public TYPE copy() { return construct(sequence); }

    /**
     * Getter of the biological sequence.
     *
     * @return sequence
     */
    public final String getSequence() {
        return sequence;
    }

    /**
     * Getter of the biological sequence length.
     *
     * @return sequenceLength
     */
    public final int getSequenceLength() {
        return sequenceLength;
    }

    /**
     * Immutable getter of the nucleotide occurrences. It calculates the occurrences at the first call.
     *
     * @return The nucleotide occurrences.
     */
    public final CountableOccurrenceMap<PART> getElementOccurrences() {
        return collectSequenceElementOccurrences().copy();
    }

    protected final synchronized CountableOccurrenceMap<PART> collectSequenceElementOccurrences() {
        if (elementOccurrences == null) {
            elementOccurrences = CountableOccurrenceMap.build(getElementSet());
            IntStream.range(0, sequence.length())
                    .forEach(index ->
                            elementOccurrences.increase(findSequenceElement(sequence.charAt(index))));
        }
        return elementOccurrences;
    }

    protected abstract PART[] getElementArray();

    protected abstract Set<PART> getElementSet();

    protected abstract PART findSequenceElement(final char sequenceElementLetter);

    @Override
    public int hashCode() {
        return sequence.hashCode();
    }

    @Override
    public String toString() {
        return getName() + " = {" + sequence + "}";
    }

    /**
     * Get the name of the sequence type (e.g. DNA).
     *
     * @return The name of the sequence type.
     */
    protected abstract String getName();

    /**
     * Cuts a {@link BiologicalSequence} part from the start position to the end of the {@link BiologicalSequence}'s length.
     *
     * @param startPosition The beginning nucletide position in the {@link BiologicalSequence} inclusive.
     * @return The {@link BiologicalSequence} part from start position (inclusive) to the end.
     */
    public final TYPE cut(int startPosition) {
        return cut(startPosition, sequenceLength);
    }

    /**
     * Cuts a {@link BiologicalSequence} part about the given index and return it to the caller. The cut operation includes the start
     * position, but excludes the end position.
     *
     * @param startPosition The beginning element position in the {@link BiologicalSequence} inclusive.
     * @param endPosition The end element position in the {@link BiologicalSequence} exclusive.
     * @return The {@link BiologicalSequence} part from start position (inclusive) to end position (exclusive).
     */
    public final TYPE cut(int startPosition, int endPosition) {
        Preconditions.checkArgument(startPosition >= 0, "Start position should bigger than 0");
        Preconditions.checkArgument(endPosition <= sequenceLength, "End position should smaller or equals than the sequence length");
        Preconditions.checkArgument(startPosition < endPosition, "Start position should smaller than the end position");
        return construct(sequence.substring(startPosition, endPosition));
    }

    /**
     * Count the occurrences of the given pattern inside the {@link BiologicalSequence} sequence.
     * The sequence parts can overlap.
     *
     * @see BiologicalSequence#findPatternsWithMismatch(BiologicalSequence, int)
     * @param pattern The pattern {@link BiologicalSequence}.
     * @return The number of found patterns.
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
     */
    public List<Integer> patternMatchingWithMismatches(final TYPE pattern, final int d) {
        Preconditions.checkArgument(d >= 0, "The maximum differenc value (d) should greater or equals than 0");
        validatePattern(pattern);
        return findPatternsWithMismatch(pattern, d);
    }

    private void validatePattern(final TYPE pattern) {
        Preconditions.checkArgument(pattern != null, StringFormatter.format("%s should not be null", getName()));
        Preconditions.checkArgument(pattern.sequenceLength <= sequenceLength, StringFormatter.format("Pattern length should smaller or equals than %s sequence length", getName()));
    }

    /**
     * The work of the algorithm:
     * <ol>
     *     <li>Cut a template sequence from the beginning of the biological sequence which length equals to
     *     the pattern's length.</li>
     *     <li>Compares the template with the pattern.</li>
     *     <li>If the pattern agains the template has at most d mismatches then add the
     *     first index of the template inside the biological sequence to the return values.</li>
     *     <li>The next template will be constructed from the second element to the end of
     *     the template concatenated with the next character of the biological sequence after this template.</li>
     *     <li>Returns to the 2. point until the loop variable reaches the difference of the
     *     biological sequence length and the pattern length.</li>
     * </ol>
     *
     * @param pattern The pattern to find.
     * @return The first indices of the occurrences inside the {@link TYPE} sequence.
     */
    private List<Integer> findPatternsWithMismatch(final TYPE pattern, final int d) {
        int lengthDiff = sequenceLength - pattern.sequenceLength;
        List<Integer> startPositionList = Lists.newLinkedList();

        for (int i = 0; i <= lengthDiff; i++) {
            String template = sequence.substring(i, i + pattern.sequenceLength);
            int j = 0;
            int mismatch = 0;
            while (j < pattern.sequenceLength && mismatch <= d) {
                if (template.charAt(j) != pattern.sequence.charAt(j)) mismatch++;
                j++;
            }
            if (mismatch <= d) {
                startPositionList.add(i);
            }
        }
        return  startPositionList;
    }

    /**
     * Get the most frequent <i>k</i> occurrences in the biological sequence.
     *
     * @param k The findable <i>k</i> long sequences.
     * @return The most frequent <i>k</i> long occurrences.
     */
    public Set<TYPE> findMostFrequentSubSequences(final int k) {
        return findMostFrequentMismatchSubSequences(k, 0);
    }

    /**
     * Get the most frequent <i>k</i> occurrences which has at most <i>d</i> different
     * nucleotides in the biological sequence.
     *
     * @param k The findable <i>k</i> long sequences.
     * @param d The maximum permitted different nucleotides.
     * @return The most frequent <i>k</i> long occurrences with at most <i>d</i> mismatches.
     */
    public Set<TYPE> findMostFrequentMismatchSubSequences(final int k, final int d) {
        return getMismatchOccurrenceMap(k, d).filterMostFrequentOccurrences();
    }

    /**
     * Get all <i>k</i> long subsequences of the DNA.
     *
     * @param k The size of the subsequnces.
     * @return All <i>k</i> long subsequences in a {@link Set}.
     */
    public Set<TYPE> getSubSequences(final int k) {
        return getMismatchSubSequences(k, 0);
    }

    /**
     * Get all <i>k</i> long subsequences with at most <i>d</i> mismatches of the DNA.
     *
     * @param k The size of the subsequnces.
     * @param d The maximum permitted different nucleotides.
     * @return All <i>k</i> long subsequences with <i>d</i> mismatch in a {@link Set}.
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
     */
    public Set<TYPE> findFrequentSubSequences(final int k, final int t) {
        return findFrequentMismatchSubSequences(k, 0, t);
    }

    /**
     * Get all <i>k</i> long sequences which has at most <i>d</i> different
     * nucleotides and are greater or equals than <i>t</i> in the DNA sequence.
     *
     * @param k The findable <i>k</i> long sequences.
     * @param d The maximum permitted different nucleotides.
     * @param t The threshold of the occurrences of the <i>k</i> long sequences in the DNA.
     * @return All <i>k</i> long sequences at most <i>d</i> mismatches which occurrences
     *             are greater or equals than <i>t</i>.
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
     *             or equal than <i>t</i> in <i>L</i> clumps.
     */
    @SuppressWarnings("unchecked")
    public Set<TYPE> findPatternsInClumps(final int k, final int L, final int t) {
        Preconditions.checkArgument(L > 0, "Clump length (L) should be greater than 0");
        Preconditions.checkArgument(L <= sequenceLength, "Clump length (L) should be smaller or equals to the sequence length");
        Preconditions.checkArgument(k > 0, "Findable subsequence length (k) should be greater than 0");
        Preconditions.checkArgument(k <= L, "Findable subsequence length (k) should be smaller or equals to the clump length (L)");

        int lengthDiff = sequenceLength - L;
        TYPE window = construct(sequence.substring(0, L));
        String firstWindowPart = window.sequence.substring(0, k);
        String lastWindowPart = window.sequence.substring(L - k, L);
        OccurrenceMap<TYPE> occurrenceMap = window.getOccurrenceMap(k);
        Set<TYPE> patternSet = Sets.newHashSet();
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
     *     The new element will be the pattern from the 1. position o the end, concatenated
     *     with the <i>i</i>. element of the sequence.
     *     </li>
     *     <li>If <i>d</i> greater than 0 add the subsequence from i + 1. to k + i. to the
     *     generator set.</li>
     *     <li>Generates the next mismatch pattern from the generator set. The algorithm
     *     iterates over every generator element and substitute every nucleobas at the last
     *     position in the pattern.</li>
     *     <li>Add these 4 element to the new mismatch set.</li>
     *     <li>Continue at 4. point until the last generator element.</li>
     *     <li>The new mismatch set will be the next mismatch map in the next run.</li>
     *     <li>Continue from 2. point until the sliding widow reaches the end of the sequence.</li>
     * </ol>
     *
     * @param k The <i>k</i> long subsequences.
     * @param d The maximum permitted different nucleotides.
     * @return The occurrence map of the sequence elements and their mismatches.
     */
    @SuppressWarnings("unchecked")
    protected final OccurrenceMap<TYPE> getMismatchOccurrenceMap(final int k, final int d) {
        Preconditions.checkArgument(k > 0, "Findable subsequence length (k) should be greater than 0");
        Preconditions.checkArgument(k <= sequenceLength, "Findable subsequence length (k) should be smaller or equals to the sequence length");

        OccurrenceMap<TYPE> occurrenceMap = OccurrenceMap.build();
        int lengthDiff = sequenceLength - k;
        Set<TYPE> mismatchSet = construct(sequence.substring(0, k))
                .generateMismatches(d);

        for (int i = 0; i < lengthDiff; i++) {
            Set<TYPE> nextMismatchesSet = Sets.newHashSet();
            Set<TYPE> generatedMismatchesSet = Sets.newHashSet();

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
                PART[] nucleotideArray = getElementArray();
                for (PART nucleotide : nucleotideArray) {
                    charArray[k - 1] = nucleotide.getLetter();
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
     * nucleotides.
     *
     * @param d The maximum permitted different nucleotides.
     * @return All nucleotides which are different at most <i>d</i> nucleotides.
     */
    public Set<TYPE> generateMismatches(final int d) {
        Preconditions.checkArgument(d >= 0, "Maximum mismatch number (d) should be greater or equals than 0");

        Map<String, Integer> mismatchMap = Maps.newHashMap();

        PART[] nucleotideArray = getElementArray();
        for (PART aNucleotideArray : nucleotideArray) {
            char nucleotideLetter = aNucleotideArray.getLetter();
            if (sequence.charAt(0) == nucleotideLetter) {
                mismatchMap.put(Character.toString(nucleotideLetter), 0);
            } else if (d > 0) {
                mismatchMap.put(Character.toString(nucleotideLetter), 1);
            }
        }

        for (int i = 1; i < sequenceLength; i++) {
            Map<String, Integer> newMismatchMap = Maps.newHashMap();
            for (String prefix : mismatchMap.keySet()){
                int mismatchCount = mismatchMap.get(prefix);
                for (PART aNucleotideArray : nucleotideArray) {
                    char nucleotideLetter = aNucleotideArray.getLetter();
                    if (sequence.charAt(i) == nucleotideLetter) {
                        newMismatchMap.put(prefix + nucleotideLetter, mismatchCount);
                    } else if (mismatchCount < d) {
                        newMismatchMap.put(prefix + nucleotideLetter, mismatchCount + 1);
                    }
                }
            }
            mismatchMap = newMismatchMap;
        }
        return mismatchMap.keySet().stream().map(this::construct).collect(Collectors.toSet());
    }

    /**
     * Compares the {@link BiologicalSequence}'s sequence with the other {@link BiologicalSequence}'s sequence and calculates
     * the number of the different nucleotides at the same sequence position.
     *
     * @param otherBiologicalSequence The other {@link BiologicalSequence} compare with.
     * @return The number of the different nucleotides at the same positions.
     * @throws IllegalArgumentException If otherBiologicalSequence has different length.
     */
    public int getMismatchNumber(final TYPE otherBiologicalSequence) {
        this.validateSameLengthBiologicalSequence(otherBiologicalSequence);

        int hamming = 0;
        for (int i = 0; i < sequenceLength; i++) {
            if (sequence.charAt(i) != otherBiologicalSequence.sequence.charAt(i)) hamming++;
        }
        return hamming;
    }

    private void validateSameLengthBiologicalSequence(BiologicalSequence otherBiologicalSequence) {
        Preconditions.checkArgument(otherBiologicalSequence != null, StringFormatter.format("Other %s should not be null", getName()));
        Preconditions.checkArgument(sequenceLength == otherBiologicalSequence.sequenceLength, StringFormatter.format("The sequence length of the other %s should be the same as the sequence length", getName()));
    }
}
