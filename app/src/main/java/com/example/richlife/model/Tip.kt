package com.example.richlife.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Tip(
    @StringRes val title: Int,
    @StringRes val description: Int,
    @DrawableRes val image: Int
)