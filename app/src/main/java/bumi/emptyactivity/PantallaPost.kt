package bumi.emptyactivity

import android.R.attr.src
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import com.google.firebase.storage.StorageReference
import data.Datos
import data.Post
import kotlinx.android.synthetic.main.activity_pantalla_post.*
import kotlinx.android.synthetic.main.post_view.view.*
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL


class PantallaPost : AppCompatActivity() {

    companion object{
        var posts = ArrayList<Post>()
    }

    var dataBase:DatabaseReference? = null
    var storage: StorageReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_post)

        dataBase = FirebaseDatabase.getInstance().getReference()

        val bundle = intent.extras
        var adaptador:AdaptadorPosts? = null

        if(bundle != null) {
            val type = bundle.getString("tipo")
            when(type){
                "plantasInicio" -> {
                    adaptador = AdaptadorPosts(this,posts)
                    opcion.text = "Inicio"
                }
                "plantasFavoritos" -> {
                    opcion.text = "Favoritos"
                    //adaptador = AdaptadorPosts(this,cargarCatalogoFavoritosPlantas())
                }
                "plantasDestacados" -> {
                    opcion.text = "Destacados"
                    //adaptador = AdaptadorPosts(this,cargarCatalogoDestacadosPlantas())
                }
                "plantasMisPlantas" -> {
                    opcion.text = "Mis Plantas"
                    //adaptador = AdaptadorPosts(this,cargarCatalogoMisPlantas())
                }

                "aguaConsejos" -> {
                    opcion.text = "Consejos"
                    //adaptador = AdaptadorPosts(this,cargarCatalogoConsejosAgua())
                }
                "aguaTemporizador" -> {
                    opcion.text = "Temporizador"
                    //adaptador = AdaptadorPosts(this,cargarCatalogoTemporizadorAgua())
                }
                "aguaCanciones" -> {
                    opcion.text = "Canciones"
                    //adaptador = AdaptadorPosts(this,cargarCatalogoHerramientasAgua())
                }
                "aguaLitros" -> {
                    opcion.text = "Litros Usados"
                    //adaptador = AdaptadorPosts(this,cargarCatalogoLitrosAhorradosAgua())
                }

                "reciclajeInicio" -> {
                    opcion.text = "Inicio"
                    adaptador = AdaptadorPosts(this,posts)
                }
                "reciclajeFavoritos" -> {
                    opcion.text = "Favoritos"
                    //adaptador = AdaptadorPosts(this,cargarCatalogoFavoritosPlantas())
                }
                "reciclajeMispost" -> {
                    opcion.text = "Mis Posts"
                    adaptador = AdaptadorPosts(this,posts)
                }
                "reciclajeReciclaje" -> {
                    opcion.text = "Reciclaje"
                    //adaptador = AdaptadorPosts(this,cargarCatalogoMisPlantas())
                }

            }

        }

        botonAgregar.setOnClickListener{
            var intento: Intent = Intent(this, AgregarPost::class.java)
            startActivityForResult(intento,1)
        }

        listaPost.adapter = adaptador
    }

   override fun onStart() {
        super.onStart()
       dataBase!!.child("Posts").addChildEventListener(object :ChildEventListener{
           override fun onCancelled(p0: DatabaseError) {
               TODO("Not yet implemented")
           }

           override fun onChildMoved(p0: DataSnapshot, p1: String?) {
               TODO("Not yet implemented")
           }

           override fun onChildChanged(p0: DataSnapshot, p1: String?) {
               TODO("Not yet implemented")
           }

           override fun onChildAdded(p0: DataSnapshot, p1: String?) {
               var data: Datos? = p0.getValue(Datos::class.java)
               if (data != null) {
                   data.key = p0.key
               }
               if (data != null) {
                   var uri:String? = data.imageId
                   data.descripcion?.let { data.tipo?.let { it1 -> Post(it1,uri, it) } }?.let { posts.add(it) }
               }
           }
           override fun onChildRemoved(p0: DataSnapshot) {
               TODO("Not yet implemented")
           }


       })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        this.finish()
        startActivity(this.intent)
    }
/*
    fun cargarCatalogoInicioPlantas():ArrayList<Post> {
        var lista = ArrayList<Post>()
        lista.add(
            Post(
                "Foto",
                BitmapFactory.decodeResource(resources,R.drawable.sunflower),
                "El dia de hoy vengo a compartirles el progreso de mi jardin de girasoles"
            )
        )
        lista.add(
            Post(
                "Foto",
                BitmapFactory.decodeResource(resources,R.drawable.sunflower),
                "El dia de hoy vengo a compartirles el progreso de mi jardin de girasoles"
            )
        )
        return lista
    }

    fun cargarCatalogoFavoritosPlantas():ArrayList<Post> {
        var lista = ArrayList<Post>()
        lista.add(
            Post(
                "Foto",
                BitmapFactory.decodeResource(resources,R.drawable.sunflower),
                "El dia de hoy vengo a compartirles el progreso de mi jardin de girasoles"
            )
        )
        return lista
    }

    fun cargarCatalogoDestacadosPlantas():ArrayList<Post> {
        var lista = ArrayList<Post>()
        lista.add(
            Post(
                "Foto",
                BitmapFactory.decodeResource(resources,R.drawable.sunflower),
                "El dia de hoy vengo a compartirles el progreso de mi jardin de girasoles"
            )
        )
        lista.add(
            Post(
                "Foto",
                BitmapFactory.decodeResource(resources,R.drawable.sunflower),
                "El dia de hoy vengo a compartirles el progreso de mi jardin de girasoles"
            )
        )
        return lista
    }

    fun cargarCatalogoMisPlantas():ArrayList<Post> {
        var lista = ArrayList<Post>()
        lista.add(
            Post(
                "Foto",
                BitmapFactory.decodeResource(resources,R.drawable.sunflower),
                "El dia de hoy vengo a compartirles el progreso de mi jardin de girasoles"
            )
        )
        lista.add(
            Post(
                "Foto",
                BitmapFactory.decodeResource(resources,R.drawable.sunflower),
                "El dia de hoy vengo a compartirles el progreso de mi jardin de girasoles"
            )
        )
        return lista
    }


    fun cargarCatalogoConsejosAgua():ArrayList<Post> {
        var lista = ArrayList<Post>()
        lista.add(
            Post(
                "Foto",
                BitmapFactory.decodeResource(resources,R.drawable.sunflower),
                "Al bañarte rápido ahorras agua"
            )
        )
        lista.add(
            Post(
                "Foto",
                BitmapFactory.decodeResource(resources,R.drawable.sunflower),
                "La ducha debe durar entre 5 y 10 min maximo"
            )
        )
        return lista
    }

    fun cargarCatalogoTemporizadorAgua():ArrayList<Post> {
        var lista = ArrayList<Post>()
        /*
        lista.add(Post("Foto",R.drawable.agua_logo,"Tienes 5 minutos"))
        lista.add(Post("Foto",R.drawable.agua_logo,"Tienes 5 minutos"))
        */
        return lista

    }

    fun cargarCatalogoHerramientasAgua():ArrayList<Post>{
        var lista = ArrayList<Post>()
        lista.add(
            Post(
                "Foto",
                BitmapFactory.decodeResource(resources,R.drawable.sunflower),
                "Sunflower By Post Malone ft. Swalee"
            )
        )
        lista.add(
            Post(
                "Foto",
                BitmapFactory.decodeResource(resources,R.drawable.awa),
                "Whiskey in the jar By Metallica"
            )
        )
        return lista
    }

    fun cargarCatalogoLitrosAhorradosAgua():ArrayList<Post>{
        var lista = ArrayList<Post>()
        lista.add(Post("Foto", BitmapFactory.decodeResource(resources,R.drawable.awa), "Ahorro: 150Lts "))
        return lista
    }


    fun cargarCatalogoInicioReciclaje():ArrayList<Post>{
        var lista = ArrayList<Post>()
        lista.add(
            Post(
                "Foto",
                BitmapFactory.decodeResource(resources,R.drawable.reciclaje_logo),
                "El reciclaje es lo máximo"
            )
        )
        lista.add(
            Post(
                "Foto",
                BitmapFactory.decodeResource(resources,R.drawable.reciclaje),
                "El mundo puede recuperarse"
            )
        )
        return lista
    }

    fun cargarCatalogoFavoritosReciclaje():ArrayList<Post>{
        var lista = ArrayList<Post>()
        lista.add(
            Post(
                "Foto",
                BitmapFactory.decodeResource(resources,R.drawable.reciclaje_logo),
                "El reciclaje es lo máximo"
            )
        )
        lista.add(
            Post(
                "Foto",
                BitmapFactory.decodeResource(resources,R.drawable.reciclaje),
                "El mundo puede recuperarse"
            )
        )
        return lista
    }

    fun cargarCatalogoDestacadosReciclaje():ArrayList<Post>{
        var lista = ArrayList<Post>()
        lista.add(
            Post(
                "Foto",
                BitmapFactory.decodeResource(resources,R.drawable.reciclaje_logo),
                "El reciclaje es lo máximo"
            )
        )
        lista.add(
            Post(
                "Foto",
                BitmapFactory.decodeResource(resources,R.drawable.reciclaje),
                "El mundo puede recuperarse"
            )
        )
        return lista
    }

    fun cargarCatalogoMisPostsReciclaje():ArrayList<Post>{
        var lista = ArrayList<Post>()
        lista.add(
            Post(
                "Foto",
                BitmapFactory.decodeResource(resources,R.drawable.reciclaje_logo),
                "El reciclaje es lo máximo"
            )
        )
        lista.add(
            Post(
                "Foto",
                BitmapFactory.decodeResource(resources,R.drawable.reciclaje),
                "El mundo puede recuperarse"
            )
        )
        return lista
    }*/


    inner class obtenerImagen : AsyncTask<Post,Void,Bitmap>(){

        override fun onPostExecute(result: Bitmap?) {
            //View.image.setImageBitmap(image)
        }

        override fun doInBackground(vararg params: Post?): Bitmap? {
            var post:Post? = params.get(0)
            val url = URL(post?.image)
            val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
            connection.setDoInput(true)
            connection.connect()
            val input: InputStream = connection.getInputStream()
            var bitmap = BitmapFactory.decodeStream(input)
            input.close()
            return bitmap
        }
    }

    inner class AdaptadorPosts: BaseAdapter {
        var contexto: Context? = null
        var opciones = ArrayList<Post>()

        constructor(context: Context, opciones:ArrayList<Post>){
            this.contexto=context
            this.opciones = opciones
        }
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var option = opciones[position]
            var inflator = LayoutInflater.from(contexto)
            var vista = inflator.inflate(R.layout.post_view, null)

            if(option.tipo.equals("Imagen")){
                vista.video.visibility = INVISIBLE
                var task = obtenerImagen()
                var image = task.execute(option).get()
                vista.image.setImageBitmap(image)
            } else {
                vista.video.visibility = VISIBLE
                //vista.video.setVideoURI(option.image)
            }
            vista.tv_title.setText(option.tipo)

            vista.descripcion.setText(option.descripcion)

            vista.image.setOnLongClickListener(OnLongClickListener { //Pulsación larga
                Toast.makeText(contexto, "YIAUAUUA", Toast.LENGTH_SHORT).show()
                false
            })

            return vista
        }

        override fun getItem(position: Int): Any {
            return opciones[position]
        }

        override fun getItemId(position: Int): Long {
            return 1
        }

        override fun getCount(): Int {
            return opciones.size
        }

    }
}
