package com.algirm.myemployee.presentation.dashboard

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.algirm.myemployee.common.AppConst
import com.algirm.myemployee.common.AppConst.Companion.TAG
import com.algirm.myemployee.common.Resource
import com.algirm.myemployee.domain.model.Attendance
import com.algirm.myemployee.domain.use_case.get_attendances.GetAttendancesUseCase
import com.algirm.myemployee.domain.use_case.insert_attendance.InsertAttendanceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AttendanceViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getAttendancesUseCase: GetAttendancesUseCase,
    private val insertAttendanceUseCase: InsertAttendanceUseCase
) : ViewModel() {

    val attendances = savedStateHandle.getStateFlow("attendances", listOf<Attendance>())

    init {
        getAttendances()
    }

    fun getAttendances() {
        Log.d(TAG, "getAttendances: viewmodel")
        viewModelScope.launch {
            val r =
            getAttendancesUseCase().collect { result ->
                when (result) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        savedStateHandle["attendances"] = result.data
                    }
                    is Resource.Error -> {}
                }
            }
            Log.d(TAG, "getAttendances: $r")
        }
    }

    fun insertAttendance(attendance: Attendance) {
        viewModelScope.launch {
            insertAttendanceUseCase(attendance).onEach { result ->
                Log.d(AppConst.TAG, "test absen: vm insert")
                when (result) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        result.data
                    }
                    is Resource.Error -> {}
                }
            }.launchIn(this)
//            }.launchIn(viewModelScope)
        }
    }

    fun updateAttendance(attendance: Attendance) {

    }

}