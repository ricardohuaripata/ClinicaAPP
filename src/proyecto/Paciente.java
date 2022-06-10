package proyecto;

import java.time.LocalDate;
import java.util.Objects;
import java.time.temporal.ChronoUnit;

public class Paciente extends Persona implements Comparable<Paciente> {

    int codigo;

    public Paciente() {

    }

    public Paciente(String nom, String ap, LocalDate nac, String gen, String dni, String direc, int tel, int cod) {
	super(nom, ap, nac, gen, dni, direc, tel);
	this.codigo = cod;
    }

    public int getCodigo() {
	return codigo;
    }

    public void setCodigo(int codigo) {
	this.codigo = codigo;
    }

    public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + Objects.hash(codigo);
	return result;
    }

    public boolean equals(Object obj) {

	Paciente other = (Paciente) obj;
	return nombre == other.nombre && apellidos == other.apellidos
		&& ChronoUnit.YEARS.between(nacimiento.compareTo(other.nacimiento) < 0 ? nacimiento : other.nacimiento,
			nacimiento.compareTo(other.nacimiento) > 0 ? nacimiento : other.nacimiento) < 10 || dni == other.dni;

    }

    @Override
    public String toString() {
	return "Paciente [Codigo: " + codigo + ", " + super.toString() + "]";
    }

    @Override
    public int compareTo(Paciente p) {
	return p.getNacimiento().compareTo(this.getNacimiento());
    }

}