package com.algirm.myemployee.domain.use_case.get_categories

import com.algirm.myemployee.domain.model.DepartmentCategory

data class GetCategoriesResponse(
    val data : List<DepartmentCategory> = listOf(),
    val result: Int
)