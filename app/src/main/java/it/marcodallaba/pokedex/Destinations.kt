package it.marcodallaba.pokedex

import androidx.navigation.NavType
import androidx.navigation.navArgument

interface Destination {
    val route: String
}

object PokemonListScreen : Destination {
    override val route = "pokemon_list_screen"
}

object PokemonDetail : Destination {
    override val route = "pokemon_detail_screen"
    const val DOMINANT_COLOR_ARG = "dominantColor"
    const val POKEMON_NAME_ARG = "pokemonName"
    val routeWithArgs = "$route/{$DOMINANT_COLOR_ARG}/{$POKEMON_NAME_ARG}"
    val arguments = listOf(
        navArgument(DOMINANT_COLOR_ARG) { type = NavType.IntType },
        navArgument(POKEMON_NAME_ARG) { type = NavType.StringType },
    )
}