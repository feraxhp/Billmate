package com.feraxhp.billmate.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = MainAppColor80,
    onPrimary = MainAppColor20,
    primaryContainer = MainAppColor30,
    onPrimaryContainer = MainAppColor90,
    inversePrimary = MainAppColor40,

    secondary = DarkMainAppColor80,
    onSecondary = DarkMainAppColor20,
    secondaryContainer = DarkMainAppColor30,
    onSecondaryContainer = DarkMainAppColor90,

    tertiary = TertiaryAppColor80,
    onTertiary = TertiaryAppColor20,
    tertiaryContainer = TertiaryAppColor30,
    onTertiaryContainer = TertiaryAppColor90,

    error = Error80,
    onError = Error20,
    errorContainer = Error30,
    onErrorContainer = Error90,

    background = Grey10,
    onBackground = Grey90,

    surface = MainVariantColor30,
    onSurface = MainVariantColor80,

    inverseSurface = Grey90,
    inverseOnSurface = Grey10,

    surfaceVariant = MainVariantColor30,
    onSurfaceVariant = MainVariantColor80,
    outline = MainVariantColor80
)

private val LightColorScheme = lightColorScheme(
    primary = MainAppColor40,
    onPrimary = Color.White,
    primaryContainer = MainAppColor90,
    onPrimaryContainer = MainAppColor10,
    inversePrimary = MainAppColor80,

    secondary = DarkMainAppColor40,
    onSecondary = Color.White,
    secondaryContainer = DarkMainAppColor90,
    onSecondaryContainer = DarkMainAppColor10,

    tertiary = TertiaryAppColor40,
    onTertiary = Color.White,
    tertiaryContainer = TertiaryAppColor90,
    onTertiaryContainer = TertiaryAppColor10,

    error = Error40,
    onError = Color.White,
    errorContainer = Error90,
    onErrorContainer = Error10,

    background = Grey99,
    onBackground = Grey10,

    surface = MainVariantColor90,
    onSurface = MainVariantColor30,

    inverseSurface = Grey20,
    inverseOnSurface = Grey95,

    surfaceVariant = MainVariantColor90,
    onSurfaceVariant = MainVariantColor30,
    outline = MainVariantColor50
)

@Composable
fun BillmateTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.copy(alpha = 0f).toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content,
//        shapes = Shapes
    )
}