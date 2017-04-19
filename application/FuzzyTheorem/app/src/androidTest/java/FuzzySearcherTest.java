import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;

import android.support.test.runner.AndroidJUnit4;
import android.widget.TabHost;

import com.fuzzyApp.fuzzyTeam.fuzzyBack.FuzzySearcher;
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
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.security.KeyException;
import java.util.HashSet;

/**
 * Created by Austin on 4/17/17.
 * <p>
 * Tests the FuzzySearch API.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class FuzzySearcherTest {
    private FuzzyTheorem applicationState;
    private FuzzySearcher applicationSearch;

    // Mock fuzzy entries we will use for
    // running our tests.
    private FuzzyEntry eulersPhi;
    private FuzzyEntry identity;
    private FuzzyEntry communitivity;
    private FuzzyEntry leplaceNote;
    private FuzzyEntry factorNote;
    private FuzzyEntry pumpingLemma;
    private FuzzyEntry eulersTheorem;
    private FuzzyEntry eulersProof;


    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule<>(MainActivity.class);


    @BeforeClass
    public void initClass() {
        applicationState = new FuzzyTheorem();
        applicationSearch = new FuzzySearcher();
    }

    @Before
    public void init() {
        applicationState.clear();

        eulersPhi = new Definition("\\phi{s}", "\\sum{d | n}{1}");
        eulersPhi.setName("Euler's Phi Function");
        eulersPhi.save();

        identity = new Definition("Identity over an algebraic structure", "An element such that a * I = a.");
        identity.setName("Identity Attribute Over Algebras");
        identity.save();

        communitivity = new Definition("Commutivity over an algebraic structure", "A * B = B * A where * is any algebraic operator.");
        communitivity.setName("Identity Attribute Over Communitivity");
        communitivity.save();

        leplaceNote = new Other("Remember to always factor before running LePlace operations.");
        leplaceNote.setName("LePlace note 1");
        leplaceNote.save();

        factorNote = new Other("Sometimes tis a good idea.");
        factorNote.setName("Notes on factoring");
        factorNote.save();

        pumpingLemma = new Lemma("honestly...", "I never knew what the Yoon was talking about here.");
        pumpingLemma.setName("Pumping Lemma");
        pumpingLemma.save();

        eulersTheorem = new Theorem("Given...", "blah");
        eulersTheorem.setName("Euler's Theorem");
        eulersTheorem.save();

        eulersProof = new Proof("Euler's Thoerem", "is blah.");
        eulersProof.setName("Euler's Theorem Proof");
        eulersProof.save();
    }

    @Test
    public void testQueryItemsByNameNone() {
        HashSet<FuzzyEntry> returnSet = applicationSearch.filterByName("Does not exist");
        Assert.assertEquals(returnSet, new HashSet<FuzzyEntry>());
    }

    @Test
    public void testQueryItemsByNameOne() {
        HashSet<FuzzyEntry> returnSet = applicationSearch.filterByName("Euler's Theorem");
        HashSet<FuzzyEntry> mockSet = new HashSet<FuzzyEntry>();
        mockSet.add(eulersTheorem);
        Assert.assertEquals(returnSet, mockSet);
    }

    @Test
    public void testQueryItemsByNameMany() {
        HashSet<FuzzyEntry> returnSet = applicationSearch.filterByName("Euler");
        HashSet<FuzzyEntry> mockSet = new HashSet<FuzzyEntry>();
        mockSet.add(eulersTheorem);
        mockSet.add(eulersPhi);
        mockSet.add(eulersProof);
        Assert.assertEquals(returnSet, mockSet);
    }

    @Test
    public void testQueryItemsByNameAll() {
        HashSet<FuzzyEntry> returnSet = applicationSearch.getAllFuzzyItems();
        HashSet<FuzzyEntry> mockSet = new HashSet<FuzzyEntry>();

        mockSet.add(identity);
        mockSet.add(communitivity);
        mockSet.add(leplaceNote);
        mockSet.add(factorNote);
        mockSet.add(pumpingLemma);
        mockSet.add(eulersTheorem);
        mockSet.add(eulersPhi);
        mockSet.add(eulersProof);

        Assert.assertEquals(returnSet, mockSet);
    }

    @Test
    public void testQueryItemsByTagNone() {
        // TODO: Implement
    }

    @Test
    public void testQueryItemsByTagOne() {
        // TODO: Implement
    }

    @Test
    public void testQueryItemsByTagMany() {
        // TODO: Implement
    }

    @Test

    public void testQueryItemsByTagAll() {
    }

    @Test
    public void testQueryItemsByCategoryNone() {
        // TODO: Implement
    }

    @Test
    public void testQueryItemsByCategorySome() {
        // TODO: Implement
    }

    @Test
    public void testQueryItemsByCategoryMany() {
        // TODO: Implement
    }

    @Test
    public void getAllItems() {
        // TODO: Implement
    }

    @Test
    public void getAllItemsEmptyDatabase() {
        // TODO: Implement
    }

    @Test
    public void getAllItemsMutable() {
        // TODO: Implement
    }

    @Test
    public void getAllItemsMutableFromEmpty() {
        // TODO: Implement
    }
}
