package com.betulkircil.rehberuygulamasi.viewModelFactory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.betulkircil.rehberuygulamasi.viewmodel.AnasayfaViewmodel

class AnaSayfaViewModelFactory(var application: Application):ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AnasayfaViewmodel(application) as T
}
}