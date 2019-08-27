package com.techyourchance.mvc.screens.questiondetails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;

import com.techyourchance.mvc.R;
import com.techyourchance.mvc.screens.common.controllers.BackPressedListener;
import com.techyourchance.mvc.screens.common.controllers.BaseActivity;

import static com.techyourchance.mvc.screens.questiondetails.QuestionDetailsFragment.EXTRA_QUESTION_ID;

public class QuestionDetailsActivity extends BaseActivity  {

    private BackPressedListener backPressedListener;

    public static void start(Context context, String questionId) {
        Intent intent = new Intent(context, QuestionDetailsActivity.class);
        intent.putExtra(EXTRA_QUESTION_ID, questionId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_content_frame);
        QuestionDetailsFragment fragment;
        if(savedInstanceState == null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            fragment = QuestionDetailsFragment.newInstance(getIntent().getStringExtra(EXTRA_QUESTION_ID));
            ft.add(R.id.frame_content, fragment).commit();
        } else {
            fragment = (QuestionDetailsFragment) getSupportFragmentManager().findFragmentById(R.id.frame_content);
        }
        backPressedListener = fragment;
    }

    @Override
    public void onBackPressed() {
        if(!backPressedListener.onBackPressed()) {
            super.onBackPressed();
        }
    }
}
