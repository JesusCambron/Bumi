package data

import android.net.Uri
import java.io.Serializable
import java.net.URL

data class Post (var idPost: String,var tipo:String, var image: String?, var descripcion: String, var favorito: String, var destacado: String):Serializable{
}