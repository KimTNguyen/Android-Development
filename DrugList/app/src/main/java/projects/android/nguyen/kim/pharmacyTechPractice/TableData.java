package projects.android.nguyen.kim.pharmacyTechPractice;

import android.provider.BaseColumns;

/**
 * TableData defines how the database is organized.
 *
 * Created by Kim Nguyen on 3/8/2017.
 */

public final class TableData {

    private TableData() {}

    public static class DrugInfo implements BaseColumns {
        public static final String TABLE_NAME = "drug";
        public static final String COLUMN_NAME_BRAND = "brand";
        public static final String COLUMN_NAME_GENERIC = "generic";
        public static final String COLUMN_NAME_FUNCTION = "function";
        public static final String COLUMN_NAME_DOSE = "dose";
    }

    public static class AbbreviationInfo implements BaseColumns {
        public static final String TABLE_NAME = "abbreviation";
        public static final String COLUMN_NAME_BRAND = "sig";
        public static final String COLUMN_NAME_TRANSLATION = "translation";
    }
}
