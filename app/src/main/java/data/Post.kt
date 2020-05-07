package data


import android.graphics.Bitmap
import java.io.Serializable

data class Post (var tipo:String, var image: Bitmap?, var descripcion: String):Serializable{

}