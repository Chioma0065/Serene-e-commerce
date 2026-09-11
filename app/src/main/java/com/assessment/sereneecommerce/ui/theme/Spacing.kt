package com.assessment.sereneecommerce.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class Spacing(
    val xxs: Dp = 4.dp,
    val xs: Dp = 8.dp,
    val sm: Dp = 12.dp,
    val md: Dp = 16.dp,
    val lg: Dp = 24.dp,
    val xl: Dp = 32.dp,
    val twoXl: Dp = 40.dp,
    val threeXl: Dp = 56.dp,
    val edgeMarginMobile: Dp = 16.dp,
    val edgeMarginTablet: Dp = 24.dp,
    val gutterMobile: Dp = 12.dp,
    val gutterTablet: Dp = 16.dp,
    val tapTargetMin: Dp = 44.dp
)

val LocalSpacing = staticCompositionLocalOf { Spacing() }
