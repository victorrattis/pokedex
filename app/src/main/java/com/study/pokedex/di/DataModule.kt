package com.study.pokedex.di

import com.study.pokedex.data.remote.IPokemonRemoteDataSource
import com.study.pokedex.data.remote.PokeApiRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    fun providesPokemonRemoteDataSource(): IPokemonRemoteDataSource =
        PokeApiRemoteDataSource("https://pokeapi.co/api/v2/")

//    @Provides
//    fun providesPokemonRemoteDataSource(): IPokemonRemoteDataSource = FakeRemoteDataSource()
}