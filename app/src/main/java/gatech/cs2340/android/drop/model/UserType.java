package gatech.cs2340.android.drop.model;

/**
 * Created by Jayden Sun on 2/13/17.
 */

public enum UserType {
    USER("User"),
    WORKER("Worker"),
    MANAGER("Manager"),
    ADMIN("Admin");

    private final String acctType;

    UserType(String _type) {
        acctType = _type;
    }

    public String getAcctType() {
        return acctType;
    }

    public String  toString() {
        return acctType;
    }
}
