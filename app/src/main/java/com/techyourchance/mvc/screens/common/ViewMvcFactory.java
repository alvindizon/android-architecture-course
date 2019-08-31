package com.techyourchance.mvc.screens.common;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.techyourchance.mvc.screens.common.navdrawer.NavDrawerHelper;
import com.techyourchance.mvc.screens.common.navdrawer.NavDrawerViewMvc;
import com.techyourchance.mvc.screens.common.navdrawer.NavDrawerViewMvcImpl;
import com.techyourchance.mvc.screens.common.toolbar.ToolbarViewMvc;
import com.techyourchance.mvc.screens.questiondetails.QuestionDetailsViewMvc;
import com.techyourchance.mvc.screens.questiondetails.QuestionDetailsViewMvcImpl;
import com.techyourchance.mvc.screens.questionslist.QuestionsListViewMvc;
import com.techyourchance.mvc.screens.questionslist.QuestionsListViewMvcImpl;
import com.techyourchance.mvc.screens.questionslist.questionslistitem.QuestionsListItemViewMvc;
import com.techyourchance.mvc.screens.questionslist.questionslistitem.QuestionsListItemViewMvcImpl;

/**
 * This class will have the Activity's LayoutInflater passed to it (see BaseActivity)
 * The LayoutInflater in turn will be passed to the specific implementation of QuestionsListViewMvc,
 * so that it can inflate the layout for the questions list screen
 *
 */
public class ViewMvcFactory {

    private final LayoutInflater layoutInflater;
    private final NavDrawerHelper navDrawerHelper;

    public ViewMvcFactory(LayoutInflater layoutInflater, NavDrawerHelper navDrawerHelper) {
        this.layoutInflater = layoutInflater;
        this.navDrawerHelper = navDrawerHelper;
    }

    public QuestionsListViewMvc getQuestionsListViewMvc(@Nullable ViewGroup parent){
        return new QuestionsListViewMvcImpl(layoutInflater, parent, this, navDrawerHelper);
    }

    public QuestionsListItemViewMvc getQuestionsListItemViewMvc(@Nullable ViewGroup parent) {
        return new QuestionsListItemViewMvcImpl(layoutInflater, parent);
    }

    public QuestionDetailsViewMvc getQuestionDetailsViewMvc(@Nullable ViewGroup parent) {
        return new QuestionDetailsViewMvcImpl(layoutInflater, parent, this);
    }

    public ToolbarViewMvc getToolbarViewMvc(@Nullable ViewGroup parent) {
        return new ToolbarViewMvc(layoutInflater, parent);
    }

    public NavDrawerViewMvc getNavDrawerViewMvc(@Nullable ViewGroup parent) {
        return new NavDrawerViewMvcImpl(layoutInflater, parent);
    }
}
