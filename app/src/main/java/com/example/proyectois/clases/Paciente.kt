package com.example.proyectois.clases

class Paciente(
    var nombre: String,
    var apellidoP: String,
    var apellidoM: String,
    var telefono: Int,
    var contraseña: String,
    var tipoSanguineo: String,
    var tipoCronico: String?,
    var alergiasComunes: Array<String>?, //El ? indica que puede ser nulo y no sea necesario escribirlo en el constructor
    var alergiasMedicamentos: Array<String>?,
    var tratamientos: Array<Tratamiento>?,
    var vitales : Array<SignoVital>?
) {

}