package com.techyourchance.mvc.common.di;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;

import com.techyourchance.mvc.networking.StackoverflowApi;
import com.techyourchance.mvc.questions.FetchQuestionDetailsUseCase;
import com.techyourchance.mvc.questions.FetchQuestionListUseCase;
import com.techyourchance.mvc.screens.common.toastshelper.ToastsHelper;
import com.techyourchance.mvc.screens.common.screensnavigator.ScreensNavigator;
import com.techyourchance.mvc.screens.common.ViewMvcFactory;
import com.techyourchance.mvc.screens.questionslist.QuestionsListController;

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

    private Context provideContext() {
        return activity;
    }

    private StackoverflowApi provideStackOverflowApi() {
        return compositionRoot.provideStackOverflowApi();
    }

    private LayoutInflater provideLayoutInflater() {
        return LayoutInflater.from(provideContext());
    }

    public FetchQuestionDetailsUseCase getFetchQuestionsDetailsUseCase() {
        return new FetchQuestionDetailsUseCase(provideStackOverflowApi());
    }

    public FetchQuestionListUseCase getFetchQuestionListUseCase() {
        return new FetchQuestionListUseCase(provideStackOverflowApi());
    }

    public ViewMvcFactory getViewMvcFactory() {
        return new ViewMvcFactory(provideLayoutInflater());
    }

    public ToastsHelper provideMessagesDisplayer() {
        return new ToastsHelper(provideContext());
    }

    public ScreensNavigator provideScreensNavigator() {
        return new ScreensNavigator(provideContext());
    }

    public QuestionsListController getQuestionsListController() {
        return new QuestionsListController(
                getFetchQuestionListUseCase(),
                provideScreensNavigator(),
                provideMessagesDisplayer());
    }


}
