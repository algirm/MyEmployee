package com.algirm.myemployee.presentation.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.algirm.myemployee.R
import com.algirm.myemployee.common.AppConst.Companion.TAG
import com.algirm.myemployee.common.viewBinding
import com.algirm.myemployee.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
//    private val binding by viewBinding(FragmentHomeBinding::bind)

    private val activityViewModel: ActivityViewModel by activityViewModels()

    private lateinit var activityAdapter: ActivityAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        initView()
        return root
    }

    private fun initView() {
        activityAdapter = ActivityAdapter(requireContext())
        binding.rcvActivities.apply {
            adapter = activityAdapter
            layoutManager = LinearLayoutManager(activity)
        }
        binding.fabCreateActivity.setOnClickListener {
            findNavController()
                .navigate(
                    HomeFragmentDirections.actionNavigationHomeToNavigationCreateActivity(
                        label = "Add Activity",
                        activitySeq = null,
                        category = null
                    )
                )
        }
        activityAdapter.setOnItemClickListener {
            findNavController()
                .navigate(
                    HomeFragmentDirections.actionNavigationHomeToNavigationCreateActivity(
                        label = "Edit Activity",
                        activitySeq = it.Seq,
                        category = it.Category
                    )
                )
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launchWhenResumed {
            activityViewModel.listActivities.collect { listActivities ->
                activityAdapter.differ.submitList(listActivities)
            }
        }
    }

}