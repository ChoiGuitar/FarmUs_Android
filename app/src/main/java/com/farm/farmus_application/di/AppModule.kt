package com.farm.farmus_application.di

import com.example.farmus_application.network.*
import com.farm.farmus_application.network.FarmApiClient
import com.farm.farmus_application.network.MyPageApiClient
import com.farm.farmus_application.network.ReserveApiClient
import com.farm.farmus_application.network.UserApiClient
import com.farm.farmus_application.repository.UserPrefsStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    
    private const val baseUrl = "http://118.67.135.247:80/"
    private val jwtToken = UserPrefsStorage.accessToken ?: ""

    @Provides
    @Singleton
    fun provideAppInterceptor(): Interceptor {
        return Interceptor {
            val newRequest = it.request().newBuilder()
                .addHeader("token", jwtToken)
                .build()
            it.proceed(newRequest)
        }
    }

    //Retrofit
    @Provides
    @Singleton
    fun provideOkHttpClient(interceptor: Interceptor): OkHttpClient {
        val logger = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }
        return OkHttpClient.Builder()
            .addInterceptor(logger)
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .build()
    }

    @Provides
    @Singleton
    fun provideUserApiClient(retrofit: Retrofit) : UserApiClient {
        return retrofit.create(UserApiClient::class.java)
    }

    // Repository를 Module로 만들어줘야 하는 이유가 없는거같아서 일단 주석처리
//    @Provides
//    @Singleton
//    fun provideUserRepository(userApiClient: UserApiClient) : UserRepository {
//        return UserRepository(userApiClient)
//    }

    @Provides
    @Singleton
    fun provideFarmApiClient(retrofit: Retrofit) : FarmApiClient {
        return retrofit.create(FarmApiClient::class.java)
    }
//
//    @Provides
//    @Singleton
//    fun provideFarmRepository(farmApiClient: FarmApiClient) : FarmRepository {
//        return FarmRepository(farmApiClient)
//    }
//
    @Provides
    @Singleton
    fun provideMyPageApiClient(retrofit: Retrofit) : MyPageApiClient {
        return retrofit.create(MyPageApiClient::class.java)
    }
//
//    @Provides
//    @Singleton
//    fun provideMyPageRepository(myPageApiClient: MyPageApiClient) : MyPageRepository {
//        return MyPageRepository(myPageApiClient)
//    }
//
    @Provides
    @Singleton
    fun provideReserveApiClient(retrofit: Retrofit) : ReserveApiClient {
        return retrofit.create(ReserveApiClient::class.java)
    }
//
//    @Provides
//    @Singleton
//    fun provideReserveRepository(reserveApiClient: ReserveApiClient) : ReserveRepository {
//        return ReserveRepository(reserveApiClient)
//    }

}
