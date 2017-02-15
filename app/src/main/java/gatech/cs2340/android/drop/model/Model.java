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
    private List<User> _userList;

    /**
     * make a new model
     */
    public Model() {
        _userList = new ArrayList<>();

        //comment this out after full app developed
        loadDummyData();
    }

    /**
     * populate the model with some dummy data.  The full app would not require this.
     * comment out when adding new courses functionality is present.
     */
    private void loadDummyData() {
        _userList.add(new Admin("Jayden Sun", "123@gmail.com", "1234567"));
    }

    /**
     * add a user to the list
     *
     * @param user the student to add
     * @return true if user added, false if not added
     */
    public boolean addUser(User user) {
        return _userList.add(user);
    }
}
