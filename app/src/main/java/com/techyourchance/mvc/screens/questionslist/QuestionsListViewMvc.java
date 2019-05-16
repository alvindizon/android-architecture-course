package com.techyourchance.mvc.screens.questionslist;

import android.view.View;

import com.techyourchance.mvc.questions.Question;

import java.util.List;

interface QuestionsListViewMvc {
    // mechanism for sending feedback to this view's controller (i.e., the activity)
    void registerListener(Listener listener);

    void unregisterListener(Listener listener);

    View getRootView();

    void bindQuestions(List<Question> questions);

    public interface Listener {
        void onQuestionClicked(Question question);
    }
}
