package gatech.cs2340.android.drop;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by jaydensun on 4/23/17.
 */

public class MyFirebaseInstanceServices extends FirebaseInstanceIdService {
    private static final String REG_TOKEN = "REG_TOKEN";

    @Override
    public void onTokenRefresh() {
        String recent_token = FirebaseInstanceId.getInstance().getToken();
        Log.d(REG_TOKEN, recent_token);
    }
}
