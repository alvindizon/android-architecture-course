package com.techyourchance.mvc.screens.questionslist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.techyourchance.mvc.R;
import com.techyourchance.mvc.common.Constants;
import com.techyourchance.mvc.networking.QuestionSchema;
import com.techyourchance.mvc.networking.QuestionsListResponseSchema;
import com.techyourchance.mvc.networking.StackoverflowApi;
import com.techyourchance.mvc.questions.FetchQuestionListUseCase;
import com.techyourchance.mvc.questions.Question;
import com.techyourchance.mvc.screens.common.BaseActivity;
import com.techyourchance.mvc.screens.questiondetails.QuestionDetailsActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// TODO extract networking code to usecase
public class QuestionsListActivity extends BaseActivity implements QuestionsListViewMvcImpl.Listener,
    FetchQuestionListUseCase.Listener{

    private QuestionsListViewMvc viewMvc;
    private FetchQuestionListUseCase useCase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        useCase = getCompositionRoot().getFetchQuestionListUseCase();
        viewMvc = getCompositionRoot().getViewMvcFactory().getQuestionsListViewMvc(null);
        viewMvc.registerListener(this);
        setContentView(viewMvc.getRootView());
    }

    @Override
    protected void onStart() {
        super.onStart();
        useCase.registerListener(this);
        useCase.fetchQuestions();
    }

    @Override
    protected void onStop() {
        super.onStop();
        useCase.unregisterListener(this);
    }

    @Override
    public void onQuestionClicked(Question question) {
        QuestionDetailsActivity.start(this, question.getId());
    }

    @Override
    public void onFetchQuestionListFetched(List<Question> questions) {
        viewMvc.bindQuestions(questions);
    }

    @Override
    public void onFetchQuestionListFailed() {
        Toast.makeText(this, R.string.error_network_call_failed, Toast.LENGTH_SHORT).show();
    }
}
