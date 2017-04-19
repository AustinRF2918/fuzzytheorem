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
    FuzzyTheorem applicationState;
    FuzzySearcher applicationSearch;

    private void setMockDatabase() {
	applicationState.clear();

	FuzzyEntry eulersPhi = new Definition("\\phi{s}", "\sum{d | n}{1}");
	eulersPhi.setName("Euler's Phi Function");

	FuzzyEntry identity = new Definition("Identity over an algebraic structure", "An element such that a * I = a.");
	identity.setName("Identity Attribute Over Algebras");

	FuzzyEntry communitivity = new Definition("Commutivity over an algebraic structure", "A * B = B * A where * is any algebraic operator.");
	communitivity.setName("Identity Attribute Over Communitivity");

	FuzzyEntry leplaceNote = new Other("Remember to always factor before running LePlace operations.");
	leplaceNote.setName("LePlace note 1");

	FuzzyEntry factorNote = new Other("Sometimes tis a good idea.");
	factorNote.setName("Notes on factoring");

	FuzzyEntry pumpingLemma = new Lemma("honestly...", "I never knew what the Yoon was talking about here.");
	pumpingLemma.setName("Pumping Lemma");

	FuzzyEntry eulersTheorem = new Theorem("Given...", "blah");
	eulersTheorem.setName("Euler's Theorem");

	FuzzyEntry eulersProof = new Proof("Euler's Thoerem", "is blah.")
	eulersProof.setName("Euler's Theorem Proof");

	eulersPhi.save();
	identity.save();
	communitivity.save();
	leplaceNote.save();
	factorNote.save();
	pumpingLemma.save();
	eulersTheorem.save();
	eulersProof.save();
    }

    @BeforeClass
    public void initClass() {
	applicationState = new FuzzyTheorem();
	applicationSearch = new FuzzySearcher();
    }

    @Before
    public void init() {
	setMockDatabase();
    }

    @Test
    public void testQueryItemsByNameNone() {
	HashSet<FuzzyEntry> returnSet = applicationSearch.filterByName("Does not exist");
	this.assertEqual(returnSet, new HashSet<FuzzyEntry>())

    }

    @Test
    public void testQueryItemsByNameOne() {
	HashSet<FuzzyEntry> returnSet = applicationSearch.filterByName("Euler's Theorem");

	this.assertEqual(returnSet, new HashSet<FuzzyEntry>())
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
