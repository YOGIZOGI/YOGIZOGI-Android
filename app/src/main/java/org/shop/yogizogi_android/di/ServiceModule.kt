package org.shop.yogizogi_android.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.shop.yogizogi_android.data.api.SignUpService
import retrofit2.Retrofit
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
}