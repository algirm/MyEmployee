package com.algirm.myemployee.domain.use_case.insert_attendance

import com.algirm.myemployee.common.Resource
import com.algirm.myemployee.domain.model.Attendance
import com.algirm.myemployee.domain.repository.AttendanceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class InsertAttendanceUseCase @Inject constructor(
    private val repository: AttendanceRepository
) {
    operator fun invoke(attendance: Attendance): Flow<Resource<Long>> = flow {
        try {
            emit(Resource.Loading())
            val result = repository.insertAttendance(attendance)
            emit(Resource.Success(result))
        } catch(e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}