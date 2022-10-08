package com.algirm.myemployee.presentation.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.algirm.myemployee.R
import com.algirm.myemployee.databinding.ItemAttendanceBinding
import com.algirm.myemployee.databinding.ItemEmployeeActivityBinding
import com.algirm.myemployee.domain.model.Attendance
import com.algirm.myemployee.domain.model.EmployeeActivity

class AttendanceAdapter(
    private val mContext: Context
) : RecyclerView.Adapter<AttendanceAdapter.AttendanceViewHolder>() {

    inner class AttendanceViewHolder(
        private val binding: ItemAttendanceBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(attendance: Attendance) {
            with(binding) {
                var textJamMasuk = "Jam Masuk: -"
                var textJamPulang = "Jam Pulang: -"
                attendance.JamMasuk?.let {
                    textJamMasuk = "Jam Masuk: $it"
                }
                attendance.JamPulang?.let {
                    textJamPulang = "Jam Pulang: $it"
                }
                tvNik.text = attendance.NIK
                tvTanggal.text = attendance.TanggalKehadiran
                tvNamaKaryawan.text = "IT Candidate"
                tvJamMasuk.text = textJamMasuk
                tvJamPulang.text = textJamPulang
            }
        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<Attendance>() {
        override fun areItemsTheSame(oldItem: Attendance, newItem: Attendance): Boolean {
            return oldItem.dateAdded == newItem.dateAdded
        }

        override fun areContentsTheSame(oldItem: Attendance, newItem: Attendance): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    private var listUser: List<Attendance>
    get() = differ.currentList
    set(value) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttendanceViewHolder {
        return AttendanceViewHolder(
            ItemAttendanceBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AttendanceViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return listUser.size
    }

    private var onItemClickListener: ((Attendance) -> Unit)? = null

    fun setOnItemClickListener(listener: (Attendance) -> Unit) {
        onItemClickListener = listener
    }
}