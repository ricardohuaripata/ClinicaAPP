package proyecto;

import java.util.Properties;
import java.io.*;

public class Configuracion {

    public static void main(String[] args) {

	String usuario = System.getProperty("user.name");
	String proyecto = "ClinicaAPP";

	Properties cfg = new Properties();
	// parametros de la base de datos
	cfg.setProperty("db", "clinica");
	cfg.setProperty("login", "root");
	cfg.setProperty("password", "");
	cfg.setProperty("url", "jdbc:mysql://127.0.0.1/" + cfg.getProperty("db"));
	cfg.setProperty("driver", "com.mysql.cj.jdbc.Driver");
	// rutas de acceso hacia los csv
	cfg.setProperty("dir_Pacientes",
		"C:\\Users\\" + usuario + "\\eclipse-workspace\\" + proyecto + "\\MATERIALES\\Pacientes.csv");
	cfg.setProperty("dir_Profesionales",
		"C:\\Users\\" + usuario + "\\eclipse-workspace\\" + proyecto + "\\MATERIALES\\Profesionales.csv");
	cfg.setProperty("dir_Tratamientos",
		"C:\\Users\\" + usuario + "\\eclipse-workspace\\" + proyecto + "\\MATERIALES\\Tratamientos.csv");
	cfg.setProperty("dir_Familias",
		"C:\\Users\\" + usuario + "\\eclipse-workspace\\" + proyecto + "\\MATERIALES\\Familias.csv");
	cfg.setProperty("dir_GrupoCaja",
		"C:\\Users\\" + usuario + "\\eclipse-workspace\\" + proyecto + "\\MATERIALES\\GrupoCaja.csv");
	cfg.setProperty("dir_ServiciosRealizados",
		"C:\\Users\\" + usuario + "\\eclipse-workspace\\" + proyecto + "\\MATERIALES\\ServiciosRealizados.csv");
	cfg.setProperty("dir_FCobro",
		"C:\\Users\\" + usuario + "\\eclipse-workspace\\" + proyecto + "\\MATERIALES\\FCobro.csv");
	cfg.setProperty("dir_Cobros",
		"C:\\Users\\" + usuario + "\\eclipse-workspace\\" + proyecto + "\\MATERIALES\\Cobros.csv");
	// rutas de acceso hacias los ficheros txt
	cfg.setProperty("dir_hombres",
		"C:\\Users\\" + usuario + "\\eclipse-workspace\\" + proyecto + "\\MATERIALES\\hombres.txt");
	cfg.setProperty("dir_mujeres",
		"C:\\Users\\" + usuario + "\\eclipse-workspace\\" + proyecto + "\\MATERIALES\\mujeres.txt");
	cfg.setProperty("dir_apellidos",
		"C:\\Users\\" + usuario + "\\eclipse-workspace\\" + proyecto + "\\MATERIALES\\apellidos.txt");

	try {

	    cfg.store(
		    new FileOutputStream(
			    "C:\\Users\\" + usuario + "\\eclipse-workspace\\" + proyecto + "\\MATERIALES\\clinica.cfg"),
		    "Fichero de configuracion");

	    System.out.println("DATABASE: " + cfg.getProperty("db"));
	    System.out.println("LOGIN: " + cfg.getProperty("login"));
	    System.out.println("PASSWORD: " + cfg.getProperty("password"));
	    System.out.println("URL: " + cfg.getProperty("url"));
	    System.out.println("DRIVER: " + cfg.getProperty("driver"));

	} catch (FileNotFoundException e) {
	    e.printStackTrace();

	} catch (IOException e) {
	    e.printStackTrace();
	}

    }

}
