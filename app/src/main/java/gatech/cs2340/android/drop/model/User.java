package gatech.cs2340.android.drop.model;

/**
 * Created by BobZhai on 14/02/2017.
 */

public abstract class User {

    private String _name;
    private String _account;
    private String _password;
    private int _id;
    private static int NextID = 0;


    protected User(String name, String account, String password) {
        _name = name;
        _account = account;
        _password = password;
        NextID++;
        _id = NextID;
    }



    public static int getNextID() {
        return NextID;
    }


    public String getName() {
        return _name;
    }

    public void setName(String _name) {
        this._name = _name;
    }

    public String getAccount() {
        return _account;
    }

    public void setAccount(String account) {
        _account = account;
    }

    public String getPassword() {
        return _password;
    }

    public void setPassword(String password) {
        _password = password;
    }

    public int getId() {
        return _id;
    }

    public void setId(int id) {
        _id = id;
    }





}
