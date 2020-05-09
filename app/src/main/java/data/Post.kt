package data

<<<<<<< HEAD

import android.graphics.Bitmap
import java.io.Serializable

data class Post (var tipo:String, var image: Bitmap?, var descripcion: String):Serializable{
=======
import android.net.Uri
import java.io.Serializable

data class Post (var tipo:String, var image: Uri?, var descripcion: String):Serializable{
>>>>>>> master

}