package bibliotecaNacional;

import java.util.Objects;

public class Prestamo {
	
	private Integer identificador;
	private Estudiante estudiante;
	private Libro libro;

	public Prestamo(Integer identificador, Estudiante estudiante, Libro libro) {
		this.identificador = identificador;
		this.estudiante = estudiante;
		this.libro = libro;
	}

	public Integer getIdentificador() {
		return identificador;
	}

	public Estudiante getEstudiante() {
		return estudiante;
	}

	public Libro getLibro() {
		return libro;
	}

	@Override
	public int hashCode() {
		return Objects.hash(identificador);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Prestamo other = (Prestamo) obj;
		return Objects.equals(identificador, other.identificador);
	}
	
}
