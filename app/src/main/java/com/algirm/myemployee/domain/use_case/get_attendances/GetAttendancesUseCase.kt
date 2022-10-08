package com.algirm.myemployee.domain.use_case.get_attendances

import android.util.Log
import com.algirm.myemployee.common.AppConst
import com.algirm.myemployee.common.AppConst.Companion.TAG
import com.algirm.myemployee.common.Resource
import com.algirm.myemployee.domain.model.Attendance
import com.algirm.myemployee.domain.repository.AttendanceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetAttendancesUseCase @Inject constructor(
    private val repository: AttendanceRepository
) {
    operator fun invoke(): Flow<Resource<List<Attendance>>> = flow {
        try {
            Log.d(AppConst.TAG, "test absen: usecase get")
            emit(Resource.Loading())
            val attendances = repository.getAttendances()
            emit(Resource.Success(attendances))
        } catch(e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        } catch (e: java.lang.Exception) {
            Log.e(TAG, "invoke usecase get error: ", e.cause)
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        }
    }
}