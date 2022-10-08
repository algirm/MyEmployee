package com.algirm.myemployee.domain.repository

import com.algirm.myemployee.domain.model.DepartmentCategory
import com.algirm.myemployee.domain.model.EmployeeActivity
import com.algirm.myemployee.domain.use_case.add_activity.AddActivityRequest
import com.algirm.myemployee.domain.use_case.add_activity.AddActivityResponse
import com.algirm.myemployee.domain.use_case.get_activities.GetActivitiesRequest
import com.algirm.myemployee.domain.use_case.get_activity.GetActivityRequest
import com.algirm.myemployee.domain.use_case.get_categories.GetCategoriesRequest
import com.algirm.myemployee.domain.use_case.save_edit_activity.SaveEditActivityRequest
import com.algirm.myemployee.domain.use_case.save_edit_activity.SaveEditActivityResponse

interface ActivityRepository {

    suspend fun getActivities(requestBody: GetActivitiesRequest): List<EmployeeActivity>

    suspend fun getCategories(requestBody: GetCategoriesRequest): List<DepartmentCategory>

    suspend fun addActivity(requestBody: AddActivityRequest): AddActivityResponse

    suspend fun getActivity(requestBody: GetActivityRequest): EmployeeActivity

    suspend fun saveEditActivity(requestBody: SaveEditActivityRequest): SaveEditActivityResponse

}