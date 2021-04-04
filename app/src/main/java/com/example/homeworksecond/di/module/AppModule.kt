package com.example.homeworksecond.di.module

import com.example.homeworksecond.data.HomeFragmentRepository
import com.example.homeworksecond.data.HomeFragmentRepositoryImpl
import com.example.homeworksecond.datasource.LocalDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun provideHomeFragmentRepository(
        homeFragmentRepositoryImpl: HomeFragmentRepositoryImpl
    ):HomeFragmentRepository

}