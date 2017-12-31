package com.event.horizon.sciencequiz.Exam;

import android.app.Dialog;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.event.horizon.sciencequiz.MainActivity;
import com.event.horizon.sciencequiz.R;

import java.util.ArrayList;

/**
 * Created by Horizon on 12/27/2017.
 */

public class ExamFragment extends Fragment {
  ViewGroup viewGroup;

    private int totalPages;
    private int page;
    private String TAG=ExamFragment.class.getName();
    private Button option1;
    private Button option2;
    private Button option3;
    private Button option4;
    private String answer;
    private boolean isPageChanged;

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
        isPageChanged=false;
        page= bundle.getInt("page");
        totalPages=bundle.getInt("totalPage");
        TextView question = view.findViewById(R.id.textView3);
        question.setText(bundle.getString("question"));

         option1=view.findViewById(R.id.button);
        option1.setText(bundle.getString("option1"));

         option2=view.findViewById(R.id.button2);
        option2.setText(bundle.getString("option2"));

         option3=view.findViewById(R.id.button3);
        option3.setText(bundle.getString("option3"));

        option4=view.findViewById(R.id.button4);
        option4.setText(bundle.getString("option4"));

        answer=bundle.getString("answer");

        ImageView displayImage=view.findViewById(R.id.imageView2);
        Glide.with(this)
                .load(bundle.getString("search"))
                .placeholder(R.drawable.placeholder)
                .into(displayImage);

        //question.setText(bundle.getInt("page"));
        Log.d(TAG,"question "+bundle.getString("question"));
        Log.d(TAG,"question now question at position "+page);

        setOnClickListner();
        return view;
    }

    private void setOnClickListner() {
        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( answer.toString().equalsIgnoreCase(option1.getText().toString())){
                    createDialogButton(true);
                }
                else
                    createDialogButton(false);

            }
        });

        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(answer.toString().equalsIgnoreCase(option1.getText().toString())){
                    createDialogButton(true);
                }
                else
                    createDialogButton(false);
            }
        });

        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(answer.toString().equalsIgnoreCase(option3.getText().toString())){
                    createDialogButton(true);
                }
                else
                    createDialogButton(false);
            }
        });

        option4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(answer.toString().equalsIgnoreCase(option4.getText().toString())){
                    createDialogButton(true);
                }
                else
                    createDialogButton(false);
            }
        });
    }

    private void createDialogButton(boolean isCorrect){

        final Dialog messageDialog=new Dialog(getContext());
        messageDialog.setCancelable(false);

        messageDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        messageDialog.setContentView(R.layout.dalog);
        TextView messageText=messageDialog.findViewById(R.id.text_dialog);
        Button nextButton=messageDialog.findViewById(R.id.btn_dialog);
        ImageView dialogImage=messageDialog.findViewById(R.id.dialog_image);
        if(isCorrect){
            dialogImage.setBackgroundResource(R.drawable.correct);
            ExamActivity.score+=1;
            Log.e(TAG,"fuck what is updated score "+ExamActivity.score);

        }
        else {
            dialogImage.setBackgroundResource(R.drawable.incorrect);
        }
        messageText.setText(answer);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(page<totalPages&&!isPageChanged)
                {

                    ((ExamActivity)getActivity()).getPager().setCurrentItem(((ExamActivity)getActivity()).getPager().getCurrentItem()+1);
                    Log.e(TAG,"get current position "+((ExamActivity)getActivity()).getPager().getCurrentItem());
                    Log.e(TAG,"total number of page "+totalPages);
                    Log.e(TAG,"current page "+page);

                    // ExamActivity.pager.setCurrentItem(1);
                   // isPageChanged=true;
                }
                else {
                    ((ExamActivity)getActivity()).updatedaScore();
                    Intent i =new Intent(getContext(), MainActivity.class);
                    startActivity(i);
                    Log.e(TAG,"question now question at position "+page);
                }
                messageDialog.dismiss();
            }
        });

        messageDialog.show();
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
