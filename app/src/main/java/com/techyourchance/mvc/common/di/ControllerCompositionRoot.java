package com.techyourchance.mvc.common.di;

import android.app.Activity;
import android.view.LayoutInflater;

import com.techyourchance.mvc.networking.StackoverflowApi;
import com.techyourchance.mvc.questions.FetchQuestionDetailsUseCase;
import com.techyourchance.mvc.questions.FetchQuestionListUseCase;

/**
 * This is tied to the activity lifecycle, every object needed to instantiate MVC views
 * can be found here
 */
public class ControllerCompositionRoot {

    private final CompositionRoot compositionRoot;
    private Activity activity;

    public ControllerCompositionRoot(CompositionRoot compositionRoot, Activity activity) {
        this.compositionRoot = compositionRoot;
        this.activity = activity;
    }

    public StackoverflowApi provideStackOverflowApi() {
        return compositionRoot.provideStackOverflowApi();
    }

    private LayoutInflater provideLayoutInflater() {
        return LayoutInflater.from(activity);
    }

    public FetchQuestionDetailsUseCase getFetchQuestionsDetailsUseCase() {
        return new FetchQuestionDetailsUseCase(provideStackOverflowApi());
    }

    public FetchQuestionListUseCase getFetchQuestionListUseCase() {
        return new FetchQuestionListUseCase(provideStackOverflowApi());
    }
}
