package com.example.androidassaignment2

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androidassaignment2.model.Model.Companion.shared
import com.example.androidassaignment2.model.Student

class AddStudentActivity : AppCompatActivity() {

    private val model = shared

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_student)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val studentName = findViewById<TextView>(R.id.nameField)
        val studentId = findViewById<TextView>(R.id.idField)
        val studentPhone = findViewById<TextView>(R.id.phoneField)
        val studentAddress = findViewById<TextView>(R.id.addressField)
        val studentIsChecked = findViewById<CheckBox>(R.id.checkBox)


        findViewById<Button>(R.id.saveButton).setOnClickListener{
            model.addStudent(Student(name = studentName.text.toString(), id = studentId.text.toString() , phone = studentPhone.text.toString().toInt(),
                                     address = studentAddress.text.toString(), isChecked = studentIsChecked.isChecked))
            finish()
        }

        findViewById<Button>(R.id.cancelButton).setOnClickListener{
            finish()
        }
    }
}