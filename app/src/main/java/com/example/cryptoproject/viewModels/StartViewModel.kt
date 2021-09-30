package com.example.cryptoproject.viewModels

import androidx.lifecycle.*
import com.example.cryptoproject.models.Crypto
import com.example.cryptoproject.web.RetroFitClient
import kotlinx.coroutines.launch


class StartViewModel: ViewModel() {

    private val _crypto = MutableLiveData<Crypto>()
    val crypto: LiveData<Crypto> = _crypto


    fun getCrypto(id: String){
        viewModelScope.launch {
            _crypto.value = RetroFitClient.retrofit.getCrypto(id).data
        }
    }

    private val _cryptoList = MutableLiveData<MutableList<Crypto>>()
    val cryptoList: LiveData<MutableList<Crypto>> = _cryptoList

    fun getCryptos(){
        viewModelScope.launch {
            _cryptoList.value = RetroFitClient.retrofit.getCryptos().data
        }
    }


}