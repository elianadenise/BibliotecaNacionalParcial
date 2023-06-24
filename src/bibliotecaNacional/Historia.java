package bibliotecaNacional;

public class Historia extends Libro implements Fotocopiable{

	public Historia(Integer codigo, String nombre, String autor) {
		super(codigo, nombre, autor);
	}

	@Override
	public String fotocopiar() {
		return "Historia [codigo=" + this.codigo + ", nombre=" + this.nombre + ", autor=" + this.autor;
	}

	
}
