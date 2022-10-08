package com.algirm.myemployee.data.local

import androidx.room.*
import com.algirm.myemployee.domain.model.Attendance

@Dao
interface AttendanceDao {

    @Query("SELECT * FROM attendances")
    suspend fun getAttendances(): List<Attendance>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAttendance(attendance: Attendance): Long

    @Update
    suspend fun updateAttendance(attendance: Attendance)

}