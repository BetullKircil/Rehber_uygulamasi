package com.betulkircil.rehberuygulamasi.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.betulkircil.rehberuygulamasi.repo.KisilerDaoRepository

class KisiKayitSayfasiViewmodel(application: Application): AndroidViewModel(application) {
    var kisilerRepo = KisilerDaoRepository(application)  //repodaki methodlara ulasabilmek icin reponun nesnesini yarattık
    fun Kaydet(kisi_ad: String, kisi_tel: String){
        kisilerRepo.KisiKaydet(kisi_ad, kisi_tel)
    }
}