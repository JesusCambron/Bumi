package data

class Datos {
    var tipo:String? = null
    var imageId:String? = null
    var descripcion:String? = null
    var key: String? = null
    var mageUrl: String? = null

    constructor() {

    }

    constructor(descripcion: String?, imageId: String?, tipo: String?) {
        this.tipo = tipo
        this.imageId = imageId
        this.descripcion = descripcion
    }
}