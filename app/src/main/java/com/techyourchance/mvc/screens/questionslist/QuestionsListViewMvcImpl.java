package com.techyourchance.mvc.screens.questionslist;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.techyourchance.mvc.R;
import com.techyourchance.mvc.questions.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionsListViewMvcImpl implements  QuestionsListViewMvc, QuestionsRecylerAdapter.Listener {

    private final View rootView;

    private RecyclerView recyclerQuestions;
    private QuestionsRecylerAdapter adapter;

    private final List<Listener> listeners = new ArrayList<>(1);

    public QuestionsListViewMvcImpl(LayoutInflater inflater, ViewGroup viewGroup) {
        rootView = inflater.inflate(R.layout.layout_questions_list, viewGroup, false);

        recyclerQuestions = findViewById(R.id.recycler_questions);
        recyclerQuestions.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new QuestionsRecylerAdapter(inflater, this);
        recyclerQuestions.setAdapter(adapter);
 
    }

    // mechanism for sending feedback to this view's controller (i.e., the activity)
    @Override
    public void registerListener(Listener listener) {
        listeners.add(listener);
    }

    @Override
    public void unregisterListener(Listener listener) {
        listeners.remove(listener);
    }

    private Context getContext() {
        return getRootView().getContext();
    }

    private <T extends View> T findViewById(int id) {
        return getRootView().findViewById(id);
    }

    @Override
    public View getRootView() {
        return rootView.getRootView();
    }

    @Override
    public void onQuestionClicked(Question question) {
        for(Listener listener : listeners) {
            listener.onQuestionClicked(question);
        }

    }

    @Override
    public void bindQuestions(List<Question> questions) {
        adapter.bindQuestions(questions);
    }
}
