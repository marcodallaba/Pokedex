package it.marcodallaba.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import it.marcodallaba.data.repository.PokemonRepositoryImpl
import it.marcodallaba.network.service.PokedexClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providePokemonRepository(
        pokedexClient: PokedexClient
    ) = PokemonRepositoryImpl(pokedexClient)
}