package bumi.emptyactivity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_opciones.*
import kotlinx.android.synthetic.main.pantalla_opciones.view.*

class Opciones : AppCompatActivity() {

    var listaDeOpcionesPlantas = ArrayList<Opcion>()
    var listaDeOpcionesAgua = ArrayList<Opcion>()
    var listaDeOpcionesReciclaje = ArrayList<Opcion>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var adaptador:AdaptadorOpciones?=null
        setContentView(R.layout.activity_opciones)
        val bundle = intent.extras
        if(bundle!=null){
            val type=bundle.getString("type")
            when(type) {
                "plantas"->{
                    cargarOpcionesPlantas()
                    adaptador = AdaptadorOpciones(this,listaDeOpcionesPlantas)
                    texto.setText("Plantas")
                    imagen.setImageResource(R.drawable.plantas)
                }
                "agua"->{
                    //cargarOpcionesAgua()
                    adaptador = AdaptadorOpciones(this,listaDeOpcionesPlantas)
                    texto.setText("Agua")
                    imagen.setImageResource(R.drawable.agua)
                }
                "reciclaje"->{
                    //cargarOpcionesReciclaje()
                    adaptador = AdaptadorOpciones(this,listaDeOpcionesPlantas)
                    texto.setText("Reciclaje")
                    imagen.setImageResource(R.drawable.reciclaje)
                }
            }
        }
        listaOpciones.adapter= adaptador
    }

    fun cargarOpcionesPlantas(){
        this.listaDeOpcionesPlantas.add(Opcion("Inicio",cargarCatalogoInicioPlantas()))
        this.listaDeOpcionesPlantas.add(Opcion("Favoritos",cargarCatalogoFavoritosPlantas()))
        this.listaDeOpcionesPlantas.add(Opcion("Destacados",cargarCatalogoDestacadosPlantas()))
        this.listaDeOpcionesPlantas.add(Opcion("Mis Plantas",cargarCatalogoMisPlantas()))
    }

    fun cargarCatalogoInicioPlantas():ArrayList<Post> {
        var lista = ArrayList<Post>()
        lista.add(Post("Foto",R.drawable.sunflower,"El dia de hoy vengo a compartirles el progreso de mi jardin de girasoles"))
        lista.add(Post("Foto",R.drawable.sunflower,"El dia de hoy vengo a compartirles el progreso de mi jardin de girasoles"))
        return lista
    }

    fun cargarCatalogoFavoritosPlantas():ArrayList<Post> {
        var lista = ArrayList<Post>()
        lista.add(Post("Foto",R.drawable.sunflower,"El dia de hoy vengo a compartirles el progreso de mi jardin de girasoles"))
        return lista
    }

    fun cargarCatalogoDestacadosPlantas():ArrayList<Post> {
        var lista = ArrayList<Post>()
        lista.add(Post("Foto",R.drawable.sunflower,"El dia de hoy vengo a compartirles el progreso de mi jardin de girasoles"))
        lista.add(Post("Foto",R.drawable.sunflower,"El dia de hoy vengo a compartirles el progreso de mi jardin de girasoles"))
        return lista
    }

    fun cargarCatalogoMisPlantas():ArrayList<Post> {
        var lista = ArrayList<Post>()
        lista.add(Post("Foto",R.drawable.sunflower,"El dia de hoy vengo a compartirles el progreso de mi jardin de girasoles"))
        lista.add(Post("Foto",R.drawable.sunflower,"El dia de hoy vengo a compartirles el progreso de mi jardin de girasoles"))
        return lista
    }
/*
    fun cargarOpcionesAgua(){
        this.listaDeOpcionesAgua.add("Consejo")
        this.listaDeOpcionesAgua.add("Temporizador")
        this.listaDeOpcionesAgua.add("Herramienta Canciones")
        this.listaDeOpcionesAgua.add("Litros ahorrados")
    }
    fun cargarOpcionesReciclaje(){
        this.listaDeOpcionesReciclaje.add("Inicio")
        this.listaDeOpcionesReciclaje.add("Favoritos")
        this.listaDeOpcionesReciclaje.add("Destacados")
        this.listaDeOpcionesReciclaje.add("Mis posts")
    }*/

    private class AdaptadorOpciones: BaseAdapter {
        var contexto: Context? = null
        var opciones = ArrayList<Opcion>()
        constructor(context: Context, opciones:ArrayList<Opcion>){
            this.contexto=context
            this.opciones = opciones
        }
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var option = opciones[position]
            //var inflator = LayoutInflater.from(contexto)
            var inflator = contexto!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var vista = inflator.inflate(R.layout.pantalla_opciones, null)
            vista.botonOpcion.setText(option.opcion)

            vista.botonOpcion.setOnClickListener{
                var intento = Intent(contexto,PantallaPost::class.java)
                intento.putExtra("opcion",option.opcion)
                intento.putExtra("catalogo",option.catalogo)
                contexto!!.startActivity(intento)
            }

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
