package com.betulkircil.rehberuygulamasi.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.betulkircil.rehberuygulamasi.repo.KisilerDaoRepository

class KisiDetaySayfasiViewmodel(application: Application): AndroidViewModel(application) {
    var kisilerRepo = KisilerDaoRepository(application)
    fun Guncelle(kisi_id: Int, kisi_ad: String, kisi_tel: String){
        kisilerRepo.KisiGuncelle(kisi_id, kisi_ad, kisi_tel)
    }
}