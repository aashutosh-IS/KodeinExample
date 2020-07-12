package com.zxc.test.ui.home.quotes

import com.xwray.groupie.databinding.BindableItem
import com.zxc.test.R
import com.zxc.test.data.database.entities.Quotes
import com.zxc.test.databinding.ItemQuoteBinding

class QuoteItem(
    private val quote :Quotes
) : BindableItem<ItemQuoteBinding>(){
    override fun getLayout() = R.layout.item_quote

    override fun bind(viewBinding: ItemQuoteBinding, position: Int) {
        viewBinding.setQuote(quote)

    }

}
