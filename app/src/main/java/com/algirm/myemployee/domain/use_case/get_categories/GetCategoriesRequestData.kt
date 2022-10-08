package com.algirm.myemployee.domain.use_case.get_categories

import com.google.gson.annotations.SerializedName

data class GetCategoriesRequestData(
    @SerializedName("Type") var Type: String = "Dropdown",
    @SerializedName("TxtSearch") var TxtSearch: String = "Category",
    @SerializedName("KdDept") var KdDept: String = "04"
)