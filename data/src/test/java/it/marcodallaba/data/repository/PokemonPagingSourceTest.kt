package it.marcodallaba.data.repository

import androidx.paging.PagingSource
import com.google.gson.Gson
import it.marcodallaba.network.dto.PokemonListResponse
import it.marcodallaba.network.json.page1Json
import it.marcodallaba.network.service.PokedexClient
import it.marcodallaba.network.util.toModel
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class PokemonPagingSourceTest {
    @Mock
    private lateinit var fakePokedexClient: PokedexClient

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun pageKeyedPagingSource() = runTest {
        val gson = Gson()
        val expectedResponse = gson.fromJson(page1Json, PokemonListResponse::class.java)
        Mockito.`when`(fakePokedexClient.fetchPokemonList(0, 20))
            .thenReturn(expectedResponse)

        val pagingSource = PokemonPagingSource(fakePokedexClient)

        val expectedPage = PagingSource.LoadResult.Page(
            data = expectedResponse.toModel(),
            prevKey = null,
            nextKey = 1,
        )

        val actualPage = pagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 20,
                placeholdersEnabled = false
            )
        )
        assertEquals(expectedPage, actualPage)
    }
}
