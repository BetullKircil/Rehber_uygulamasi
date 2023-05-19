package com.betulkircil.rehberuygulamasi.components

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun AppBar(title: String, colorResource: Color) {
    TopAppBar(title = { Text(text = title) })
}