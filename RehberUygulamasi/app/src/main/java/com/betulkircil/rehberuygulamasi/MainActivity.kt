package com.betulkircil.rehberuygulamasi

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.betulkircil.rehberuygulamasi.components.AppBar
import com.betulkircil.rehberuygulamasi.entity.Kisiler
import com.betulkircil.rehberuygulamasi.ui.theme.RehberUygulamasiTheme
import com.betulkircil.rehberuygulamasi.viewModelFactory.AnaSayfaViewModelFactory
import com.betulkircil.rehberuygulamasi.viewmodel.AnasayfaViewmodel
import com.google.gson.Gson

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RehberUygulamasiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    SayfaGecisleri()
                }
            }
        }
    }
}

@Composable
fun SayfaGecisleri() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "anasayfa"){
        composable("anasayfa"){
            AnaSayfa(navController = navController)  //anasayfadan gecis yapildigi icin anasayfaya parametre olarak navcontroller verildi
        }
        composable("kayit_sayfa"){
            KisiKayitSayfasi()  //burada gecisin hangi sayfaya olacagi belirtiliyor
        }
        composable("kisi_detay_sayfa/{kisi}", arguments = listOf(
            navArgument("kisi"){
                type = NavType.StringType
            }
        )
        ){
            val json = it.arguments?.getString("kisi")
            val nesne = Gson().fromJson(json, Kisiler::class.java)  //gelen bilgiyi nesneye donusturduk
            KisiDetaySayfasi(nesne)
        }
    }
}


@Composable
fun AnaSayfa(navController: NavController) {
    val isSearching = remember {
        mutableStateOf(false)
    }
    val searchText = remember {
        mutableStateOf("")
    }
    val context = LocalContext.current
    val viewmodel: AnasayfaViewmodel = viewModel(
        factory = AnaSayfaViewModelFactory(context.applicationContext as Application)
    )
    val kisilerListesi = viewmodel.kisilerListesi.observeAsState(listOf())
    LaunchedEffect(key1 = true){
        viewmodel.KisiListele()
    }
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = colorResource(id =R.color.teal_200),
                title = {
                    if(isSearching.value){
                        Box(modifier = Modifier.fillMaxWidth()){
                            TextField(
                                value = searchText.value,
                                onValueChange = { searchText.value = it
                                    viewmodel.Ara(it)  //search bar'da kisi arama islemi viewmodel katmaninda gerceklestiriliyor
                                },
                                label = {
                                    Text(text = stringResource(id = R.string.search_text_field))
                                },
                                colors = TextFieldDefaults.textFieldColors(
                                    backgroundColor = Color.Transparent,
                                    focusedLabelColor = Color.White,
                                    focusedIndicatorColor = Color.White,
                                    unfocusedIndicatorColor = Color.White,
                                    unfocusedLabelColor = Color.White,
                                )
                            )
                        }
                    }
                    else{
                        Text(text = stringResource(id = R.string.first_screen_title))
                    }
                     },
                actions = {
                    if(isSearching.value){
                        IconButton(onClick = {
                            isSearching.value = false
                            searchText.value = ""
                        }) {
                            Icon(painter = painterResource(id = R.drawable.icon_close), contentDescription = null)
                        }
                    }
                    else{
                        IconButton(onClick = {
                            isSearching.value = true
                        }) {
                            Icon(painter = painterResource(id = R.drawable.icon_search), contentDescription = null)
                        }
                    }
                }
            )
        },
        content =  {
            LazyColumn{
                items(
                    count = kisilerListesi.value!!.count(),
                    itemContent = {
                        val kisi = kisilerListesi.value!![it]
                        Card(modifier = Modifier
                            .padding(all = 10.dp)
                            .fillMaxWidth(), backgroundColor = colorResource(id = R.color.swirling_water)){
                            Row(modifier = Modifier.clickable {
                                val kisiJson = Gson().toJson(kisi)  //
                               navController.navigate("kisi_detay_sayfa/${kisiJson}")
                            }) {
                                Row(modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(all = 10.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
                                    Column(
                                        verticalArrangement = Arrangement.Center,
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        modifier = Modifier.padding(all = 5.dp)
                                    ) {
                                        Text(text = "${kisi.kisi_ad}")
                                        Text(text = "${kisi.kisi_tel}")
                                    }
                                    IconButton(onClick = {
                                        viewmodel.Sil(kisi.kisi_id)
                                    }) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.icon_delete),
                                            contentDescription = null
                                        )
                                    }
                                }

                            }
                        }
                    }
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate("kayit_sayfa")
                          },
                backgroundColor = colorResource(id = R.color.teal_200),
                contentColor = colorResource(id = R.color.white)
            ){
                Icon(painter = painterResource(id = R.drawable.icon_add),"")
            }
        }
    )
}


