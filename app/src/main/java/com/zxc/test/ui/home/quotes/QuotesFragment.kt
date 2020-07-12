package com.zxc.test.ui.home.quotes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import com.zxc.test.R
import com.zxc.test.data.database.entities.Quotes
import com.zxc.test.util.Coroutines
import com.zxc.test.util.hide
import com.zxc.test.util.show
import kotlinx.android.synthetic.main.quotes_fragment.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class QuotesFragment : Fragment() ,KodeinAware{

    override val kodein by kodein()

    private val factory:QuotesFactory by instance()
    private lateinit var viewModel: QuotesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.quotes_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this,factory).get(QuotesViewModel::class.java)


        bindUI()

        }

    private fun bindUI() = Coroutines.main {
        viewModel.quotes.await().observe(this, Observer {
            initRecyclerView(it.toQutoeItem())

        })
    }

    private fun initRecyclerView(quoteItem: List<QuoteItem>) {

        val mAdapter = GroupAdapter<ViewHolder>().apply {
            addAll(quoteItem)
        }
        recyclerview.apply {
            layoutManager=LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter=mAdapter
        }

    }


    private  fun  List<Quotes>.toQutoeItem():List<QuoteItem>{
        return this.map {
            QuoteItem(it)
        }
    }
}

