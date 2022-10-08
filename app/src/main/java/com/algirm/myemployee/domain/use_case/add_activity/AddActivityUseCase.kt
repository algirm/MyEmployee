package com.algirm.myemployee.domain.use_case.add_activity

import com.algirm.myemployee.common.Resource
import com.algirm.myemployee.domain.repository.ActivityRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class AddActivityUseCase @Inject constructor(
    private val repository: ActivityRepository
) {
    operator fun invoke(requestBody: AddActivityRequest): Flow<Resource<AddActivityResponse>> = flow {
        try {
            emit(Resource.Loading())
            val response = repository.addActivity(requestBody)
            emit(Resource.Success(response))
        } catch(e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}