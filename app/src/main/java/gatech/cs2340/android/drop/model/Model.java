package gatech.cs2340.android.drop.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BobZhai on 14/02/2017.
 */

public class Model {

    /** Singleton instance */
    private static final Model _instance = new Model();
    public static Model getInstance() { return _instance; }

    /** holds the list of all user */
    private List<User> _user;

    /**
     * make a new model
     */
    public Model() {
        _user = new ArrayList<>();

        //comment this out after full app developed
        loadDummyData();
    }

    /**
     * populate the model with some dummy data.  The full app would not require this.
     * comment out when adding new courses functionality is present.
     */
    private void loadDummyData() {
        _user.add(new Admin("Jayden Sun", "123@gmail.com", "1234567"));
    }
}
