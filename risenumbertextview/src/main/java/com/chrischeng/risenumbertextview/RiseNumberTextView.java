package com.chrischeng.risenumbertextview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;

import java.text.DecimalFormat;

public class RiseNumberTextView extends TextView {

    private int mType;
    private float mStartNum;
    private float mEndNum;
    private int mDecimal;
    private int mDuration;
    private DecimalFormat mDecimalFormat;
    private boolean mRunning;

    public RiseNumberTextView(Context context) {
        this(context, null);
    }

    public RiseNumberTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        initAttrs(context, attrs);
        initFormat();
    }

    public void start() {
        if (!mRunning) {
            mRunning = true;

            switch (mType) {
                case 0:
                    runInt();
                    break;
                case 1:
                    runFloat();
                    break;
            }
        }
    }

    public void setType(NumberType type) {
        mType = type.ordinal();
    }

    public void setNum(float startNum, float endNum) {
        mStartNum = startNum;
        mEndNum = endNum;
    }

    public void setNum(int startNum, int endNum) {
        mStartNum = startNum;
        mEndNum = endNum;
    }

    public void setStartNum(float startNum) {
        mStartNum = startNum;
    }

    public void setStartNum(int startNum) {
        mStartNum = startNum;
    }

    public void setEndNum(float endNum) {
        mEndNum = endNum;
    }

    public void setEndNum(int endNum) {
        mEndNum = endNum;
    }

    public void setDecimal(int decimal) {
        if (mDecimal > 1 && mDecimal < 9)
            mDecimal = decimal;
    }

    public void setDuration(int duraion) {
        mDuration = duraion;
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        Resources res = context.getResources();

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RiseNumberTextView);
        mDecimal = a.getInteger(R.styleable.RiseNumberTextView_decimal, res.getInteger(R.integer.default_decimal));
        if (mDecimal < 1 || mDecimal > 9)
            mDecimal = res.getInteger(R.integer.default_decimal);
        mType = a.getInteger(R.styleable.RiseNumberTextView_type, 0);
        mStartNum = a.getFloat(R.styleable.RiseNumberTextView_startNum, 0);
        mEndNum = a.getFloat(R.styleable.RiseNumberTextView_endNum, 0);
        mDuration = a.getInteger(R.styleable.RiseNumberTextView_duration, res.getInteger(R.integer.default_duration));
        a.recycle();
    }

    private void initFormat() {
        StringBuilder pattern = new StringBuilder("##0.");
        for (int i = 0; i < mDecimal; i++)
            pattern.append("0");

        mDecimalFormat = new DecimalFormat(pattern.toString());
    }

    private void runInt() {
        ValueAnimator animator = ValueAnimator.ofInt((int) mStartNum, (int) mEndNum);
        animator.setDuration(mDuration);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                setText(animation.getAnimatedValue().toString());
                if (animation.getAnimatedFraction() >= 1)
                    mRunning = false;
            }
        });
        animator.start();
    }

    private void runFloat() {
        ValueAnimator animator = ValueAnimator.ofFloat(mStartNum, mEndNum);
        animator.setDuration(mDuration);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                setText(mDecimalFormat.format(Float.parseFloat(animation.getAnimatedValue().toString())));
                if (animation.getAnimatedFraction() >= 1)
                    mRunning = false;
            }
        });
        animator.start();
    }
}
