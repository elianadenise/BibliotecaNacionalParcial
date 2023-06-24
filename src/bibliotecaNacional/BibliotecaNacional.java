package bibliotecaNacional;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.TreeSet;

public class BibliotecaNacional {

	private TreeSet<Libro> librosDisponibles;
	private LinkedHashSet<Prestamo> prestamos;
	private HashSet<Estudiante> estudiantesInscriptos;
	private Integer contadorPrestamos;
	
	public BibliotecaNacional() {
		this.librosDisponibles = new TreeSet<Libro>();
		this.prestamos = new LinkedHashSet<Prestamo>();
		this.estudiantesInscriptos = new HashSet<Estudiante>();
		this.contadorPrestamos = 0;
	}
	
	public Boolean agregarLibroABiblioteca(Libro libro) {
		return this.librosDisponibles.add(libro);
	}
	public Boolean inscribirEstudiante(Estudiante estudiante) {
		return this.estudiantesInscriptos.add(estudiante);
	}
	
	public Prestamo prestarLibro(Estudiante estudiante, Libro libro) throws PrestamoNoRegistrado {
		if(this.estudiantesInscriptos.contains(estudiante) && this.librosDisponibles.contains(libro)) {
			try {
				this.contadorPrestamos++;
				estudiante.pedirPrestadoUnLibro(libro);
				this.librosDisponibles.remove(libro);
				Prestamo prestamo = new Prestamo(this.contadorPrestamos, estudiante, libro);
				this.prestamos.add(prestamo);
				return prestamo;
			} catch (PoseeDosLibrosPrestados e) {
				e.printStackTrace();
			}
		} else if(!this.librosDisponibles.contains(libro)){
			throw new PrestamoNoRegistrado("El libro no se encuentra en la Biblioteca"); 
			
		} else {
			throw new PrestamoNoRegistrado("El estudiante no figura inscripto");
		}
		return null;
	}
	
	public Boolean devolverLibro(Prestamo prestamo) throws PrestamoNoRegistrado {
		if(this.prestamos.contains(prestamo)) {
			prestamo.getEstudiante().devolverLibro(prestamo.getLibro());
			this.prestamos.remove(prestamo);
			return this.agregarLibroABiblioteca(prestamo.getLibro());
		} else {
			throw new PrestamoNoRegistrado("El prestamo no fue registrado con anterioridad");
		}
	}
	
	public String imprimir(Libro libro) throws NoFotocopiable {
		if(libro instanceof Geografia) {
			return ((Geografia) libro).fotocopiar();
		} else if(libro instanceof Historia) {
			return ((Historia) libro).fotocopiar();
		}
		throw new NoFotocopiable("Libro no fotocopiable");
	}
	
	public Integer cantidadDeLibrosDisponibles() {
		return this.librosDisponibles.size();
	}
	public Integer cantidadDeEstudiantesInscriptos() {
		return this.estudiantesInscriptos.size();
	}
}
