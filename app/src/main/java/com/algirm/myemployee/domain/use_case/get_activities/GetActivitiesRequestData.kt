package com.algirm.myemployee.domain.use_case.get_activities

import com.google.gson.annotations.SerializedName

data class GetActivitiesRequestData(
    @SerializedName("FromDate") val FromDate: String,
    @SerializedName("NIK") val NIK: String,
    @SerializedName("Status") val Status: String,
    @SerializedName("ToDate") val ToDate: String
)