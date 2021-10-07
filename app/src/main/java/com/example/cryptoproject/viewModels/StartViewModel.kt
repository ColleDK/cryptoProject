package com.example.cryptoproject.viewModels

import android.graphics.BitmapFactory
import androidx.lifecycle.*
import com.example.cryptoproject.models.Crypto
import com.example.cryptoproject.repositories.CryptoRepository
import com.example.cryptoproject.web.RetroFitClient
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject


class StartViewModel @Inject constructor(private val cryptoRepository: CryptoRepository): ViewModel() {
    // Variables for a single crypto data to be observed
    private val _crypto = MutableLiveData<Crypto>()
    val crypto: LiveData<Crypto> = _crypto

    // Get a specific crypto based on their id/name
    fun getCrypto(id: String){
        viewModelScope.launch {
            _crypto.value = cryptoRepository.getCrypto(id)
            cryptoRepository.loadCrypto(id)
                //RetroFitClient.retrofit.getCrypto(id).data
        }
    }

    // Variables for a set of crypto data to be observed (all)
    private val _cryptoList = MutableLiveData<MutableList<Crypto>>()
    val cryptoList: LiveData<MutableList<Crypto>> = _cryptoList

    // Get all cryptos
    fun getCryptos(){
        viewModelScope.launch {
            _cryptoList.value = cryptoRepository.getCryptos()
            // iterate all the cryptos and get their pictures
            for (crypto in _cryptoList.value!!){
                getCryptoPics(crypto)
            }
        }
    }

    fun getCryptoPics(crypto: Crypto){
        // check if the crypto is in the current data
        if (_cryptoList.value!!.indexOf(crypto) == -1) _cryptoList.value?.add(crypto)
        viewModelScope.launch {
            RetroFitClient.retrofitPic.getCryptoPics(crypto.symbol.toLowerCase()).enqueue(object: retrofit2.Callback<ResponseBody> {
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    if (response.isSuccessful){
                        _cryptoList.value?.get(_cryptoList.value!!.indexOf(crypto))?.picture = BitmapFactory.decodeStream(response.body()?.byteStream())
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
        }
    }

    // get single cryptos picture
    fun getCryptoPic(crypto: Crypto){
        viewModelScope.launch {
            RetroFitClient.retrofitPic.getCryptoPics(crypto.symbol.toLowerCase()).enqueue(object: retrofit2.Callback<ResponseBody> {
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