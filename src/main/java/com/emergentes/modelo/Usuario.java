
package com.emergentes.modelo;

public class Usuario {
    private int id_usuario;
    private String ci;
    private String nombres;
    private String apellidos;
    private String direccion;
    private String calular;
    private String email;
    private String contrasena;
    private Cargo cargo;

    public Usuario() {
        
    }

    public Usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }
    

    public Usuario(int id_usuario, String ci, String nombres, String apellidos, String direccion, String calular, String email, String contrasena) {
        this.id_usuario = id_usuario;
        this.ci = ci;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.calular = calular;
        this.email = email;
        this.contrasena = contrasena;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCalular() {
        return calular;
    }

    public void setCalular(String calular) {
        this.calular = calular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }
    
}
