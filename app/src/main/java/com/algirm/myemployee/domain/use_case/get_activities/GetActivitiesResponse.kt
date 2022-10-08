package com.algirm.myemployee.domain.use_case.get_activities

import com.algirm.myemployee.domain.model.EmployeeActivity

data class GetActivitiesResponse(
    val data : List<EmployeeActivity> = listOf(),
    val result: Int
)