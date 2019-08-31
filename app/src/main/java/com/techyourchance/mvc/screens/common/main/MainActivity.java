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
import com.techyourchance.mvc.screens.common.navdrawer.NavDrawerHelper;
import com.techyourchance.mvc.screens.common.navdrawer.NavDrawerViewMvc;
import com.techyourchance.mvc.screens.common.screensnavigator.ScreensNavigator;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends BaseActivity implements BackPressDispatcher,
        FragmentFrameWrapper,
        NavDrawerViewMvc.Listener,
        NavDrawerHelper {

    private ScreensNavigator screensNavigator;
    private Set<BackPressedListener> backPressedListeners = new HashSet<>();

    private NavDrawerViewMvc viewMvc;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        screensNavigator = getCompositionRoot().provideScreensNavigator();
        viewMvc = getCompositionRoot().getViewMvcFactory().getNavDrawerViewMvc(null);
        setContentView(viewMvc.getRootView());
        if(savedInstanceState == null) {
            screensNavigator.toQuestionsList();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        viewMvc.registerListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        viewMvc.unregisterListener(this);
    }

    @Override
    public void onQuestionsListClicked() {
        screensNavigator.toQuestionsList();
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
        return viewMvc.getFragmentFrame();
    }

    @Override
    public void openDrawer() {
        viewMvc.openDrawer();
    }

    @Override
    public void closeDrawer() {
        viewMvc.closeDrawer();
    }

    @Override
    public boolean isDrawerOpen() {
        return viewMvc.isDrawerOpen();
    }
}