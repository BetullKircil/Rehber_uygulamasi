package com.betulkircil.rehberuygulamasi

import android.app.Application
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.betulkircil.rehberuygulamasi.components.AppBar
import com.betulkircil.rehberuygulamasi.entity.Kisiler
import com.betulkircil.rehberuygulamasi.viewModelFactory.KisiDetayViewModelFactory
import com.betulkircil.rehberuygulamasi.viewmodel.KisiDetaySayfasiViewmodel

@Composable
fun KisiDetaySayfasi(gelen_kisi: Kisiler) {
    var tf_kisi_ad = remember {
        mutableStateOf("")
    }
    var tf_kisi_tel = remember {
        mutableStateOf("")
    }
    val context = LocalContext.current
    val viewmodel: KisiDetaySayfasiViewmodel = viewModel(
        factory = KisiDetayViewModelFactory(context.applicationContext as Application)
    )
    LaunchedEffect(key1 = true){
        tf_kisi_ad.value = gelen_kisi.kisi_ad
        tf_kisi_tel.value = gelen_kisi.kisi_tel
    } //ekran ilk acildiginda tum verilerin aktarilmasi icin, sayfa acildigi ilk anda calisir
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = colorResource(id = R.color.teal_200),
                title = {
                    Text(text = stringResource(id = R.string.person_detail_screen_title))
                }
            )
        },
    content = {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = tf_kisi_ad.value,
                onValueChange = {
                    tf_kisi_ad.value = it
                },
                label = { Text(text = stringResource(id = R.string.tf_name_label), color = colorResource(
                    id = R.color.black
                )) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = colorResource(id = R.color.teal_200),
                    unfocusedBorderColor = colorResource(id = R.color.swirling_water),
                    backgroundColor = colorResource(id = R.color.swirling_water)
                    )
            )
            TextField(
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.None,
                    autoCorrect = true,
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                value = tf_kisi_tel.value,
                onValueChange = {
                    tf_kisi_tel.value = it
                },
                label = { Text(text = stringResource(id = R.string.tf_name_label), color = colorResource(
                    id = R.color.black
                )) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = colorResource(id = R.color.teal_200),
                    unfocusedBorderColor = colorResource(id = R.color.swirling_water), 
                    backgroundColor = colorResource(id = R.color.swirling_water)
                    )
            )
            Button(onClick = {
                var kisi_ad = tf_kisi_ad.value
                var kisi_tel = tf_kisi_tel.value
                viewmodel.Guncelle(gelen_kisi.kisi_id, kisi_ad, kisi_tel)
            }, colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.swirling_water))) {
                Image(
                    painterResource(id = R.drawable.icon_update),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp))

                Text(text = stringResource(id = R.string.update_button_text),Modifier.padding(start = 10.dp))
            }
        }
    }
    )
}

