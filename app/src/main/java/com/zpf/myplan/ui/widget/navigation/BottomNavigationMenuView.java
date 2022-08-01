package com.zpf.myplan.ui.widget.navigation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.zpf.myplan.R;

public class BottomNavigationMenuView extends ViewGroup {
    private static final String TAG = "BottomNavigationMenuView";

    public BottomNavigationMenuView(Context context) {
        super(context);
    }

    //    private MenuInflater menuInflater;
    private BottomNavigationMenuInflater menuInflater;
    private BottomNavigationMenu menu;
    private BottomMenuItemView chooseItemView;

    public BottomNavigationMenuView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.menu = new BottomNavigationMenu();
        menuInflater = new BottomNavigationMenuInflater(getContext());
        @SuppressLint("Recycle") TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BottomNavigationMenuView);
        if (typedArray.hasValue(R.styleable.BottomNavigationMenuView_menu)) {
            inflateMenu(typedArray.getResourceId(R.styleable.BottomNavigationMenuView_menu, 0));
        }

        for (int i = 0; i < menu.getItems().size(); i++) {
            BottomMenuItem bottomMenuItem = menu.getItems().get(i);
            BottomMenuItemView itemView = new BottomMenuItemView(context, bottomMenuItem.getTitle(), bottomMenuItem.getIcon());
            addView(itemView);
            itemView.setOnClickListener(v -> {
                BottomMenuItemView thisView = (BottomMenuItemView) v;
                thisView.onChoose();
                if (chooseItemView!=null){
                    chooseItemView.onUnChoose();
                }
                chooseItemView = thisView;
                Log.d(TAG, "BottomNavigationMenuView: ");
            });
        }
    }

    public void inflateMenu(int resId) {
        menuInflater.inflate(resId, menu);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (getChildCount() > 0) {
            int count = getChildCount();
            for (int i = 0; i < count; i++) {
                View child = getChildAt(i);
                measureChild(child, widthMeasureSpec, heightMeasureSpec);
            }
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count = getChildCount();
        int parentWith = ((ViewGroup) getParent()).getWidth();
        int parentHeight = ((ViewGroup) getParent()).getHeight();
        int itemWith = parentWith / count;
        int widget = parentHeight - 10;
        for (int i = 0; i < count; i++) {
            View view = getChildAt(i);
            int height = view.getMeasuredHeight();
            int width = view.getMeasuredWidth();
            int left = itemWith * i + (itemWith - width) / 2;
            view.layout(left, widget - height, left + width, widget);
        }
    }
}
