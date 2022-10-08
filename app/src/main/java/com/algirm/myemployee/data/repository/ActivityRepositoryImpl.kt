package com.algirm.myemployee.data.repository

import com.algirm.myemployee.data.remote.MyApi
import com.algirm.myemployee.domain.model.DepartmentCategory
import com.algirm.myemployee.domain.model.EmployeeActivity
import com.algirm.myemployee.domain.repository.ActivityRepository
import com.algirm.myemployee.domain.use_case.add_activity.AddActivityRequest
import com.algirm.myemployee.domain.use_case.add_activity.AddActivityResponse
import com.algirm.myemployee.domain.use_case.get_activities.GetActivitiesRequest
import com.algirm.myemployee.domain.use_case.get_activity.GetActivityRequest
import com.algirm.myemployee.domain.use_case.get_categories.GetCategoriesRequest
import com.algirm.myemployee.domain.use_case.save_edit_activity.SaveEditActivityRequest
import com.algirm.myemployee.domain.use_case.save_edit_activity.SaveEditActivityResponse
import javax.inject.Inject

class ActivityRepositoryImpl @Inject constructor(
    private val api: MyApi
) : ActivityRepository {

    override suspend fun getActivities(requestBody: GetActivitiesRequest): List<EmployeeActivity> {
        return api.listActivity(requestBody).body()!!.data
    }

    override suspend fun getCategories(requestBody: GetCategoriesRequest): List<DepartmentCategory> {
        return api.getCategories(requestBody).body()!!.data
    }

    override suspend fun addActivity(requestBody: AddActivityRequest): AddActivityResponse {
        return api.addActivity(requestBody).body()!!
    }

    override suspend fun getActivity(requestBody: GetActivityRequest): EmployeeActivity {
        return api.getActivity(requestBody).body()!!.data[0]
    }

    override suspend fun saveEditActivity(requestBody: SaveEditActivityRequest): SaveEditActivityResponse {
        return api.saveEditActivity(requestBody).body()!!
    }
}