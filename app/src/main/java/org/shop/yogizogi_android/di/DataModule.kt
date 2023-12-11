package org.shop.yogizogi_android.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.shop.yogizogi_android.repository.AuthRepository
import org.shop.yogizogi_android.repository.AuthRepositoryImpl
import org.shop.yogizogi_android.repository.StoreRepository
import org.shop.yogizogi_android.repository.StoreRepositoryImpl
import org.shop.yogizogi_android.repository.UserRepository
import org.shop.yogizogi_android.repository.UserRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun provideAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository

    @Binds
    fun provideUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository

    @Binds
    fun provideStoreRepository(storeRepositoryImpl: StoreRepositoryImpl): StoreRepository
}