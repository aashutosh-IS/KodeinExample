package com.zxc.test.data.network.responses

import com.zxc.test.data.database.entities.Quotes

data class QuotesResponse(
    val isSuccessful: Boolean,
    val quotes: List<Quotes>
)