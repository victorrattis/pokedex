package com.study.pokedex.di

import com.study.pokedex.BuildConfig
import com.study.pokedex.data.remote.IPokemonRemoteDataSource
import com.study.pokedex.data.remote.PokeApiRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Named("Data-Coroutines-Scope")
    fun providesDataScope(): CoroutineScope = CoroutineScope(Dispatchers.IO)

    @Provides
    fun providesPokemonRemoteDataSource(
        @Named("Data-Coroutines-Scope") scope: CoroutineScope
    ): IPokemonRemoteDataSource =
        PokeApiRemoteDataSource(BuildConfig.BASE_URL, scope)
}