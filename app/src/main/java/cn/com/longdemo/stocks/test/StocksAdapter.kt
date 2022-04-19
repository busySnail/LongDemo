package cn.com.longdemo.stocks.test

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cn.com.longdemo.databinding.StockListItemBinding


class StocksAdapter(private val stockList: List<NewStock?>?) :
    RecyclerView.Adapter<RecipeViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val itemBinding =
            StockListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(stockList?.get(position))
    }

    override fun getItemCount(): Int {
        return stockList?.size ?: 0
    }
}

class RecipeViewHolder(val itemBinding: StockListItemBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(stock: NewStock?) {
        itemBinding.tvName.text = stock?.paperName
    }
}