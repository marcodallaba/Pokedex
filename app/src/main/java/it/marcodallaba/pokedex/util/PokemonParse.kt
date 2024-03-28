package it.marcodallaba.pokedex.util

import androidx.compose.ui.graphics.Color
import it.marcodallaba.pokedex.ui.theme.AtkColor
import it.marcodallaba.pokedex.ui.theme.DefColor
import it.marcodallaba.pokedex.ui.theme.HPColor
import it.marcodallaba.pokedex.ui.theme.SpAtkColor
import it.marcodallaba.pokedex.ui.theme.SpDefColor
import it.marcodallaba.pokedex.ui.theme.SpdColor
import it.marcodallaba.pokedex.ui.theme.TypeBug
import it.marcodallaba.pokedex.ui.theme.TypeDark
import it.marcodallaba.pokedex.ui.theme.TypeDragon
import it.marcodallaba.pokedex.ui.theme.TypeElectric
import it.marcodallaba.pokedex.ui.theme.TypeFairy
import it.marcodallaba.pokedex.ui.theme.TypeFighting
import it.marcodallaba.pokedex.ui.theme.TypeFire
import it.marcodallaba.pokedex.ui.theme.TypeFlying
import it.marcodallaba.pokedex.ui.theme.TypeGhost
import it.marcodallaba.pokedex.ui.theme.TypeGrass
import it.marcodallaba.pokedex.ui.theme.TypeGround
import it.marcodallaba.pokedex.ui.theme.TypeIce
import it.marcodallaba.pokedex.ui.theme.TypeNormal
import it.marcodallaba.pokedex.ui.theme.TypePoison
import it.marcodallaba.pokedex.ui.theme.TypePsychic
import it.marcodallaba.pokedex.ui.theme.TypeRock
import it.marcodallaba.pokedex.ui.theme.TypeSteel
import it.marcodallaba.pokedex.ui.theme.TypeWater

@Suppress("CyclomaticComplexMethod")
fun parseTypeToColor(typeName: String): Color {
    return when (typeName.lowercase()) {
        "normal" -> TypeNormal
        "fire" -> TypeFire
        "water" -> TypeWater
        "electric" -> TypeElectric
        "grass" -> TypeGrass
        "ice" -> TypeIce
        "fighting" -> TypeFighting
        "poison" -> TypePoison
        "ground" -> TypeGround
        "flying" -> TypeFlying
        "psychic" -> TypePsychic
        "bug" -> TypeBug
        "rock" -> TypeRock
        "ghost" -> TypeGhost
        "dragon" -> TypeDragon
        "dark" -> TypeDark
        "steel" -> TypeSteel
        "fairy" -> TypeFairy
        else -> Color.Black
    }
}

fun parseStatToColor(statName: String): Color {
    return when (statName.lowercase()) {
        "hp" -> HPColor
        "attack" -> AtkColor
        "defense" -> DefColor
        "special-attack" -> SpAtkColor
        "special-defense" -> SpDefColor
        "speed" -> SpdColor
        else -> Color.White
    }
}

fun parseStatToAbbr(statName: String): String {
    return when (statName.lowercase()) {
        "hp" -> "HP"
        "attack" -> "Atk"
        "defense" -> "Def"
        "special-attack" -> "SpAtk"
        "special-defense" -> "SpDef"
        "speed" -> "Spd"
        else -> ""
    }
}
