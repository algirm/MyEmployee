package com.algirm.myemployee.presentation.create_activity

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.algirm.myemployee.common.AppConst.Companion.TAG
import com.algirm.myemployee.databinding.FragmentCreateActivityBinding
import com.algirm.myemployee.domain.model.DepartmentCategory
import com.algirm.myemployee.domain.use_case.add_activity.AddActivityRequest
import com.algirm.myemployee.domain.use_case.add_activity.AddActivityRequestData
import com.algirm.myemployee.domain.use_case.get_activities.GetActivitiesRequest
import com.algirm.myemployee.domain.use_case.get_activities.GetActivitiesRequestData
import com.algirm.myemployee.domain.use_case.get_activity.GetActivityRequest
import com.algirm.myemployee.domain.use_case.get_activity.GetActivityRequestData
import com.algirm.myemployee.domain.use_case.save_edit_activity.SaveEditActivityRequest
import com.algirm.myemployee.domain.use_case.save_edit_activity.SaveEditActivityRequestData
import com.algirm.myemployee.presentation.home.ActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateActivityFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private var _binding: FragmentCreateActivityBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val activityViewModel: ActivityViewModel by activityViewModels()

    private var listCategory = arrayListOf("Pilih Category")
    private var listDepartmentCategory = arrayListOf<DepartmentCategory>()
    private var categorySeq: String? = null
    private lateinit var activitySeq: String

    private val args: CreateActivityFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCreateActivityBinding.inflate(inflater, container, false)
        val root: View = binding.root
        initView()
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launchWhenResumed {
            activityViewModel.categories.collect { listCategories ->
                listCategory = arrayListOf("Pilih Category")
                listDepartmentCategory = arrayListOf()
                listDepartmentCategory.addAll(listCategories)
                listCategories.forEach { category ->
                    listCategory.add(category.Category)
                }
                setSpinnerCategoryAdapter()
                if (args.label == "Edit Activity") {
//                    val dc: String = listDepartmentCategory.filter { it.Seq == args.activitySeq }[0].Category
                    var dcString: String
                    var indexCategory: Int
                    if (listCategories.isNotEmpty()) {
                        Log.d(TAG, "onViewCreated: test if1")
                        var dc: DepartmentCategory
                        listCategories.forEach {
                            Log.d(TAG, "onViewCreated: test foreach ${it.Seq} ${args.activitySeq}")
                            if (it.Category == args.category) {
                                Log.d(TAG, "onViewCreated: test if2")
                                dc = it
                                dcString = dc.Category
                                indexCategory = listCategory.indexOf(dcString)
                                binding.spinnerCategories.setSelection(indexCategory)
                            }
                        }
                    }
                }
            }
        }

        lifecycleScope.launchWhenResumed {
            activityViewModel.resultAddActivity.collect { result ->
                Toast.makeText(activity, result, Toast.LENGTH_SHORT).show()
                activityViewModel.getListActivites(
                    GetActivitiesRequest(
                        RequestData = GetActivitiesRequestData(
                            "",
                            "11111111",
                            "",
                            ""
                        )
                    )
                )
                findNavController().navigateUp()
            }
        }

        lifecycleScope.launchWhenResumed {
            if (args.label == "Edit Activity") {
                activityViewModel.getActivity(
                    GetActivityRequest(
                        RequestData = GetActivityRequestData(Seq = args.activitySeq.toString())
                    )
                )
            }

            activityViewModel.selectedActivity.takeIf {
                args.label == "Edit Activity"
            }?.collect { employeeActivity ->
                employeeActivity?.let {
                    with(binding) {
                        activitySeq = it.Seq
                        etActivityName.setText(it.Activity)
                        etNik.setText(it.NIK)
                        etActDate.setText(it.ActDate)
                        etWaktuMulai.setText(it.TimeIn)
                        etWaktuSelesai.setText(it.TimeOut)
                        etDeadline.setText(it.Deadline)
                        etRemark.setText(it.Remark)
                    }
                }
            }
        }

        lifecycleScope.launchWhenResumed {
            activityViewModel.resultSaveEditActivity.collect { result ->
                Toast.makeText(activity, result, Toast.LENGTH_SHORT).show()
                activityViewModel.getListActivites(
                    GetActivitiesRequest(
                        RequestData = GetActivitiesRequestData(
                            "",
                            "11111111",
                            "",
                            ""
                        )
                    )
                )
                findNavController().navigateUp()
            }
        }
    }

    private fun saveEditActivity() {
        activityViewModel.saveEditActivity(
            SaveEditActivityRequest(
                RequestData = SaveEditActivityRequestData(
                    Seq = activitySeq,
                    NIK = binding.etNik.text.toString(),
                    ActDate = binding.etActDate.text.toString(),
                    TimeIn = binding.etWaktuMulai.text.toString().ifBlank { "00:00" },
                    TimeOut = binding.etWaktuSelesai.text.toString().ifBlank { "00:00" },
                    CategorySeq = categorySeq.toString(),
                    Activity = binding.etActivityName.text.toString(),
                    Deadline = binding.etDeadline.text.toString(),
                    Remark = binding.etRemark.text.toString(),
                    Status = "P",
                    SavedBy = binding.etNik.text.toString()
                )
            )
        )
    }

    private fun addActivity() {
        activityViewModel.addActivity(
            AddActivityRequest(
                RequestData = AddActivityRequestData(
                    NIK = binding.etNik.text.toString(),
                    ActDate = binding.etActDate.text.toString(),
                    TimeIn = binding.etWaktuMulai.text.toString().ifBlank { "00:00" },
                    TimeOut = binding.etWaktuSelesai.text.toString().ifBlank { "00:00" },
                    CategorySeq = categorySeq.toString(),
                    Activity = binding.etActivityName.text.toString(),
                    Deadline = binding.etDeadline.text.toString(),
                    Remark = binding.etRemark.text.toString(),
                    SavedBy = binding.etNik.text.toString()
                )
            )
        )
    }

    private fun initView() {
        setSpinnerCategoryAdapter()
        binding.buttonSimpan.setOnClickListener {
            if (args.label == "Add Activity") {
                addActivity()
            } else {
                saveEditActivity()
            }
        }
        binding.spinnerCategories.onItemSelectedListener = this
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val selected = parent?.getItemAtPosition(position) as String
        listDepartmentCategory.forEach { departmentCategory ->
            if (selected == departmentCategory.Category) {
                categorySeq = departmentCategory.Seq
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        //
    }

    private fun setSpinnerCategoryAdapter() {
        // set spinner category
        val categoryAdapter = object : ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_spinner_item,
            listCategory
        ) {
            override fun isEnabled(position: Int): Boolean {
                return position != 0
            }

            override fun getDropDownView(
                position: Int,
                convertView: View?,
                parent: ViewGroup
            ): View {
                // Get the item view
                val view = super.getDropDownView(
                    position, convertView, parent
                )
                val textView = view as TextView
                if (position == 0) {
                    // Set the hint text color gray
                    textView.setTextColor(Color.GRAY)
                } else {
                    textView.setTextColor(Color.BLACK)
                }
                return view
            }
        }
        binding.spinnerCategories.adapter = categoryAdapter
    }

}