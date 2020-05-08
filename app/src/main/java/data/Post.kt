package data

import android.net.Uri
import java.io.Serializable

data class Post (var tipo:String, var image: Uri?, var descripcion: String):Serializable{

}