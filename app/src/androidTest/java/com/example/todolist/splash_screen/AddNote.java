package com.example.todolist.splash_screen;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.example.todolist.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AddNote {

    @Rule
    public ActivityTestRule<SplashActivity> mActivityTestRule = new ActivityTestRule<>(SplashActivity.class);

    @Test
    public void addNote() {
        ViewInteraction if_ = onView(
                allOf(withText("Sign in"),
                        childAtPosition(
                                allOf(withId(R.id.sign_in_button),
                                        childAtPosition(
                                                withClassName(is("android.widget.FrameLayout")),
                                                0)),
                                0),
                        isDisplayed()));

        ViewInteraction linearLayout = onView(
                allOf(withId(R.id.rootView),
                        childAtPosition(
                                allOf(withId(R.id.timelineView),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                0),
                        isDisplayed()));

        ViewInteraction linearLayout2 = onView(
                allOf(withId(R.id.rootView),
                        childAtPosition(
                                allOf(withId(R.id.timelineView),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                2),
                        isDisplayed()));

        ViewInteraction linearLayout3 = onView(
                allOf(withId(R.id.rootView),
                        childAtPosition(
                                allOf(withId(R.id.timelineView),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                2),
                        isDisplayed()));
        linearLayout3.perform(click());

        ViewInteraction linearLayout4 = onView(
                allOf(withId(R.id.rootView),
                        childAtPosition(
                                allOf(withId(R.id.timelineView),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                3),
                        isDisplayed()));
        linearLayout4.perform(click());

        ViewInteraction linearLayout5 = onView(
                allOf(withId(R.id.rootView),
                        childAtPosition(
                                allOf(withId(R.id.timelineView),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                2),
                        isDisplayed()));
        linearLayout5.perform(click());

        ViewInteraction linearLayout6 = onView(
                allOf(withId(R.id.rootView),
                        childAtPosition(
                                allOf(withId(R.id.timelineView),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                3),
                        isDisplayed()));
        linearLayout6.perform(click());

        ViewInteraction materialButton = onView(
                allOf(withId(R.id.add_note_btn), withText("Add Note"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.frame_layout_id),
                                        0),
                                3),
                        isDisplayed()));
        materialButton.perform(click());

        ViewInteraction editText = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withId(android.R.id.custom),
                                0),
                        0),
                        isDisplayed()));
        editText.perform(replaceText("TestAddNote"), closeSoftKeyboard());

        ViewInteraction editText2 = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withId(android.R.id.custom),
                                0),
                        1),
                        isDisplayed()));
        editText2.perform(replaceText("Test addnote"), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
                allOf(withId(android.R.id.button1), withText("Add Note"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        appCompatButton.perform(scrollTo(), click());

        ViewInteraction button = onView(
                allOf(withId(R.id.add_note_btn),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.frame_layout_id),
                                        0),
                                2),
                        isDisplayed()));
        button.check(matches(isDisplayed()));

        ViewInteraction textView = onView(
                allOf(withId(R.id.card_description), withText("Test addnote"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.calendar_detail_time_card),
                                        0),
                                2),
                        isDisplayed()));
        textView.check(matches(withText("Test addnote")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.card_title), withText("TestAddNote"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.calendar_detail_time_card),
                                        0),
                                0),
                        isDisplayed()));
        textView2.check(matches(withText("TestAddNote")));

        ViewInteraction frameLayout = onView(
                allOf(withId(R.id.to_do_item_view),
                        childAtPosition(
                                allOf(withId(R.id.calendar_detail_time_card),
                                        childAtPosition(
                                                withId(R.id.recycler_view_id),
                                                0)),
                                0),
                        isDisplayed()));
        frameLayout.check(matches(isDisplayed()));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
