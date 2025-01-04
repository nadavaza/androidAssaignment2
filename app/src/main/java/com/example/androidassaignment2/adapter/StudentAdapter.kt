package com.example.androidassaignment2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidassaignment2.R
import com.example.androidassaignment2.model.Student

class StudentAdapter(
    private val students: List<Student>,
    private val onRowClick: (Int) -> Unit,
) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    class StudentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name = view.findViewById<TextView>(R.id.nameField)
        val id = view.findViewById<TextView>(R.id.studentId)
        val checkBox = view.findViewById<CheckBox>(R.id.checkBox)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.student_item, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = students[position]
        holder.name.text = student.name
        holder.id.text = student.id
        holder.checkBox.isChecked = student.isChecked
        holder.itemView.setOnClickListener { onRowClick(position) }
    }

    override fun getItemCount(): Int = students.size
}
