package bumi.emptyactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import data.JsonFile
import kotlinx.android.synthetic.main.activity_ahorro_agua.*
import org.json.JSONArray
import org.json.JSONException

class AhorroAgua : AppCompatActivity() {
    var jsonFile: JsonFile? = null
    var data: Boolean = false
    var tiempoGuardado:Long = 0;
    var segundos:Int = 0
    val LITROS:Float = 307.0F
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ahorro_agua)
        jsonFile = JsonFile()
        fetchingData()
        calcularSegundos()

        litrosAhorrados.setText(""+calcularLitros()+" Litros")
    }

    fun calcularLitros():Float{
        return LITROS-(segundos * 0.0035532407407407f);
    }

    fun calcularSegundos(){
        segundos = (tiempoGuardado / 1000).toInt()
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
            } catch(exception: JSONException){
                exception.printStackTrace()
            }
        }
        return t
    }
}
