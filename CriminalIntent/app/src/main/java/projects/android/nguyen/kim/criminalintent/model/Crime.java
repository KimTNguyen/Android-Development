package projects.android.nguyen.kim.criminalintent.model;

import java.util.UUID;

/**
 * Created by Kimmy on 3/22/2017.
 */

public class Crime {
    private UUID mId;
    private String mTitle;

    public Crime() {
        // Generates unique identifier
        mId = UUID.randomUUID();
    }

    public UUID getId() {
        return mId;
    }

    public void setId(UUID mId) {
        this.mId = mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }
}
