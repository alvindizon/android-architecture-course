package com.techyourchance.mvc.screens.questiondetails;

import com.techyourchance.mvc.questions.QuestionDetails;
import com.techyourchance.mvc.screens.common.ViewMvc;

public interface QuestionDetailsViewMvc extends ViewMvc {

    void bindQuestionDetails(QuestionDetails questionDetails);

    void showProgressBar();

    void hideProgressBar();
}
