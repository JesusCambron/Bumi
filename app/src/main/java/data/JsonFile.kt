package data

import android.content.Context
import android.util.Log
import java.io.IOException

class JsonFile {
    val BUMI = "data.json"

    constructor(){

    }

    fun saveData(context: Context, json:String){
        try{
            context.openFileOutput(BUMI, Context.MODE_PRIVATE).use{
                it.write(json.toByteArray())
            }
        } catch (e: IOException){
            Log.e("GUARDAR","Error in Writing"+e.localizedMessage)
        }
    }

    fun getData(context: Context):String{
        try{
            return context.openFileInput(BUMI).bufferedReader().readLine()
        } catch (e: IOException){
            Log.e("OBTENER","Error in fetching data"+e.localizedMessage)
            return ""
        }
    }
}