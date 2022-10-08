package com.algirm.myemployee.domain.use_case.get_categories

import com.algirm.myemployee.common.Resource
import com.algirm.myemployee.domain.model.DepartmentCategory
import com.algirm.myemployee.domain.repository.ActivityRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val repository: ActivityRepository
) {
    operator fun invoke(requestBody: GetCategoriesRequest): Flow<Resource<List<DepartmentCategory>>> = flow {
        try {
            emit(Resource.Loading())
            val categories = repository.getCategories(requestBody)
            emit(Resource.Success(categories))
        } catch(e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}