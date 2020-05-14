package data



import android.graphics.Bitmap
import java.io.Serializable

data class Post (var tipo:String, var image: Bitmap?, var descripcion: String):Serializable{

import android.net.Uri
import java.io.Serializable
import java.net.URL


data class Post (var tipo:String, var image: Uri?, var descripcion: String):Serializable{



data class Post (var idPost: String,var tipo:String, var image: String?, var descripcion: String, var favorito: String, var destacado: String):Serializable{

}