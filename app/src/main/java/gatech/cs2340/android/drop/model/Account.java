package gatech.cs2340.android.drop.model;

/**
 * Created by Jayden Sun on 3/3/17.
 */

public class Account {
    private String _name;
    private String _email;
    private String _password;
    private UserType _userType;

    /**
     * Account to store user's information
     * @param name user's name
     * @param email user's email
     * @param password user's password
     * @param type user type
     */
    public Account(String name, String email, String password, UserType type) {
        _name = name;
        _email = email;
        _password = password;
        _userType = type;
    }

    /**
     * Get current user's name
     * @return user name
     */
    public String getName() {
        return _name;
    }

    /**
     * Set the current user name
     * @param name user's name
     */
    public void setName(String name) {
        this._name = name;
    }

    /**
     * Get the user's email
     * @return user email
     */
    public String getEmail() {
        return _email;
    }

    /**
     * Set user email
     * @param email user's email
     */
    public void setEmail(String email) {
        this._email = email;
    }

    /**
     * Get user password
     * @return user's password
     */
    public String getPassword() {
        return _password;
    }

    /**
     * Set user password
     * @param password user's password
     */
    public void setPassword(String password) {
        this._password = password;
    }

    /**
     * Get user type of the user
     * @return user type of the user
     */
    public UserType getUserType() {
        return _userType;
    }

    /**
     * Set user type of the user
     * @param _userType user type of the user
     */
    public void setUserType(UserType _userType) {
        this._userType = _userType;
    }
}
