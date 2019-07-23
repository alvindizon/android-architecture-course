package com.techyourchance.mvc.screens.questionslist;

import com.techyourchance.mvc.questions.FetchQuestionListUseCase;
import com.techyourchance.mvc.questions.Question;
import com.techyourchance.mvc.screens.common.ScreensNavigator;
import com.techyourchance.mvc.screens.common.MessagesDisplayer;

import java.util.List;

/**
 * Activities are not unit testable, thus we need to extract logic from the Activity and
 * place it in this class.
 *
 * Activities will delegate lifecycle events to this object.
 */
public class QuestionsListController implements QuestionsListViewMvcImpl.Listener,
        FetchQuestionListUseCase.Listener{

    private QuestionsListViewMvc viewMvc;
    // fields that will be injected via constructor injection are marked final
    private final FetchQuestionListUseCase useCase;
    private final ScreensNavigator screensNavigator;
    private final MessagesDisplayer messagesDisplayer;

    public QuestionsListController(FetchQuestionListUseCase useCase, ScreensNavigator screensNavigator, MessagesDisplayer messagesDisplayer) {
        this.useCase = useCase;
        this.screensNavigator = screensNavigator;
        this.messagesDisplayer = messagesDisplayer;
    }

    public void bindView(QuestionsListViewMvc viewMvc) {
        this.viewMvc = viewMvc;
        viewMvc.registerListener(this);
    }

    public void onStart() {
        viewMvc.registerListener(this);
        viewMvc.showProgressBar();
        useCase.registerListener(this);
        useCase.fetchQuestions();
    }

    public void onStop() {
        // make sure that any updates originating from this screen is stopped and does not reach
        // the next screen
        viewMvc.unregisterListener(this);
        useCase.unregisterListener(this);
    }

    @Override
    public void onQuestionClicked(Question question) {
        screensNavigator.toDialogDetails(question.getId());
    }

    @Override
    public void onFetchQuestionListFetched(List<Question> questions) {
        viewMvc.hideProgressBar();
        viewMvc.bindQuestions(questions);
    }

    @Override
    public void onFetchQuestionListFailed() {
        viewMvc.hideProgressBar();
        messagesDisplayer.showUseCaseError();
    }
}
