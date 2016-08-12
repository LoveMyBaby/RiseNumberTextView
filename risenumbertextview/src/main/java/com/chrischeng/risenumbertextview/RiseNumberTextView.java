package com.chrischeng.risenumbertextview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;

import java.text.DecimalFormat;

public class RiseNumberTextView extends TextView {

    private int mDecimalPlace;
    private int mMaxDecimalPlace;
    private int mMinDecimalPlace;
    private DecimalFormat mDecimalFormat;
    private ValueAnimator mAnimator;

    public RiseNumberTextView(Context context) {
        this(context, null);
    }

    public RiseNumberTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public void run() {
        if (!mAnimator.isRunning())
            mAnimator.start();
    }

    public void setNum(float startNum, float endNum) {
        mAnimator.setFloatValues(startNum, endNum);
    }

    public void setDecimalPlace(int decimalPlace) {
        if (mDecimalPlace >= mMinDecimalPlace && mDecimalPlace <= mMaxDecimalPlace) {
            mDecimalPlace = decimalPlace;
            setAnimListener();
        }
    }

    public void setDuration(int duraion) {
        mAnimator.setDuration(duraion);
    }

    private void init(AttributeSet attrs) {
        setAttrs(attrs);
        setFormat();
        setAnimListener();
    }

    private void setAttrs(AttributeSet attrs) {
        Resources res = getContext().getResources();

        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.RiseNumberTextView);
        mMinDecimalPlace = res.getInteger(R.integer.rntv_default_decimal_place_min);
        mMaxDecimalPlace = res.getInteger(R.integer.rntv_default_decimal_place_max);
        mDecimalPlace = a.getInteger(R.styleable.RiseNumberTextView_rntv_decimal_place,
                res.getInteger(R.integer.rntv_default_decimal_place));
        if (mDecimalPlace < mMinDecimalPlace || mDecimalPlace > mMaxDecimalPlace)
            mDecimalPlace = res.getInteger(R.integer.rntv_default_decimal_place);

        mAnimator = ValueAnimator.ofFloat();
        mAnimator.setFloatValues(a.getFloat(R.styleable.RiseNumberTextView_rntv_num_start,
                res.getInteger(R.integer.rntv_default_num_start)),
                a.getFloat(R.styleable.RiseNumberTextView_rntv_num_end,
                        res.getInteger(R.integer.rntv_default_num_end)));
        mAnimator.setDuration(res.getInteger(R.integer.rntv_default_anim_duration));

        a.recycle();
    }

    private void setFormat() {
        StringBuilder pattern = new StringBuilder("##0.");
        for (int i = 0; i < mDecimalPlace; i++)
            pattern.append("0");

        mDecimalFormat = new DecimalFormat(pattern.toString());
    }

    private void setAnimListener() {
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                setText(mDecimalFormat.format(Float.parseFloat(animation.getAnimatedValue().toString())));
            }
        });
    }
}
