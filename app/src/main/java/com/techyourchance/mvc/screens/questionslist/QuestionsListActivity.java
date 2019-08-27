package com.techyourchance.mvc.screens.questionslist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;

import com.techyourchance.mvc.R;
import com.techyourchance.mvc.screens.common.controllers.BackPressedListener;
import com.techyourchance.mvc.screens.common.controllers.BaseActivity;

public class QuestionsListActivity extends BaseActivity {

    public static void startClearTop(Context context) {
        Intent intent = new Intent(context, QuestionsListActivity.class);
        // call previous activity (in this case, question details calls EXISTING question list screen
        intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

    private BackPressedListener backPressedListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_content_frame);
        QuestionsListFragment fragment;
        if(savedInstanceState == null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            fragment = new QuestionsListFragment();
            ft.add(R.id.frame_content, fragment).commit();
        } else {
            fragment = (QuestionsListFragment) getSupportFragmentManager().findFragmentById(R.id.frame_content);
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
