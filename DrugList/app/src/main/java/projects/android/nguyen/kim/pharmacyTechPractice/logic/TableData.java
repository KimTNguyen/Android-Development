package projects.android.nguyen.kim.pharmacyTechPractice.logic;

import android.provider.BaseColumns;

/**
 * TableData defines how the database is organized.
 *
 * @author Kim Nguyen
 * @version 3/8/2017.
 *
 * Modified by Kim Nguyen on 04/09/2017
 */

final class TableData {

    private TableData() {
    }

    static class DrugInfo implements BaseColumns {
        static final String TABLE_NAME = "drug";
        static final String COLUMN_NAME_BRAND = "brand";
        static final String COLUMN_NAME_GENERIC = "generic";
        static final String COLUMN_NAME_SCHEDULED = "scheduled";
        static final String COLUMN_NAME_DOSE_FORMS = "doseForm";
        static final String COLUMN_NAME_FUNCTION = "function";
        static final String COLUMN_NAME_SIDE_EFFECTS = "sideEffects";
        static final String COLUMN_NAME_COMMENTS = "comments";
    }

    static class AbbreviationInfo implements BaseColumns {
        static final String TABLE_NAME = "abbreviation";
        static final String COLUMN_SIG_CODE = "sig";
        static final String COLUMN_NAME_TRANSLATION = "translation";
    }
}
