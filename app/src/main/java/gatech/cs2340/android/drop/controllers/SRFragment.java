package gatech.cs2340.android.drop.controllers;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import gatech.cs2340.android.drop.R;
import gatech.cs2340.android.drop.model.Model;
import gatech.cs2340.android.drop.model.SourceReport;

/**
 * Created by yilih on 3/7/2017.
 */

public class SRFragment extends Fragment {
    /**
     * The fragment arguments representing the  ID's that this fragment
     * represents.  Used to pass keys into other activities through Bundle/Intent
     */
    public static final String ARG_SR_ID = "course_id";
    public static final String ARG_USER_ID = "student_id";

    private SourceReport sreport;

    private SimpleSRRecyclerViewAdapter adapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public SRFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Check if we got a valid course passed to us
        if (getArguments().containsKey(ARG_SR_ID)) {
            // Get the id from the intent arguments (bundle) and
            //ask the model to give us the course object
            Model model = Model.getInstance();
            // mCourse = model.getCourseById(getArguments().getInt(ARG_COURSE_ID));
            sreport = model.getCurrentSourceReport();

            Activity activity = this.getActivity();


        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_source_report, container, false);

        //Step 1.  Setup the recycler view by getting it from our layout in the main window
        View recyclerView = rootView.findViewById(R.id.SR_detail_container);
        assert recyclerView != null;
        //Step 2.  Hook up the adapter to the view
        setupRecyclerView((RecyclerView) recyclerView);

        return rootView;
    }


    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    private void setupRecyclerView( RecyclerView recyclerView) {
        adapter = new SimpleSRRecyclerViewAdapter(Model.getInstance().get_sourceReports());
        recyclerView.setAdapter(adapter);
    }

    public class SimpleSRRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleSRRecyclerViewAdapter.ViewHolder> {

        private final List<SourceReport> SValues;

        public SimpleSRRecyclerViewAdapter(List<SourceReport> items) {
            SValues = items;
        }


        @Override
        public SimpleSRRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.activity_source_report, parent, false);
            return new SimpleSRRecyclerViewAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final SimpleSRRecyclerViewAdapter.ViewHolder holder, int position) {
            holder.sReport = SValues.get(position);

            holder.mIdView.setText("" + SValues.get(position).getId());
            holder.mContentView.setText(SValues.get(position).toString());

            /*
             * set up a listener to handle if the user clicks on this list item, what should happen?
             */
            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //on a phone, we need to change windows to the detail view
                    Context context = v.getContext();
                    //create our new intent with the new screen (activity)
                    Intent intent = new Intent(context, EditSourceReport.class);
                        /*
                            pass along the selected student we can retrieve the correct data in
                            the next window
                         */
                    intent.putExtra(SRFragment.ARG_SR_ID, holder.sReport);

                    //now just display the new window
                    context.startActivity(intent);

                }
            });

        }

        @Override
        public int getItemCount() {
            return SValues.size();
        }


        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView mIdView;
            public final TextView mContentView;
            public SourceReport sReport;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mIdView = (TextView) view.findViewById(R.id.edit_sr_rn);
                Log.d("Holder", mIdView.toString());
                mContentView = (TextView) view.findViewById(R.id.edit_sr_location);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mContentView.getText() + "'";
            }
        }


    }





}
