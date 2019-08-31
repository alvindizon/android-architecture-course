package com.techyourchance.mvc.screens.questiondetails;

import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.techyourchance.mvc.R;
import com.techyourchance.mvc.questions.QuestionDetails;
import com.techyourchance.mvc.screens.common.ViewMvcFactory;
import com.techyourchance.mvc.screens.common.toolbar.ToolbarViewMvc;
import com.techyourchance.mvc.screens.common.views.BaseObservableViewMvc;

public class QuestionDetailsViewMvcImpl extends BaseObservableViewMvc<QuestionDetailsViewMvc.Listener> implements QuestionDetailsViewMvc {
    private static final String TAG = QuestionDetailsViewMvcImpl.class.getSimpleName();

    private TextView questionTitle;
    private WebView questionBody;
    private ProgressBar progressBar;

    // nested MVC views
    private Toolbar toolbar;
    private ToolbarViewMvc toolbarViewMvc;

    public QuestionDetailsViewMvcImpl(LayoutInflater layoutInflater, ViewGroup viewGroup, ViewMvcFactory viewMvcFactory) {
        setRootView(layoutInflater.inflate(R.layout.questions_details, viewGroup, false));
        questionTitle = findViewById(R.id.title);
        questionBody = findViewById(R.id.body);
        progressBar = findViewById(R.id.progressBar);

        toolbar = findViewById(R.id.include);
        toolbarViewMvc = viewMvcFactory.getToolbarViewMvc(toolbar);
        toolbarViewMvc.setTitle(getString(R.string.questions_detail_screen_title));
        toolbarViewMvc.setButtonVisible(true);
        toolbarViewMvc.setButtonListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "click");
                for(Listener listener : getListeners()) {
                    listener.onUpButtonClick();
                }
            }
        });
        toolbar.addView(toolbarViewMvc.getRootView());
    }

    @Override
    public void bindQuestionDetails(QuestionDetails questionDetails) {
        questionTitle.setText(questionDetails.getTitle());
        questionBody.loadDataWithBaseURL(null, questionDetails.getBody(), "text/html", "utf-8", null);
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

}
