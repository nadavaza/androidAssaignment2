package com.example.androidassaignment2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androidassaignment2.model.Model
import com.example.androidassaignment2.model.Student

class EditStudentActivity : AppCompatActivity() {

    private var studentIndex: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_student)

        val studentImage: ImageView = findViewById(R.id.studentImage)
        val nameField: EditText = findViewById(R.id.nameField)
        val idField: EditText = findViewById(R.id.idField)
        val phoneField: EditText = findViewById(R.id.phoneField)
        val addressField: EditText = findViewById(R.id.addressField)
        val checkBoxField: CheckBox = findViewById(R.id.checkBoxField)
        val saveButton: Button = findViewById(R.id.saveButton)
        val cancelButton: Button = findViewById(R.id.cancelButton)
        val deleteButton: Button = findViewById(R.id.deleteButton)

        studentIndex = intent.getIntExtra("STUDENT_INDEX", -1)

        if (studentIndex != -1) {
            val student = Model.shared.getStudents()[studentIndex]
            studentImage.setImageResource(R.drawable.user)
            nameField.setText(student.name)
            idField.setText(student.id)
            phoneField.setText(student.phone.toString())
            addressField.setText(student.address)
            checkBoxField.isChecked = student.isChecked
        }

        saveButton.setOnClickListener {
            saveStudentDetails(
                nameField.text.toString(),
                idField.text.toString(),
                phoneField.text.toString(),
                addressField.text.toString(),
                checkBoxField.isChecked
            )
        }

        cancelButton.setOnClickListener {
            returnToMainActivity()
        }

        deleteButton.setOnClickListener {
            deleteStudent()
        }
    }

    private fun saveStudentDetails(name: String, id: String, phone: String, address: String, isChecked: Boolean) {
        if (studentIndex != -1) {
            val updatedStudent = Student(
                name = name,
                id = id,
                phone = phone.toIntOrNull() ?: 0,
                address = address,
                isChecked = isChecked
            )

            Model.shared.updateStudent(studentIndex, updatedStudent)
            returnToMainActivity()
        }
    }

    private fun deleteStudent() {
        if (studentIndex != -1) {
            Model.shared.deleteStudent(studentIndex)
            returnToMainActivity()
        }
    }

    private fun returnToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }
}
