package com.algirm.myemployee.domain.repository

import com.algirm.myemployee.data.local.AttendanceDao
import com.algirm.myemployee.domain.model.Attendance

interface AttendanceRepository {

    suspend fun getAttendances(): List<Attendance>

    suspend fun insertAttendance(attendance: Attendance): Long

}