package com.algirm.myemployee.data.repository

import com.algirm.myemployee.data.local.AttendanceDao
import com.algirm.myemployee.domain.model.Attendance
import com.algirm.myemployee.domain.repository.AttendanceRepository
import javax.inject.Inject

class AttendanceRepositoryImpl @Inject constructor(
    private val attendanceDao: AttendanceDao
) : AttendanceRepository {

    override suspend fun getAttendances(): List<Attendance> = attendanceDao.getAttendances()

    override suspend fun insertAttendance(attendance: Attendance): Long =
        attendanceDao.insertAttendance(attendance)
}