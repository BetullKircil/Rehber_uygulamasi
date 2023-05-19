package com.betulkircil.rehberuygulamasi.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.betulkircil.rehberuygulamasi.entity.Kisiler
import com.betulkircil.rehberuygulamasi.repo.KisilerDaoRepository

class AnasayfaViewmodel(application: Application): AndroidViewModel(application) {
    var kisilerRepo = KisilerDaoRepository(application)
    var kisilerListesi = MutableLiveData<List<Kisiler>>()
    init {
        KisiListele()
        kisilerListesi = kisilerRepo.Bagla()
    }

    fun KisiListele(){
        kisilerRepo.TumKisileriGetir()
    }
    fun Sil(kisi_id: Int){
        kisilerRepo.KisiSil(kisi_id)
    }
    fun Ara(aranacak_kelime: String){
        kisilerRepo.KisiAra(aranacak_kelime)
    }
}