package it.marcodallaba.pokedex.pokemonlist

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.filter
import androidx.palette.graphics.Palette
import dagger.hilt.android.lifecycle.HiltViewModel
import it.marcodallaba.data.repository.PokemonRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val repository: PokemonRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    companion object {
        const val QUERY = "query"
        const val DEFAULT_QUERY = ""
    }

    init {
        if (!savedStateHandle.contains(QUERY)) {
            savedStateHandle[QUERY] = DEFAULT_QUERY
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    val pokemonListFlow = savedStateHandle.getLiveData<String>(QUERY)
        .asFlow()
        .flatMapLatest { query ->
            repository.getPokemonListEntry(0).map {
                it.filter { pokemonListEntry ->
                    pokemonListEntry.name.contains(query.trim(), ignoreCase = true) ||
                            pokemonListEntry.number == query.trim()
                }
            }
        }
        // cachedIn() shares the paging state across multiple consumers of posts,
        // e.g. different generations of UI across rotation config change
        .cachedIn(viewModelScope)

    fun search(query: String) {
        if (!shouldShowQuery(query)) return
        savedStateHandle[QUERY] = query
    }

    private fun shouldShowQuery(query: String): Boolean {
        return savedStateHandle.get<String>(QUERY) != query
    }

    fun calcDominantColor(drawable: Drawable, onFinish: (Color) -> Unit) {
        val bitmap = (drawable as BitmapDrawable).bitmap.copy(Bitmap.Config.ARGB_8888, true)

        Palette.from(bitmap).generate { palette ->
            palette?.dominantSwatch?.rgb?.let { colorValue ->
                onFinish(Color(colorValue))
            }
        }
    }
}
