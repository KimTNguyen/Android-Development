package projects.android.nguyen.kim.pharmacyTechPractice.model;

/**
 * PharmacyAbbreviationModel holds the pharmacy abbreviation data.
 *
 * @author Kim Nguyen
 * @version 3/15/2017.
 */

public class PharmacyAbbreviationModel {
    private String sigCode;
    private String meaning;

    public String getSigCode() {
        return sigCode;
    }

    public void setSigCode(String sigCode) {
        this.sigCode = sigCode;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }
}
