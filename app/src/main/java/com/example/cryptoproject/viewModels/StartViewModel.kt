package com.example.cryptoproject.viewModels

import androidx.lifecycle.*
import com.example.cryptoproject.models.Crypto
import com.example.cryptoproject.repositories.CryptoRepository
import com.example.cryptoproject.web.RetroFitClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StartViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    cryptoRepository: CryptoRepository
): ViewModel() {

    private val _cryptos = MutableLiveData<Crypto>()
    val cryptos: LiveData<Crypto> = _cryptos

    fun getCrypto(id: String){
        viewModelScope.launch {
            _cryptos.value = RetroFitClient.retrofit.getCrypto(id).data
        }
    }



}