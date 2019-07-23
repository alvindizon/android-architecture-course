package com.techyourchance.mvc.screens.questiondetails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.techyourchance.mvc.R;
import com.techyourchance.mvc.networking.QuestionDetailsResponseSchema;
import com.techyourchance.mvc.networking.QuestionSchema;
import com.techyourchance.mvc.networking.StackoverflowApi;
import com.techyourchance.mvc.questions.FetchQuestionDetailsUseCase;
import com.techyourchance.mvc.questions.QuestionDetails;
import com.techyourchance.mvc.screens.common.BaseActivity;
import com.techyourchance.mvc.screens.common.ViewMvcFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionDetailsActivity extends BaseActivity implements FetchQuestionDetailsUseCase.Listener {

    public static final String EXTRA_QUESTION_ID = "EXTRA_QUESTION_ID";

    private QuestionDetailsViewMvc viewMvc;
    private FetchQuestionDetailsUseCase useCase;

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
        setContentView(viewMvc.getRootView());
    }

    @Override
    protected void onStart() {
        super.onStart();
        viewMvc.showProgressBar();
        useCase.registerListener(this);
        useCase.fetchQuestionDetailsAndNotify(getIntent().getStringExtra(EXTRA_QUESTION_ID));
    }

    @Override
    protected void onStop() {
        super.onStop();
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
        Toast.makeText(this, R.string.error_network_call_failed, Toast.LENGTH_SHORT).show();
    }
}
