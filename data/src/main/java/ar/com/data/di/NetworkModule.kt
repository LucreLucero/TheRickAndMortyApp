package ar.com.data.di

import ar.com.data.api.RickAndMortyApi
import ar.com.data.api.ServiceBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Provides
    fun provideMoviesApi(): RickAndMortyApi = ServiceBuilder.api
}
