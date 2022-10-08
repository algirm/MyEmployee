package com.algirm.myemployee.domain.use_case.add_activity

import com.google.gson.annotations.SerializedName

data class AddActivityRequestData(
    @SerializedName("NIK") val NIK: String,
    @SerializedName("KdDept") var KdDept: String = "04",
    @SerializedName("ActDate") val ActDate: String,
    @SerializedName("TimeIn") val TimeIn: String,
    @SerializedName("TimeOut") val TimeOut: String,
    @SerializedName("CategorySeq") val CategorySeq: String,
    @SerializedName("Activity") val Activity: String,
    @SerializedName("Deadline") val Deadline: String,
    @SerializedName("Remark") val Remark: String,
    @SerializedName("SavedBy") val SavedBy: String
)