package proyecto;

import java.util.Scanner;
import java.io.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Properties;

public class ClinicaApp {

    static Properties cfg = new Properties(); // se declara del fichero como statico para usarlo en todo el programa
    static Connection conexion; // lo mismo con la conexion, para asi evitar abrir y cerrar en cada metodo

    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {

	int opcionMenu;
	int opcionSubmenu;
	String dir_configuracion = "C:\\Users\\" + System.getProperty("user.name")
		+ "\\eclipse-workspace\\ClinicaAPP\\MATERIALES\\clinica.cfg";
	// cargo el fichero
	cfg.load(new BufferedReader(new FileReader(dir_configuracion)));

	Class.forName(cfg.getProperty("driver"));
	// establece la conexion
	conexion = DriverManager.getConnection(cfg.getProperty("url"), cfg.getProperty("login"),
		cfg.getProperty("password"));

	System.out.println("\nConexion a la base de datos '" + cfg.getProperty("db") + "' establecida");

	do {

	    opcionMenu = menu(0, 7);

	    switch (opcionMenu) {

	    case 1:

		do {
		    opcionSubmenu = submenu(0, 3); // submenu para administrar la base de datos

		    switch (opcionSubmenu) {

		    case 1:
			generarPacientesCSV();
			break;
		    case 2:
			instalacionBBDD();
			break;
		    case 3:
			implementarDatosBBDD();
			break;
		    }
		} while (opcionSubmenu != 0);

		break;

	    case 2:
		insertarServicioBBDD();
		break;
	    case 3:
		insertarCobroBBDD();
		break;
	    case 4:
		liquidacionProfesionalesBBDD();
		break;
	    case 5:
		consultarHistorialPacienteBBDD();
		break;
	    case 6:
		consultarLiquidacionesPendientesBBDD();
		break;
	    case 7:
		resumenImportesTratamientosBBDD();
		break;

	    }

	} while (opcionMenu != 0);

	conexion.close();

	System.out.println("FIN DEL PROGRAMA");

    }

    public static int menu(int min, int max) {
	Scanner sc = new Scanner(System.in);
	int opcion = 0;
	boolean repetir;

	System.out.println("\n=== MENU ====");
	System.out.println("1 - (Administrar BBDD)");
	System.out.println("2 - Insertar servicio");
	System.out.println("3 - Insertar cobro");
	System.out.println("4 - Realizar liquidacion de profesionales");
	System.out.println("5 - Historial de un paciente");
	System.out.println("6 - Liquidaciones pendientes de profesionales");
	System.out.println("7 - Resumen de importes");
	System.out.println("0 - Salir");

	do {
	    repetir = false;
	    try {
		System.out.print("Seleccionar Opcion: ");
		opcion = sc.nextInt();
		if (opcion < min || opcion > max) {
		    System.err.println("Debes elegir una opcion entre " + min + " y " + max);
		    repetir = true;
		}
	    } catch (Exception e) {
		System.err.println("Error, vuelve a intentarlo");
		repetir = true;
	    } finally {
		sc.nextLine();
	    }

	} while (repetir == true);
	return opcion;

    }

    public static int submenu(int min, int max) {
	Scanner sc = new Scanner(System.in);
	int opcion = 0;
	boolean repetir;

	System.out.println("\n=== MENU ====");
	System.out.println("1 - (NEW_FILE) Generar CSV de pacientes");
	System.out.println("2 - (CREATE_TABLE) Montar la BBDD");
	System.out.println("3 - (INSERT) Implementar datos a la BBDD");
	System.out.println("0 - Salir");

	do {
	    repetir = false;
	    try {
		System.out.print("Seleccionar Opcion: ");
		opcion = sc.nextInt();
		if (opcion < min || opcion > max) {
		    System.err.println("Debes elegir una opcion entre " + min + " y " + max);
		    repetir = true;
		}
	    } catch (Exception e) {
		System.err.println("Error, vuelve a intentarlo");
		repetir = true;
	    } finally {
		sc.nextLine();
	    }

	} while (repetir == true);
	return opcion;

    }

    public static int random(int min, int max) {
	int numero = (int) Math.floor((max - min + 1) * Math.random() + min);
	return numero;
    }

    public static ArrayList<String> ficheroArrayList(ArrayList<String> lista, String fichero) throws IOException {
	BufferedReader br = new BufferedReader(new FileReader(fichero));
	String line;
	while ((line = br.readLine()) != null) {
	    lista.add(line);
	}
	br.close();
	return lista;
    }

    public static void generarPacientesCSV() {
	// GENERAR FICHERO CSV CON DATOS DE PACIENTES FICTICIOS
	ArrayList<Paciente> listaPacientes = new ArrayList();
	ArrayList<String> men = new ArrayList();
	ArrayList<String> women = new ArrayList();
	ArrayList<String> listaApellidos = new ArrayList();

	try {

	    ficheroArrayList(men, cfg.getProperty("dir_hombres"));
	    ficheroArrayList(women, cfg.getProperty("dir_mujeres"));
	    ficheroArrayList(listaApellidos, cfg.getProperty("dir_apellidos"));

	    for (int i = 0; i < 100; i++) { // funcion mejorada
		int codigo = i + 1;
		boolean ok;
		Paciente paciente;

		do {
		    ok = true;
		    paciente = CrearPacientes.nuevoPaciente(codigo, men, women, listaApellidos);
		    for (int j = 0; j < listaPacientes.size(); j++) {
			if (listaPacientes.get(j).equals(paciente))
			    ok = false;
		    }
		} while (ok == false);

		listaPacientes.add(paciente);
	    }

	    BufferedWriter writer = new BufferedWriter(new FileWriter(cfg.getProperty("dir_Pacientes")));

	    for (int i = 0; i < listaPacientes.size(); i++) {

		writer.write(
			(i + 1) + ";" + listaPacientes.get(i).getNombre() + ";" + listaPacientes.get(i).getApellidos()
				+ ";" + listaPacientes.get(i).getNacimiento() + ";" + listaPacientes.get(i).getGenero()
				+ ";" + listaPacientes.get(i).getDni() + ";" + listaPacientes.get(i).getDireccion()
				+ ";" + listaPacientes.get(i).getTelefono() + ";" + listaPacientes.get(i).getCodigo());

		writer.newLine();

	    }

	    writer.close();

	    System.out.println("Hecho.");

	} catch (IOException e) {
	    System.out.println(e);
	}

    }

    public static void instalacionBBDD() {

	try {

	    Statement st = conexion.createStatement();

	    System.out.println("\nEjecutando...");
	    System.out.println("Creando tabla 'pacientes'...");
	    st.executeUpdate("CREATE TABLE Pacientes(" + "idPaciente INT AUTO_INCREMENT PRIMARY KEY,"
		    + "nombre VARCHAR(250) character set utf8 NOT NULL,"
		    + "apellidos VARCHAR(250) character set utf8 NOT NULL," + "nacimiento DATE NOT NULL,"
		    + "genero VARCHAR(1) NOT NULL," + "dni VARCHAR(9) NULL," + "direccion VARCHAR(500) NOT NULL,"
		    + "telefono INT NOT NULL," + "codigo INT NOT NULL);");

	    System.out.println("Creando tabla 'profesionales'...");
	    st.executeUpdate("CREATE TABLE Profesionales(" + "idProfesional INT AUTO_INCREMENT PRIMARY KEY,"
		    + "nombre VARCHAR(250) character set utf8 NOT NULL,"
		    + "apellidos VARCHAR(250) character set utf8 NOT NULL," + "nacimiento DATE NOT NULL,"
		    + "dni VARCHAR(9) NOT NULL," + "direccion VARCHAR(250) NOT NULL," + "telefono INT NOT NULL,"
		    + "comision DECIMAL(18,2) NOT NULL);");

	    System.out.println("Creando tabla 'familias'...");
	    st.executeUpdate("CREATE TABLE Familias(" + "idFamilia INT AUTO_INCREMENT PRIMARY KEY,"
		    + "nombre VARCHAR(250) character set utf8 NOT NULL);");

	    System.out.println("Creando tabla 'tratamientos'...");
	    st.executeUpdate("CREATE TABLE Tratamientos(" + "idTratamiento INT AUTO_INCREMENT PRIMARY KEY,"
		    + "codTto VARCHAR(10) NOT NULL," + "nombre VARCHAR(250) character set utf8 NOT NULL,"
		    + "precio DECIMAL(18,2) NOT NULL," + "idFamilia INT NOT NULL,"
		    + "CONSTRAINT fkFamiliaTratamiento FOREIGN KEY(idFamilia) REFERENCES Familias(idFamilia));");

	    System.out.println("Creando tabla 'grupocaja'...");
	    st.executeUpdate("CREATE TABLE GrupoCaja(" + "idGrupoCaja INT AUTO_INCREMENT PRIMARY KEY,"
		    + "nombre VARCHAR(250) character set utf8 NOT NULL);");

	    System.out.println("Creando tabla 'fcobro'...");
	    st.executeUpdate("CREATE TABLE FCobro(" + "idFCobro INT AUTO_INCREMENT PRIMARY KEY,"
		    + "nombre VARCHAR(250) character set utf8 NOT NULL," + "idGrupoCaja INT NOT NULL,"
		    + "CONSTRAINT fkGrupoCajaFCobro FOREIGN KEY(idGrupoCaja) REFERENCES GrupoCaja(idGrupoCaja));");

	    System.out.println("Creando tabla 'liquidaciones'...");
	    st.executeUpdate("CREATE TABLE Liquidaciones(" + "idLiquidacion INT AUTO_INCREMENT PRIMARY KEY,"
		    + "fecha DATE NOT NULL," + "comision DECIMAL(18, 2) NOT NULL," + "idProfesional INT NOT NULL,"
		    + "CONSTRAINT fkProfesionalLiquidacion FOREIGN KEY(idProfesional) REFERENCES Profesionales(idProfesional));");

	    System.out.println("Creando tabla 'serviciosrealizados'...");
	    st.executeUpdate("CREATE TABLE ServiciosRealizados(" + "idServicio INT AUTO_INCREMENT PRIMARY KEY,"
		    + "fecha DATE NOT NULL," + "idPaciente INT NOT NULL,"
		    + "CONSTRAINT fkPacienteServicio FOREIGN KEY(idPaciente) REFERENCES Pacientes(idPaciente),"
		    + "idProfesional INT NOT NULL,"
		    + "CONSTRAINT fkProfesionalServicio FOREIGN KEY(idProfesional) REFERENCES Profesionales(idProfesional),"
		    + "idTratamiento INT NOT NULL,"
		    + "CONSTRAINT fkTratamientoServicio FOREIGN KEY(idTratamiento) REFERENCES Tratamientos(idTratamiento),"
		    + "precio DECIMAL(18,2) NOT NULL, cobrado DECIMAL(18,2) NULL," + "idLiquidacion INT NULL,"
		    + "FOREIGN KEY(idLiquidacion) REFERENCES Liquidaciones(idLiquidacion));");

	    System.out.println("Creando tabla 'cobros'...");
	    st.executeUpdate("CREATE TABLE Cobros(" + "idCobro INT AUTO_INCREMENT PRIMARY KEY," + "fecha DATE NOT NULL,"
		    + "idPaciente INT NOT NULL,"
		    + "CONSTRAINT fkPacienteCobro FOREIGN KEY(idPaciente) REFERENCES Pacientes(idPaciente),"
		    + "idFCobro INT NOT NULL,"
		    + "CONSTRAINT fkFCobroCobro FOREIGN KEY(idFCobro) REFERENCES FCobro(idFCobro),"
		    + "cobrado DECIMAL(18,2) NOT NULL," + "imputado DECIMAL(18,2) NOT NULL, idServicio INT NOT NULL,"
		    + "CONSTRAINT fkServicioCobro FOREIGN KEY(idServicio) REFERENCES serviciosrealizados(idServicio));");

	    System.out.println("Todas las tablas han sido creadas correctamente");

	    st.close();

	} catch (SQLException e) {
	    System.out.println(e);
	} catch (Exception e) {
	    System.out.println(e);
	}

    }

    public static void implementarDatosBBDD() {

	String line;

	try {
	    // usar preparedstament me permitira evitar que se corten la sentencias por
	    // culpa de cadenas con comillas simples
	    PreparedStatement ps = null;

	    BufferedReader br1 = new BufferedReader(new FileReader(cfg.getProperty("dir_Pacientes")));
	    BufferedReader br2 = new BufferedReader(new FileReader(cfg.getProperty("dir_Profesionales")));
	    BufferedReader br3 = new BufferedReader(new FileReader(cfg.getProperty("dir_Familias")));
	    BufferedReader br4 = new BufferedReader(new FileReader(cfg.getProperty("dir_Tratamientos")));
	    BufferedReader br5 = new BufferedReader(new FileReader(cfg.getProperty("dir_GrupoCaja")));
	    BufferedReader br6 = new BufferedReader(new FileReader(cfg.getProperty("dir_FCobro")));
	    BufferedReader br7 = new BufferedReader(new FileReader(cfg.getProperty("dir_ServiciosRealizados")));
	    BufferedReader br8 = new BufferedReader(new FileReader(cfg.getProperty("dir_Cobros")));

	    System.out.println("Ejecutando...");

	    System.out.println("Insertando en 'pacientes'...");
	    while ((line = br1.readLine()) != null) {

		String[] ar = line.split(";");
		ps = conexion.prepareStatement(
			"INSERT INTO pacientes(idPaciente,nombre,apellidos,nacimiento,genero,dni,direccion,telefono,codigo) VALUES (?,?,?,?,?,?,?,?,?)");

		ps.setString(1, ar[0]);
		ps.setString(2, ar[1]);
		ps.setString(3, ar[2]);
		ps.setString(4, ar[3]);
		ps.setString(5, ar[4]);
		ps.setString(6, (ar[5].equalsIgnoreCase("null")) ? null : ar[5]);
		ps.setString(7, ar[6]);
		ps.setString(8, ar[7]);
		ps.setString(9, ar[8]);

		ps.executeUpdate();

	    }
	    br1.close();

	    System.out.println("Insertando en 'profesionales'...");
	    while ((line = br2.readLine()) != null) {

		String[] ar = line.split(";");
		ps = conexion.prepareStatement(
			"INSERT INTO profesionales(idProfesional,nombre,apellidos,nacimiento,dni,direccion,telefono,comision) VALUES (?,?,?,?,?,?,?,?)");

		ps.setString(1, ar[0]);
		ps.setString(2, ar[1]);
		ps.setString(3, ar[2]);
		ps.setString(4, ar[3]);
		ps.setString(5, ar[4]);
		ps.setString(6, ar[5]);
		ps.setString(7, ar[6]);
		ps.setString(8, ar[7]);

		ps.executeUpdate();

	    }
	    br2.close();

	    System.out.println("Insertando en 'familias'...");
	    while ((line = br3.readLine()) != null) {

		String[] ar = line.split(";");
		ps = conexion.prepareStatement("INSERT INTO familias(idFamilia,nombre) VALUES (?,?)");
		ps.setString(1, ar[0]);
		ps.setString(2, ar[1]);

		ps.executeUpdate();

	    }
	    br3.close();

	    System.out.println("Insertando en 'tratamientos'...");
	    while ((line = br4.readLine()) != null) {

		String[] ar = line.split(";");
		ps = conexion.prepareStatement(
			"INSERT INTO tratamientos(idTratamiento,codTto,nombre,precio,idFamilia) VALUES (?,?,?,?,?)");
		ps.setString(1, ar[0]);
		ps.setString(2, ar[1]);
		ps.setString(3, ar[2]);
		ps.setString(4, ar[3]);
		ps.setString(5, ar[4]);

		ps.executeUpdate();

	    }
	    br4.close();

	    System.out.println("Insertando en 'grupocaja'...");
	    while ((line = br5.readLine()) != null) {

		String[] ar = line.split(";");
		ps = conexion.prepareStatement("INSERT INTO grupocaja(idGrupoCaja,nombre) VALUES (?,?)");
		ps.setString(1, ar[0]);
		ps.setString(2, ar[1]);

		ps.executeUpdate();

	    }
	    br5.close();

	    System.out.println("Insertando en 'fcobro'...");
	    while ((line = br6.readLine()) != null) {

		String[] ar = line.split(";");
		ps = conexion.prepareStatement("INSERT INTO fcobro(idFCobro,nombre,idGrupoCaja) VALUES (?,?,?)");
		ps.setString(1, ar[0]);
		ps.setString(2, ar[1]);
		ps.setString(3, ar[2]);

		ps.executeUpdate();

	    }
	    br6.close();

	    System.out.println("Insertando en 'serviciosrealizados'...");
	    while ((line = br7.readLine()) != null) {

		String[] ar = line.split(";");
		ps = conexion.prepareStatement(
			"INSERT INTO serviciosrealizados(idServicio,fecha,idPaciente,idProfesional,idTratamiento,precio,cobrado,idLiquidacion) VALUES (?,?,?,?,?,?,?,?)");
		ps.setString(1, ar[0]);
		ps.setString(2, ar[1]);
		ps.setString(3, ar[2]);
		ps.setString(4, ar[3]);
		ps.setString(5, ar[4]);
		ps.setString(6, ar[5]);
		ps.setString(7, (ar[6].equalsIgnoreCase("null")) ? null : ar[6]);
		ps.setString(8, (ar[7].equalsIgnoreCase("null")) ? null : ar[7]);

		ps.executeUpdate();

	    }
	    br7.close();

	    System.out.println("Insertando en 'cobros'...");
	    while ((line = br8.readLine()) != null) {

		String[] ar = line.split(";");
		ps = conexion.prepareStatement(
			"INSERT INTO cobros(idCobro,fecha,idPaciente,idFCobro,cobrado,imputado,idServicio) VALUES (?,?,?,?,?,?,?)");
		ps.setString(1, ar[0]);
		ps.setString(2, ar[1]);
		ps.setString(3, ar[2]);
		ps.setString(4, ar[3]);
		ps.setString(5, ar[4]);
		ps.setString(6, ar[5]);
		ps.setString(7, ar[6]);

		ps.executeUpdate();

	    }
	    br8.close();

	    System.out.println("Todos los datos han sido insertados correctamente");

	    ps.close();

	} catch (SQLException e) {
	    System.out.println(e);
	} catch (IOException e) {
	    System.out.println(e);
	} catch (Exception e) {
	    System.out.println(e);
	}

    }

    public static void insertarPacienteBBDD() {

	Scanner sc = new Scanner(System.in);

	boolean valido = true;
	// el bucle se repetiria en caso de que diese algun error que no permitiese
	// insertar al paciente con los datos que ha indicado
	do {

	    try {

		valido = true;

		Statement st = conexion.createStatement();

		System.out.println("\n=== Registrar nuevo paciente ===");

		LocalDate fechaNacimiento = leerFechaValida("Fecha de nacimiento", "yyyy-MM-dd");

		System.out.print("Nombre: ");
		String nombre = sc.nextLine();
		System.out.print("Apellidos: ");
		String apellidos = sc.nextLine();
		System.out.print("DNI('NULL' en caso de no tener): ");
		String dni = sc.nextLine();
		// si se introduce 'null', el campo se establece con valor null, ya que pueden
		// haber
		// paciente menores de 14 años
		dni = dni.equalsIgnoreCase("null") ? "NULL" : "'" + dni + "'";
		System.out.print("Genero('M':'F'): ");
		String genero = sc.nextLine();
		System.out.print("Direccion: ");
		String direccion = sc.nextLine();
		System.out.print("Telefono: ");
		int telefono = sc.nextInt();

		int codigo = validarRegistroNoExistente("Codigo: ", "pacientes", "codigo");

		st.executeUpdate(
			"INSERT INTO pacientes(nombre,apellidos,nacimiento,genero,dni,direccion,telefono,codigo) VALUES('"
				+ nombre + "','" + apellidos + "'," + "(SELECT STR_TO_DATE('"
				+ fechaNacimiento.getDayOfMonth() + "," + fechaNacimiento.getMonthValue() + ","
				+ fechaNacimiento.getYear() + "' ,'%d,%m,%Y'))" + ",'" + genero + "'," + dni + ",'"
				+ direccion + "'," + telefono + "," + codigo + ");");

		st.close();

	    } catch (SQLException e) {
		System.err.println(e);
		valido = false;
	    } catch (InputMismatchException e) {
		System.err.println("ERROR AL INTRODUCIR DATOS");
		valido = false;
	    } catch (Exception e) {
		System.out.println(e);
		valido = false;
	    }

	} while (valido == false);

    }

    public static void insertarServicioBBDD() {

	Scanner sc = new Scanner(System.in);

	try {

	    Statement st = conexion.createStatement();
	    ResultSet rs;

	    int idPaciente = 0;
	    LocalDate fechaServicio = leerFechaValida("Fecha del servicio", "yyyy-MM-dd");

	    System.out.print("(IDENTIFICANDO)Nombre del paciente: ");
	    String nombre = sc.nextLine();
	    System.out.print("(IDENTIFICANDO)Apellidos del paciente: ");
	    String apellidos = sc.nextLine();
	    System.out.print("(IDENTIFICANDO)DNI del paciente('NULL' en caso de no tener): ");
	    String dni = sc.nextLine();
	    String busqueda;
	    // se localiza al paciente con dichos datos
	    if (dni.equalsIgnoreCase("NULL"))
		busqueda = "SELECT idPaciente FROM pacientes WHERE nombre='" + nombre + "' AND apellidos='" + apellidos
			+ "' AND dni IS NULL;";
	    else
		busqueda = "SELECT idPaciente FROM pacientes WHERE nombre='" + nombre + "' AND apellidos='" + apellidos
			+ "' AND dni='" + dni + "';";

	    rs = st.executeQuery(busqueda);

	    while (rs.next()) {
		idPaciente = rs.getInt(1);
	    }
	    // si no se han encontrado registro signicara que no habra entrado en el bucle y
	    // la variable seguira valiendo 0
	    if (idPaciente == 0) {
		insertarPacienteBBDD(); // por tanto se procede a registrar un nuevo paciente para el servicio

		rs = st.executeQuery("SELECT MAX(idPaciente) FROM pacientes"); // se obtiene la id del ultimo paciente
		// insertado

		while (rs.next()) {
		    idPaciente = rs.getInt(1);
		}
	    }

	    int idProfesional = validarRegistroExistente("Introducir profesional(ID): ", "profesionales",
		    "idProfesional");

	    int idTratamiento = validarRegistroExistente("Introducir tratamiento(ID): ", "tratamientos",
		    "idTratamiento");

	    rs = st.executeQuery("SELECT nombre, precio FROM tratamientos WHERE idTratamiento=" + idTratamiento + ";");

	    while (rs.next()) {
		System.out
			.println("NOMBRE TRATAMIENTO: " + rs.getString(1) + ", PRECIO TRATAMIENTO: " + rs.getString(2));
	    }

	    st.executeUpdate(
		    "INSERT INTO serviciosrealizados(fecha,idPaciente,idProfesional,idTratamiento,precio) VALUES("
			    + "(SELECT STR_TO_DATE('" + fechaServicio.getDayOfMonth() + ","
			    + fechaServicio.getMonthValue() + "," + fechaServicio.getYear() + "' ,'%d,%m,%Y'))" + ","
			    + idPaciente + "," + idProfesional + "," + idTratamiento
			    + ",(SELECT precio FROM tratamientos WHERE idTratamiento=" + idTratamiento + "));");

	    st.close();
	    rs.close();

	} catch (SQLException e) {
	    System.out.println(e);
	} catch (InputMismatchException e) {
	    System.err.println("ERROR AL INTRODUCIR DATOS");
	} catch (Exception e) {
	    System.out.println(e);
	}

    }

    public static void insertarCobroBBDD() {

	Scanner sc = new Scanner(System.in);

	try {
	    conexion.setAutoCommit(false);
	    Statement st = conexion.createStatement();
	    ResultSet rs;

	    int idServicio = validarRegistroExistente("Introducir servicio(ID): ", "serviciosrealizados", "idServicio");

	    rs = st.executeQuery("SELECT nombre, precio FROM tratamientos WHERE idTratamiento= "
		    + "(SELECT idTratamiento FROM serviciosrealizados WHERE idServicio=" + idServicio + ");");

	    while (rs.next()) {
		System.out
			.println("NOMBRE TRATAMIENTO: " + rs.getString(1) + ", PRECIO TRATAMIENTO: " + rs.getString(2));
	    }

	    LocalDate fechaCobro = leerFechaValida("Fecha del cobro", "yyyy-MM-dd");

	    int idFCobro = validarRegistroExistente("Introducir forma de de cobro(ID): ", "FCobro", "idFCobro");

	    System.out.print("Introducir total cobrado: ");
	    double cobrado = sc.nextDouble();

	    double imputado = cobrado;

	    rs = st.executeQuery(
		    "SELECT IFNULL(sum(c.cobrado), 0) FROM cobros c " + "INNER JOIN serviciosrealizados sr "
			    + "ON c.idServicio=sr.idServicio WHERE sr.idServicio=" + idServicio);

	    while (rs.next()) {
		imputado += rs.getDouble(1);
	    }

	    st.executeUpdate("INSERT INTO cobros(fecha,idPaciente,idFCobro,cobrado,imputado, idServicio) VALUES("
		    + "(SELECT STR_TO_DATE('" + fechaCobro.getDayOfMonth() + "," + fechaCobro.getMonthValue() + ","
		    + fechaCobro.getYear() + "' ,'%d,%m,%Y'))"
		    + ", (SELECT idPaciente FROM serviciosrealizados WHERE idServicio=" + idServicio + ")," + idFCobro
		    + "," + cobrado + "," + imputado + "," + idServicio + ");");
	    // se actualiza el campo cobrado de la tabla servicios, donde se le suma lo
	    // cobrado
	    st.executeUpdate(
		    "UPDATE serviciosrealizados SET cobrado=" + imputado + " WHERE idServicio=" + idServicio + ";");
	    
	    conexion.commit();
	    conexion.setAutoCommit(true);
	    st.close();
	    rs.close();

	} catch (SQLException e) {
	    try {
		conexion.rollback(); // si algo falla se deshace todo lo anterior
	    } catch (SQLException e1) {
		e1.printStackTrace();
	    }
	} catch (InputMismatchException e) {
	    System.err.println("ERROR AL INTRODUCIR DATOS");
	} catch (Exception e) {
	    System.out.println(e);
	}

    }

    public static void liquidacionProfesionalesBBDD() {

	try {
	    conexion.setAutoCommit(false); // desactivamos autocomit para esta seccion
	    Statement st = conexion.createStatement();
	    Statement st2 = conexion.createStatement();
	    ResultSet rs;

	    LocalDate fechaLiquidacion = leerFechaValida("Fecha de liquidacion", "yyyy-MM-dd");

	    System.out.println("\nServicios a liquidar a fecha(" + fechaLiquidacion + "):");
	    // se liquidaran todos aquellos servicios cuya fecha sea igual o inferior a la
	    // introducida por el usario y ademas, no esté ya liquidado
	    rs = st.executeQuery("SELECT * FROM serviciosrealizados WHERE cobrado>=precio AND fecha<='"
		    + fechaLiquidacion + "' AND idLiquidacion IS NULL;");

	    while (rs.next()) {

		System.out.println("IDSERVICIO: " + rs.getString("idServicio") + ", FECHA: " + rs.getString("fecha")
			+ ", IDPROFESIONAL: " + rs.getString("idProfesional") + ", IDPACIENTE: "
			+ rs.getString("idPaciente") + ", PRECIO: (" + rs.getString("precio") + "), COBRADO: ("
			+ rs.getDouble("cobrado") + ")");

		st2.executeUpdate("INSERT INTO liquidaciones(fecha,comision,idProfesional) VALUES( NOW(), "
			+ "(((SELECT precio FROM serviciosrealizados WHERE idServicio=" + rs.getString("idServicio")
			+ ")" + "*(SELECT comision FROM profesionales WHERE idProfesional="
			+ rs.getString("idProfesional") + "))/100), " + rs.getString("idProfesional") + ");");

		st2.executeUpdate(
			"UPDATE serviciosrealizados SET idLiquidacion=(SELECT MAX(idLiquidacion) FROM liquidaciones) "
				+ "WHERE idServicio=" + rs.getString("idServicio") + ";");
		conexion.commit();

	    }
	    conexion.setAutoCommit(true); // volvemos a activar autocomit para el resto del programa
	    st.close();
	    rs.close();
	    st2.close();

	} catch (SQLException e) {
	    try {
		conexion.rollback(); // si algo falla se deshace todo lo anterior
	    } catch (SQLException e1) {
		e1.printStackTrace();
	    }
	} catch (InputMismatchException e) {
	    System.err.println("ERROR AL INTRODUCIR DATOS");
	} catch (Exception e) {
	    System.out.println(e);
	}

    }

    public static void consultarHistorialPacienteBBDD() {

	Scanner sc = new Scanner(System.in);

	try {

	    Statement st = conexion.createStatement();
	    ResultSet rs;

	    int idPaciente = 0;

	    System.out.print("(IDENTIFICANDO)Nombre del paciente: ");
	    String nombre = sc.nextLine();
	    System.out.print("(IDENTIFICANDO)Apellidos del paciente: ");
	    String apellidos = sc.nextLine();
	    System.out.print("(IDENTIFICANDO)DNI del paciente('NULL' en caso de no tener): ");
	    String dni = sc.nextLine();
	    String busqueda;

	    if (dni.equalsIgnoreCase("NULL"))
		busqueda = "SELECT idPaciente FROM pacientes WHERE nombre='" + nombre + "' AND apellidos='" + apellidos
			+ "' AND dni IS NULL;";
	    else
		busqueda = "SELECT idPaciente FROM pacientes WHERE nombre='" + nombre + "' AND apellidos='" + apellidos
			+ "' AND dni='" + dni + "';";

	    rs = st.executeQuery(busqueda);

	    while (rs.next()) {
		idPaciente = rs.getInt(1);
	    }
	    // si el paciente no existise, se informaria de ello
	    if (idPaciente == 0)
		System.out.println("El paciente '" + nombre + " " + apellidos + "' con DNI '" + dni
			+ "' no existe en la BBDD '" + cfg.getProperty("db"));
	    // si existe se realiza la consulta con su historial
	    else {

		rs = st.executeQuery("SELECT * FROM pacientes WHERE idPaciente=" + idPaciente);

		while (rs.next()) {

		    System.out.println("\nDatos del paciente: ");
		    System.out.println("IDPACIENTE: " + rs.getString(1) + ", NOMBRE: " + rs.getString(2)
			    + ", APELLIDOS:" + rs.getString(3) + ", NACIMIENTO: " + rs.getString(4) + ", GENERO: "
			    + rs.getString(5) + ", DNI: " + rs.getString(6) + ", DIRECCION: " + rs.getString(7)
			    + ", TELEFONO: " + rs.getString(8) + ", CODIGO: " + rs.getString(9));

		}

		System.out.println("\nServicios realizados al paciente '" + idPaciente + "': ");

		rs = st.executeQuery(
			"SELECT sr.idServicio,sr.fecha,sr.idProfesional,t.nombre,sr.precio,sr.cobrado,sr.idLiquidacion FROM serviciosrealizados sr "
				+ "INNER JOIN tratamientos t " + "ON sr.idTratamiento=t.idTratamiento "
				+ "WHERE sr.idPaciente=" + idPaciente);

		while (rs.next()) {

		    System.out.println("IDSERVICIO: " + rs.getString("idServicio") + ", FECHA: " + rs.getString("fecha")
			    + ", TRATAMIENTO: " + rs.getString("nombre") + ", IDPROFESIONAL: "
			    + rs.getString("idProfesional") + ", PRECIO: " + rs.getString("precio") + ", COBRADO: "
			    + rs.getString("cobrado") + ", IDLIQUIDACION: " + rs.getString("idLiquidacion"));

		}

		System.out.println("\nCobros efectuados al paciente: ");

		rs = st.executeQuery(
			"SELECT c.idCobro, c.fecha, fc.nombre as 'formaCobro', c.cobrado, c.imputado, c.idServicio, gj.nombre as 'grupoCaja' FROM cobros c "
				+ "INNER JOIN fcobro fc ON c.idFCobro=fc.idFCobro "
				+ "INNER JOIN grupocaja gj ON fc.idGrupoCaja=gj.idGrupoCaja " + "WHERE idPaciente="
				+ idPaciente);

		while (rs.next()) {

		    System.out.println("IDCOBRO: " + rs.getString("idCobro") + ", FECHA: " + rs.getString("fecha")
			    + ", FORMA DE COBRO: " + rs.getString("formaCobro") + ", COBRADO: "
			    + rs.getString("cobrado") + ", IMPUTADO: " + rs.getString("imputado") + ", IDSERVICIO: "
			    + rs.getString("idServicio") + ", GRUPO CAJA: " + rs.getString("grupoCaja"));

		}

	    }

	    st.close();
	    rs.close();

	} catch (SQLException e) {
	    System.out.println(e);
	} catch (InputMismatchException e) {
	    System.err.println("ERROR AL INTRODUCIR DATOS");
	} catch (Exception e) {
	    System.out.println(e);
	}

    }

    public static void consultarLiquidacionesPendientesBBDD() {

	Scanner sc = new Scanner(System.in);

	try {

	    Statement st = conexion.createStatement();

	    ResultSet rs = st.executeQuery(
		    "SELECT sr.cobrado,sr.idServicio, Sr.fecha, pa.idPaciente, pa.nombre AS 'nombrePaciente', "
			    + "pa.apellidos AS 'apellidoPaciente', pr.idProfesional,"
			    + "pr.nombre AS 'nombreProfesional', pr.apellidos AS 'apellidoProfesional',"
			    + "t.idTratamiento, t.nombre AS 'nombreTratamiento', sr.precio "
			    + "FROM serviciosrealizados sr INNER JOIN profesionales pr ON "
			    + "sr.idProfesional = pr.idProfesional INNER JOIN pacientes pa ON "
			    + "sr.idPaciente = pa.idPaciente INNER JOIN tratamientos t ON "
			    + "sr.idTratamiento = t.idTratamiento WHERE sr.idLiquidacion IS NULL AND sr.cobrado>=sr.precio "
			    + "ORDER BY sr.idProfesional;");
	    // imprimira todos aquellos servicicios que no esten liquidados y que ademas
	    // esten ya totalmente cobrados
	    while (rs.next()) {

		System.out.println("\nPROFESIONAL " + rs.getString("idProfesional") + " '"
			+ rs.getString("nombreProfesional") + " " + rs.getString("apellidoProfesional") + "':");

		System.out.println("IDSERVICIO: " + rs.getString("idServicio") + ", FECHA: " + rs.getString("fecha")
			+ ", IDTRATAMIENTO: " + rs.getString("idTratamiento") + ", TRATAMIENTO: '"
			+ rs.getString("nombreTratamiento") + "', PRECIO: (" + rs.getDouble("precio") + ") COBRADO: ("
			+ rs.getDouble("cobrado") + "), IDPACIENTE: " + rs.getString("idPaciente") + ", PACIENTE: '"
			+ rs.getString("nombrePaciente") + " " + rs.getString("apellidoPaciente") + "'");

	    }

	    st.close();
	    rs.close();

	} catch (SQLException e) {
	    System.out.println(e);
	} catch (InputMismatchException e) {
	    System.err.println("ERROR AL INTRODUCIR DATOS");
	} catch (Exception e) {
	    System.out.println(e);
	}

    }

    // metodo que controla que se devuelva una fecha valida con el formato indicado
    public static LocalDate leerFechaValida(String mensaje, String formatoFecha) {

	Scanner sc = new Scanner(System.in);
	DateTimeFormatter fmt = DateTimeFormatter.ofPattern(formatoFecha);
	boolean valido = false;
	LocalDate fechaValida = null;

	while (valido == false) {

	    try {
		System.out.print(mensaje + "(" + formatoFecha + "): ");
		String stringFecha = sc.nextLine();
		fechaValida = LocalDate.parse(stringFecha, fmt);
		valido = true;
	    } catch (DateTimeParseException e) {
		System.err.println("INTRODUZCA UNA FECHA VALIDA CON EL FORMATO(" + formatoFecha + ")");
	    }

	}
	return fechaValida;
    }

    // metodo que controla que registro insertado, sea un existe en la base de datos
    public static int validarRegistroExistente(String mensaje, String tabla, String primaryKey) {

	Scanner sc = new Scanner(System.in);

	boolean repetir = true;
	int id = 0;

	try {

	    Statement st = conexion.createStatement();
	    ResultSet rs;
	    do {
		System.out.print(mensaje);
		id = sc.nextInt();

		rs = st.executeQuery("SELECT 1 FROM " + tabla + " WHERE " + primaryKey + "=" + id + ";");

		if (rs.next()) {
		    repetir = false;

		} else
		    System.err.println("EL REGISTRO '" + id + "' NO EXISTE EN LA TABLA '" + tabla + "'");
	    } while (repetir == true);

	    st.close();
	    rs.close();

	} catch (SQLException e) {
	    e.printStackTrace();
	}

	return id;

    }

    // metodo que impreme el total de importes provenientes de cada uno de los tipos
    // de tratamientos
    public static void resumenImportesTratamientosBBDD() {

	Scanner sc = new Scanner(System.in);

	try {

	    Statement st1 = conexion.createStatement();
	    Statement st2 = conexion.createStatement();
	    Statement st3 = conexion.createStatement();
	    ResultSet rs1 = null;
	    ResultSet rs2 = null;
	    ResultSet rs3 = null;

	    rs1 = st1.executeQuery("SELECT idFamilia,nombre AS nombreFamilia FROM familias;");

	    while (rs1.next()) {

		double totalImportesFamilia = 0;
		System.out.println("\n=== FAMILIA " + rs1.getString("nombreFamilia") + " ===");

		rs2 = st2.executeQuery("SELECT idTratamiento, nombre AS nombreTratamiento FROM tratamientos "
			+ "WHERE idFamilia=" + rs1.getString("idFamilia") + ";");

		while (rs2.next()) {

		    double totalImportesTratamiento = 0;
		    System.out.println("\nTRATAMIENTO: " + rs2.getString("nombreTratamiento"));

		    rs3 = st3.executeQuery("SELECT * from serviciosrealizados WHERE idTratamiento="
			    + rs2.getString("idTratamiento") + ";");

		    while (rs3.next()) {

			System.out.println("IDSERVICIO: " + rs3.getString("idServicio") + ", FECHA: "
				+ rs3.getString("fecha") + ", PRECIO: " + rs3.getString("precio") + ", IDTRATAMIENTO: "
				+ rs3.getString("idTratamiento") + ", COBRADO: " + rs3.getDouble("cobrado"));

			totalImportesTratamiento += rs3.getDouble("cobrado");

		    }
		    System.out.println("TOTAL DE IMPORTES DEL TRATAMIENTO: " + totalImportesTratamiento);
		    totalImportesFamilia += totalImportesTratamiento; // acumula los importes provenientes de dicho
		    // tratamiento

		}

		System.out.println("\n ----- TOTAL DE IMPORTES FAMILIA '" + rs1.getString("nombreFamilia") + "': "
			+ totalImportesFamilia); // acumula los importes provenientes de la dicha familia

	    }

	    System.out.println("\nTOTAL DE IMPORTES POR FECHA"); // imprime el total de importes obtenidos en cada fecha

	    rs1 = st1.executeQuery("SELECT SUM(cobrado) AS totalCobrado,fecha FROM serviciosrealizados GROUP BY fecha");

	    while (rs1.next()) {
		System.out.println(
			"FECHA: " + rs1.getString("fecha") + ", TOTAL COBRADO: " + rs1.getDouble("totalCobrado"));
	    }

	    st1.close();
	    st2.close();
	    st3.close();
	    rs1.close();
	    rs2.close();
	    rs3.close();

	} catch (SQLException e) {
	    System.out.println(e);
	} catch (InputMismatchException e) {
	    System.err.println("ERROR AL INTRODUCIR DATOS");
	} catch (Exception e) {
	    System.out.println(e);
	}

    }

    public static int validarRegistroNoExistente(String mensaje, String tabla, String primaryKey) {

	Scanner sc = new Scanner(System.in);

	boolean repetir = true;
	int id = 0;

	try {

	    Statement st = conexion.createStatement();
	    ResultSet rs;
	    do {
		System.out.print(mensaje);
		id = sc.nextInt();

		rs = st.executeQuery("SELECT 1 FROM " + tabla + " WHERE " + primaryKey + "=" + id + ";");

		if (rs.next()) {
		    System.err.println("EL REGISTRO '" + id + "' YA EXISTE EN LA TABLA '" + tabla + "'");

		} else
		    repetir = false;
	    } while (repetir == true);

	    st.close();
	    rs.close();

	} catch (SQLException e) {
	    e.printStackTrace();
	}

	return id;

    }

}