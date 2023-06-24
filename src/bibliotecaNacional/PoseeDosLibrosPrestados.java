package bibliotecaNacional;

public class PoseeDosLibrosPrestados extends Exception {

	private static final long serialVersionUID = 1L;
	private String mensaje;

	public PoseeDosLibrosPrestados() {}
	public PoseeDosLibrosPrestados(String mensaje) {
		super(mensaje);
		this.mensaje = mensaje;
	}
	public void printStackTrace() {
		System.err.println(this.mensaje);
	}
}
