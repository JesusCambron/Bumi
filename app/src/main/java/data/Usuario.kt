package data

class Usuario {
    var nombre:String? = null
    var correo:String? = null

    constructor() {

    }
    constructor(nombre: String?, correo: String?) {
        this.nombre = nombre
        this.correo = correo
    }
}