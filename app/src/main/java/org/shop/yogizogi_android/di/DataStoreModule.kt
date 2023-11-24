package org.shop.yogizogi_android.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.shop.yogizogi_android.data.model.UserPreference
import org.shop.yogizogi_android.datastore.UserPreferenceSerializer
import java.io.File
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DataStoreModule {
    @Provides
    @Singleton
    fun providesUserPreferenceDataStore(@ApplicationContext context: Context): DataStore<UserPreference> {
        return DataStoreFactory.create(serializer = UserPreferenceSerializer()){
            File("${context.cacheDir.path}/$PATH_PB_USER")
        }
    }

    companion object{
        private const val PATH_PB_USER = "yogizogi_user.preference_pb"
    }
}