package com.example.cryptoproject.viewModels

import androidx.lifecycle.*
import com.example.cryptoproject.models.Crypto
import com.example.cryptoproject.web.RetroFitClient
import kotlinx.coroutines.launch


class StartViewModel: ViewModel() {
    // Variables for a single crypto data to be observed
    private val _crypto = MutableLiveData<Crypto>()
    val crypto: LiveData<Crypto> = _crypto

    // Get a specific crypto based on their id/name
    fun getCrypto(id: String){
        viewModelScope.launch {
            _crypto.value = RetroFitClient.retrofit.getCrypto(id).data
        }
    }

    // Variables for a set of crypto data to be observed (all)
    private val _cryptoList = MutableLiveData<MutableList<Crypto>>()
    val cryptoList: LiveData<MutableList<Crypto>> = _cryptoList

    // Get all cryptos
    fun getCryptos(){
        viewModelScope.launch {
            _cryptoList.value = RetroFitClient.retrofit.getCryptos().data
        }
    }


}