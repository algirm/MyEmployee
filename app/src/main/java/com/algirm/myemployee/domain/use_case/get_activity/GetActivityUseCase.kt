package com.algirm.myemployee.domain.use_case.get_activity

import com.algirm.myemployee.common.Resource
import com.algirm.myemployee.domain.model.EmployeeActivity
import com.algirm.myemployee.domain.repository.ActivityRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetActivityUseCase @Inject constructor(
    private val repository: ActivityRepository
) {
    operator fun invoke(requestBody: GetActivityRequest): Flow<Resource<EmployeeActivity>> = flow {
        try {
            emit(Resource.Loading())
            val activity = repository.getActivity(requestBody)
            emit(Resource.Success(activity))
        } catch(e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}