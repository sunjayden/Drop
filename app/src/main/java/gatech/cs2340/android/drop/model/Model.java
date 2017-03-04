package gatech.cs2340.android.drop.model;

/**
 * Created by Jayden Sun on 3/3/17.
 */

public class Model {

    private AccountDAO accountDAO = new AccountDAO();

    /** Singleton instance */
    private static final Model _instance = new Model();
    public static Model getInstance() { return _instance; }

    /**
     * make a new model
     */
    public Model() {

    }

    public void createAccount(String name, String email, String password, UserType userType) {
        accountDAO.addAccount(new Account(name, email, password, userType));
    }
}
