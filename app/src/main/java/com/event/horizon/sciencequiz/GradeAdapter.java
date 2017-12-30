package com.event.horizon.sciencequiz;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;

import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Horizon on 12/26/2017.
 */

public class GradeAdapter extends ArrayAdapter<Grade> {


    public GradeAdapter(@NonNull Context context, ArrayList<Grade> grades) {
        super(context,0, grades);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Grade grade=getItem(position);
        if(convertView==null)
             convertView= LayoutInflater.from(getContext()).inflate(R.layout.grade, parent, false);
        ConstraintLayout layout=(ConstraintLayout)convertView.findViewById(R.id.constraint);
        TextView textView=(TextView)convertView.findViewById(R.id.textView2);



        layout.setBackground(ContextCompat.getDrawable(getContext(),grade.getBackgroundImage()));
        textView.setText("Score "+grade.getScore());

        return convertView;
    }
}