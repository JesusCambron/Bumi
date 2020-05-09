package bumi.emptyactivity

import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import android.util.Log
import android.widget.Button
import android.widget.Chronometer
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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

        guardarBoton.setOnClickListener {
            tiempoTotal = time
            time = 0
            cronometro.base = SystemClock.elapsedRealtime() - time
            guardar()
        }
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
        Toast.makeText(this,"Datos guardados", Toast.LENGTH_SHORT).show()
    }
}
