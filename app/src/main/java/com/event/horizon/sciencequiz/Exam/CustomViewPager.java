package com.event.horizon.sciencequiz.Exam;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Horizon on 12/31/2017.
 */

public class CustomViewPager extends ViewPager {
    private boolean isPagingEnabled=true;
    public CustomViewPager(Context context) {
        super(context);
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return this.isPagingEnabled&&super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return this.isPagingEnabled&&super.onTouchEvent(ev);
    }

    public void setPagingEnabled(boolean enabled) {
        this.isPagingEnabled= enabled;
    }
}
