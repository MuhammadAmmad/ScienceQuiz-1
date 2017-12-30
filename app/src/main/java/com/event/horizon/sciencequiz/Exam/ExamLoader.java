package com.event.horizon.sciencequiz.Exam;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by Horizon on 12/29/2017.
 */

public class ExamLoader extends AsyncTaskLoader<List<ExamFormat>> {
    String mUrl;
    int grade;

    public ExamLoader(Context context, String mUrl, int grade) {

        super(context);
        this.mUrl=mUrl;
        this.grade=grade;
    }

    @Override
    public List<ExamFormat> loadInBackground() {
        if(mUrl==null){
            return null;
        }

        List<ExamFormat> examFormats=QueryUtils.fetchExamData(mUrl,grade);

        return examFormats;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }
}