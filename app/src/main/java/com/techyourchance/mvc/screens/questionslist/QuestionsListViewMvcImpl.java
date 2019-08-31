package com.techyourchance.mvc.screens.questionslist;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.techyourchance.mvc.R;
import com.techyourchance.mvc.questions.Question;
import com.techyourchance.mvc.screens.common.ViewMvcFactory;
import com.techyourchance.mvc.screens.common.navdrawer.NavDrawerHelper;
import com.techyourchance.mvc.screens.common.toolbar.ToolbarViewMvc;
import com.techyourchance.mvc.screens.common.views.BaseObservableViewMvc;

import java.util.List;

public class QuestionsListViewMvcImpl extends BaseObservableViewMvc<QuestionsListViewMvc.Listener> implements QuestionsListViewMvc, QuestionsRecylerAdapter.Listener {

    private RecyclerView recyclerQuestions;
    private QuestionsRecylerAdapter adapter;
    private ProgressBar progressBar;
    // nested MVC views
    private Toolbar toolbar;
    private ToolbarViewMvc toolbarViewMvc;

    private final NavDrawerHelper navDrawerHelper;

    public QuestionsListViewMvcImpl(LayoutInflater inflater,
                                    ViewGroup viewGroup,
                                    ViewMvcFactory viewMvcFactory,
                                    final NavDrawerHelper navDrawerHelper) {
        this.navDrawerHelper = navDrawerHelper;
        setRootView(inflater.inflate(R.layout.layout_questions_list, viewGroup, false));

        recyclerQuestions = findViewById(R.id.recycler_questions);
        progressBar = findViewById(R.id.progressBar);
        recyclerQuestions.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new QuestionsRecylerAdapter(this, viewMvcFactory);
        recyclerQuestions.setAdapter(adapter);

        toolbar = findViewById(R.id.include);
        toolbarViewMvc = viewMvcFactory.getToolbarViewMvc(toolbar);
        toolbarViewMvc.setTitle(getString(R.string.questions_list_screen_title));
        toolbarViewMvc.setHamburgerVisible(true);
        toolbarViewMvc.setHamburgerListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navDrawerHelper.openDrawer();
            }
        });
        toolbar.addView(toolbarViewMvc.getRootView());
    }


    @Override
    public void onQuestionClicked(Question question) {
        for(Listener listener : getListeners()) {
            listener.onQuestionClicked(question);
        }

    }

    @Override
    public void bindQuestions(List<Question> questions) {
        adapter.bindQuestions(questions);
    }

    @Override
    public void showProgressBar() {
        recyclerQuestions.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
        recyclerQuestions.setVisibility(View.VISIBLE);
    }

}