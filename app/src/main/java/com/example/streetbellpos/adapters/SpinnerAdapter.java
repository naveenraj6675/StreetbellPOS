package com.example.streetbellpos.adapters;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.streetbellpos.R;


public class SpinnerAdapter extends ArrayAdapter<String> {

    private Context mContext;
    private String[] mObjects;
    private String mFirstElement;
    private boolean mIsFirstTime;
//    private boolean needsWhite;
//    private boolean needsGrey;

    //constructor
    public SpinnerAdapter(@NonNull Context context, int textViewResourceId, String[] objects, String defaultText) {
        super(context, textViewResourceId, objects);
        this.mContext = context;
        this.mObjects = objects;
        this.mIsFirstTime = true;
//        this.needsWhite = needsWhite;
//        this.needsGrey = needsGrey;
        setDefaultText(defaultText);

    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (mIsFirstTime) {
            mObjects[0] = mFirstElement;
            mIsFirstTime = false;

        }
        return getCustomView(position, convertView, parent);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        notifyDataSetChanged();
        return getCustomView(position, convertView, parent);
    }

    //To show default text on spinner
    public void setDefaultText(String defaultText) {
        this.mFirstElement = mObjects[0];
        mObjects[0] = defaultText;
    }


    //Customize the spinner view
    public View getCustomView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_spinner_row, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.textView = convertView.findViewById(R.id.spinner_text);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder = (ViewHolder) convertView.getTag();
        viewHolder.textView.setText(mObjects[position]);

        viewHolder.textView.setGravity(Gravity.CENTER);


//        if(white){
//            viewHolder.textView.setTextColor(Color.WHITE);
//        }else if(grey){
//            viewHolder.textView.setTextColor(ContextCompat.getColor(mContext,R.color.black));
//        }
//        else{
//            viewHolder.textView.setTextColor(Color.BLACK);
//        }

        return convertView;
    }

    private class ViewHolder {
        TextView textView;
    }

}
