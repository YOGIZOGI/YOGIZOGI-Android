package org.shop.yogizogi_android.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.shop.yogizogi_android.data.api.AuthService
import org.shop.yogizogi_android.data.api.ReviewService
import org.shop.yogizogi_android.data.api.SignUpService
import org.shop.yogizogi_android.data.api.StoreService
import org.shop.yogizogi_android.data.api.UserService
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Provides
    @Singleton
    fun providesSignUpService(
        retrofit: Retrofit
    ): SignUpService {
        return retrofit.create(SignUpService::class.java)
    }

    @Provides
    @Singleton
    fun providesAuthService(
        retrofit: Retrofit
    ): AuthService {
        return retrofit.create(AuthService::class.java)
    }

    @Provides
    @Singleton
    fun provideUserService(
        retrofit: Retrofit
    ): UserService {
        return retrofit.create(UserService::class.java)
    }

    @Provides
    @Singleton
    fun provideReviewService(
        retrofit: Retrofit
    ): ReviewService {
        return retrofit.create(ReviewService::class.java)
    }

    @Provides
    @Singleton
    fun provideStoreService(
        retrofit: Retrofit
    ): StoreService {
        return retrofit.create(StoreService::class.java)
    }
}