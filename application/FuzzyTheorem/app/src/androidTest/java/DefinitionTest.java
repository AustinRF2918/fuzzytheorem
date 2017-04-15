import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.fuzzyApp.fuzzyTeam.fuzzyFront.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Austin on 4/15/17.
 *
 * Tests the Definition API.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class DefinitionTest {

    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testSayHello(){
        onView(withText("Hello gain")).perform(click());
        onView(withText("Hello gain")).check(matches(withText("Hello gain")));
    }
}
