package com.algirm.myemployee.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.algirm.myemployee.domain.model.Attendance

@Database(
    entities = [Attendance::class],
    version = 1
)
@androidx.room.TypeConverters(TypeConverters::class)
abstract class AttendanceDatabase : RoomDatabase() {

    abstract fun getAttendanceDao(): AttendanceDao
}