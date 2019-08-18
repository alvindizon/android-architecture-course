package com.techyourchance.mvc.screens.common.toolbar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.techyourchance.mvc.R;
import com.techyourchance.mvc.screens.common.views.BaseViewMvc;

public class ToolbarViewMvc extends BaseViewMvc {

    private final TextView mTxtTitle;
    private final ImageButton mBtnBack;
    private final ImageButton mBtnHamburger;

    public ToolbarViewMvc(LayoutInflater inflater, ViewGroup parent) {
        setRootView(inflater.inflate(R.layout.layout_toolbar, parent, false));
        mTxtTitle = findViewById(R.id.txt_toolbar_title);
        mBtnBack = findViewById(R.id.btn_back);
        mBtnHamburger = findViewById(R.id.btn_hamburger);
    }

    public void setTitle(String title) {
        mTxtTitle.setText(title);
    }

    public void setButtonListener(ImageButton.OnClickListener listener) {
        mBtnBack.setOnClickListener(listener);
    }

    public void setButtonVisible(boolean isVisible) {
        if(isVisible) {
            mBtnBack.setVisibility(View.VISIBLE);
        } else {
            mBtnBack.setVisibility(View.GONE);
        }
    }

    public void setHamburgerListener(ImageButton.OnClickListener listener) {
        mBtnHamburger.setOnClickListener(listener);
    }

    public void setHamburgerVisible(boolean isVisible) {
        if(isVisible) {
            mBtnHamburger.setVisibility(View.VISIBLE);
        } else {
            mBtnHamburger.setVisibility(View.GONE);
        }
    }
}
