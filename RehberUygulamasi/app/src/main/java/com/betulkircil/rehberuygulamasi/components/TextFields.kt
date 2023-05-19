package com.betulkircil.rehberuygulamasi.components

import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.Placeholder
import com.betulkircil.rehberuygulamasi.R

@Composable
fun TextFields(label: String, placeholder: String) {
    var tf_kisi_ad = remember {
        mutableStateOf("")
    }
    var tf_kisi_tel = remember {
        mutableStateOf("")
    }
    OutlinedTextField(
        value = tf_kisi_ad.value,
        leadingIcon = { Icon(painterResource(id = R.drawable.icon_add),"") },
        //trailingIcon = { Icon(imageVector = Icons.Default.Add, contentDescription = null) },
        onValueChange = {
            tf_kisi_ad.value = it
        },
        label = { Text(text = label) },
        placeholder = { Text(text = placeholder) },
    )
}