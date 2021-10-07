package com.example.cryptoproject.viewModels

import android.graphics.BitmapFactory
import androidx.lifecycle.*
import com.example.cryptoproject.models.CryptoDto
import com.example.cryptoproject.web.RetroFitClient
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response


class StartViewModel: ViewModel() {
    // Variables for a single crypto data to be observed
    private val _crypto = MutableLiveData<CryptoDto>()
    val cryptoDto: LiveData<CryptoDto> = _crypto

    // Get a specific crypto based on their id/name
    fun getCrypto(id: String){
        viewModelScope.launch {
            _crypto.value = RetroFitClient.retrofit.getCrypto(id).data
        }
    }

    // Variables for a set of crypto data to be observed (all)
    private val _cryptoList = MutableLiveData<MutableList<CryptoDto>>()
    val cryptoDtoList: LiveData<MutableList<CryptoDto>> = _cryptoList

    // Get all cryptos
    fun getCryptos(){
        viewModelScope.launch {
            _cryptoList.value = RetroFitClient.retrofit.getCryptos().data
            // iterate all the cryptos and get their pictures
            for (crypto in _cryptoList.value!!){
                getCryptoPics(crypto)
            }
        }
    }

    fun getCryptoPics(cryptoDto: CryptoDto){
        // check if the crypto is in the current data
        if (_cryptoList.value!!.indexOf(cryptoDto) == -1) _cryptoList.value?.add(cryptoDto)
        viewModelScope.launch {
            RetroFitClient.retrofitPic.getCryptoPics(cryptoDto.symbol.toLowerCase()).enqueue(object: retrofit2.Callback<ResponseBody> {
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    if (response.isSuccessful){
                        _cryptoList.value?.get(_cryptoList.value!!.indexOf(cryptoDto))?.picture = BitmapFactory.decodeStream(response.body()?.byteStream())
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
        }
    }

    // get single cryptos picture
    fun getCryptoPic(cryptoDto: CryptoDto){
        viewModelScope.launch {
            RetroFitClient.retrofitPic.getCryptoPics(cryptoDto.symbol.toLowerCase()).enqueue(object: retrofit2.Callback<ResponseBody> {
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    if (response.isSuccessful){
                        _crypto.value?.picture = BitmapFactory.decodeStream(response.body()?.byteStream())
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
        }
    }
}