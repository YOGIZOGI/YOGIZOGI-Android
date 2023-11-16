package org.shop.yogizogi_android.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.shop.yogizogi_android.repository.AuthRepository
import org.shop.yogizogi_android.repository.AuthRepositoryImpl
import org.shop.yogizogi_android.repository.SignUpRepository
import org.shop.yogizogi_android.repository.SignUpRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun provideAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository

    @Binds
    fun provideSignUpRepository(signUpRepositoryImpl: SignUpRepositoryImpl): SignUpRepository
}