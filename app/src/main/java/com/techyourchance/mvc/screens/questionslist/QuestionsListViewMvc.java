package com.techyourchance.mvc.screens.questionslist;

import com.techyourchance.mvc.questions.Question;
import com.techyourchance.mvc.screens.common.ObservableViewMvc;
import com.techyourchance.mvc.screens.common.ViewMvc;

import java.util.List;

interface QuestionsListViewMvc extends ObservableViewMvc<QuestionsListViewMvc.Listener> {
    public interface Listener {
        void onQuestionClicked(Question question);
    }
    
    void bindQuestions(List<Question> questions);


}
