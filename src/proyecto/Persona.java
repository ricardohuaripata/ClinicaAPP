package proyecto;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Persona {

    String nombre;
    String apellidos;
    LocalDate nacimiento;
    String genero;
    String dni;
    String direccion;
    int telefono;

    public Persona() {

    }

    public Persona(String nom, String ap, LocalDate nac, String gen, String dni, String direc, int tel) {
	this.nombre = nom;
	this.apellidos = ap;
	this.nacimiento = nac;
	this.genero = gen;
	this.dni = dni;
	this.direccion = direc;
	this.telefono = tel;
    }

    public String getNombre() {
	return nombre;
    }

    public void setNombre(String nombre) {
	this.nombre = nombre;
    }

    public String getApellidos() {
	return apellidos;
    }

    public void setApellidos(String apellidos) {
	this.apellidos = apellidos;
    }

    public LocalDate getNacimiento() {
	return nacimiento;
    }

    public int getEdad() {
	return Integer.valueOf((int) ChronoUnit.YEARS.between(nacimiento, LocalDate.now()));
    }

    public void setNacimiento(LocalDate nacimiento) {
	this.nacimiento = nacimiento;
    }

    public String getGenero() {
	return genero;
    }

    public void setGenero(String genero) {
	this.genero = genero;
    }

    public String getDni() {
	return dni;
    }

    public void setDni(String dni) {
	this.dni = dni;
    }

    public String getDireccion() {
	return direccion;
    }

    public void setDireccion(String direccion) {
	this.direccion = direccion;
    }

    public int getTelefono() {
	return telefono;
    }

    public void setTelefono(int telefono) {
	this.telefono = telefono;
    }

    public int hashCode() {
	return Objects.hash(apellidos, direccion, dni, genero, nacimiento, nombre, telefono);
    }

    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Persona other = (Persona) obj;
	return Objects.equals(apellidos, other.apellidos) && Objects.equals(direccion, other.direccion)
		&& Objects.equals(dni, other.dni) && Objects.equals(genero, other.genero)
		&& Objects.equals(nacimiento, other.nacimiento) && Objects.equals(nombre, other.nombre)
		&& telefono == other.telefono;
    }

    @Override
    public String toString() {
	return "Nombre: " + nombre + ", Apellidos: " + apellidos + ", Nacimiento: " + nacimiento + ", Genero: " + genero
		+ ", DNI: " + dni + ", Direccion: " + direccion + ", Telefono: " + telefono;
    }

}