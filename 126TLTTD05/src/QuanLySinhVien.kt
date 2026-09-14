package com.example.baitapkotlin.student

data class Student(
    val studentId: String,
    val fullName: String,
    val age: Int,
    val major: String,
    val gpa: Double
) {
    override fun toString(): String {
        return "ID: $studentId | Họ tên: $fullName | Tuổi: $age | Ngành: $major | GPA: $gpa"
    }
}

fun main() {
    val students = mutableListOf(
        Student("SV101", "Nguyen Ly Luat", 21, "CNTT", 8.8),
        Student("SV102", "Van Ba Trinh", 20, "Kinh Te", 4.9),
        Student("SV103", "Nguyen Khanh Gia Huy", 22, "CNTT", 9.2),
        Student("SV104", "Tran Van Hieu", 19, "Ngoai Ngu", 7.5),
        Student("SV105", "Truong Nghia Anh Tung", 23, "Kinh Te", 6.8)
    )

    while (true) {
        println("\n =====STUDENT MANAGEMENT==== ")
        println("1. Add student")
        println("2. Display all students")
        println("3. Search student")
        println("4. Calculate average GPA")
        println("5. Find student with highest GPA")
        println("6. Remove student")
        println("--- CÁC CHỨC NĂNG BỔ SUNG ---")
        println("7. Đếm số sinh viên có GPA >= 8.0")
        println("8. Đếm số sinh viên có GPA < 5.0")
        println("9. Tính GPA trung bình theo ngành")
        println("10. Tìm sinh viên lớn tuổi nhất")
        println("11. Tìm sinh viên có GPA trong khoảng 7.0 -> 8.5")
        println("12. Tìm tất cả sinh viên thuộc một ngành")
        println("13. Tìm sinh viên theo một phần tên")
        println("14. Sắp xếp sinh viên theo GPA giảm dần")
        println("15. Hiển thị 3 sinh viên có GPA cao nhất")
        println("16. Sắp xếp sinh viên theo tuổi")
        println("17. Sắp xếp sinh viên theo tên")
        println("0. Exit")
        print("Choose: ")

        val choice = readLine()?.trim()?.toIntOrNull() ?: -1

        when (choice) {
            1 -> {
                print("Nhập Student ID: ")
                val id = readLine() ?: ""
                print("Nhập Full Name: ")
                val name = readLine() ?: ""
                print("Nhập Age: ")
                val age = readLine()?.toIntOrNull() ?: 0
                print("Nhập Major: ")
                val major = readLine() ?: ""
                print("Nhập GPA: ")
                val gpa = readLine()?.toDoubleOrNull() ?: 0.0
                students.add(Student(id, name, age, major, gpa))
                println("--> Thêm sinh viên thành công!")
            }
            2 -> {
                println("\n--- DANH SÁCH TẤT CẢ SINH VIÊN ---")
                if (students.isEmpty()) println("Danh sách trống!")
                else students.forEach { println(it) }
            }
            3 -> {
                print("Nhập ID hoặc tên sinh viên cần tìm: ")
                val keyword = readLine() ?: ""
                val result = students.filter {
                    it.studentId.contains(keyword, ignoreCase = true) ||
                            it.fullName.contains(keyword, ignoreCase = true)
                }
                if (result.isEmpty()) println("Không tìm thấy sinh viên phù hợp!")
                else result.forEach { println(it) }
            }
            4 -> {
                if (students.isEmpty()) println("Danh sách trống!")
                else {
                    val avg = students.map { it.gpa }.average()
                    println("--> GPA trung bình của tất cả sinh viên: $avg")
                }
            }
            5 -> {
                if (students.isEmpty()) println("Danh sách trống!")
                else {
                    val maxStudent = students.maxByOrNull { it.gpa }
                    println("--> Sinh viên có GPA cao nhất: $maxStudent")
                }
            }
            6 -> {
                print("Nhập Student ID cần xóa: ")
                val id = readLine() ?: ""
                val removed = students.removeIf { it.studentId.equals(id, ignoreCase = true) }
                if (removed) println("--> Đã xóa sinh viên thành công!")
                else println("--> Không tìm thấy sinh viên có ID này!")
            }
            7 -> {
                val count = students.count { it.gpa >= 8.0 }
                println("--> Số sinh viên có GPA >= 8.0 là: $count")
            }
            8 -> {
                val count = students.count { it.gpa < 5.0 }
                println("--> Số sinh viên có GPA < 5.0 là: $count")
            }
            9 -> {
                print("Nhập tên ngành cần tính GPA trung bình: ")
                val majorInput = readLine() ?: ""
                val filtered = students.filter { it.major.equals(majorInput, ignoreCase = true) }
                if (filtered.isEmpty()) println("Không có sinh viên nào thuộc ngành này!")
                else {
                    val avg = filtered.map { it.gpa }.average()
                    println("--> GPA trung bình ngành $majorInput là: $avg")
                }
            }
            10 -> {
                if (students.isEmpty()) println("Danh sách trống!")
                else {
                    val oldest = students.maxByOrNull { it.age }
                    println("--> Sinh viên lớn tuổi nhất: $oldest")
                }
            }
            11 -> {
                val result = students.filter { it.gpa in 7.0..8.5 }
                if (result.isEmpty()) println("Không có sinh viên nào có GPA từ 7.0 đến 8.5")
                else {
                    println("\n--- SINH VIÊN CÓ GPA TRONG KHOẢNG 7.0 -> 8.5 ---")
                    result.forEach { println(it) }
                }
            }
            12 -> {
                print("Nhập tên ngành cần tìm: ")
                val majorInput = readLine() ?: ""
                val result = students.filter { it.major.equals(majorInput, ignoreCase = true) }
                if (result.isEmpty()) println("Không có sinh viên thuộc ngành này!")
                else {
                    println("\n--- SINH VIÊN THUỘC NGÀNH $majorInput ---")
                    result.forEach { println(it) }
                }
            }
            13 -> {
                print("Nhập phần tên cần tìm: ")
                val partName = readLine() ?: ""
                val result = students.filter { it.fullName.contains(partName, ignoreCase = true) }
                if (result.isEmpty()) println("Không tìm thấy sinh viên phù hợp!")
                else {
                    println("\n--- KẾT QUẢ TÌM KIẾM ---")
                    result.forEach { println(it) }
                }
            }
            14 -> {
                val sorted = students.sortedByDescending { it.gpa }
                println("\n--- DANH SÁCH SẮP XẾP GPA GIẢM DẦN ---")
                sorted.forEach { println(it) }
            }
            15 -> {
                val top3 = students.sortedByDescending { it.gpa }.take(3)
                println("\n--- TOP 3 SINH VIÊN CÓ GPA CAO NHẤT ---")
                top3.forEach { println(it) }
            }
            16 -> {
                val sorted = students.sortedBy { it.age }
                println("\n--- DANH SÁCH SẮP XẾP THEO TUỔI TĂNG DẦN ---")
                sorted.forEach { println(it) }
            }
            17 -> {
                val sorted = students.sortedBy { it.fullName }
                println("\n--- DANH SÁCH SẮP XẾP THEO TÊN (ALPHABET) ---")
                sorted.forEach { println(it) }
            }
            0 -> {
                println("Thoát chương trình. Tạm biệt!")
                return
            }
            else -> println("Lựa chọn không hợp lệ! Vui lòng chọn từ 0 đến 17.")
        }
    }
}