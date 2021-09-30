package com.example.cryptoproject.viewModels

import androidx.lifecycle.*
import com.example.cryptoproject.models.CryptoData
import com.example.cryptoproject.repositories.CryptoRepository
import com.example.cryptoproject.web.RetroFitClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import javax.inject.Inject


class StartViewModel: ViewModel() {

    private val _cryptos = MutableLiveData<CryptoData>()
    val cryptos: LiveData<CryptoData> = _cryptos

    fun getCrypto(id: String){
        viewModelScope.launch {
            _cryptos.value = RetroFitClient.retrofit.getCrypto(id)
        }
    }



}