package com.techyourchance.mvc.screens.common.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.FrameLayout;

import com.techyourchance.mvc.R;
import com.techyourchance.mvc.screens.common.controllers.BackPressDispatcher;
import com.techyourchance.mvc.screens.common.controllers.BackPressedListener;
import com.techyourchance.mvc.screens.common.controllers.BaseActivity;
import com.techyourchance.mvc.screens.common.fragmentframehelper.FragmentFrameWrapper;
import com.techyourchance.mvc.screens.common.screensnavigator.ScreensNavigator;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends BaseActivity implements BackPressDispatcher, FragmentFrameWrapper {

    private ScreensNavigator screensNavigator;

    public static void startClearTop(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        // call previous activity (in this case, question details calls EXISTING question list screen
        intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

    private Set<BackPressedListener> backPressedListeners = new HashSet<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_content_frame);
        screensNavigator = getCompositionRoot().provideScreensNavigator();
        if(savedInstanceState == null) {
            screensNavigator.toQuestionsList();
        }
    }

    @Override
    public void onBackPressed() {
        boolean isBackPressConsumedByAnyListener = false;
        for(BackPressedListener listener : backPressedListeners) {
            if(listener.onBackPressed()) {
                isBackPressConsumedByAnyListener = true;
            }
        }
        if(!isBackPressConsumedByAnyListener) {
            super.onBackPressed();
        }
    }

    @Override
    public void registerListener(BackPressedListener listener) {
        backPressedListeners.add(listener);
    }

    @Override
    public void unregisterListener(BackPressedListener listener) {
        backPressedListeners.remove(listener);
    }

    @Override
    public FrameLayout getFragmentFrame() {
        return findViewById(R.id.frame_content);
    }
}