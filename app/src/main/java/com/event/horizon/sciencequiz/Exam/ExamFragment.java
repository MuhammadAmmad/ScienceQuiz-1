package com.event.horizon.sciencequiz.Exam;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.event.horizon.sciencequiz.R;

import java.util.ArrayList;

/**
 * Created by Horizon on 12/27/2017.
 */

public class ExamFragment extends Fragment {
  ViewGroup viewGroup;
    ArrayList<String> questions;
    ArrayList<String> options4;
    String answer;
    int page;
    private String TAG=ExamFragment.class.getName();

    public ExamFragment() {
        super();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.exam,container,false);
        View view=inflater.inflate(R.layout.exam,container,false);
        Bundle bundle=getArguments();
        //int position=bundle.getInt("position");
        int page = bundle.getInt("page");
        TextView question = view.findViewById(R.id.textView3);
        question.setText(bundle.getString("question"));

        Button option1=view.findViewById(R.id.button);
        option1.setText(bundle.getString("option1"));

        Button option2=view.findViewById(R.id.button2);
        option2.setText(bundle.getString("option2"));

        Button option3=view.findViewById(R.id.button3);
        option3.setText(bundle.getString("option3"));

        Button option4=view.findViewById(R.id.button4);
        option4.setText(bundle.getString("option4"));

        answer=bundle.getString("answer");

        ImageView displayImage=view.findViewById(R.id.imageView2);
        Glide.with(this)
                .load(bundle.getString("search"))
                .into(displayImage);
        //question.setText(bundle.getInt("page"));
        Log.d(TAG,"question "+bundle.getString("question"));
        Log.d(TAG,"question now question at position "+page);


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG,"ON START");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG,"ON Pause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG,"ON  STOP");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"ON DESTROY");
    }
}
