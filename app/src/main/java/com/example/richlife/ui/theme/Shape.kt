package com.example.richlife.ui.theme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val Shapes = Shapes(
    small = RoundedCornerShape(bottomStart = 36.dp, topEnd = 36.dp, bottomEnd = 36.dp, topStart = 36.dp),
    medium = RoundedCornerShape(
        bottomStart = 36.dp,
        bottomEnd = 8.dp,
        topStart = 8.dp,
        topEnd = 36.dp
        )
)