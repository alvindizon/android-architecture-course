package com.techyourchance.mvc.screens.common.controllers;

import android.support.v7.app.AppCompatActivity;

import com.techyourchance.mvc.common.CustomApplication;
import com.techyourchance.mvc.common.di.ControllerCompositionRoot;

public abstract class BaseActivity extends AppCompatActivity {

    private ControllerCompositionRoot controllerCompositionRoot;

    protected ControllerCompositionRoot getCompositionRoot() {
        if(controllerCompositionRoot == null) {
            controllerCompositionRoot = new ControllerCompositionRoot(
                    ((CustomApplication) getApplication()).getCompositionRoot(),
                    this
            );
        }
        return controllerCompositionRoot;
    }

}
