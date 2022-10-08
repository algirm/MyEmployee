package com.algirm.myemployee.di

import android.content.Context
import androidx.room.Room
import com.algirm.myemployee.data.local.AttendanceDao
import com.algirm.myemployee.data.local.AttendanceDatabase
import com.algirm.myemployee.data.remote.MyApi
import com.algirm.myemployee.data.repository.ActivityRepositoryImpl
import com.algirm.myemployee.data.repository.AttendanceRepositoryImpl
import com.algirm.myemployee.domain.repository.ActivityRepository
import com.algirm.myemployee.domain.repository.AttendanceRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideActivityRepository(api: MyApi): ActivityRepository {
        return ActivityRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideAttendanceRepository(attendanceDao: AttendanceDao): AttendanceRepository {
        return AttendanceRepositoryImpl(attendanceDao)
    }

    @Singleton
    @Provides
    fun provideAttendanceDao(attendanceDatabase: AttendanceDatabase): AttendanceDao {
        return attendanceDatabase.getAttendanceDao()
    }

    @Singleton
    @Provides
    fun provideArticleDatabase(
        @ApplicationContext context: Context
    ): AttendanceDatabase {
        return Room.databaseBuilder(
            context,
            AttendanceDatabase::class.java,
            "attendance_db"
        ).build()
    }

}