package com.example.formulaone.adapters

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.formulaoneapplicationn.R
import com.example.formulaoneapplicationn.databinding.SingleUpcomingRaceBinding
import com.example.formulaoneapplicationn.domain.model.RaceScheduleDomain
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class UpcomingRaceAdapter :
    ListAdapter<RaceScheduleDomain, UpcomingRaceAdapter.UpcomingRaceViewHolder>(
        UpcomingDiffCallBack()
    ) {
    private lateinit var itemClickListener: (RaceScheduleDomain, Int) -> Unit


    inner class UpcomingRaceViewHolder(private val binding: SingleUpcomingRaceBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private var model: RaceScheduleDomain? = null

        @RequiresApi(Build.VERSION_CODES.O)
        fun bindData() {
            model = getItem(bindingAdapterPosition)

            val dateFromModel = model!!.date
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val date = LocalDate.parse(dateFromModel, formatter)
            val splittedDate = date.month.name.split("")
            val accronymDate = "${splittedDate[1]}${splittedDate[2]}${splittedDate[3]}"
            val droppedDays = date.toString().drop(8)

            val timeFromModel = model?.time?.dropLast(4)

            binding.apply {
                ivTicket.setImageResource(R.drawable.ic_baseline_airplane_ticket_24)
                tvCountry.text = model?.Circuit?.Location?.country
                tvDate.text = "$droppedDays\n$accronymDate"
                tvRound.text = "Round ${model?.round}"
                tvGrandPrixName.text = model?.raceName
                tvTime.text = timeFromModel
                binding.root.setBackgroundResource(R.drawable.outline)
            }
            binding.ivTicket.setOnClickListener {
                itemClickListener.invoke(model!!, absoluteAdapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingRaceViewHolder {
        val binding =
            SingleUpcomingRaceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UpcomingRaceViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: UpcomingRaceViewHolder, position: Int) {
        holder.bindData()
    }
    fun setOnItemClickListener(clickListener: (RaceScheduleDomain, Int) -> Unit) {
        itemClickListener = clickListener
    }
}


class UpcomingDiffCallBack : DiffUtil.ItemCallback<RaceScheduleDomain>() {
    override fun areItemsTheSame(
        oldItem: RaceScheduleDomain,
        newItem: RaceScheduleDomain
    ): Boolean {
        return oldItem.date == newItem.date
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(
        oldItem: RaceScheduleDomain,
        newItem: RaceScheduleDomain
    ): Boolean {
        return oldItem == newItem
    }
}