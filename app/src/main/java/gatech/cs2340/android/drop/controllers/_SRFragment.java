package gatech.cs2340.android.drop.controllers;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import gatech.cs2340.android.drop.R;
import gatech.cs2340.android.drop.model.Type;
import gatech.cs2340.android.drop.model.Water;
import gatech.cs2340.android.drop.model._sRReport;

/**
 * {@link Fragment} that displays a list of phrases.
 */
public class _SRFragment extends Fragment {

    public _SRFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.report_list, container, false);

        // Create a list of words
        final ArrayList<_sRReport> words = new ArrayList<>();
        words.add(new _sRReport("report1", Type.LAKE, Water.WASTE));
        words.add(new _sRReport("report1", Type.LAKE, Water.WASTE));
        words.add(new _sRReport("report1", Type.LAKE, Water.WASTE));
        words.add(new _sRReport("report1", Type.LAKE, Water.WASTE));
        words.add(new _sRReport("report1", Type.LAKE, Water.WASTE));
        words.add(new _sRReport("report1", Type.LAKE, Water.WASTE));
        words.add(new _sRReport("report1", Type.LAKE, Water.WASTE));
        words.add(new _sRReport("report1", Type.LAKE, Water.WASTE));
        words.add(new _sRReport("report1", Type.LAKE, Water.WASTE));
        words.add(new _sRReport("report1", Type.LAKE, Water.WASTE));
        words.add(new _sRReport("report1", Type.LAKE, Water.WASTE));


        // Create an {@link _SRAdapter}, whose data source is a list of {@link _sRReport}s. The
        // adapter knows how to create list items for each item in the list.
        _SRAdapter adapter = new _SRAdapter(getActivity(), words);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // report_list.xmlml layout file.
        ListView listView = (ListView) rootView.findViewById(R.id.list);

        // Make the {@link ListView} use the {@link _SRAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link _sRReport} in the list.
        listView.setAdapter(adapter);

        // Set a click listener to play the audio when the list item is clicked on
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                // Get the {@link _sRReport} object at the given position the user clicked on
                _sRReport word = words.get(position);

            }
        });

        return rootView;
    }
}