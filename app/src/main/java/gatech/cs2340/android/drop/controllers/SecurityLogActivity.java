package gatech.cs2340.android.drop.controllers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import gatech.cs2340.android.drop.R;
import gatech.cs2340.android.drop.model.SLog;

public class SecurityLogActivity extends AppCompatActivity {

    private static final String TAG = "SecurityLogActivity";

    //private FloatingActionButton fab;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    //private DatabaseReference databaseReference;
    private FirebaseRecyclerAdapter<SLog, LogViewHolder> firebaseRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_log);

        //recycler view
        recyclerView = (RecyclerView)findViewById(R.id.log_recycler_view);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(false);

        //Database Initialization
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<SLog, LogViewHolder>(
                SLog.class,
                R.layout.log_item,
                LogViewHolder.class,
                databaseReference.child("logs")) {
            @Override
            protected void populateViewHolder(LogViewHolder viewHolder, SLog model, int position) {
                viewHolder.slog.setText(model._message);

/*                viewHolder.itemView.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(SourceReportActivity.this, "Clicked", Toast.LENGTH_LONG).show();
                    }
                });*/
            }
        };

        firebaseRecyclerAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver(){
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount){
                super.onItemRangeInserted(positionStart, itemCount);
                int roomCount = firebaseRecyclerAdapter.getItemCount();
                int lastVisiblePosition = linearLayoutManager.findLastCompletelyVisibleItemPosition();
                if (lastVisiblePosition == -1 || (positionStart >= (roomCount -1) && lastVisiblePosition == (positionStart -1))){
                    recyclerView.scrollToPosition(positionStart);
                }
            }
        });
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    private static class LogViewHolder extends RecyclerView.ViewHolder {
        private final TextView slog;

        public LogViewHolder(View v) {
            super(v);
            slog = (TextView) itemView.findViewById(R.id.log);
        }
    }
}
