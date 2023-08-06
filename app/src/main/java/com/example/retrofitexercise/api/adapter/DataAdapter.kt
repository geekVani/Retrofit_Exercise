package com.example.retrofitexercise.api.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitexercise.databinding.CustomListitemBinding
import com.example.retrofitexercise.model.retrofit.Entries

class DataAdapter(var dataList: List<Entries>, private val onItemClick: (String)-> Unit)
    :RecyclerView.Adapter<DataAdapter.DataViewHolder>() {

    inner class DataViewHolder(val adapterBinding: CustomListitemBinding) :
        RecyclerView.ViewHolder(adapterBinding.root) {

        private val tvLink: TextView = adapterBinding.tvLink
        fun bind(dataItem: Entries) {
            adapterBinding.dataItem = dataItem
            adapterBinding.executePendingBindings()
            tvLink.text = tvLink.toString()
            tvLink.setOnClickListener {
                onItemClick(tvLink.toString())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {

        val view = CustomListitemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return DataViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        /*holder.adapterBinding.tvApi.text = dataList[position].API
        holder.adapterBinding.tvDescription.text = dataList[position].Description
        holder.adapterBinding.tvAuth.text = dataList[position].Auth
        holder.adapterBinding.tvHttp.text = dataList[position].HTTPS
        holder.adapterBinding.tvCors.text = dataList[position].Cors
        holder.adapterBinding.tvLink.text = dataList[position].Link
        holder.adapterBinding.tvCategory.text = dataList[position].Category
*/

        val dataItem = dataList[position]
        holder.bind(dataItem)

        // on click event
        holder.itemView.setOnClickListener {
            onItemClick(dataItem.Link)

            /*val bundle = Bundle()

            // second(web) fragment object
            val webViewFragment = WebFragment()
            webViewFragment.arguments = bundle

            // fragment manager cannot directly be accessed hence use requireActivity()
            val fragManager: FragmentManager = webViewFragment.requireActivity().supportFragmentManager
            val fragTransaction: FragmentTransaction = fragManager.beginTransaction()
            fragTransaction
                .replace(R.id.frameLayout, webViewFragment)
                .addToBackStack(null)
                .commit()*/

//        }
        }
    }
}