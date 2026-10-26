package com.example.praktinisdarbas2;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.containsString;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> rule =
            new ActivityScenarioRule<>(MainActivity.class);

    //pagalbiniai metodai

    private void typeText(String text) {
        onView(withId(R.id.textInput))
                .perform(replaceText(text), closeSoftKeyboard());
    }


    private void selectMode(int position) {
        onView(withId(R.id.spinner)).perform(click());
        onData(anything()).atPosition(position).perform(click());
    }

    private void assertResult(String label, int count) {
        onView(withId(R.id.textOutput)).check(matches(
                allOf(withText(containsString(label)),
                        withText(containsString(String.valueOf(count))))));
    }

    //pradine busena

    @Test
    public void allViews_areDisplayed() {
        onView(withId(R.id.textInput)).check(matches(isDisplayed()));
        onView(withId(R.id.spinner)).check(matches(isDisplayed()));
        onView(withId(R.id.textOutput)).check(matches(isDisplayed()));
    }

    @Test
    public void initialState_showsSentencesZero() {
        assertResult("Sentences", 0);
    }

    //skaiciavimas pagal rezima

    @Test
    public void sentencesMode_countsSentences() {
        typeText("Hello. World! Fine?");
        assertResult("Sentences", 3);
    }

    @Test
    public void wordsMode_countsWords() {
        selectMode(1);
        typeText("Hello Dear World!");
        assertResult("Words", 3);
    }

    @Test
    public void punctuationMode_countsPunctuation() {
        selectMode(2);
        typeText("Hi, there!");
        assertResult("Punctuation", 2);
    }

    @Test
    public void numbersMode_countsDigits() {
        selectMode(3);
        typeText("abc 123");
        assertResult("Numbers", 3);
    }

    //sąveika

    @Test
    public void changingSpinner_updatesResultForSameText() {
        typeText("Hello world. Bye.");
        assertResult("Sentences", 2);

        selectMode(1);
        assertResult("Words", 3);
    }

    @Test
    public void clearingText_resetsCountToZero() {
        selectMode(1);
        typeText("one two three");
        assertResult("Words", 3);

        onView(withId(R.id.textInput)).perform(clearText(), closeSoftKeyboard());
        assertResult("Words", 0);
    }

    @Test
    public void lithuanianCharacters_areCountedInWordsMode() {
        selectMode(1);
        typeText("Abra kadabra");
        assertResult("Words", 2);
    }
}