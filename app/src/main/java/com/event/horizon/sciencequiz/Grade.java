package com.event.horizon.sciencequiz;

import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Horizon on 12/26/2017.
 */

public class Grade

{

     int score;
   int backgroundImage;

    public Grade(int score,int backgroundImage) {
        this.score=score;
        this.backgroundImage=backgroundImage;



    }

    public int getScore() {
        return score;
    }

    public int getBackgroundImage() {
        return backgroundImage;
    }

}
