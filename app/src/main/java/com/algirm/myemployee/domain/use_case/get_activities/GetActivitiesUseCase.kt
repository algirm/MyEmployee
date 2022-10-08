package com.algirm.myemployee.domain.use_case.get_activities

import com.algirm.myemployee.common.Resource
import com.algirm.myemployee.domain.model.EmployeeActivity
import com.algirm.myemployee.domain.repository.ActivityRepository
import com.algirm.myemployee.domain.use_case.get_activity.GetActivityRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetActivitiesUseCase @Inject constructor(
    private val repository: ActivityRepository
) {
    operator fun invoke(requestBody: GetActivitiesRequest): Flow<Resource<List<EmployeeActivity>>> = flow {
        try {
            emit(Resource.Loading())
            val activities = repository.getActivities(requestBody)
            emit(Resource.Success(activities))
        } catch(e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}