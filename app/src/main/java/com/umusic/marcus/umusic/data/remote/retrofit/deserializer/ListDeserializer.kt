package com.umusic.marcus.umusic.data.remote.retrofit.deserializer

import com.google.gson.JsonDeserializer


internal interface ListDeserializer<T> : JsonDeserializer<List<T>>