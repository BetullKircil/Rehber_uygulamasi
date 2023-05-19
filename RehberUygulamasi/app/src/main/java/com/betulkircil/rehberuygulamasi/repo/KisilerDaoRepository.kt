package com.betulkircil.rehberuygulamasi.repo

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.betulkircil.rehberuygulamasi.Veritabani
import com.betulkircil.rehberuygulamasi.entity.Kisiler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class KisilerDaoRepository(var application: Application) {
    var db: Veritabani? = null

    //arayuzde guncelleme yapmak icin live data tanımlıyoruz
    var kisilerListesi = MutableLiveData<List<Kisiler>>()

    init {
        db = Veritabani.veriTabaniErisim(application)
        kisilerListesi = MutableLiveData()
    }

    fun Bagla(): MutableLiveData<List<Kisiler>>{
        return kisilerListesi
}
    fun TumKisileriGetir(){
        val job: Job = CoroutineScope(Dispatchers.Main).launch {
            kisilerListesi.value = db!!.KisilerDao().TumKisileriGetir()
        }
    }
    fun KisiAra(aranacak_kelime: String){
        val job: Job = CoroutineScope(Dispatchers.Main).launch {
            kisilerListesi.value = db!!.KisilerDao().KisiAra(aranacak_kelime)
        }
    }
    fun KisiKaydet(kisi_ad: String, kisi_tel: String){
        val job: Job = CoroutineScope(Dispatchers.Main).launch {
            val eklenecekKisi = Kisiler(0, kisi_ad, kisi_tel)
            db!!.KisilerDao().KisiEkle(eklenecekKisi)
        }
    }
    fun KisiGuncelle(kisi_id: Int, kisi_ad: String, kisi_tel: String){
        val job: Job = CoroutineScope(Dispatchers.Main).launch {
            val guncellenecekKisi = Kisiler(kisi_id, kisi_ad, kisi_tel)
            db!!.KisilerDao().KisiGuncelle(guncellenecekKisi)
        }
    }
    fun KisiSil(kisi_id: Int){
        val job: Job = CoroutineScope(Dispatchers.Main).launch {
            val silinecekKisi = Kisiler(kisi_id, "","")
            db!!.KisilerDao().KisiSil(silinecekKisi)
            TumKisileriGetir()
        }
    }
}