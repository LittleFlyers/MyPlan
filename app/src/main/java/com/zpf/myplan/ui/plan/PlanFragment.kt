package com.zpf.myplan.ui.plan

import android.content.res.AssetManager
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.zpf.myplan.R
import com.zpf.myplan.databinding.FragmentPlanBinding

class PlanFragment : Fragment() {

    private lateinit var mView: View
    private lateinit var planViewModel: PlanViewModel
    private var _binding: FragmentPlanBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        planViewModel =
            ViewModelProvider(this).get(PlanViewModel::class.java)

        _binding = FragmentPlanBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textHome
        planViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mView = view
        val header = view.findViewById<TextView>(R.id.header)
        val assetManager: AssetManager? = context?.assets
        val typeFace: Typeface =
            Typeface.createFromAsset(assetManager, "fonts/HuXiaoBoSaoBaoTi-2.otf");
        header.typeface = typeFace
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}