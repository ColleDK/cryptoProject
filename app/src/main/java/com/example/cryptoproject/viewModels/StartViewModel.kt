package com.example.cryptoproject.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.cryptoproject.models.CryptoData

class StartViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {
    val cryptos: MutableLiveData<List<CryptoData>> = TODO()


}