package com.techyourchance.mvc.screens.questionslist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.techyourchance.mvc.screens.common.controllers.BaseActivity;

import retrofit2.http.HEAD;

public class QuestionsListActivity extends BaseActivity {

    private QuestionsListController controller;

    public static void startClearTop(Context context) {
        Intent intent = new Intent(context, QuestionsListActivity.class);
        // call previous activity (in this case, question details calls EXISTING question list screen
        intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        QuestionsListViewMvc viewMvc = getCompositionRoot().getViewMvcFactory().getQuestionsListViewMvc(null);
        controller = getCompositionRoot().getQuestionsListController();
        controller.bindView(viewMvc);
        setContentView(viewMvc.getRootView());
    }

    @Override
    protected void onStart() {
        super.onStart();
        controller.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        controller.onStop();
    }

    @Override
    public void onBackPressed() {
        if(!controller.onBackPressed()) {
            super.onBackPressed();
        }
    }
}
