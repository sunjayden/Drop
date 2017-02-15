package gatech.cs2340.android.drop.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Arrays;
import java.util.List;

/**
 * Created by BobZhai on 14/02/2017.
 */

public class User {

    public static List<AccountType> legalAcctType = Arrays.asList(AccountType.values());
    private static int NextID = 0;

    private String _name;
    private String _email;
    private String _password;
    private int _id;

    public User(String name, String email, String password) {
        _name = name;
        _email = email;
        _password = password;
        _id = User.NextID++;
    }

    /* *********************************
    * These methods are required by the parcelable interface
    *
    */
//    private User(Parcel in) {
//        _name = in.readString();
//        _account = in.readString();
//        _password = in.readString();
//        _id = in.readInt();
//    }
//
//    public int describeContents() {
//        return 0;
//    }
//
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeString(_name);
//        dest.writeString(_account);
//        dest.writeString(_password);
//        dest.writeInt(_id);
//    }
//
//    public static final Parcelable.Creator<User> CREATOR
//            = new Parcelable.Creator<User>() {
//        public User createFromParcel(Parcel in) {
//            return new User(in);
//        }
//
//        public User[] newArray(int size) {
//            return new User[size];
//        }
//    };


    public static int getNextID() {
        return NextID;
    }

    public String getName() {
        return _name;
    }

    public void setName(String _name) {
        this._name = _name;
    }

    public String getEmail() {
        return _email;
    }

    public void setEmail(String email) {
        _email = email;
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

    public String toString(){
        return _name + " " + _email;
    }


}
