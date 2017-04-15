package projects.android.nguyen.kim.pharmacyTechPractice;

import android.content.Context;
import android.test.RenamingDelegatingContext;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowApplication;

import java.util.List;
import java.util.Map;

import projects.android.nguyen.kim.pharmacyTechPractice.logic.AddDrugActivityLogic;
import projects.android.nguyen.kim.pharmacyTechPractice.logic.AddPharmacyAbbreviationLogic;
import projects.android.nguyen.kim.pharmacyTechPractice.logic.DrugLab;
import projects.android.nguyen.kim.pharmacyTechPractice.logic.QuizLogic;
import projects.android.nguyen.kim.pharmacyTechPractice.logic.TestSigCodeLogic;
import projects.android.nguyen.kim.pharmacyTechPractice.model.DrugModel;
import projects.android.nguyen.kim.pharmacyTechPractice.model.PharmacyAbbreviationModel;

import static junit.framework.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class ExampleUnitTest {

    Context context;

    @Before
    public void setUp() {
        context = Shadows.shadowOf(RuntimeEnvironment.application).getApplicationContext();
    }

    @After
    public void tearDown() {
        context = null;
    }

    /**
     * AddPharmacyAbbreviationLogic
     */
    @Test
    public void testAbbreviationDbLogic() throws Exception {
        AddPharmacyAbbreviationLogic logic = new AddPharmacyAbbreviationLogic(context);

        // prepare test data
        String sigCode = "testSigCode";
        String meaning = "testMeaning";
        PharmacyAbbreviationModel model = new PharmacyAbbreviationModel();

        // execute function
        logic.saveData(sigCode, meaning, model);

        // verify result
        // TODO: how to get the saved PharmacyAbbreviation => lack of method for getting back from db
    }

    /**
     * AddDrugActivityLogic
     */
    @Test
    public void testAddDrugActivityLogic() {
        AddDrugActivityLogic logic = new AddDrugActivityLogic(context);

        // prepare test data
        String brand = "testBrand";
        String generic = "testGeneric";
        String scheduled = "testSchedule";
        String doseForm = "testDoseForm";
        String function = "testFunction";
        String sideEffects = "testSideEffects";
        String comments = "testComments";
        DrugModel drugModel = new DrugModel();

        // execute function
        logic.saveData(brand, generic, scheduled, doseForm, function, sideEffects, comments, drugModel);

        // verify result
        // TODO: how to get the saved AddDrugActivityLogic => lack of method for getting back from db
    }

    /**
     * DrugLab
     */
    @Test
    public void testGetDrugList() {
        DrugLab lab = DrugLab.get(context);
        List<DrugModel> list = lab.getDrugList();
        Assert.assertNotNull(list);
    }

    /**
     * QuizLogic
     */
    @Test
    public void testGetGeneratedDrugs() {
        QuizLogic quiz = new QuizLogic(context);
        Map<String, String> map = quiz.getGeneratedDrugs();
        Assert.assertNotNull(map);
    }

    /**
     * TestSigCodeLogic
     */
    @Test
    public void testGenerateRandAbbreviation() {
        TestSigCodeLogic logic = new TestSigCodeLogic(context);
        Assert.assertNotSame("", logic.generateRandAbbreviation());
    }
}