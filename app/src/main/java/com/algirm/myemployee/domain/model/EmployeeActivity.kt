package com.algirm.myemployee.domain.model

data class EmployeeActivity(
    val ActDate: String,
    val Activity: String,
    val Category: String,
    val CreatedDate: String,
    val Deadline: String,
    val Department: String,
    val KdDept: String,
    val NIK: String,
    val NamaHead: String,
    val NamaKaryawan: String,
    val Remark: String,
    val Seq: String,
    val Status: String,
    val TimeIn: String,
    val TimeOut: String
) : java.io.Serializable