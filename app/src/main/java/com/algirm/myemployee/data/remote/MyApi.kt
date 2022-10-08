package com.algirm.myemployee.data.remote

import com.algirm.myemployee.domain.use_case.add_activity.AddActivityRequest
import com.algirm.myemployee.domain.use_case.add_activity.AddActivityResponse
import com.algirm.myemployee.domain.use_case.get_activities.GetActivitiesRequest
import com.algirm.myemployee.domain.use_case.get_activities.GetActivitiesResponse
import com.algirm.myemployee.domain.use_case.get_activity.GetActivityResponse
import com.algirm.myemployee.domain.use_case.get_activity.GetActivityRequest
import com.algirm.myemployee.domain.use_case.get_categories.GetCategoriesRequest
import com.algirm.myemployee.domain.use_case.get_categories.GetCategoriesResponse
import com.algirm.myemployee.domain.use_case.save_edit_activity.SaveEditActivityRequest
import com.algirm.myemployee.domain.use_case.save_edit_activity.SaveEditActivityResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface MyApi {

    @Headers("Content-Type: application/json")
    @POST("activity?act=act_s")
    suspend fun listActivity(
        @Body requestBody: GetActivitiesRequest
    ): Response<GetActivitiesResponse>

    @Headers("Content-Type: application/json")
    @POST("global?act=act_e")
    suspend fun getCategories(
        @Body requestBody: GetCategoriesRequest
    ): Response<GetCategoriesResponse>

    @Headers("Content-Type: application/json")
    @POST("activity?act=act_s_add")
    suspend fun addActivity(
        @Body requestBody: AddActivityRequest
    ): Response<AddActivityResponse>

    @Headers("Content-Type: application/json")
    @POST("activity?act=act_e")
    suspend fun getActivity(
        @Body requestBody: GetActivityRequest
    ): Response<GetActivityResponse>

    @Headers("Content-Type: application/json")
    @POST("activity?act=act_s_edt")
    suspend fun saveEditActivity(
        @Body requestBody: SaveEditActivityRequest
    ): Response<SaveEditActivityResponse>

}