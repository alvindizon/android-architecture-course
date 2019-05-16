package com.techyourchance.mvc.screens.questionslist;

import com.techyourchance.mvc.questions.Question;
import com.techyourchance.mvc.screens.common.ViewMvc;

import java.util.List;

interface QuestionsListViewMvc extends ViewMvc {
    // mechanism for sending feedback to this view's controller (i.e., the activity)
    void registerListener(Listener listener);

    void unregisterListener(Listener listener);

    void bindQuestions(List<Question> questions);

    public interface Listener {
        void onQuestionClicked(Question question);
    }
}
