package org.shop.yogizogi_android.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.shop.yogizogi_android.repository.AuthRepository
import org.shop.yogizogi_android.repository.AuthRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun provideAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository
}