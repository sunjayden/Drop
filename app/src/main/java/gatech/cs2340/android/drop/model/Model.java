package gatech.cs2340.android.drop.model;

import java.util.ArrayList;
import java.util.List;

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
     * make a new model
     */
    public Model() {

    }

    public void registerAccount(String name, String email, String password, UserType userType) {
        accountDAO.registerAccount(new Account(name, email, password, userType));
    }

    public void getAccount(String email) {
         accountDAO.getAccount(email);
    }

    public ArrayList<Account> getAccount() {
        return accountDAO.getAccount();
    }

}
