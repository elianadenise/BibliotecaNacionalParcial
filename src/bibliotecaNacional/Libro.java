package bibliotecaNacional;

public abstract class Libro implements Comparable<Libro>{

	protected Integer codigo;
	protected String nombre;
	protected String autor;
	
	public Libro(Integer codigo, String nombre, String autor) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.autor = autor;
	}
	
	public Integer getCodigo() {
		return codigo;
	}

	public int compareTo(Libro libro) {
		return this.getCodigo().compareTo(libro.getCodigo());
	}
}
