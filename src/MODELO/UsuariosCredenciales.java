package MODELO;
public class UsuariosCredenciales {
    private int id_usuario;
    private String fk_dni_empleado;
    private String usuario;
    private String contrasena;
    private int fk_id_role;
    public UsuariosCredenciales() {}

    public int getFk_id_role() {return fk_id_role; }
    public void setFk_id_role(int fk_id_role) {this.fk_id_role = fk_id_role; }
    public String getUsuario() {return usuario;}
    public void setUsuario(String usuario) {this.usuario = usuario;}
    public String getContrasena() {return contrasena;}
    public void setContrasena(String contrasena) {this.contrasena = contrasena;
    }
     public Object[] Registro(int num) {
        Object[] fila = {num, id_usuario, usuario, fk_id_role};
        return fila;
    }
}
