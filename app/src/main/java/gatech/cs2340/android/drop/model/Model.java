package gatech.cs2340.android.drop.model;

import gatech.cs2340.android.drop.controllers.AccountDAO;

/**
 * Created by Jayden Sun on 3/3/17.
 */

public class Model {

    private AccountDAO accountDAO = new AccountDAO();

    /** Singleton instance */
    private static final Model _instance = new Model();
    public static Model getInstance() { return _instance; }

    /**
     * Model of this class
     */
    public Model() {

    }

    /**
     * Register the account
     * @param name user's name
     * @param email user's email
     * @param password user's password
     * @param userType user's account type
     */
    public void registerAccount(String name, String email, String password, UserType userType) {
        accountDAO.registerAccount(new Account(name, email, password, userType));
    }
}
