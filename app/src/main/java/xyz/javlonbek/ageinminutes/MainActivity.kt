package xyz.javlonbek.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var selectedDateText: TextView
    private lateinit var inMinutesText: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        selectedDateText = findViewById(R.id.selectedDateText)
        inMinutesText = findViewById(R.id.inMinutesText)
        val dateBtn: Button = findViewById(R.id.dateBtn)

        dateBtn.setOnClickListener {
            showDatePicker()
        }
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val dp = DatePickerDialog(
            this, { _, year, month, day ->
                selectedDateText.text =
                    "${if (day < 10) "0$day" else day}.${if (month < 10) "0$month" else month}.$year"
                val fmt = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val date = fmt.parse("$day/$month/$year")
                val dateInMinutes = (date?.time ?: 6000) / 6000
                val currentDateInMinutes = (fmt.parse(fmt.format(System.currentTimeMillis()))?.time
                    ?: 6000) / 6000

                inMinutesText.text = (currentDateInMinutes - dateInMinutes).toString()
            }, year, month, day
        )
        dp.datePicker.maxDate = System.currentTimeMillis() - 86400
        dp.show()
    }
}