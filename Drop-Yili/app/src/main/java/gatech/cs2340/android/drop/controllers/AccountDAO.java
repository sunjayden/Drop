package gatech.cs2340.android.drop.controllers;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import gatech.cs2340.android.drop.model.Account;

import static android.R.attr.value;
import static android.R.id.list;

/**
 * Created by Jayden Sun on 3/3/17.
 */

public class AccountDAO {
    private static final String TAG = "AccountDAO";

    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

    public void registerAccount(Account account) {
        String accountId = account.getEmail().replace("@", " ");
        accountId = accountId.replace(".", " ");
        mDatabase.child("Account").child(accountId).setValue(account);
    }

    public void getAccount(String email) {
        mDatabase = FirebaseDatabase.getInstance().getReference("Account");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Account account = dataSnapshot.getValue(Account.class);
                Log.d(TAG, "Value is: " + account.getName());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

    }

    public ArrayList<Account> getAccount() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Account");
        final ArrayList<Account> list = new ArrayList<Account>();
        ref.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                            list.add((Account) dsp.getValue());
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        //handle databaseError
                    }
                });
        return list;
    }

    public void updateAccount(Account account) {

    }
}