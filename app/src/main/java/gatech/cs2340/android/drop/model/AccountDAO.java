package gatech.cs2340.android.drop.model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

/**
 * Created by Jayden Sun on 3/3/17.
 */

public class AccountDAO {
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

    public void registerAccount(Account account) {
        String accountId = account.getEmail().replace("@", " ");
        accountId = accountId.replace(".", " ");
        mDatabase.child("Account").child(accountId).setValue(account);
    }

    public boolean checkAccount(String email, String password) {
        return false;
    }

    public List<Account> getAccount() {
        return null;
    }

    public void updateAccount(Account account) {

    }
}
