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
    private String function;
    private String direction;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getGeneric() {
        return generic;
    }

    public void setGeneric(String generic) {
        this.generic = generic;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
