package com.techyourchance.mvc.screens.questiondetails;

import com.techyourchance.mvc.questions.QuestionDetails;
import com.techyourchance.mvc.screens.common.navdrawer.DrawerItems;
import com.techyourchance.mvc.screens.common.navdrawer.NavDrawerViewMvc;
import com.techyourchance.mvc.screens.common.views.ObservableViewMvc;

public interface QuestionDetailsViewMvc extends ObservableViewMvc<QuestionDetailsViewMvc.Listener>,
        NavDrawerViewMvc {

    public interface Listener {
        void onUpButtonClick();
        void onDrawerItemClicked(DrawerItems item);
    }


    void bindQuestionDetails(QuestionDetails questionDetails);

    void showProgressBar();

    void hideProgressBar();
}
