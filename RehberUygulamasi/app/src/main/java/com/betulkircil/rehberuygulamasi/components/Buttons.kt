package com.betulkircil.rehberuygulamasi.components

import android.graphics.drawable.Icon
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.betulkircil.rehberuygulamasi.R

@Composable
fun ButtonsWithIcon(icon: String? = null, text: String) {
    var localFocusManager =
        LocalFocusManager.current
    Button(onClick = {
                     Log.e("msg", "Kaydet buttonuna tıklandi")
        localFocusManager.clearFocus()  ////geri tusuna bastigimizda textfield'lardaki secimi kaldiriyoruz
    }, modifier = Modifier.size(250.dp, 50.dp)) {
        Image(
            painter = painterResource(LocalContext.current.resources.getIdentifier(icon, "drawable", LocalContext.current.packageName)),
            contentDescription = null,
            modifier = Modifier.size(20.dp))

        Text(text = text,Modifier.padding(start = 10.dp))
    }
}