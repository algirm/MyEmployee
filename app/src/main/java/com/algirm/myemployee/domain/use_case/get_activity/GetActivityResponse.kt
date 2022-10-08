package com.algirm.myemployee.domain.use_case.get_activity

import com.algirm.myemployee.domain.model.EmployeeActivity

data class GetActivityResponse(
    val data : List<EmployeeActivity> = listOf(),
    val result: Int,
    val message: String
)