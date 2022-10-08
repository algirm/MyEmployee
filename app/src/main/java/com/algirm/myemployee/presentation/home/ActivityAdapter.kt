package com.algirm.myemployee.presentation.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.algirm.myemployee.R
import com.algirm.myemployee.databinding.ItemEmployeeActivityBinding
import com.algirm.myemployee.domain.model.EmployeeActivity

class ActivityAdapter(
    private val mContext: Context
) : RecyclerView.Adapter<ActivityAdapter.ActivityViewHolder>() {

    inner class ActivityViewHolder(
        private val binding: ItemEmployeeActivityBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(employeeActivity: EmployeeActivity) {
            with(binding) {
                val textCategory =
                    "${mContext.getString(R.string.category)} ${employeeActivity.Category}"
                tvActivityName.text = employeeActivity.Activity
                tvRemark.text = employeeActivity.Remark
                tvActDate.text = employeeActivity.ActDate
                tvDepartment.text = employeeActivity.Department
                tvTimeIn.text = employeeActivity.TimeIn
                tvTimeOut.text = employeeActivity.TimeOut
                tvCategory.text = textCategory
                tvDeadline.text = if (employeeActivity.Deadline == "0000-00-00") "-" else employeeActivity.Deadline
                root.setOnClickListener {
                    onItemClickListener?.let {
                        it(employeeActivity)
                    }
                }
            }
        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<EmployeeActivity>() {
        override fun areItemsTheSame(oldItem: EmployeeActivity, newItem: EmployeeActivity): Boolean {
            return oldItem.Seq == newItem.Seq
        }

        override fun areContentsTheSame(oldItem: EmployeeActivity, newItem: EmployeeActivity): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    private var listUser: List<EmployeeActivity>
    get() = differ.currentList
    set(value) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityViewHolder {
        return ActivityViewHolder(
            ItemEmployeeActivityBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ActivityViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return listUser.size
    }

    private var onItemClickListener: ((EmployeeActivity) -> Unit)? = null

    fun setOnItemClickListener(listener: (EmployeeActivity) -> Unit) {
        onItemClickListener = listener
    }
}