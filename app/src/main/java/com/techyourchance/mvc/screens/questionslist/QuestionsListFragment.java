package com.techyourchance.mvc.screens.questionslist;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.techyourchance.mvc.screens.common.controllers.BaseFragment;

public class QuestionsListFragment extends BaseFragment {

    private QuestionsListController controller;

    public static Fragment newInstance() {
        return new QuestionsListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        QuestionsListViewMvc viewMvc = getCompositionRoot().getViewMvcFactory().getQuestionsListViewMvc(container);
        controller = getCompositionRoot().getQuestionsListController();
        controller.bindView(viewMvc);
        return viewMvc.getRootView();
    }

    @Override
    public void onStart() {
        super.onStart();
        controller.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        controller.onStop();
    }
}
