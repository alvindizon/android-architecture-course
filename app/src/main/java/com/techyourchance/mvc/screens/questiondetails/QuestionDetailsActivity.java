package com.techyourchance.mvc.screens.questiondetails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.techyourchance.mvc.questions.FetchQuestionDetailsUseCase;
import com.techyourchance.mvc.questions.QuestionDetails;
import com.techyourchance.mvc.screens.common.controllers.BaseActivity;
import com.techyourchance.mvc.screens.common.toastshelper.ToastsHelper;

public class QuestionDetailsActivity extends BaseActivity implements FetchQuestionDetailsUseCase.Listener, QuestionDetailsViewMvc.Listener {

    public static final String EXTRA_QUESTION_ID = "EXTRA_QUESTION_ID";

    private QuestionDetailsViewMvc viewMvc;
    private FetchQuestionDetailsUseCase useCase;
    private ToastsHelper toastsHelper;

    public static void start(Context context, String questionId) {
        Intent intent = new Intent(context, QuestionDetailsActivity.class);
        intent.putExtra(EXTRA_QUESTION_ID, questionId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        useCase = getCompositionRoot().getFetchQuestionsDetailsUseCase();
        viewMvc = getCompositionRoot().getViewMvcFactory().getQuestionDetailsViewMvc(null);
        toastsHelper = getCompositionRoot().provideMessagesDisplayer();
        setContentView(viewMvc.getRootView());
    }

    @Override
    protected void onStart() {
        super.onStart();
        viewMvc.showProgressBar();
        viewMvc.registerListener(this);
        useCase.registerListener(this);
        useCase.fetchQuestionDetailsAndNotify(getIntent().getStringExtra(EXTRA_QUESTION_ID));
    }

    @Override
    protected void onStop() {
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
        onBackPressed();
    }
}
