package com.techyourchance.mvc.screens.questiondetails;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.techyourchance.mvc.questions.FetchQuestionDetailsUseCase;
import com.techyourchance.mvc.questions.QuestionDetails;
import com.techyourchance.mvc.screens.common.controllers.BackPressedListener;
import com.techyourchance.mvc.screens.common.controllers.BaseFragment;
import com.techyourchance.mvc.screens.common.navdrawer.DrawerItems;
import com.techyourchance.mvc.screens.common.screensnavigator.ScreensNavigator;
import com.techyourchance.mvc.screens.common.toastshelper.ToastsHelper;

public class QuestionDetailsFragment extends BaseFragment implements
        FetchQuestionDetailsUseCase.Listener, QuestionDetailsViewMvc.Listener, BackPressedListener {

    public static final String EXTRA_QUESTION_ID = "EXTRA_QUESTION_ID";
    private QuestionDetailsViewMvc viewMvc;
    private FetchQuestionDetailsUseCase useCase;
    private ToastsHelper toastsHelper;
    private ScreensNavigator screensNavigator;

    public static QuestionDetailsFragment newInstance(String questionId) {
        QuestionDetailsFragment fragment = new QuestionDetailsFragment();
        Bundle args = new Bundle();
        args.putString(EXTRA_QUESTION_ID, questionId);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        useCase = getCompositionRoot().getFetchQuestionsDetailsUseCase();
        viewMvc = getCompositionRoot().getViewMvcFactory().getQuestionDetailsViewMvc(null);
        toastsHelper = getCompositionRoot().provideMessagesDisplayer();
        screensNavigator = getCompositionRoot().provideScreensNavigator();
        return viewMvc.getRootView();
    }

    @Override
    public void onStart() {
        super.onStart();
        viewMvc.showProgressBar();
        viewMvc.registerListener(this);
        useCase.registerListener(this);
        useCase.fetchQuestionDetailsAndNotify(getArguments().getString(EXTRA_QUESTION_ID));
    }

    @Override
    public void onStop() {
        super.onStop();
        viewMvc.unregisterListener(this);
        useCase.unregisterListener(this);
    }

    @Override
    public void onQuestionDetailsFetched(QuestionDetails questionDetails) {
        viewMvc.hideProgressBar();
        viewMvc.bindQuestionDetails(questionDetails);
    }

    @Override
    public void onQuestionDetailsFetchFailed() {
        viewMvc.hideProgressBar();
        toastsHelper.showUseCaseError();
    }

    @Override
    public void onUpButtonClick() {
        screensNavigator.toQuestionsListClearTop();
    }

    @Override
    public void onDrawerItemClicked(DrawerItems item) {
        switch (item) {
            case QUESTIONS_LIST:
                screensNavigator.toQuestionsListClearTop();
        }
    }

    @Override
    public boolean onBackPressed() {
        if(viewMvc.isDrawerOpen()) {
            viewMvc.closeDrawer();
            return true;
        }
        return false;
    }
}
