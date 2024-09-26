package com.example.game.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF1E88E5), // Azul oscuro para el tema principal
    secondary = Color(0xFFD32F2F), // Rojo vibrante para la "X"
    background = Color(0xFF1976D2), // Fondo más claro para el tablero
    surface = Color.White, // Blanco para las superficies
    onPrimary = Color.White, // Color del texto sobre el botón principal
    onSecondary = Color.White, // Texto en botones secundarios y "X"
    onBackground = Color.Black, // Texto sobre fondo
    onSurface = Color.Black // Texto en superficies claras
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF1E88E5), // Azul oscuro para el fondo del tablero
    secondary = Color(0xFFD32F2F), // Rojo vibrante para la "X"
    background = Color(0xFF1976D2), // Azul para el fondo
    surface = Color.White, // Blanco para las superficies
    onPrimary = Color.White, // Texto blanco sobre botones azules
    onSecondary = Color.White, // Texto sobre la "X"
    onBackground = Color.Black, // Texto sobre fondo
    onSurface = Color.Black // Texto sobre superficies
)

@Composable
fun GameTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false, // Desactivar colores dinámicos para personalización completa
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

@Composable
fun BoardCell(value: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .size(80.dp)
            .padding(2.dp)
            .clickable(onClick = onClick)
            .shadow(  6.dp, RoundedCornerShape(8.dp)),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface) // Superficie blanca
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = if (value == "_") "" else value,
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = when (value) {
                    "X" -> MaterialTheme.colorScheme.secondary // Rojo para la "X"
                    "O" -> MaterialTheme.colorScheme.onSurface // Negro para la "O"
                    else -> MaterialTheme.colorScheme.onSurface // Para la celda vacía
                },
                textAlign = TextAlign.Center
            )
        }
    }
}

