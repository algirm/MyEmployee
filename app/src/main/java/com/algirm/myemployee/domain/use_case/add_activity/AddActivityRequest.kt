package com.algirm.myemployee.domain.use_case.add_activity

import com.google.gson.annotations.SerializedName

data class AddActivityRequest(
    @SerializedName("GroupCD") var GroupCD: String? = "adm",
    @SerializedName("Password") var Password: String? = "MmMyZjZmZTgyNzc0MjNlNGQyZTNkMzk0ZmU0MDhiMjM=",
    @SerializedName("RequestData") val RequestData: AddActivityRequestData,
    @SerializedName("Token") var Token: String? = "Y2Q3MDFhODZiYWQwMDE0OGM1MTM1ZWM2MTA2YjEyMTYxNGNjZmE2ODVkODQyMzExM2UwNzk3ZWZhZTFhMzA5Mg==",
    @SerializedName("UserID") var UserID: String? = "intranet"
)