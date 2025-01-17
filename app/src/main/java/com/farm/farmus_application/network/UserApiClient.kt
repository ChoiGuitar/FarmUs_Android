package com.farm.farmus_application.network

import com.farm.farmus_application.model.user.*
import com.farm.farmus_application.model.user.findaccount.FindAccountRes
import com.farm.farmus_application.model.user.findpassword.FindPasswordRes
import com.farm.farmus_application.model.user.likes.DeleteLikeFarmRes
import com.farm.farmus_application.model.user.likes.LikeFarmReq
import com.farm.farmus_application.model.user.likes.LikeFarmRes
import com.farm.farmus_application.model.user.login.LoginReq
import com.farm.farmus_application.model.user.login.LoginRes
import com.farm.farmus_application.model.user.signup.SignUpReq
import com.farm.farmus_application.model.user.signup.SignUpRes
import com.farm.farmus_application.model.user.signup_verification.SignUpVerificationReq
import com.farm.farmus_application.model.user.signup_verification.SignUpVerificationRes
import com.farm.farmus_application.model.user.verification.VerificationReq
import com.farm.farmus_application.model.user.verification.VerificationRes
import com.farm.farmus_application.model.user.withdrawal.WithdrawalRes
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface UserApiClient {
    @POST("/user/signup")
    suspend fun postSignUp(@Body params: SignUpReq): Response<SignUpRes>

    @POST("/user/login")
    suspend fun postLogin(@Body params: LoginReq): Response<LoginRes>

    @GET("/user/email/verification/{email}")
    suspend fun getUserEmailVerification(@Path(value = "email") params: String) : Response<LoginRes>

    @POST("/user/signup/verification")
    suspend fun postUserSignupVerification(@Body params: SignUpVerificationReq): Response<SignUpVerificationRes>

    @POST("/user/verification")
    suspend fun postUserVerification(@Body params: VerificationReq): Response<VerificationRes>

    @GET("/user/find-account")
    suspend fun getUserFindAccount(
        @Query("name") name: String,
        @Query("phoneNumber") phoneNumber: String
    ): Response<FindAccountRes>

    @GET("/user/find-password")
    suspend fun getUserFindPassword(@Query("userEmail") userEmail: String): Response<FindPasswordRes>

    @DELETE("/user/withdrawal")
    suspend fun deleteUserWithdrawal(): Response<WithdrawalRes>


    @POST("/user/likes")
    suspend fun postUserLikeFarm(@Body params : LikeFarmReq) : Response<LikeFarmRes>

    // TODO : DELETE 함수는 안드로이드 상에서 Body로 전달 받을 수 없음, 쿼리로 전달 받아야함.
    @DELETE("/user/likes")
    suspend fun deleteUserLikeFarm(@Query("email") email : String, @Query("farmid") farmid : Int) : Response<DeleteLikeFarmRes>
    // 농장 찜하기. 생일 수정 API는 추가 예정
}
