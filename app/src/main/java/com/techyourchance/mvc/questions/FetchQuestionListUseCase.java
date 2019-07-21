package com.techyourchance.mvc.questions;

import com.techyourchance.mvc.common.BaseObservable;
import com.techyourchance.mvc.common.Constants;
import com.techyourchance.mvc.networking.QuestionSchema;
import com.techyourchance.mvc.networking.QuestionsListResponseSchema;
import com.techyourchance.mvc.networking.StackoverflowApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FetchQuestionListUseCase extends BaseObservable<FetchQuestionListUseCase.Listener> {

    private final StackoverflowApi stackoverflowApi;

    public FetchQuestionListUseCase(StackoverflowApi stackoverflowApi) {
        this.stackoverflowApi = stackoverflowApi;
    }

    public interface Listener {

        void onFetchQuestionListFetched(List<Question> questions);

        void onFetchQuestionListFailed();
    }

    public void fetchQuestions() {
        stackoverflowApi.fetchLastActiveQuestions(Constants.QUESTIONS_LIST_PAGE_SIZE)
            .enqueue(new Callback<QuestionsListResponseSchema>() {
                @Override
                public void onResponse(Call<QuestionsListResponseSchema> call, Response<QuestionsListResponseSchema> response) {
                    if (response.isSuccessful()) {
                        notifySuccess(response.body().getQuestions());
                    } else {
                        notifyFailure();
                    }
                }

                @Override
                public void onFailure(Call<QuestionsListResponseSchema> call, Throwable t) {
                    notifyFailure();
                }
            } );
    }

    private void notifyFailure() {
        for(Listener listener : getListeners()) {
            listener.onFetchQuestionListFailed();
        }
    }

    private void notifySuccess(List<QuestionSchema> questionSchemas) {
        List<Question> questions = new ArrayList<>(questionSchemas.size());

        for(QuestionSchema questionSchema : questionSchemas) {
            questions.add(new Question(
                    questionSchema.getId(),
                    questionSchema.getTitle()));
        }

        for(Listener listener : getListeners()) {
            listener.onFetchQuestionListFetched(questions);
        }
    }
}
