package gatech.cs2340.android.drop.model;

/**
 * Created by Jayden Sun on 3/3/17.
 */

public class Account {
    private String _name;
    private String _email;
    private String _password;
    private UserType _userType;

    public Account(String name, String email, String password, UserType type) {
        _name = name;
        _email = email;
        _password = password;
        _userType = type;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        this._name = name;
    }

    public String getEmail() {
        return _email;
    }

    public void setEmail(String email) {
        this._email = email;
    }

    public String getPassword() {
        return _password;
    }

    public void setPassword(String password) {
        this._password = password;
    }

    public UserType getUserType() {
        return _userType;
    }

    public void setUserType(UserType _userType) {
        this._userType = _userType;
    }
}
