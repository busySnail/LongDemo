package cn.com.longdemo.stocks.test

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cn.com.longdemo.base.RecyclerItemListener
import cn.com.longdemo.databinding.StockListItemBinding


class StocksAdapter(
    private val viewModel: StockViewModel?, private val stockList: List<NewStock>?
) : RecyclerView.Adapter<RecipeViewHolder>() {

    private val onItemClickListener: RecyclerItemListener<NewStock> =
        object : RecyclerItemListener<NewStock> {
            override fun onItemSelected(stock: NewStock) {
                viewModel?.openStockDetail(stock)
            }
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val itemBinding =
            StockListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        stockList?.get(position)?.let {
            holder.bind(it, onItemClickListener)
        }
    }

    override fun getItemCount(): Int {
        return stockList?.size ?: 0
    }
}

class RecipeViewHolder(private val itemBinding: StockListItemBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(stock: NewStock, itemClickListener: RecyclerItemListener<NewStock>) {
        itemBinding.tvName.text = stock.paperName
        itemBinding.root.setOnClickListener {
            itemClickListener.onItemSelected(stock)
        }
    }
}