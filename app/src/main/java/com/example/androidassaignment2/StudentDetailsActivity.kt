package com.example.androidassaignment2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.androidassaignment2.model.Model

class StudentDetailsActivity : AppCompatActivity() {

    private var studentIndex: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_details)

        val studentImage: ImageView = findViewById(R.id.studentImage)
        val nameField: TextView = findViewById(R.id.nameField)
        val idField: TextView = findViewById(R.id.idField)
        val phoneField: TextView = findViewById(R.id.phoneField)
        val addressField: TextView = findViewById(R.id.addressField)
        val checkBoxField: CheckBox = findViewById(R.id.checkBoxField)
        val backButton: Button = findViewById(R.id.backButton)
        val editButton: Button = findViewById(R.id.editButton)

        studentIndex = intent.getIntExtra("STUDENT_INDEX", -1)

        if (studentIndex != -1) {
            val student = Model.shared.getStudents()[studentIndex]
            nameField.text = "Name: ${student.name}"
            idField.text = "ID: ${student.id}"
            phoneField.text = "Phone: ${student.phone}"
            addressField.text = "Address: ${student.address}"
            checkBoxField.isChecked = student.isChecked
            checkBoxField.isEnabled = false  // Disable checkbox interaction in details screen
            studentImage.setImageResource(R.drawable.user)
        }

        editButton.setOnClickListener {
            openEditStudentActivity()
        }

        backButton.setOnClickListener {
            returnToMainActivity()
        }
    }

    private fun openEditStudentActivity() {
        val intent = Intent(this, EditStudentActivity::class.java)
        intent.putExtra("STUDENT_INDEX", studentIndex)
        startActivity(intent)
    }

    private fun returnToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }
}
