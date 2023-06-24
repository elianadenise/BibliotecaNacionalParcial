package bibliotecaNacional;

import java.util.LinkedHashSet;
import java.util.Objects;

public class Estudiante {

	private String apellido;
	private Integer dni;
	private String nombre;
	private LinkedHashSet<Libro> librosPrestados;

	public Estudiante(Integer dni, String apellido, String nombre) {
		this.dni = dni;
		this.apellido = apellido;
		this.nombre = nombre;
		this.librosPrestados = new LinkedHashSet<Libro>();
	}
	
	public void pedirPrestadoUnLibro(Libro libro) throws PoseeDosLibrosPrestados {
		if(this.librosPrestados.size() <= 1) {
			this.librosPrestados.add(libro);
		} else {
			throw new PoseeDosLibrosPrestados("Debe devolver al menos un libro para que la Biblioteca Nacional pueda prestarle este libro");
		}
	}
	
	public void devolverLibro(Libro libro) {
		if(this.librosPrestados.contains(libro)) {
			this.librosPrestados.remove(libro);
		}
	}
	
	public Integer cantidadDeLibrosPrestados() {
		return this.librosPrestados.size();
	}

	@Override
	public int hashCode() {
		return Objects.hash(dni);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Estudiante other = (Estudiante) obj;
		return Objects.equals(dni, other.dni);
	}
	
	
}
