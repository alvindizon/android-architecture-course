package com.techyourchance.mvc.screens.common;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.techyourchance.mvc.screens.questionslist.QuestionsListItemViewMvc;
import com.techyourchance.mvc.screens.questionslist.QuestionsListItemViewMvcImpl;
import com.techyourchance.mvc.screens.questionslist.QuestionsListViewMvc;
import com.techyourchance.mvc.screens.questionslist.QuestionsListViewMvcImpl;

/**
 * This class will have the Activity's LayoutInflater passed to it (see BaseActivity)
 * The LayoutInflater in turn will be passed to the specific implementation of QuestionsListViewMvc,
 * so that it can inflate the layout for the questions list screen
 *
 */
public class ViewMvcFactory {

    private final LayoutInflater layoutInflater;

    public ViewMvcFactory(LayoutInflater layoutInflater) {
        this.layoutInflater = layoutInflater;
    }

    public QuestionsListViewMvc getQuestionsListViewMvc(@Nullable ViewGroup parent){
        return new QuestionsListViewMvcImpl(layoutInflater, parent, this);
    }

    public QuestionsListItemViewMvc getQuestionsListItemViewMvc(@Nullable ViewGroup parent) {
        return new QuestionsListItemViewMvcImpl(layoutInflater, parent);
    }
}
