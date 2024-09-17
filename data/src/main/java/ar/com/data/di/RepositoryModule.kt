package ar.com.data.di

import ar.com.data.repository.IRickAndMortyRepository
import ar.com.data.repository.RickAndMortyRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun bindIRickAndMortyRepository(rickAndMortyRepository: RickAndMortyRepository): IRickAndMortyRepository
}
