package com.techyourchance.mvc.screens.questionslist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.techyourchance.mvc.R;
import com.techyourchance.mvc.questions.Question;
import com.techyourchance.mvc.screens.common.BaseObservableViewMvc;

/**
 * Actual implementation of a list view item's UI. Now the listview adapter doesn't have to know
 * about the UI of the list item
 */
public class QuestionsListItemViewMvcImpl extends BaseObservableViewMvc<QuestionsListItemViewMvc.Listener>
        implements QuestionsListItemViewMvc {

    private Question question;
    private TextView textTitle;

    public QuestionsListItemViewMvcImpl(LayoutInflater inflater, ViewGroup viewGroup) {
        setRootView(inflater.inflate(R.layout.layout_question_list_item, viewGroup, false));
        textTitle = findViewById(R.id.txt_title);
        // set click listener
        getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(Listener listener : getListeners()) {
                    listener.onQuestionClicked(question);
                }
            }
        });
    }


    @Override
    public void bindQuestion(Question question) {
        this.question = question;
        textTitle.setText(this.question.getTitle());
    }


}
