package projects.android.nguyen.kim.pharmacyTechPractice;

import android.provider.BaseColumns;

/**
 * TableData defines how the database is organized.
 *
 * @author Kim Nguyen
 * @version 3/8/2017.
 */

final class TableData {

    private TableData() {}

    static class DrugInfo implements BaseColumns {
        static final String TABLE_NAME = "drug";
        static final String COLUMN_NAME_BRAND = "brand";
        static final String COLUMN_NAME_GENERIC = "generic";
        static final String COLUMN_NAME_FUNCTION = "function";
        static final String COLUMN_NAME_DOSE = "dose";
    }

    static class AbbreviationInfo implements BaseColumns {
        static final String TABLE_NAME = "abbreviation";
        static final String COLUMN_SIG_CODE = "sig";
        static final String COLUMN_NAME_TRANSLATION = "translation";
    }
}
