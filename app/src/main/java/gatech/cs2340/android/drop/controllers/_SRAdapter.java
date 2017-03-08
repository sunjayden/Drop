package gatech.cs2340.android.drop.controllers;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import gatech.cs2340.android.drop.R;
import gatech.cs2340.android.drop.model._sRReport;

public class _SRAdapter extends ArrayAdapter<_sRReport> {
    public _SRAdapter(Activity context, ArrayList<_sRReport> SRReports) {
        super(context, 0, SRReports);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listView = convertView;
        if (listView == null) {
            listView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        _sRReport currentSRReport = getItem(position);

        TextView miwokTextView = (TextView)listView.findViewById(R.id.miwok_text_view);
        miwokTextView.setText(currentSRReport.getName());



        View textContainer = listView.findViewById(R.id.text_view);
        //int background = ContextCompat.getColor(getContext(), color);
        //textContainer.setBackgroundColor(background);

        return listView;
    }
}
