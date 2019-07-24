package com.techyourchance.mvc.screens.common.toastshelper;

import android.content.Context;
import android.widget.Toast;

import com.techyourchance.mvc.R;

public class ToastsHelper {

    private final Context context;

    public ToastsHelper(Context context) {
        this.context = context;
    }

    public void showUseCaseError() {
        Toast.makeText(context, R.string.error_network_call_failed, Toast.LENGTH_SHORT).show();

    }
}
