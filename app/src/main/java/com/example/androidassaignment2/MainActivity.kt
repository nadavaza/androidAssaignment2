package com.example.androidassaignment2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidassaignment2.adapter.StudentAdapter
import com.example.androidassaignment2.model.Model.Companion.shared

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: StudentAdapter
    private val model = shared

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recyclerView = findViewById(R.id.studentsList)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val students = model.getStudents()

        adapter = StudentAdapter(
            students = students,
            onRowClick = { index -> openStudentDetails(index) },
        )
        recyclerView.adapter = adapter

        findViewById<Button>(R.id.addButton).setOnClickListener {
            openAddStudentActivity()
        }
    }

    private fun openStudentDetails(index: Int) {
        val intent = Intent(this, StudentDetailsActivity::class.java)
        intent.putExtra("STUDENT_INDEX", index)
        startActivity(intent)
    }

    private fun openAddStudentActivity() {
        val intent = Intent(this, AddStudentActivity::class.java)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }

}