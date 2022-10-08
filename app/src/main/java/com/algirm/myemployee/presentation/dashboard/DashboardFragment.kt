package com.algirm.myemployee.presentation.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.algirm.myemployee.common.AppConst.Companion.TAG
import com.algirm.myemployee.databinding.FragmentDashboardBinding
import com.algirm.myemployee.domain.model.Attendance
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val attendanceViewModel: AttendanceViewModel by activityViewModels()

    private lateinit var attendanceAdapter: AttendanceAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root
        initView()
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenResumed {
            attendanceViewModel.attendances.collect{ listAttendance ->
                Log.d(TAG, "test absen: $listAttendance")
                attendanceAdapter.differ.submitList(listAttendance)
            }
        }
    }

    private fun initView() {
        attendanceAdapter = AttendanceAdapter(requireContext())
        binding.rcvAttendances.apply {
            adapter = attendanceAdapter
            layoutManager = LinearLayoutManager(activity)
        }
        binding.buttonAbsenMasuk.setOnClickListener {
            val formatterTanggal = SimpleDateFormat("yyyy-MM-dd", Locale.ROOT)
            val formatterJam = SimpleDateFormat("HH:mm", Locale.ROOT)
            val textTanggal = formatterTanggal.format(Date())
            val textJamMasuk = formatterJam.format(Date())
            attendanceViewModel.insertAttendance(
                Attendance(
                    dateAdded = Date(),
                    NIK = "11111111",
                    TanggalKehadiran = textTanggal,
                    JamMasuk = textJamMasuk
                )
            )

            attendanceViewModel.getAttendances()
        }
        binding.buttonAbsenPulang.setOnClickListener {

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}