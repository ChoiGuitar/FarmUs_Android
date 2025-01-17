package com.farm.farmus_application.network

import com.farm.farmus_application.model.mypage.*
import com.farm.farmus_application.model.user.*
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.Part
import retrofit2.http.Query

interface MyPageApiClient {
    @PATCH("/mypage/editInfo/nickname")
    suspend fun patchEditInfoNickname(
        @Query("email") email: String,
        @Body params: EditInfoNicknameReq
    ): Response<MyPageNickNameRes>

    @PATCH("/mypage/editInfo/name")
    suspend fun patchEditInfoName(
        @Query("email") email: String,
        @Body params: EditInfoNameReq
    ): Response<MyPageNameRes>

    @PATCH("/mypage/editInfo/password")
    suspend fun patchEditInfoPassword(
        @Query("email") email: String,
        @Body params: EditInfoPasswordReq
    ): Response<MyPagePasswordRes>

    @PATCH("/mypage/editInfo/phoneNumber")
    suspend fun patchEditInfoPhoneNumber(
        @Query("email") email: String,
        @Body params: EditInfoPhoneNumberReq
    ): Response<MyPagePhoneNumberRes>

    @Multipart
    @PATCH("/mypage/editInfo/profileImg")
    suspend fun patchEditInfoProfileImg(
        @Query("email") email: String,
        @Part file: MultipartBody.Part
    ): Response<MyPageProfileImageRes>

}
