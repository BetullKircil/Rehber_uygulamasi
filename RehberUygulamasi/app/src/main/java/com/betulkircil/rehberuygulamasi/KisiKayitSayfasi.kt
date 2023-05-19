package com.betulkircil.rehberuygulamasi

import android.app.Application
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.betulkircil.rehberuygulamasi.viewModelFactory.KisiKayitSayfasiViewModelFactory
import com.betulkircil.rehberuygulamasi.viewmodel.KisiKayitSayfasiViewmodel
import kotlinx.coroutines.*

@Composable
fun KisiKayitSayfasi() {
    var tf_kisi_ad = remember {
        mutableStateOf("")
    }
    var tf_kisi_tel = remember {
        mutableStateOf("")
    }
    val context = LocalContext.current
    val viewmodel: KisiKayitSayfasiViewmodel = viewModel(
        factory = KisiKayitSayfasiViewModelFactory(context.applicationContext as Application)
    )
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.save_title))
                },
                backgroundColor = colorResource(id = R.color.teal_200)
            )
        },
        content = {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                value = tf_kisi_ad.value,
                leadingIcon = { Icon(painterResource(id = R.drawable.icon_add),"") },
                //trailingIcon = { Icon(imageVector = Icons.Default.Add, contentDescription = null) },
                onValueChange = {
                    tf_kisi_ad.value = it
                },
                label = { Text(text = stringResource(id = R.string.tf_name_label), color = colorResource(
                    id = R.color.teal_200
                ))},
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = colorResource(id = R.color.teal_200),
                        unfocusedBorderColor = colorResource(id = R.color.swirling_water))
            )

                OutlinedTextField(
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.None,
                        autoCorrect = true,
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    value = tf_kisi_tel.value,
                    leadingIcon = { Icon(painterResource(id = R.drawable.icon_add),"") },
                    //trailingIcon = { Icon(imageVector = Icons.Default.Add, contentDescription = null) },
                    onValueChange = {
                        tf_kisi_tel.value = it
                    },
                    label = { Text(text = stringResource(id = R.string.tf_phone_label), color = colorResource(
                        id = R.color.teal_200
                    ))},
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = colorResource(id = R.color.teal_200),
                        unfocusedBorderColor = colorResource(id = R.color.swirling_water))
                )

                Button(onClick = {
                    val kisi_ad = tf_kisi_ad.value
                    val kisi_tel = tf_kisi_tel.value
                    if(kisi_ad != "" && kisi_tel != ""){
                        viewmodel.Kaydet(kisi_ad, kisi_tel)
                    }
                    else{
                        println("eheheheh")
                        scope.launch {scaffoldState.
                            snackbarHostState.showSnackbar(
                                message = "Alanları bos birakmayiniz"
                            )
                        }
                    }

                },
                    colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.swirling_water))) {
                    Image(
                        painterResource(id = R.drawable.icon_save),
                        contentDescription = null,
                        modifier = Modifier.size(20.dp))

                    Text(text = stringResource(id = R.string.save_button_text),Modifier.padding(start = 10.dp))
                }
            }
        }
    )
}
