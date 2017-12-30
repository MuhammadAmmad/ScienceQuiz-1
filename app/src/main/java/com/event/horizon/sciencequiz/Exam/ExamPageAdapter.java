package com.event.horizon.sciencequiz.Exam;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Horizon on 12/27/2017.
 */

public class ExamPageAdapter extends FragmentStatePagerAdapter {
    private static final String TAG=ExamPageAdapter.class.getSimpleName();
    Bundle bundle;
    int count;
    int currentPosition;
    List<ExamFormat> examFormatList;
    ExamFragment examFragment;




    public ExamPageAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        //this.bundle=bundle;
        bundle=new Bundle();
       // examFragment=new ExamFragment();


    }



    @Override
    public Fragment getItem(int position) {
        if(examFormatList!=null){
           setPageData();
        }


        examFragment=new ExamFragment();
        bundle.putInt("page",position+1);

        currentPosition=position;
        bundle.putInt("position",position);
        examFragment.setArguments(bundle);

        return examFragment;
    }

    @Override
    public int getCount() {
        return count;
    }


    public void setPage(List<ExamFormat> exams){
        if(exams!=null) {

           count = exams.size();
            notifyDataSetChanged();
            examFormatList=exams;
          // bundle.putString("question", exams.get(currentPosition).getQuestion());
           // Log.d(TAG,"current position "+currentPosition);


        }
    }

    private void setPageData(){
       // Log.d(TAG,"question "+examFormatList.get(currentPosition).getQuestion());
       bundle.putString("question", examFormatList.get(currentPosition).getQuestion());
        bundle.putString("option1", examFormatList.get(currentPosition).getOption1());
        bundle.putString("option2", examFormatList.get(currentPosition).getOption2());
        bundle.putString("option3", examFormatList.get(currentPosition).getOption3());
        bundle.putString("option4", examFormatList.get(currentPosition).getOption4());
        bundle.putString("answer", examFormatList.get(currentPosition).getAnswer());
        bundle.putString("search", examFormatList.get(currentPosition).getSearch());
        //examFragment.setArguments(bundle);
    }


}
