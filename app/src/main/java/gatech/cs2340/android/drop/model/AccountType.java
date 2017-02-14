package gatech.cs2340.android.drop.model;

/**
 * Created by jaydensun on 2/13/17.
 */

public enum AccountType {
    USER("User"),
    WORKER("Worker"),
    MANAGER("Manager"),
    ADMIN("Admin");

    private final String acctType;

    AccountType(String _type) {
        acctType = _type;
    }

    public String getAcctType() {
        return acctType;
    }

    public String  toString() {
        return acctType;
    }
}
