package com.example.keeper

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.room.Database
import com.example.keeper.database.entities.Game
import com.example.keeper.database.entities.GameViewModel
import kotlinx.android.synthetic.main.activity_new_game.*
import java.text.SimpleDateFormat
import java.util.*

class NewGameActivity : AppCompatActivity() {

    private lateinit var gameViewModel: GameViewModel


    //private var formate = SimpleDateFormat("dd MM, YYYY", Locale.US)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_game)

        gameViewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)

        val buttonSave = findViewById<Button>(R.id.button_done)

        buttonSave.setOnClickListener { click() }


        score_a_1.setOnClickListener {
            puntosA.text = ((puntosA.text.toString()).toInt()+1).toString()
        }

        score_a_2.setOnClickListener {
            puntosA.text = ((puntosA.text.toString()).toInt()+2).toString()
        }

        score_a_3.setOnClickListener {
            puntosA.text = ((puntosA.text.toString()).toInt()+3).toString()
        }




        score_b_1.setOnClickListener {
            puntosB.text = ((puntosB.text.toString()).toInt()+1).toString()
        }

        score_b_2.setOnClickListener {
            puntosB.text = ((puntosB.text.toString()).toInt()+2).toString()
        }

        score_b_3.setOnClickListener {
            puntosB.text = ((puntosB.text.toString()).toInt()+3).toString()
        }


       /* button_fecha.setOnClickListener {
            val now = Calendar.getInstance()
                       val datePicker = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                         val selectedDate = Calendar.getInstance()
                selectedDate.set(Calendar.YEAR,year)
                selectedDate.set(Calendar.MONTH,month)
                selectedDate.set(Calendar.DAY_OF_MONTH,dayOfMonth)
                val date = formate.format(selectedDate.time)
                Toast.makeText(this,"date : " + date,Toast.LENGTH_SHORT).show()
            },
                    now.get(Calendar.YEAR),now.get(Calendar.MONTH),now.get(Calendar.DAY_OF_MONTH))
           datePicker.show()
        }*/
    }


    fun click(){

        if(TextUtils.isEmpty(editText_equipo1.text)&& TextUtils.isEmpty(editText_equipo2.text)){
            Toast.makeText(applicationContext, "Please fill all the fields",Toast.LENGTH_SHORT).show()
        }else{
            var nombre = editText_equipo1.text.toString()+" vs "+editText_equipo2.text.toString()
            var equipoA = editText_equipo1.text.toString()
            var equipoB = editText_equipo2.text.toString()
            var puntosA = puntosA.text.toString().toInt()
            var puntosB = puntosB.text.toString().toInt()


            var game= Game(nombre,equipoA,puntosA,equipoB,puntosB,nombre)
            gameViewModel.insert(game)
        }
        finish()
    }


}
