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
    primary = Color(0xFF1E88E5),
    secondary = Color(0xFFD32F2F),
    background = Color(0xFF1976D2),
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color.Black,
    onSurface = Color.Black
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF1E88E5),
    secondary = Color(0xFFD32F2F),
    background = Color(0xFF1976D2),
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color.Black,
    onSurface = Color.Black
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

