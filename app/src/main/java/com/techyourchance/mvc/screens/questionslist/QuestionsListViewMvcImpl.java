package com.techyourchance.mvc.screens.questionslist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.techyourchance.mvc.R;
import com.techyourchance.mvc.questions.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionsListViewMvcImpl implements QuestionsListAdapter.OnQuestionClickListener, QuestionsListViewMvc {

    private final View rootView;
    private ListView mLstQuestions;
    private QuestionsListAdapter mQuestionsListAdapter;

    private final List<Listener> listeners = new ArrayList<>(1);

    public QuestionsListViewMvcImpl(LayoutInflater inflater, ViewGroup viewGroup) {
        rootView = inflater.inflate(R.layout.layout_questions_list, viewGroup, false);

        mLstQuestions = findViewById(R.id.lst_questions);
        mQuestionsListAdapter = new QuestionsListAdapter(getContext(), this);
        mLstQuestions.setAdapter(mQuestionsListAdapter);
 
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
        mQuestionsListAdapter.clear();
        mQuestionsListAdapter.addAll(questions);
        mQuestionsListAdapter.notifyDataSetChanged();
    }
}
