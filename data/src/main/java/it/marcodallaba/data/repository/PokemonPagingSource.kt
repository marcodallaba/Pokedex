package it.marcodallaba.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import it.marcodallaba.model.PokemonListEntry
import it.marcodallaba.network.service.PokedexClient
import it.marcodallaba.network.util.toModel

class PokemonPagingSource(
    private val pokedexClient: PokedexClient,
) : PagingSource<Int, PokemonListEntry>() {
    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, PokemonListEntry> {
        try {
            val nextPageNumber = params.key ?: 0
            val response = pokedexClient.fetchPokemonList(nextPageNumber, params.loadSize)
            val nextKey = if (response.next != null) {
                nextPageNumber + 1
            } else {
                null
            }
            return LoadResult.Page(
                data = response.toModel(),
                prevKey = null, // Only paging forward.
                nextKey = nextKey
            )
        } catch (e: Exception) {
            // IOException for network failures.
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PokemonListEntry>): Int? {
        // Try to find the page key of the closest page to anchorPosition from
        // either the prevKey or the nextKey; you need to handle nullability
        // here.
        //  * prevKey == null -> anchorPage is the first page.
        //  * nextKey == null -> anchorPage is the last page.
        //  * both prevKey and nextKey are null -> anchorPage is the
        //    initial page, so return null.
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
