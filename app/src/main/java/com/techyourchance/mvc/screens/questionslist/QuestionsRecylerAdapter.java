package com.techyourchance.mvc.screens.questionslist;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.techyourchance.mvc.questions.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionsRecylerAdapter extends RecyclerView.Adapter<QuestionsRecylerAdapter.MyViewHolder>
    implements QuestionsListItemViewMvc.Listener {

    public interface Listener {
        void onQuestionClicked(Question question);
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        private final QuestionsListItemViewMvc viewMvc;

        public MyViewHolder(QuestionsListItemViewMvc viewMvc) {
            super(viewMvc.getRootView());
            this.viewMvc = viewMvc;
        }
    }

    private final LayoutInflater inflater;
    private final Listener listener;

    private List<Question> questions = new ArrayList<>();

    public QuestionsRecylerAdapter(LayoutInflater inflater, Listener listener) {
        this.inflater = inflater;
        this.listener = listener;
    }

    public void bindQuestions(List<Question> questions) {
        this.questions = questions;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        QuestionsListItemViewMvc viewMvc = new QuestionsListItemViewMvcImpl(inflater, parent);
        viewMvc.registerListener(this);
        return new MyViewHolder(viewMvc);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.viewMvc.bindQuestion(questions.get(position));
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    @Override
    public void onQuestionClicked(Question question) {
        listener.onQuestionClicked(question);
    }
}
