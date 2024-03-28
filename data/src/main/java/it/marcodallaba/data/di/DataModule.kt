package it.marcodallaba.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import it.marcodallaba.common.network.Dispatcher
import it.marcodallaba.common.network.PokedexDispatchers
import it.marcodallaba.data.repository.PokemonRepositoryImpl
import it.marcodallaba.data.repository.PokemonRepository
import it.marcodallaba.network.service.PokedexClient
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun providePokemonRepository(
        pokedexClient: PokedexClient,
        @Dispatcher(PokedexDispatchers.IO) ioDispatcher: CoroutineDispatcher,
    ): PokemonRepository = PokemonRepositoryImpl(pokedexClient, ioDispatcher)
}
