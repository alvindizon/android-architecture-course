package com.techyourchance.mvc.screens.common.screensnavigator;

import android.content.Context;

import com.techyourchance.mvc.screens.questiondetails.QuestionDetailsActivity;
import com.techyourchance.mvc.screens.questionslist.QuestionsListActivity;

public class ScreensNavigator {
    private final Context context;

    public ScreensNavigator(Context context) {
        this.context = context;
    }

    public void toDialogDetails(String questionId) {
        QuestionDetailsActivity.start(context, questionId);
    }

    public void toQuestionsListClearTop() {
        QuestionsListActivity.startClearTop(context);
    }
}
