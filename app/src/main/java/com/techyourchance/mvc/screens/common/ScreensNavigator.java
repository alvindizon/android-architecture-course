package com.techyourchance.mvc.screens.common;

import android.content.Context;

import com.techyourchance.mvc.screens.questiondetails.QuestionDetailsActivity;

public class ScreensNavigator {
    private final Context context;

    public ScreensNavigator(Context context) {
        this.context = context;
    }

    public void toDialogDetails(String questionId) {
        QuestionDetailsActivity.start(context, questionId);
    }
}
