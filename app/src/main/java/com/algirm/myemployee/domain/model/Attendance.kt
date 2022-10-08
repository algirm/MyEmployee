package com.algirm.myemployee.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(
    tableName = "attendances"
)
data class Attendance(
    @PrimaryKey
    val dateAdded: Date,
    val NIK: String,
    var TanggalKehadiran: String,
    var JamMasuk: String? = null,
    var JamPulang: String? = null
    )