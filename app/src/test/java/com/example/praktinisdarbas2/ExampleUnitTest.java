package com.example.praktinisdarbas2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Before;
import org.junit.Test;

public class ExampleUnitTest {

    private TextCounter counter;

    @Before
    public void setUp() {
        counter = new TextCounter();
    }

    //countSentences

    @Test
    public void countSentences_emptyString_returnsZero() {
        assertEquals(0, counter.countSentences(""));
    }

    @Test
    public void countSentences_onlyWhitespace_returnsZero() {
        assertEquals(0, counter.countSentences("   "));
    }

    @Test
    public void countSentences_singleSentenceWithoutPunctuation_returnsOne() {
        assertEquals(1, counter.countSentences("Hello World"));
    }

    @Test
    public void countSentences_twoSentences_returnsTwo() {
        assertEquals(2, counter.countSentences("Hello. World."));
    }

    @Test
    public void countSentences_newlineBetweenSentences_returnsTwo() {
        assertEquals(2, counter.countSentences("Hello.\nWorld."));
    }

    @Test
    public void countSentences_noSpaceAfterDot_returnsOne() {
        assertEquals(1, counter.countSentences("Hello.World"));
    }

    //countWords

    @Test
    public void countWords_emptyString_returnsZero() {
        assertEquals(0, counter.countWords(""));
    }

    @Test
    public void countWords_simpleSentence_returnsThree() {
        assertEquals(3, counter.countWords("Hello dear world!"));
    }

    @Test
    public void countWords_multipleSpaces_returnsTwo() {
        assertEquals(2, counter.countWords("Hello     world"));
    }

    @Test
    public void countWords_curlyApostropheIsOneWord() {
        assertEquals(1, counter.countWords("it’s"));
    }

    @Test
    public void countWords_hyphenatedWordIsOneWord() {
        assertEquals(1, counter.countWords("how-are-you"));
    }

    @Test
    public void countWords_numbersAreNotWords() {
        assertEquals(1, counter.countWords("abc 123"));
    }

    @Test
    public void countWords_onlyPunctuation_returnsZero() {
        assertEquals(0, counter.countWords("... !!! ???"));
    }

    @Test
    public void countWords_spacedHyphenSeparatesWords() {
        assertEquals(2, counter.countWords("hello - world"));
    }

    //countNumbers

    @Test
    public void countNumbers_emptyString_returnsZero() {
        assertEquals(0, counter.countNumbers(""));
    }

    @Test
    public void countNumbers_noDigits_returnsZero() {
        assertEquals(0, counter.countNumbers("abc def"));
    }

    @Test
    public void countNumbers_singleDigit_returnsOne() {
        assertEquals(1, counter.countNumbers("a5b"));
    }

    @Test
    public void countNumbers_multiDigitNumber_countsEachDigit() {
        assertEquals(3, counter.countNumbers("abc 123"));
    }

    @Test
    public void countNumbers_twoNumbers_countsAllDigits() {
        assertEquals(5, counter.countNumbers("12 and 345"));
    }

    //countPunctuation

    @Test
    public void countPunctuation_emptyString_returnsZero() {
        assertEquals(0, counter.countPunctuation(""));
    }

    @Test
    public void countPunctuation_noPunctuation_returnsZero() {
        assertEquals(0, counter.countPunctuation("Hello world 123"));
    }

    @Test
    public void countPunctuation_commaAndExclamation_returnsTwo() {
        assertEquals(2, counter.countPunctuation("Hello, world!"));
    }


    @Test
    public void countWords_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> counter.countWords(null));
    }
}