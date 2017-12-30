package com.event.horizon.sciencequiz.Exam;

import android.app.LoaderManager;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v4.app.FragmentManager;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.event.horizon.sciencequiz.R;

import java.util.ArrayList;
import java.util.List;

public class ExamActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<ExamFormat>> {
    private static final String EXAM_URL = "https://young-fjord-88144.herokuapp.com/exams/JSON";
    ArrayList<String> questions, answeres, option1, option2, option3, option4, search;
    ExamPageAdapter examPageAdapter;
    int grade = 3;
    ViewPager pager;
    private static final int EXAM_LOADER_ID = 1;

    public static final String PIXABAY_AUTHORITY="pixabay.com";
    public static final String PIXABAY_BASE_KEY="7478283-f650c3f567f6b144653996ed8";

    @Override
    public void onBackPressed() {
        if (pager.getCurrentItem() == 0)
            super.onBackPressed();
        else {
            pager.setCurrentItem(pager.getCurrentItem() - 1);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
        pager = findViewById(R.id.view_pager);
        grade=getIntent().getExtras().getInt("grade");

        questions = new ArrayList<String>();
        option1 = new ArrayList<String>();
        option2 = new ArrayList<String>();
        option3 = new ArrayList<String>();
        option4 = new ArrayList<String>();
        answeres = new ArrayList<String>();




        examPageAdapter = new ExamPageAdapter(getSupportFragmentManager());



        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getLoaderManager();

            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
           loaderManager.initLoader(EXAM_LOADER_ID, null, this);
        }


    }



    @Override
    public Loader<List<ExamFormat>> onCreateLoader(int i, Bundle bundle) {

        return new ExamLoader(this,EXAM_URL,grade);

    }

    @Override
    public void onLoadFinished(Loader<List<ExamFormat>> loader, List<ExamFormat> examFormats) {

        pager.setAdapter(examPageAdapter);

       examPageAdapter.setPage(examFormats);
    }

    @Override
    public void onLoaderReset(Loader<List<ExamFormat>> loader) {

    }





}
