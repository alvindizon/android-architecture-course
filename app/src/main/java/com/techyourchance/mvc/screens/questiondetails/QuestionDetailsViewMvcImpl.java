package com.techyourchance.mvc.screens.questiondetails;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import com.techyourchance.mvc.R;
import com.techyourchance.mvc.questions.QuestionDetails;
import com.techyourchance.mvc.screens.common.BaseViewMvc;

public class QuestionDetailsViewMvcImpl extends BaseViewMvc implements QuestionDetailsViewMvc {

    private TextView questionTitle;
    private WebView questionBody;

    public QuestionDetailsViewMvcImpl(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        setRootView(layoutInflater.inflate(R.layout.questions_details, viewGroup, false));
        questionTitle = findViewById(R.id.title);
        questionBody = findViewById(R.id.body);
    }

    @Override
    public void bindQuestionDetails(QuestionDetails questionDetails) {
        questionTitle.setText(questionDetails.getTitle());
        questionBody.loadDataWithBaseURL(null, questionDetails.getBody(), "text/html", "utf-8", null);
    }
}
