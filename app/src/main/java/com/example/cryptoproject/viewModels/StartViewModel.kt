package com.example.cryptoproject.viewModels

import androidx.lifecycle.*
import com.example.cryptoproject.models.CryptoData
import com.example.cryptoproject.repositories.CryptoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StartViewModel @Inject constructor(savedStateHandle: SavedStateHandle, cryptoRepository: CryptoRepository) : ViewModel() {

    private val _cryptos = MutableLiveData<CryptoData>()
    val cryptos: LiveData<CryptoData> = _cryptos

    init {
        viewModelScope.launch {
            _cryptos.value = cryptoRepository.getCrypto("BTC")
        }
    }



}