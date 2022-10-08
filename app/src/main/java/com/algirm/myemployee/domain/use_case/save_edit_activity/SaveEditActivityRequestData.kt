package com.algirm.myemployee.domain.use_case.save_edit_activity

import com.google.gson.annotations.SerializedName

data class SaveEditActivityRequestData(
    @SerializedName("Seq") val Seq: String,
    @SerializedName("NIK") val NIK: String,
    @SerializedName("KdDept") var KdDept: String = "04",
    @SerializedName("ActDate") val ActDate: String,
    @SerializedName("TimeIn") val TimeIn: String,
    @SerializedName("TimeOut") val TimeOut: String,
    @SerializedName("CategorySeq") val CategorySeq: String,
    @SerializedName("Activity") val Activity: String,
    @SerializedName("Deadline") val Deadline: String,
    @SerializedName("Remark") val Remark: String,
    @SerializedName("Status") val Status: String,
    @SerializedName("SavedBy") val SavedBy: String
)