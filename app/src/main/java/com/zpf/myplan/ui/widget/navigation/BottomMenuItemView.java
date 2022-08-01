package com.zpf.myplan.ui.widget.navigation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.zpf.myplan.R;

@SuppressLint("ViewConstructor")
public class BottomMenuItemView extends ConstraintLayout {
    private static final String TAG = "BottomMenuItemView";
    private static final float UN_CHOOSE = 0.3f;
    private final TextView textView;
    private final ImageView imageView;

    public BottomMenuItemView(Context context, int titleId, int iconId) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.view_botton_menu_item, this);
        textView = findViewById(R.id.bottom_item_text);
        textView.setText(titleId);
        textView.setAlpha(UN_CHOOSE);
        textView.setOnClickListener(null);
        imageView = findViewById(R.id.bottom_item_icon);
        imageView.setImageResource(iconId);
        imageView.setAlpha(UN_CHOOSE);
        imageView.setOnClickListener(null);
    }

    public void onChoose() {
        textView.setAlpha(1f);
        imageView.setAlpha(1f);
    }

    public void onUnChoose() {
        textView.setAlpha(UN_CHOOSE);
        imageView.setAlpha(UN_CHOOSE);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }
}
