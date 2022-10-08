package com.algirm.myemployee.presentation.home

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.algirm.myemployee.common.AppConst.Companion.TAG
import com.algirm.myemployee.common.Resource
import com.algirm.myemployee.domain.model.DepartmentCategory
import com.algirm.myemployee.domain.model.EmployeeActivity
import com.algirm.myemployee.domain.use_case.add_activity.AddActivityRequest
import com.algirm.myemployee.domain.use_case.add_activity.AddActivityUseCase
import com.algirm.myemployee.domain.use_case.get_activities.GetActivitiesRequest
import com.algirm.myemployee.domain.use_case.get_activities.GetActivitiesRequestData
import com.algirm.myemployee.domain.use_case.get_activities.GetActivitiesUseCase
import com.algirm.myemployee.domain.use_case.get_activity.GetActivityRequest
import com.algirm.myemployee.domain.use_case.get_activity.GetActivityUseCase
import com.algirm.myemployee.domain.use_case.get_categories.GetCategoriesRequest
import com.algirm.myemployee.domain.use_case.get_categories.GetCategoriesRequestData
import com.algirm.myemployee.domain.use_case.get_categories.GetCategoriesUseCase
import com.algirm.myemployee.domain.use_case.save_edit_activity.SaveEditActivityRequest
import com.algirm.myemployee.domain.use_case.save_edit_activity.SaveEditActivityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class ActivityViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getActivitiesUseCase: GetActivitiesUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val addActivityUseCase: AddActivityUseCase,
    private val getActivityUseCase: GetActivityUseCase,
    private val saveEditActivityUseCase: SaveEditActivityUseCase,
) : ViewModel() {

    val listActivities = savedStateHandle.getStateFlow("listActivities", listOf<EmployeeActivity>())
    val categories = savedStateHandle.getStateFlow("categories", listOf<DepartmentCategory>())
    private val _resultAddActivity = Channel<String?>()
    val resultAddActivity = _resultAddActivity.receiveAsFlow()
    val selectedActivity = savedStateHandle.getStateFlow<EmployeeActivity?>("selectedActivity", null)
    private val _resultSaveEditActivity = Channel<String?>()
    val resultSaveEditActivity = _resultSaveEditActivity.receiveAsFlow()

    init {
        getListActivites(
            GetActivitiesRequest(
                RequestData = GetActivitiesRequestData(
                    "",
                    "11111111",
                    "",
                    ""
                )
            )
        )
        getCategories(GetCategoriesRequest(RequestData = GetCategoriesRequestData()))
    }

    fun getListActivites(requestBody: GetActivitiesRequest) {
        getActivitiesUseCase(requestBody).onEach { result ->
            Log.d(TAG, "getListActivites: $result ")
            when (result) {
                is Resource.Loading -> {}
                is Resource.Success -> {
                    savedStateHandle["listActivities"] = result.data
                }
                is Resource.Error -> {}
            }
        }.launchIn(viewModelScope)
    }

    fun getCategories(requestBody: GetCategoriesRequest) {
        getCategoriesUseCase(requestBody).onEach { result ->
            when (result) {
                is Resource.Loading -> {}
                is Resource.Success -> {
                    savedStateHandle["categories"] = result.data
                }
                is Resource.Error -> {}
            }
        }.launchIn(viewModelScope)
    }

    fun addActivity(requestBody: AddActivityRequest) {
        addActivityUseCase(requestBody).onEach { result ->
            when (result) {
                is Resource.Loading -> {}
                is Resource.Success -> {
                    _resultSaveEditActivity.send(result.data?.message)
                }
                is Resource.Error -> {}
            }
        }.launchIn(viewModelScope)
    }

    fun getActivity(requestBody: GetActivityRequest) {
        getActivityUseCase(requestBody).onEach { result ->
            when (result) {
                is Resource.Loading -> {}
                is Resource.Success -> {
                    savedStateHandle["selectedActivity"] = result.data
                }
                is Resource.Error -> {}
            }
        }.launchIn(viewModelScope)
    }

    fun saveEditActivity(requestBody: SaveEditActivityRequest) {
        saveEditActivityUseCase(requestBody).onEach { result ->
            when (result) {
                is Resource.Loading -> {}
                is Resource.Success -> {
                    _resultSaveEditActivity.send(result.data?.message)
                }
                is Resource.Error -> {}
            }
        }.launchIn(viewModelScope)
    }

}