package com.techyourchance.mvc.screens.common.screensnavigator;

import com.techyourchance.mvc.screens.common.fragmentframehelper.FragmentFrameHelper;
import com.techyourchance.mvc.screens.questiondetails.QuestionDetailsFragment;
import com.techyourchance.mvc.screens.questionslist.QuestionsListFragment;

public class ScreensNavigator {

    private FragmentFrameHelper fragmentFrameHelper;

    public ScreensNavigator(FragmentFrameHelper fragmentFrameHelper) {
        this.fragmentFrameHelper = fragmentFrameHelper;
    }

    public void toQuestionDetails(String questionId) {
        fragmentFrameHelper.replaceFragment(QuestionDetailsFragment.newInstance(questionId));
    }

    public void toQuestionsList() {
        // Since the questions list screen is the root screen, we pop the back stack up to the root screen
        fragmentFrameHelper.replaceFragmentAndClearBackstack(QuestionsListFragment.newInstance());
    }

    public void navigateUp() {
        fragmentFrameHelper.navigateUp();
    }
}

