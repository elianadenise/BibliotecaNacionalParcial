package bibliotecaNacional;

public class NoFotocopiable extends Exception {
	private static final long serialVersionUID = 1L;
	private String mensaje;

	public NoFotocopiable() {}
	public NoFotocopiable(String mensaje) {
		super(mensaje);
		this.mensaje = mensaje;
	}
	public void printStackTrace() {
		System.err.println(this.mensaje);
	}
}

