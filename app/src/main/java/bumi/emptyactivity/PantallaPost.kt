package bumi.emptyactivity

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Toast
import data.Post
import kotlinx.android.synthetic.main.activity_pantalla_post.*
import kotlinx.android.synthetic.main.post_view.view.*

class PantallaPost : AppCompatActivity() {

    companion object{
        var posts = ArrayList<Post>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_post)

        val bundle = intent.extras
        var adaptador:AdaptadorPosts? = null

        if(bundle != null) {
            val type = bundle.getString("tipo")
            when(type){
                "plantasInicio" -> adaptador = AdaptadorPosts(this,posts)
                "plantasFavoritos" -> adaptador = AdaptadorPosts(this,cargarCatalogoFavoritosPlantas())
                "plantasDestacados" -> adaptador = AdaptadorPosts(this,cargarCatalogoDestacadosPlantas())
                "plantasMisPlantas" -> adaptador = AdaptadorPosts(this,cargarCatalogoMisPlantas())

                "aguaConsejos" -> adaptador = AdaptadorPosts(this,cargarCatalogoConsejosAgua())
                "aguaTemporizador" -> adaptador = AdaptadorPosts(this,cargarCatalogoTemporizadorAgua())
                "aguaCanciones" -> adaptador = AdaptadorPosts(this,cargarCatalogoHerramientasAgua())
                "aguaLitros" -> adaptador = AdaptadorPosts(this,cargarCatalogoLitrosAhorradosAgua())

                "reciclajeInicio" -> adaptador = AdaptadorPosts(this,cargarCatalogoInicioPlantas())
                "reciclajeFavoritos" -> adaptador = AdaptadorPosts(this,cargarCatalogoFavoritosPlantas())
                "reciclajeMispost" -> adaptador = AdaptadorPosts(this,cargarCatalogoDestacadosPlantas())
                "reciclajeReciclaje" -> adaptador = AdaptadorPosts(this,cargarCatalogoMisPlantas())

            }

        }


        botonAgregar.setOnClickListener{
            var intento: Intent = Intent(this, AgregarPost::class.java)
            startActivity(intento)

        }
        refrescar.setOnClickListener {
            this.finish()
            startActivity(this.intent)
        }
        listaPost.adapter = adaptador
    }

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
    }



    private class AdaptadorPosts: BaseAdapter {
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

            vista.tv_title.setText(option.tipo)
            vista.image.setImageBitmap(option.image)
            vista.descripcion.setText(option.descripcion)

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
