package projects.android.nguyen.kim.pharmacyTechPractice.model;

/**
 * DrugModel holds the medication information.
 *
 * @author Kim Nguyen
 * @version 3/15/2017.
 */

public class DrugModel {
    private String brand;
    private String generic;
    private String doseForm;
    private String commonUses;
    private String sideEffects;
    private String comments;
    private String scheduled;

    public String getScheduled() { return scheduled; }

    public void setScheduled(String scheduled) {
        this.scheduled = scheduled;
    }

    public String getDoseForm() { return doseForm; }

    public void setDoseForm(String doseForm) {
        this.doseForm = doseForm;
    }

    public String getSideEffects() {
        return sideEffects;
    }

    public void setSideEffects(String sideEffects) {
        this.sideEffects = sideEffects;
    }

    public String getBrand() { return brand; }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getGeneric() {
        return generic;
    }

    public void setGeneric(String generic) {
        this.generic = generic;
    }

    public String getCommonUses() {
        return commonUses;
    }

    public void setCommonUses(String function) {
        this.commonUses = function;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
