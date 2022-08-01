package com.zpf.myplan.ui.widget.navigation

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup
import com.zpf.myplan.R
import com.zpf.myplan.navigation.NavigationManager
import com.zpf.myplan.ui.event.EventFragment
import com.zpf.myplan.ui.mine.MineFragment
import com.zpf.myplan.ui.note.NoteFragment
import com.zpf.myplan.ui.plan.PlanFragment
import com.zpf.myplan.ui.time.TimeFragment

class BottomNavigationMenuView : ViewGroup {
    constructor(context: Context?) : super(context) {}

    //    private MenuInflater menuInflater;
    private var mNavigationManager: NavigationManager? = null
    private var menuInflater: BottomNavigationMenuInflater? = null
    private var menu: BottomNavigationMenu? = null
    private var chooseItemView: BottomMenuItemView? = null

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        menu = BottomNavigationMenu()
        menuInflater = BottomNavigationMenuInflater(getContext())
        @SuppressLint("Recycle") val typedArray =
            context.obtainStyledAttributes(attrs, R.styleable.BottomNavigationMenuView)
        if (typedArray.hasValue(R.styleable.BottomNavigationMenuView_menu)) {
            inflateMenu(typedArray.getResourceId(R.styleable.BottomNavigationMenuView_menu, 0))
        }
        for (i in menu!!.items.indices) {
            val bottomMenuItem = menu!!.items[i]
            val itemView = BottomMenuItemView(context, bottomMenuItem.title, bottomMenuItem.icon, i)
            addView(itemView)
            itemView.setOnClickListener { v: View ->
                val thisView = v as BottomMenuItemView
                thisView.onChoose()
                if (chooseItemView != null) {
                    chooseItemView!!.onUnChoose()
                }
                chooseItemView = thisView
                switchFragment(itemView.id)
                Log.d(
                    TAG,
                    "BottomNavigationMenuView: "
                )
            }
            if (i == 0) {
                chooseItemView = itemView
                itemView.onChoose()
            }
        }
    }

    private fun inflateMenu(resId: Int) {
        menuInflater!!.inflate(resId, menu)
    }

    fun setNavigationManager(navigationManager: NavigationManager) {
        mNavigationManager = navigationManager
        mNavigationManager?.switchFragment<PlanFragment>()
    }

    fun switchFragment(id: Int) {
        when (id) {
            0 -> mNavigationManager?.switchFragment<PlanFragment>()
            1 -> mNavigationManager?.switchFragment<NoteFragment>()
            2 -> mNavigationManager?.switchFragment<EventFragment>()
            3 -> mNavigationManager?.switchFragment<TimeFragment>()
            4 -> mNavigationManager?.switchFragment<MineFragment>()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        if (childCount > 0) {
            val count = childCount
            for (i in 0 until count) {
                val child = getChildAt(i)
                measureChild(child, widthMeasureSpec, heightMeasureSpec)
            }
        }
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        val count = childCount
        val parentWith = (parent as ViewGroup).width
        val parentHeight = (parent as ViewGroup).height
        val itemWith = parentWith / count
        val widget = parentHeight - 10
        for (i in 0 until count) {
            val view = getChildAt(i)
            val height = view.measuredHeight
            val width = view.measuredWidth
            val left = itemWith * i + (itemWith - width) / 2
            view.layout(left, widget - height, left + width, widget)
        }
    }

    companion object {
        private const val TAG = "BottomNavigationMenuView"
    }
}