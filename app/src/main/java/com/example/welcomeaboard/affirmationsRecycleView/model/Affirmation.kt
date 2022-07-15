package com.example.welcomeaboard.affirmationsRecycleView.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Affirmation(
    @StringRes val stringResourceId: Int,
    @DrawableRes val drawableResourceId: Int) {

}