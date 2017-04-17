import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.TabHost;

import com.fuzzyApp.fuzzyTeam.fuzzyBack.FuzzyTheorem;
import com.fuzzyApp.fuzzyTeam.fuzzyBack.fuzzyEntry.Definition;
import com.fuzzyApp.fuzzyTeam.fuzzyBack.fuzzyEntry.FuzzyEntry;
import com.fuzzyApp.fuzzyTeam.fuzzyBack.fuzzyEntry.Lemma;
import com.fuzzyApp.fuzzyTeam.fuzzyBack.fuzzyEntry.Other;
import com.fuzzyApp.fuzzyTeam.fuzzyBack.fuzzyEntry.Proof;
import com.fuzzyApp.fuzzyTeam.fuzzyBack.fuzzyEntry.Theorem;
import com.fuzzyApp.fuzzyTeam.fuzzyFront.MainActivity;
import com.orm.SugarRecord;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.security.KeyException;

/**
 * Created by Austin on 4/17/17.
 *
 * Tests the FuzzySearch API.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class FuzzySearcherTest {
    // Local test fields go here.

    // Prerequisites: We need a way to restart application state before creating these
    // test cases.

    private void setMockDatabase() {
	// Reset local database. Add mock data to
	// help with querying.
    }

    @Before
    public void init() {
    }

    @Test
    public void testQueryItemsByNameNone() {
    }

    @Test
    public void testQueryItemsByNameOne() {
    }

    @Test
    public void testQueryItemsByNameMany() {
    }

    @Test
    public void testQueryItemsByNameAll() {
    }

    @Test
    public void testQueryItemsByTagNone() {
    }

    @Test
    public void testQueryItemsByTagOne() {
    }

    @Test
    public void testQueryItemsByTagMany() {
    }
    @Test

    public void testQueryItemsByTagAll() {
    }

    @Test
    public void testQueryItemsByCategoryNone() {
    }

    @Test
    public void testQueryItemsByCategorySome() {
    }

    @Test
    public void testQueryItemsByCategoryMany() {
    }

    @Test
    public void getAllItems() {
    }

    @Test
    public void getAllItemsEmptyDatabase() {
    }

    @Test
    public void getAllItemsMutable() {
    }

    @Test
    public void getAllItemsMutableFromEmpty() {
    }
}
