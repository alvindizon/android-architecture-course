package com.techyourchance.mvc.common.di;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;

import com.techyourchance.mvc.networking.StackoverflowApi;
import com.techyourchance.mvc.questions.FetchQuestionDetailsUseCase;
import com.techyourchance.mvc.questions.FetchQuestionListUseCase;
import com.techyourchance.mvc.screens.common.ViewMvcFactory;
import com.techyourchance.mvc.screens.common.controllers.BackPressDispatcher;
import com.techyourchance.mvc.screens.common.fragmentframehelper.FragmentFrameHelper;
import com.techyourchance.mvc.screens.common.fragmentframehelper.FragmentFrameWrapper;
import com.techyourchance.mvc.screens.common.navdrawer.NavDrawerHelper;
import com.techyourchance.mvc.screens.common.screensnavigator.ScreensNavigator;
import com.techyourchance.mvc.screens.common.toastshelper.ToastsHelper;
import com.techyourchance.mvc.screens.questionslist.QuestionsListController;

/**
 * This is tied to the activity lifecycle, every object needed to instantiate MVC views
 * can be found here
 */
public class ControllerCompositionRoot {

    private final CompositionRoot compositionRoot;
    private FragmentActivity activity;

    public ControllerCompositionRoot(CompositionRoot compositionRoot, FragmentActivity activity) {
        this.compositionRoot = compositionRoot;
        this.activity = activity;
    }

    public FragmentActivity provideActivity() {
        return activity;
    }

    private Context provideContext() {
        return activity;
    }

    private FragmentManager provideFragmentManager() {
        return activity.getSupportFragmentManager();
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
        return new ViewMvcFactory(provideLayoutInflater(), provideNavDrawerHelper());
    }

    private NavDrawerHelper provideNavDrawerHelper() {
        return (NavDrawerHelper) provideActivity();
    }

    public ToastsHelper provideToastsHelper() {
        return new ToastsHelper(provideContext());
    }

    public ScreensNavigator provideScreensNavigator() {
        return new ScreensNavigator(provideFragmentFrameHelper());
    }

    private FragmentFrameHelper provideFragmentFrameHelper() {
        return new FragmentFrameHelper(provideActivity(), provideFragmentFrameWrapper(), provideFragmentManager());
    }

    public QuestionsListController getQuestionsListController() {
        return new QuestionsListController(
                getFetchQuestionListUseCase(),
                provideScreensNavigator(),
                provideToastsHelper());
    }


    public BackPressDispatcher provideBackPresDispatcher() {
        return (BackPressDispatcher) provideActivity();
    }

    private FragmentFrameWrapper provideFragmentFrameWrapper() {
        return (FragmentFrameWrapper) provideActivity();
    }
}
