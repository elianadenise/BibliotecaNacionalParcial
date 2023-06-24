package bibliotecaNacional;

public class PrestamoNoRegistrado extends Exception {
	private static final long serialVersionUID = 1L;
	private String mensaje;

	public PrestamoNoRegistrado() {}
	public PrestamoNoRegistrado(String mensaje) {
		super(mensaje);
		this.mensaje = mensaje;
	}
	public void printStackTrace() {
		System.err.println(this.mensaje);
	}
}
