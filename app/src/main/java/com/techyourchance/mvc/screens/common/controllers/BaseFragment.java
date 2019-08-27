package com.techyourchance.mvc.screens.common.controllers;

import android.support.v4.app.Fragment;

import com.techyourchance.mvc.common.CustomApplication;
import com.techyourchance.mvc.common.di.ControllerCompositionRoot;

public abstract class BaseFragment extends Fragment {

    private ControllerCompositionRoot controllerCompositionRoot;

    protected ControllerCompositionRoot getCompositionRoot() {
        if(controllerCompositionRoot == null) {
            controllerCompositionRoot = new ControllerCompositionRoot(
                    ((CustomApplication) requireActivity().getApplication()).getCompositionRoot(),
                    requireActivity()
            );
        }
        return controllerCompositionRoot;
    }
}
