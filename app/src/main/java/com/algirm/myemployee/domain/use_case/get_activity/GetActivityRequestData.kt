package com.algirm.myemployee.domain.use_case.get_activity

import com.google.gson.annotations.SerializedName

data class GetActivityRequestData(
    @SerializedName("Seq") val Seq: String,
    @SerializedName("NIK") var NIK: String = "11111111",
    @SerializedName("KdDept") var KdDept: String = "04"
)