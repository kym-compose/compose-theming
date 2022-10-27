package com.kimym.compose

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

object SampleData {
    fun getColor(): RainbowEntity = rainbow.random()
    fun getColors(): List<RainbowEntity> = rainbow
}

data class RainbowEntity(
    @StringRes val color: Int,
    @StringRes val code: Int,
    @DrawableRes val drawable: Int,
)

private val rainbow = listOf(
    RainbowEntity(R.string.red, R.string.red_code, R.drawable.red),
    RainbowEntity(R.string.orange, R.string.orange_code, R.drawable.orange),
    RainbowEntity(R.string.yellow, R.string.yellow_code, R.drawable.yellow),
    RainbowEntity(R.string.green, R.string.green_code, R.drawable.green),
    RainbowEntity(R.string.blue, R.string.blue_code, R.drawable.blue),
    RainbowEntity(R.string.navy, R.string.navy_code, R.drawable.navy),
    RainbowEntity(R.string.purple, R.string.purple_code, R.drawable.purple)
)
