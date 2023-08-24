package com.farmus.farmus_application.ui.message.adapter


import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.farmus.farmus_application.databinding.RvChatTab1ItemBinding
import com.farmus.farmus_application.ui.message.*


class ChatTab1RVAdapter : ListAdapter<RVTab1DataModel, ChatTab1RVAdapter.ViewHolder>(diffUtil) {

    private lateinit var chatTab1Binding: RvChatTab1ItemBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        chatTab1Binding = RvChatTab1ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        //텍스트 밑줄
        chatTab1Binding.rvChatHistory.paintFlags = Paint.UNDERLINE_TEXT_FLAG

        return ViewHolder(chatTab1Binding)
    }

    override fun onBindViewHolder(holder: ChatTab1RVAdapter.ViewHolder, position: Int) {
        holder.bindItems(currentList[position])
    }

    inner class ViewHolder(private val binding : RvChatTab1ItemBinding): RecyclerView.ViewHolder(binding.root){

        fun bindItems(item: RVTab1DataModel){

            binding.rvChatImg.setImageResource(item.farm_image)
            binding.rvChatName.text = item.farm_name
            binding.rvChatPeriodDay.text = item.farm_period

            binding.rvChatHistory.setOnClickListener {

            }
        }
    }

    companion object{
        val diffUtil = object: DiffUtil.ItemCallback<RVTab1DataModel>(){
            override fun areItemsTheSame(
                oldItem: RVTab1DataModel,
                newItem: RVTab1DataModel
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: RVTab1DataModel,
                newItem: RVTab1DataModel
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}