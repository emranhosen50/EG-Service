package Main;

import java.util.prefs.Preferences;

public class PreferenceKey {
    private static final String FRUIT = "fruit";

    public void savePreference(String favoriteFruit) {
        Preferences prefs = Preferences.userNodeForPackage(PreferenceKey.class);
        prefs.put(FRUIT, favoriteFruit);
    }

    public String readPreference() {
        Preferences prefs = Preferences.userNodeForPackage(PreferenceKey.class);

        return prefs.get(FRUIT, "NULL");
    }
}
