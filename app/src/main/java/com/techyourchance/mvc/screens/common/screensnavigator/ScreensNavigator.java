package com.techyourchance.mvc.screens.common.screensnavigator;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.techyourchance.mvc.screens.common.controllers.FragmentFrameWrapper;
import com.techyourchance.mvc.screens.questiondetails.QuestionDetailsFragment;
import com.techyourchance.mvc.screens.questionslist.QuestionsListFragment;

public class ScreensNavigator {

    private final FragmentManager fragmentManager;
    private final FragmentFrameWrapper fragmentFrameWrapper;

    public ScreensNavigator(FragmentManager fragmentManager, FragmentFrameWrapper fragmentFrameWrapper) {
        this.fragmentManager = fragmentManager;
        this.fragmentFrameWrapper = fragmentFrameWrapper;
    }

    public void toQuestionDetails(String questionId) {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.addToBackStack(null);
        ft.replace(fragmentFrameWrapper.getFragmentFrame().getId(), QuestionDetailsFragment.newInstance(questionId)).commit();
    }

    public void toQuestionsList() {
        // Since the questions list screen is the root screen, we pop the back stack up to the root screen
        fragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(fragmentFrameWrapper.getFragmentFrame().getId(), QuestionsListFragment.newInstance()).commit();
    }
}
