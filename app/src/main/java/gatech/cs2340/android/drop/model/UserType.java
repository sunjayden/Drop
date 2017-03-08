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

    /**
     * Account user type
     * @param _type account type of user
     */
    UserType(String _type) {
        acctType = _type;
    }

    /**
     * Get account type
     * @return account type
     */
    public String getAcctType() {
        return acctType;
    }

    @Override
    public String  toString() {
        return acctType;
    }
}
