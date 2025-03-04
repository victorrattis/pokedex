package com.study.pokedex.di

import android.content.Context
import com.study.pokedex.BuildConfig
import com.study.pokedex.data.IPokemonLocalDataSource
import com.study.pokedex.data.IPokemonRemoteDataSource
import com.study.pokedex.data.PokemonRepository
import com.study.pokedex.data.local.RoomPokemonLocalDataSource
import com.study.pokedex.data.remote.PokeApiRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Named
import javax.inject.Singleton

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

    @Singleton
    @Provides
    fun providesPokemonLocalDataSource(
        @ApplicationContext context: Context
    ): IPokemonLocalDataSource = RoomPokemonLocalDataSource(context)

    @Provides
    fun providesPokemonRepository(
        remoteDataSource: IPokemonRemoteDataSource,
        localDataSource: IPokemonLocalDataSource,
        @Named("Data-Coroutines-Scope") scope: CoroutineScope
    ): PokemonRepository = PokemonRepository(
        remoteDataSource,
        localDataSource,
        scope
    )
}