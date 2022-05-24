package com.example.fanianggita_p11

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // TODO 1: Proses mengambil nilai vsariabel
        // Mengambil isi variabel editNamaFile dan EditCatatan dari activity_main.xml
        val fileName = findViewById<EditText>(R.id.editNamaFile)
        val fileData = findViewById<EditText>(R.id.editCatatan)

        // Membaca tombol dari activity_main.xml
        val btnSave = findViewById<Button>(R.id.btnSave)
        val btnView = findViewById<Button>(R.id.btnRead)

        // TODO 2: Proses menyimpan file ke memori internal
        // Apabila tombol btnSave ditekan, maka blok kode ini akan dijalankan.
        btnSave.setOnClickListener {
            // Menkonversi isi variabel sebagai data string.
            val file: String = fileName.text.toString()
            val data: String = fileData.text.toString()

            // FileOutputStream dimaksudkan untuk menulis aliran byte pada data
            val fileOutputStream: FileOutputStream


            try {
                fileOutputStream = openFileOutput(file, Context.MODE_PRIVATE)
                fileOutputStream.write(data.toByteArray()) // Proses menyimpan data ke folder Data pada memori internal.
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            } catch (e: NumberFormatException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: Exception) {
                e.printStackTrace()
            }

            // Toast ini akan muncul apabila data berhasil disimpan.
            Toast.makeText(applicationContext, "Data berhasil di simpan!", Toast.LENGTH_LONG).show()

            // menghapus isi editNamaFile dan EditCatatan
            fileName.text.clear()
            fileData.text.clear()
        }

        // TODO 3: Proses membaca file dari memori internal
        btnView.setOnClickListener {
            // Membaca apakah file sudah ada
            val filename = fileName.text.toString()
            if (filename.trim() != "") {
                val fileInputStream: FileInputStream? = openFileInput(filename)
                val inputStreamReader = InputStreamReader(fileInputStream)
                val bufferedReader = BufferedReader(inputStreamReader)
                val stringBuilder: StringBuilder = StringBuilder()
                var text: String?
                while (run {
                        text = bufferedReader.readLine()
                        text
                    } != null) {
                    stringBuilder.append(text)
                }
                // Menampilkan data di EditText
                fileData.setText(stringBuilder.toString()).toString()
            } else {
                Toast.makeText(applicationContext, "Nama file tidak boleh kosong", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }
}