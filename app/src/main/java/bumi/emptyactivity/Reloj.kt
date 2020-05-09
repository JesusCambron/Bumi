package bumi.emptyactivity

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.widget.Button
import android.widget.Chronometer
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import data.JsonFile
import kotlinx.android.synthetic.main.activity_reloj.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject


class Reloj : AppCompatActivity() {
    var jsonFile: JsonFile? = null
    var data: Boolean = false
    var encendido:Boolean = false
    var tiempoTotal:Long = 0
    var time:Long = 0
    var tiempoGuardado:Long = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reloj)

        dialogoAlerta()

        jsonFile = JsonFile()
        var botonTiempo: Button = findViewById(R.id.botonReloj) as Button
        var cronometro: Chronometer = findViewById(R.id.cronometro)

        fetchingData()
        cronometro.base = SystemClock.elapsedRealtime() - tiempoGuardado
        time = tiempoGuardado
        botonTiempo.setOnClickListener {
            encendido = !encendido
            if(encendido) {
                botonReloj.text = "Pausar"
                cronometro.base = SystemClock.elapsedRealtime() - time
                cronometro.start()
            } else {
                botonReloj.text = "Iniciar"
                time = SystemClock.elapsedRealtime() - cronometro.base
                cronometro.stop()
                guardar()
            }
        }

        reiniciarBoton.setOnClickListener {
            dialogoOpcionesReiniciar()
        }
/*
        guardarBoton.setOnClickListener {
            dialogoOpcionesCalcular()
        }

 */
    }

    fun fetchingData(){
        try {
            var json:String = jsonFile?.getData(this)?: ""
            if(json != ""){
                this.data = true
                var jsonArray: JSONArray = JSONArray(json)
                this.tiempoGuardado = parseJson(jsonArray)
            } else {
                this.data = false
            }
        } catch (exception: JSONException){
            exception.printStackTrace()
        }
    }

    fun parseJson(jsonArray: JSONArray):Long{
        var t:Long = 0
        for (i in 0..jsonArray.length()){
            try{
                    t = jsonArray.getJSONObject(0).getLong("tiempo")
            } catch(exception:JSONException){
                exception.printStackTrace()
            }
        }
        return t
    }

    fun guardar(){
        var jsonArray = JSONArray()
        var o : Int = 0
        Log.d("tiempo","1")
        var j:JSONObject = JSONObject()
        j.put("tiempo", time)
        jsonArray.put(o,j)
        jsonFile?.saveData(this, jsonArray.toString())

    }

    fun dialogoAlerta(){
        val fragmentManager: FragmentManager = supportFragmentManager
        var opcion:Boolean = false
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setMessage("De acuerdo con el Sacmex, una persona consume en promedio 307 litros de agua al día, lo que representa cerca de un 200% más de lo que se recomienda, que es de 96 litros.")
            .setTitle("Información")
            .setPositiveButton("OK",
                DialogInterface.OnClickListener { dialog, id -> run{
                    opcion = true
                    dialog.cancel()
                } })
        builder.create()
        builder.show()
    }
/*
    fun dialogoOpcionesCalcular(){
        val fragmentManager: FragmentManager = supportFragmentManager
        val builder =
            AlertDialog.Builder(this)
        builder.setMessage("¿Desea calcular el agua usada durante el día?")
            .setTitle("Mensaje")
            .setPositiveButton(
                "Aceptar"
            ) { dialog, id ->run {
                tiempoTotal = time
                guardar()
                //time = 0
                //cronometro.base = SystemClock.elapsedRealtime() - time
                Toast.makeText(this,"Datos guardados", Toast.LENGTH_SHORT).show()
                dialog.cancel()
            }

            }
            .setNegativeButton(
                "Cancelar"
            ) { dialog, id ->run {
                dialog.cancel()
            }
            }
        builder.create()
        builder.show()
    }

 */

    fun dialogoOpcionesReiniciar(){
        val fragmentManager: FragmentManager = supportFragmentManager
        val builder =
            AlertDialog.Builder(this)
        builder.setMessage("¿Desea reiniciar el cronometro a un nuevo día?")
            .setTitle("Mensaje")
            .setPositiveButton(
                "Aceptar"
            ) { dialog, id ->run {
                time = 0
                cronometro.base = SystemClock.elapsedRealtime() - time
                guardar()
                dialog.cancel()
            }

            }
            .setNegativeButton(
                "Cancelar"
            ) { dialog, id ->run {
                dialog.cancel()
            }
            }
        builder.create()
        builder.show()
    }
}
