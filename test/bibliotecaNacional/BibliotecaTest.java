package bibliotecaNacional;

import static org.junit.Assert.*;

import org.junit.Test;

public class BibliotecaTest {

	@Test
	public void queSePuedanAgregarLibrosALaBiblioteca() {
		BibliotecaNacional biblioteca = new BibliotecaNacional();
		Libro matematica = new Matematica(1, "Matematica", "Einstein");
		Libro historia = new Historia(2, "Historia", "Pyky");
		Libro geografia = new Geografia(3, "Geografia", "Fluck");
		Libro matematicaDos = new Matematica(4, "Matematica", "Murph");
		Libro historiaDos = new Historia(1, "Historia argentina", "Sarmiento"); // estos poseen codigo repetido
		Libro geografiaDos = new Geografia(2, "Geografia argentina", "Stegman");

		assertTrue(biblioteca.agregarLibroABiblioteca(matematica));
		assertTrue(biblioteca.agregarLibroABiblioteca(historia));
		assertTrue(biblioteca.agregarLibroABiblioteca(geografia));
		assertTrue(biblioteca.agregarLibroABiblioteca(matematicaDos));
		assertFalse(biblioteca.agregarLibroABiblioteca(historiaDos));
		assertFalse(biblioteca.agregarLibroABiblioteca(geografiaDos));
		assertEquals(new Integer(4), biblioteca.cantidadDeLibrosDisponibles());
	}

	@Test
	public void queSePuedaPrestarLibroAEstudianteInscripto() throws PrestamoNoRegistrado {
		BibliotecaNacional biblioteca = new BibliotecaNacional();
		Libro matematica = new Matematica(1, "Matematica", "Einstein");
		Libro historia = new Historia(2, "Historia", "Pyky");
		Libro geografia = new Geografia(3, "Geografia", "Fluck");
		Estudiante estudiante = new Estudiante(41334491, "Navarro", "Eliana");
		biblioteca.agregarLibroABiblioteca(matematica);
		biblioteca.agregarLibroABiblioteca(historia);
		biblioteca.agregarLibroABiblioteca(geografia);
		biblioteca.inscribirEstudiante(estudiante);

		assertNotNull(biblioteca.prestarLibro(estudiante, geografia));
		assertEquals(new Integer(1), biblioteca.cantidadDeEstudiantesInscriptos());
		assertEquals(new Integer(2), biblioteca.cantidadDeLibrosDisponibles());
		assertEquals(new Integer(1), estudiante.cantidadDeLibrosPrestados());

	}

	@Test
	public void queNoSePuedaPrestarLibroAEstudianteInscripto() throws PoseeDosLibrosPrestados, PrestamoNoRegistrado {
		BibliotecaNacional biblioteca = new BibliotecaNacional();
		Libro matematica = new Matematica(1, "Matematica", "Einstein");
		Libro historia = new Historia(2, "Historia", "Pyky");
		Libro geografia = new Geografia(3, "Geografia", "Fluck");
		Estudiante estudiante = new Estudiante(41334491, "Navarro", "Eliana");
		biblioteca.agregarLibroABiblioteca(matematica);
		biblioteca.agregarLibroABiblioteca(historia);
		biblioteca.agregarLibroABiblioteca(geografia);
		biblioteca.inscribirEstudiante(estudiante);
		assertNotNull(biblioteca.prestarLibro(estudiante, geografia));
		assertNotNull(biblioteca.prestarLibro(estudiante, matematica));
		try {
			assertNull(biblioteca.prestarLibro(estudiante, historia));
		} catch (PrestamoNoRegistrado e) {
			e.printStackTrace();
		}
		assertEquals(new Integer(2), estudiante.cantidadDeLibrosPrestados());
	}
	
	@Test
	public void queSePuedaDevolverLibro() throws PrestamoNoRegistrado {
		BibliotecaNacional biblioteca = new BibliotecaNacional();
		Libro matematica = new Matematica(1, "Matematica", "Einstein");
		Libro historia = new Historia(2, "Historia", "Pyky");
		Libro geografia = new Geografia(3, "Geografia", "Fluck");
		Estudiante estudiante = new Estudiante(41334491, "Navarro", "Eliana");
		biblioteca.agregarLibroABiblioteca(matematica);
		biblioteca.agregarLibroABiblioteca(historia);
		biblioteca.agregarLibroABiblioteca(geografia);
		biblioteca.inscribirEstudiante(estudiante);

		Prestamo prestamo = biblioteca.prestarLibro(estudiante, geografia);
		assertTrue(biblioteca.devolverLibro(prestamo));
		assertEquals(new Integer(0), estudiante.cantidadDeLibrosPrestados());
		assertEquals(new Integer(3), biblioteca.cantidadDeLibrosDisponibles());
	}
	
	@Test
	public void queSePuedaImprimirUnLibro() throws NoFotocopiable {
		BibliotecaNacional biblioteca = new BibliotecaNacional();
		Libro historia = new Historia(2, "Historia", "Pyky");
		Libro geografia = new Geografia(3, "Geografia", "Fluck");
		
		assertNotNull(biblioteca.imprimir(geografia));
		assertNotNull(biblioteca.imprimir(historia));
	}

	@Test (expected = NoFotocopiable.class)
	public void queNoSePuedaImprimirUnLibroDeMatematica() throws NoFotocopiable {
		BibliotecaNacional biblioteca = new BibliotecaNacional();
		Libro matematica = new Matematica(1, "Matematica", "Einstein");
		
		biblioteca.imprimir(matematica);
	}
	@Test 
	public void queNoSePuedaImprimirUnLibroDeMatematicaEnConsola(){
		BibliotecaNacional biblioteca = new BibliotecaNacional();
		Libro matematica = new Matematica(1, "Matematica", "Einstein");
		try {
			biblioteca.imprimir(matematica);
		} catch(NoFotocopiable e) {
			e.printStackTrace();
		}
	}
	
	@Test (expected = PrestamoNoRegistrado.class)
	public void queNoSePuedaPrestarPorqueNoEstaDisponibleElEstudiante() throws PrestamoNoRegistrado {
		BibliotecaNacional biblioteca = new BibliotecaNacional();
		Libro matematica = new Matematica(1, "Matematica", "Einstein");
		Libro historia = new Historia(2, "Historia", "Pyky");
		Libro geografia = new Geografia(3, "Geografia", "Fluck");
		Estudiante estudiante = new Estudiante(41334491, "Navarro", "Eliana");
		biblioteca.agregarLibroABiblioteca(matematica);
		biblioteca.agregarLibroABiblioteca(historia);
		biblioteca.agregarLibroABiblioteca(geografia);

		assertNull(biblioteca.prestarLibro(estudiante, geografia));
		assertEquals(new Integer(0), biblioteca.cantidadDeEstudiantesInscriptos());
		assertEquals(new Integer(3), biblioteca.cantidadDeLibrosDisponibles());
		assertEquals(new Integer(0), estudiante.cantidadDeLibrosPrestados());
	}
	@Test 
	public void queNoSePuedaPrestarPorqueNoEstaDisponibleElEstudianteEnConsola() {
		BibliotecaNacional biblioteca = new BibliotecaNacional();
		Libro matematica = new Matematica(1, "Matematica", "Einstein");
		Libro historia = new Historia(2, "Historia", "Pyky");
		Libro geografia = new Geografia(3, "Geografia", "Fluck");
		Estudiante estudiante = new Estudiante(41334491, "Navarro", "Eliana");
		biblioteca.agregarLibroABiblioteca(matematica);
		biblioteca.agregarLibroABiblioteca(historia);
		biblioteca.agregarLibroABiblioteca(geografia);

		try {
			assertNull(biblioteca.prestarLibro(estudiante, geografia));
		} catch (PrestamoNoRegistrado e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(new Integer(0), biblioteca.cantidadDeEstudiantesInscriptos());
		assertEquals(new Integer(3), biblioteca.cantidadDeLibrosDisponibles());
		assertEquals(new Integer(0), estudiante.cantidadDeLibrosPrestados());
	}
	@Test (expected = PrestamoNoRegistrado.class)
	public void queNoSePuedaPrestarPorqueNoEstaDisponibleElLibro() throws PrestamoNoRegistrado {
		BibliotecaNacional biblioteca = new BibliotecaNacional();
		Libro geografia = new Geografia(3, "Geografia", "Fluck");
		Estudiante estudiante = new Estudiante(41334491, "Navarro", "Eliana");
		biblioteca.inscribirEstudiante(estudiante);
		
		assertNull(biblioteca.prestarLibro(estudiante, geografia));
		assertEquals(new Integer(1), biblioteca.cantidadDeEstudiantesInscriptos());
		assertEquals(new Integer(0), biblioteca.cantidadDeLibrosDisponibles());
		assertEquals(new Integer(0), estudiante.cantidadDeLibrosPrestados());
	}
	@Test 
	public void queNoSePuedaPrestarPorqueNoEstaDisponibleElLibroEnConsola() {
		BibliotecaNacional biblioteca = new BibliotecaNacional();
		Libro geografia = new Geografia(3, "Geografia", "Fluck");
		Estudiante estudiante = new Estudiante(41334491, "Navarro", "Eliana");
		biblioteca.inscribirEstudiante(estudiante);

		try {
			assertNull(biblioteca.prestarLibro(estudiante, geografia));
		} catch (PrestamoNoRegistrado e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(new Integer(1), biblioteca.cantidadDeEstudiantesInscriptos());
		assertEquals(new Integer(0), biblioteca.cantidadDeLibrosDisponibles());
		assertEquals(new Integer(0), estudiante.cantidadDeLibrosPrestados());
	}
	
	@Test
	public void devolverPrestamoNoPrestado() {
		BibliotecaNacional biblioteca = new BibliotecaNacional();
		Libro geografia = new Geografia(3, "Geografia", "Fluck");
		Estudiante estudiante = new Estudiante(41334491, "Navarro", "Eliana");
		biblioteca.inscribirEstudiante(estudiante);
		Prestamo prestamo = new Prestamo(4, estudiante, geografia);
		
		try {
			biblioteca.devolverLibro(prestamo);
		} catch (PrestamoNoRegistrado e) {
			e.printStackTrace();
		}
	}
	
	@Test (expected = PrestamoNoRegistrado.class)
	public void devolverPrestamoNoPrestadoDos() throws PrestamoNoRegistrado{
		BibliotecaNacional biblioteca = new BibliotecaNacional();
		Libro geografia = new Geografia(3, "Geografia", "Fluck");
		Estudiante estudiante = new Estudiante(41334491, "Navarro", "Eliana");
		biblioteca.inscribirEstudiante(estudiante);
		Prestamo prestamo = new Prestamo(4, estudiante, geografia);
		
		biblioteca.devolverLibro(prestamo);
	}
}
