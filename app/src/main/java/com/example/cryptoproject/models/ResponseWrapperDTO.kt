package com.example.cryptoproject.models

data class ResponseWrapperDTO<R>(val data: R, val timestamp: Long)