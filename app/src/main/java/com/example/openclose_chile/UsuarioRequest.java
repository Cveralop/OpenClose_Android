package com.example.openclose_chile;

public class UsuarioRequest {
    public String idUsuario;
    public String nombre;
    public String apellido;
    public Integer clave;
    public Integer tipo;

    public UsuarioRequest(String idUsuario, String nombre, String apellido, Integer clave, Integer tipo) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.clave = clave;
        this.tipo = tipo;
    }

    public UsuarioRequest() {
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Integer getClave() {
        return clave;
    }

    public void setClave(Integer clave) {
        this.clave = clave;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }


}
