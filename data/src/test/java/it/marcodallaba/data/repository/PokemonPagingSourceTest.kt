package it.marcodallaba.data.repository

import it.marcodallaba.network.service.PokedexClient
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class PokemonPagingSourceTest {
    private val pokemonListEntryFactory = PokemonListEntryFactory()
    private val fakePosts = listOf(
        pokemonListEntryFactory.createPokemon(),
        pokemonListEntryFactory.createPokemon(),
        pokemonListEntryFactory.createPokemon()
    )
    @Mock
    private lateinit var fakePokedexClient: PokedexClient

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun pageKeyedPagingSource() = runTest {

    }
}
