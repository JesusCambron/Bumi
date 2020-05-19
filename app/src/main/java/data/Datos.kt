package data

class Datos {
    var id:String? = null
    var tipo:String? = null
    var imageId:String? = null
    var descripcion:String? = null
    var favorito: String? = null
    var destacado: String? = null
    var usuario:String? = null

    constructor() {

    }

    constructor(
        id: String?,
        tipo: String?,
        imageId: String?,
        descripcion: String?,
        favorito: String?,
        destacado: String?,usuario:String?
    ) {
        this.id = id
        this.tipo = tipo
        this.imageId = imageId
        this.descripcion = descripcion
        this.favorito = favorito
        this.destacado = destacado
        this.usuario = usuario
    }


}