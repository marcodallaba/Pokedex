package it.marcodallaba.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import it.marcodallaba.pokedex.pokemondetail.PokemonDetailScreen
import it.marcodallaba.pokedex.pokemonlist.PokemonListScreen
import it.marcodallaba.pokedex.ui.theme.PokedexTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokedexTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = PokemonListScreen.route,
                ) {
                    composable(route = PokemonListScreen.route) {
                        PokemonListScreen(navController = navController)
                    }
                    composable(
                        route = PokemonDetail.routeWithArgs,
                        arguments = PokemonDetail.arguments,
                    ) {
                        val dominantColor = remember {
                            val color = it.arguments?.getInt(PokemonDetail.DOMINANT_COLOR_ARG)
                            color?.let { Color(it) } ?: Color.White
                        }
                        val pokemonName = remember {
                            it.arguments?.getString(PokemonDetail.POKEMON_NAME_ARG)
                        }
                        PokemonDetailScreen(
                            dominantColor = dominantColor,
                            pokemonName = pokemonName?.lowercase().orEmpty(),
                            navController = navController,
                        )
                    }
                }
            }
        }
    }
}
