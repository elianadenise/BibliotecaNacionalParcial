package bibliotecaNacional;

public class Geografia extends Libro implements Fotocopiable{

	public Geografia(Integer codigo, String nombre, String autor) {
		super(codigo, nombre, autor);
	}

	@Override
	public String fotocopiar() {
		return "Geografia [codigo=" + this.codigo + ", nombre=" + this.nombre + ", autor=" + this.autor;
	}


}
