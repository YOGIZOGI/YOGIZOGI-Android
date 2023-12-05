package org.shop.yogizogi_android.di

import android.content.Context
import android.util.Log
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.shop.yogizogi_android.data.model.UserPreference
import org.shop.yogizogi_android.di.network.Network
import org.shop.yogizogi_android.di.network.NetworkConnectivity
import org.shop.yogizogi_android.repository.UserDataStore
import org.shop.yogizogi_android.repository.UserRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Qualifier
import javax.inject.Singleton
import kotlin.coroutines.coroutineContext

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Auth

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    @Provides
    @Singleton
    fun provideNetworkConnectivity(@ApplicationContext context: Context): NetworkConnectivity {
        return Network(context)
    }

    @Provides
    @Singleton
    fun providesConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create(
            GsonBuilder()
                .setLenient()
                .create()
        )
    }

    @Provides
    @Singleton
    fun providesHttpLogger(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return loggingInterceptor
    }

    @Provides
    @Singleton
    fun providesHeaderInterceptor(): Interceptor {
        return Interceptor { chain ->
            val original = chain.request()
            val request = original.newBuilder()
                .header(CONTENT_TYPE, CONTENT_TYPE_VALUE)
                .method(original.method, original.body)
                .build()

            chain.proceed(request)
        }
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(
        logger: HttpLoggingInterceptor,
        headerInterceptor: Interceptor,
    ): OkHttpClient.Builder {
        return OkHttpClient.Builder().apply {
            addInterceptor(headerInterceptor)
            addInterceptor(logger)
            connectTimeout(TIMEOUT_CONNECT, TimeUnit.SECONDS)
            readTimeout(TIMEOUT_READ, TimeUnit.SECONDS)
            writeTimeout(TIMEOUT_WRITE, TimeUnit.SECONDS)
        }
    }

    @Provides
    @Singleton
    fun providesRetrofit(
        client: OkHttpClient.Builder,
        gsonConverterFactory: GsonConverterFactory,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client.build())
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

//    @Provides
//    @Singleton
//    @Auth
//    suspend fun provideAuthHeaderInterceptor(userRepository: UserRepository): Interceptor {
//        var accessToken=""
//        CoroutineScope(Dispatchers.Main).launch {
//            CoroutineScope(Dispatchers.IO).async {
//                accessToken = userRepository.getUserData().first().accessToken
//            }.await()
//        }
//        Log.d("RetrofitModule", accessToken)
//
//        return Interceptor { chain ->
////            val accessToken = runBlocking(Dispatchers.IO) {
////                kotlin.runCatching {
////                    userRepository.getUserData().first()
////                }.getOrDefault(UserPreference.getDefaultInstance())
////            }
//
//            val original = chain.request()
//            val request = original.newBuilder()
//                .header(CONTENT_TYPE, CONTENT_TYPE_VALUE)
//                .header(ACCESS_TOKEN, accessToken)
//                .method(original.method, original.body)
//                .build()
//
//            chain.proceed(request)
//        }
//    }
//
//    @Provides
//    @Singleton
//    @Auth
//    fun providesAuthOkHttpClient(
//        logger: HttpLoggingInterceptor,
//        @Auth headerInterceptor: Interceptor,
//    ): OkHttpClient.Builder {
//        return OkHttpClient.Builder().apply {
//            addInterceptor(headerInterceptor)
//            addInterceptor(logger)
//            connectTimeout(TIMEOUT_CONNECT, TimeUnit.SECONDS)
//            readTimeout(TIMEOUT_READ, TimeUnit.SECONDS)
//            writeTimeout(TIMEOUT_WRITE, TimeUnit.SECONDS)
//        }
//    }
//
//    @Provides
//    @Singleton
//    @Auth
//    fun providesAuthRetrofit(
//        @Auth client: OkHttpClient.Builder,
//        gsonConverterFactory: GsonConverterFactory,
//    ): Retrofit {
//        return Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .client(client.build())
//            .addConverterFactory(gsonConverterFactory)
//            .build()
//    }
}