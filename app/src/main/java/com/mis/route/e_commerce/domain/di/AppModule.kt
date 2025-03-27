package com.mis.route.e_commerce.domain.di

import com.mis.route.e_commerce.data.repositories.auth_repository.AuthRepositoryImpl
import com.mis.route.e_commerce.data.repositories.auth_repository.datasources.auth_remote_data_source.AuthRemoteDataSource
import com.mis.route.e_commerce.data.repositories.auth_repository.datasources.auth_remote_data_source.AuthRemoteDataSourceImpl
import com.mis.route.e_commerce.data.repositories.home_repository.HomeRepositoryImpl
import com.mis.route.e_commerce.data.repositories.home_repository.dataSources.home_remote_datasource.HomeRemoteDataSource
import com.mis.route.e_commerce.data.repositories.home_repository.dataSources.home_remote_datasource.HomeRemoteDataSourceImpl
import com.mis.route.e_commerce.domain.repositories.AuthRepository
import com.mis.route.e_commerce.domain.repositories.HomeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface AppModule {

    @Binds
    fun bindsAuthRepo(authRepositoryImpl: AuthRepositoryImpl): AuthRepository
    @Binds
    fun bindsAuthRemoteDataSource(authRemoteDataSource: AuthRemoteDataSourceImpl): AuthRemoteDataSource

    @Binds
    fun bindHomeRemoteDataSource(homeRemoteDataSourceImpl: HomeRemoteDataSourceImpl): HomeRemoteDataSource

    @Binds
    fun bindHomeRepo(repo: HomeRepositoryImpl): HomeRepository

}