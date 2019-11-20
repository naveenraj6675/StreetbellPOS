package com.example.streetbellpos.adapters;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.streetbellpos.R;


public class SpinnerAdapter extends ArrayAdapter<String> {

    String[] spinnerArray;
    LayoutInflater inflater;
    String mainText;
    Context mContext;

    public SpinnerAdapter(Context context, int resource, String[] spinnerArray, String mainText) {
        super(context, resource, spinnerArray);
        this.spinnerArray = spinnerArray;
        inflater = ((Activity) context).getLayoutInflater();
        this.mainText = mainText;
        this.mContext = context;
    }

    @Override
    public View getDropDownView(int position, View cnvtView, ViewGroup parent) {
        return getCustomDropDownView(position, cnvtView, parent);
    }

    @Override
    public View getView(int pos, View cnvtView, ViewGroup parent) {
        return getCustomView(pos, cnvtView, parent);
    }

    private View getCustomView(int position, View cnvtView, ViewGroup parent) {
        if (cnvtView == null) {
            cnvtView = inflater.inflate(R.layout.spinner_main, parent, false);
        }

        TextView mainTextView = (TextView) cnvtView.findViewById(R.id.main_text);
        mainTextView.setText(mainText);

        TextView spinnerTextView = (TextView) cnvtView.findViewById(R.id.spinner_text);
        spinnerTextView.setText(spinnerArray[position]);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

        } else {
            spinnerTextView.setTextSize(16);
            spinnerTextView.setTextColor(ContextCompat.getColor(mContext, R.color.mid_black));
        }

        return cnvtView;
    }

    private View getCustomDropDownView(int position, View cnvtView, ViewGroup parent) {
        if (cnvtView == null) {
            cnvtView = inflater.inflate(R.layout.spinner_dropdown, parent, false);
        }

        TextView spinnerTextView = (TextView) cnvtView.findViewById(R.id.spinner_textView);
        spinnerTextView.setText(spinnerArray[position]);
        return cnvtView;
    }
}
