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
 * Created by Austin on 4/15/17.
 *
 * Tests the FuzzyEntry API.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class FuzzyEntryTest {
    private FuzzyEntry fermatsTheorem;
    private FuzzyEntry pumpingLemma;
    private FuzzyEntry other;
    private FuzzyEntry myProof;
    private FuzzyEntry myDefinition;

    private FuzzyEntry fermatsTheoremDeserialized;
    private FuzzyEntry pumpingLemmaDeserialized;
    private FuzzyEntry otherDeserialized;
    private FuzzyEntry myProofDeserialized;
    private FuzzyEntry myDefinitionDeserialized;

    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule<>(MainActivity.class);

    private boolean theoremEqual(Theorem t1, Theorem t2) throws Exception {
        try {
            return t1.getName().equals(t2.getName()) &&
                    t1.getDescription().equals(t2.getDescription()) &&
                    t1.getString("precondition").equals(t2.getString("precondition")) &&
                    t1.getString("postcondition").equals(t2.getString("postcondition"));
        } catch (KeyException e) {
            throw new Exception("Bad Theorem passed to theoremEqual");
        }
    }

    private boolean lemmaEqual(Lemma l1, Lemma l2) throws Exception {
        try {
            return l1.getName().equals(l2.getName()) &&
                    l1.getDescription().equals(l2.getDescription()) &&
                    l1.getString("precondition").equals(l2.getString("precondition")) &&
                    l1.getString("postcondition").equals(l2.getString("postcondition"));
        } catch (KeyException e) {
            throw new Exception("Bad Lemma passed to lemmaEqual");
        }
    }

    private boolean otherEqual(Other o1, Other o2) throws Exception {
        try {
            return o1.getName().equals(o2.getName()) &&
                    o1.getDescription().equals(o2.getDescription()) &&
                    o1.getString("statement").equals(o2.getString("statement"));
        } catch (KeyException e) {
            throw new Exception("Bad Other passed to otherEqual");
        }
    }

    private boolean proofEqual(Proof p1, Proof p2) throws Exception {
        try {
            return p1.getName().equals(p2.getName()) &&
                    p1.getDescription().equals(p2.getDescription()) &&
                    p1.getString("statementName").equals(p2.getString("statementName")) &&
                    p1.getString("content").equals(p2.getString("content"));
        } catch (KeyException e) {
            throw new Exception("Bad Proof passed to proofEqual");
        }
    }

    private boolean definitionEqual(Definition d1, Definition d2) throws Exception {
        try {
            return d1.getName().equals(d2.getName()) &&
                    d1.getDescription().equals(d2.getDescription()) &&
                    d1.getString("symbolContent").equals(d2.getString("symbolContent")) &&
                    d1.getString("symbolContent").equals(d2.getString("symbolContent"));
        } catch (KeyException e) {
            throw new Exception("Bad Definition passed to definitionEqual");
        }
    }

    @Before
    public void init() {

        fermatsTheorem = new Theorem("$$ a, p \\in \\mathbb{Z} \\and (a, p) = 1 $$", "$$ a^{p-1} == 1 (mod p) $$");
        pumpingLemma = new Lemma("If we have a finite state machine with blah", "then blah");
        other = new Other("I think, therefore I am.");
        myProof = new Proof("Why did the chicken cross the road?", "I don't know.");
        myDefinition = new Definition("J. Baugh", "A good professor.");

        long fermatId = fermatsTheorem.save();
        long pumpingId = pumpingLemma.save();
        long otherId = other.save();
        long myProofId = myProof.save();
        long myDefinitionId = myDefinition.save();

        fermatsTheoremDeserialized = SugarRecord.findById(Theorem.class, fermatId);
        pumpingLemmaDeserialized = SugarRecord.findById(Lemma.class, pumpingId);
        otherDeserialized = SugarRecord.findById(Other.class, otherId);
        myProofDeserialized = SugarRecord.findById(Proof.class, myProofId);
        myDefinitionDeserialized = SugarRecord.findById(Definition.class, myDefinitionId);
    }

    @Test
    public void testSerialization(){
        // Tests for the general property that if we send an item to
        // the database, when we retrieve it, it will be functionally the same as
        // the thing we put in.

        try {
            Assert.assertTrue(theoremEqual((Theorem) fermatsTheoremDeserialized, (Theorem) fermatsTheorem));
            Assert.assertTrue(lemmaEqual ((Lemma) pumpingLemmaDeserialized, (Lemma) pumpingLemma));
            Assert.assertTrue(otherEqual ((Other) otherDeserialized, (Other) other));
            Assert.assertTrue(proofEqual ((Proof) myProofDeserialized, (Proof) myProof));
            Assert.assertTrue(definitionEqual ((Definition) myDefinitionDeserialized, (Definition) myDefinition));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMutability() {
        // Tests that changes to local models that are commited persist on repull.
        // basically a sanity test for our ORM.
        try {
            fermatsTheoremDeserialized.putString("precondition", "testing");
            Long id = fermatsTheoremDeserialized.save();

            fermatsTheoremDeserialized = SugarRecord.findById(Theorem.class, id);
            Assert.assertTrue(fermatsTheoremDeserialized.getString("precondition").equals("testing"));
        } catch (KeyException e) {
            e.printStackTrace();
        }
    }

    @Test(expected = KeyException.class)
    public void testErrorsTheorem() throws KeyException {
        // Makes sure Theorem API works as intended.

        fermatsTheoremDeserialized.putString("nonexistent-field", "hi!");
    }

    @Test(expected = KeyException.class)
    public void testErrorsDefinition() throws KeyException {
        // Makes sure Definition API works as intended.

        myDefinitionDeserialized.putString("nonexistent-field", "hi!");
    }

    @Test(expected = KeyException.class)
    public void testErrorsLemma() throws KeyException {

        // Makes sure Definition API works as intended.
        pumpingLemmaDeserialized.putString("nonexistent-field", "hi!");
    }

    @Test(expected = KeyException.class)
    public void testErrorsOther() throws KeyException {
        // Makes sure Definition API works as intended.

        otherDeserialized.putString("nonexistent-field", "hi!");
    }

    @Test(expected = KeyException.class)
    public void testErrorsProof() throws KeyException {
        // Makes sure Definition API works as intended.

        myProofDeserialized.putString("nonexistent-field", "hi!");
    }
}
