package proyecto;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class CrearPacientes {

    public static Paciente nuevoPaciente(int codigo, ArrayList<String> men, ArrayList<String> women,
	    ArrayList<String> apellidos) {

	Paciente paciente = new Paciente();

	paciente.setGenero(random(1, 100) <= 45 ? "M" : "F");

	paciente.setNombre(paciente.getGenero() == "M" ? men.get(random(0, men.size() - 1))
		: women.get(random(0, men.size() - 1)));

	paciente.setApellidos(
		apellidos.get(random(0, apellidos.size() - 1)) + " " + apellidos.get(random(0, apellidos.size() - 1)));

	paciente.setNacimiento(nacimientoAleatorio(6, 100));
	paciente.setDni(
		ChronoUnit.YEARS.between(paciente.getNacimiento(), LocalDate.now()) >= 14 ? dniAleatorio() : null);
	paciente.setDireccion("Calle " + String.valueOf(random(1, 100)));
	paciente.setTelefono(random(600000000, 799999999));
	paciente.setCodigo(codigo);

	return paciente;
    }

    public static int random(int min, int max) {
	int numero = (int) Math.floor((max - min + 1) * Math.random() + min);
	return numero;
    }

    public static String dniAleatorio() {
	int numero = random(10000000, 99999999);
	String cadena = "TRWAGMYFPDXBNJZSQVHLCKET";
	int resto = numero % 23;
	char letra = cadena.charAt(resto);
	String dni = String.valueOf(numero) + letra;
	return dni;

    }

    public static LocalDate nacimientoAleatorio(int edadMin, int edadMax) {
	int anyo = random(LocalDate.now().getYear() - edadMin, LocalDate.now().getYear() - edadMax);

	LocalDate aux = Year.of(anyo).atDay(1);
	if (aux.isLeapYear()) {
	    LocalDate result = Year.of(anyo).atDay(random(1, 366));
	    return result;
	}

	int mes = random(1, 12);
	int dia = random(1, Month.of(mes).minLength());
	return LocalDate.of(anyo, mes, dia);
    }

}