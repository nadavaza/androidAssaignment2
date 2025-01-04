package com.example.androidassaignment2.model

class Model private constructor() {

    private val students = mutableListOf<Student>()

    init {
        for (i in 0..20) {
            val student = Student(
                name = "Name $i",
                id = "$i",
                phone = i,
                address = "$i",
                isChecked = false
            )
            students.add(student)
        }
    }

    fun getStudents(): List<Student> = students

    fun addStudent(student: Student) {
        students.add(student)
    }

    fun updateStudent(index: Int, student: Student) {
        students[index] = student
    }

    fun deleteStudent(index: Int) {
        students.removeAt(index)
    }

    companion object {
        val shared = Model()
    }
}