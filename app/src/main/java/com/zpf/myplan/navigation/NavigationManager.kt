package com.zpf.myplan.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.zpf.myplan.ui.plan.PlanFragment

class NavigationManager constructor(
    val supportFragmentManager: FragmentManager,
    val resId: Int
) {

    inline fun <reified F : Fragment> switchFragment() {
        supportFragmentManager.commit {
            replace<F>(resId)
            setReorderingAllowed(true)
            addToBackStack("name") // name can be null
        }
    }
}