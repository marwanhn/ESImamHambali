package com.example.esimamhambali

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.esimamhambali.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inisialisasi Spinner dan Adapter untuk kondisi 1
        val kondisi1Options = listOf("Bersuci dari Najis", "Bersuci dari Hadas")
        val kondisi1Adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, kondisi1Options)
        kondisi1Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner1.adapter = kondisi1Adapter

        // Inisialisasi Spinner dan Adapter untuk kondisi 2
        val kondisi2Options = listOf(
            "Terdapat air mutlak",
            "Terjadi perubahan pada najis",
            "Tidak adanya air",
            "Tidak cukupnya air",
            "Sakit semakin parah jika terkena air",
            "Waktu yang terlalu sempit untuk menggunakan air"
        )
        val kondisi2Adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, kondisi2Options)
        kondisi2Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner2.adapter = kondisi2Adapter

        // Aksi ketika item dipilih di Spinner 1
        binding.spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                // Do nothing
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do nothing
            }
        }

        // Aksi ketika item dipilih di Spinner 2
        binding.spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                // Do nothing
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do nothing
            }
        }

        // Aksi ketika tombol SUBMIT ditekan
        binding.button.setOnClickListener {
            // Mendapatkan pilihan dari Spinner 1 dan Spinner 2
            val kondisi1 = binding.spinner1.selectedItem.toString()
            val kondisi2 = binding.spinner2.selectedItem.toString()

            // Menjalankan logika aturan fikih Imam Hambali
            val hasil = aturanFikihImamHambali(kondisi1, kondisi2)

            // Menampilkan hasil di TextView
            binding.tvHasil.text = hasil
        }
    }

    // Metode untuk menentukan hasil berdasarkan kondisi yang dipilih
    private fun aturanFikihImamHambali(kondisi1: String, kondisi2: String): String {
        return when {
            kondisi1 == "Bersuci dari Najis" && kondisi2 == "Terdapat air mutlak" -> "Air mutlak"
            kondisi1 == "Bersuci dari Najis" && kondisi2 == "Terjadi perubahan pada najis" -> "Istihalah"
            kondisi1 == "Bersuci dari Hadas" && kondisi2 == "Terdapat air mutlak" -> "Air Mutlak"
            kondisi1 == "Bersuci dari Hadas" && kondisi2 == "Tidak adanya air" -> "Tayamum karena tidak ada air"
            kondisi1 == "Bersuci dari Hadas" && kondisi2 == "Sakit semakin parah jika terkena air" -> "Tayamum karena sakit"
            kondisi1 == "Bersuci dari Hadas" && kondisi2 == "Tidak cukupnya air" -> "Tayamum karena air tidak mencukupi"
            kondisi1 == "Bersuci dari Hadas" && kondisi2 == "Waktu yang terlalu sempit untuk menggunakan air" -> "Tayamum karena waktu terlalu sempit"
            else -> "Tidak ada aturan yang sesuai"
        }
    }
}
