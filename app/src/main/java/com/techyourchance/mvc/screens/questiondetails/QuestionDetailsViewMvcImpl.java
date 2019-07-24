package com.techyourchance.mvc.screens.questiondetails;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.techyourchance.mvc.R;
import com.techyourchance.mvc.questions.QuestionDetails;
import com.techyourchance.mvc.screens.common.views.BaseViewMvc;

public class QuestionDetailsViewMvcImpl extends BaseViewMvc implements QuestionDetailsViewMvc {

    private TextView questionTitle;
    private WebView questionBody;
    private ProgressBar progressBar;

    public QuestionDetailsViewMvcImpl(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        setRootView(layoutInflater.inflate(R.layout.questions_details, viewGroup, false));
        questionTitle = findViewById(R.id.title);
        questionBody = findViewById(R.id.body);
        progressBar = findViewById(R.id.progressBar);
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
