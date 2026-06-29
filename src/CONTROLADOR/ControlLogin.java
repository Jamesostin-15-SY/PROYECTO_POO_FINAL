
package CONTROLADOR;
import VISTA.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import MODELO.*;
import javax.swing.JOptionPane;
import DAO.*;
import Procesos.*;
public class ControlLogin implements ActionListener {

    frmLogin vista;
    frmMenusVeterinaria menuPrincipal;
    // Declaramos la interfaz del CRUD
    CrudUsuarios crud; 

    public ControlLogin(frmLogin vista) {
        this.vista = vista;
        // Instanciamos tu clase de persistencia SQL
        this.crud = new CrudUsuarioslmp(); 
        
        // Escuchamos los botones del formulario login
        this.vista.btnIniciar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Al hacer clic en Ingresar
        if (e.getSource() == vista.btnIniciar) {
            
            // 1. Leemos los datos de la vista usando Procesos
            UsuariosCredenciales userTemporal = ProcesosLogin.Leer(vista);
            
            // Validación básica de campos vacíos
            if (userTemporal.getUsuario().isEmpty() || userTemporal.getContrasena().isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Por favor, complete todos los campos.");
                return;
            }
            
            // 2. Mandamos a validar con tu método del CRUD
            UsuariosCredenciales userValidado = crud.ValidarAcceso(userTemporal.getUsuario(), userTemporal.getContrasena());
            
            // 3. Verificamos la respuesta
            if (userValidado != null) {
                // Credenciales correctas -> Abrimos el menú principal
                menuPrincipal = new frmMenusVeterinaria();
                menuPrincipal.setTitle("VETCARE S.A.C - Sistema Principal");
                menuPrincipal.setLocationRelativeTo(null);
                menuPrincipal.setVisible(true);
                
                // Cerramos el Login
                vista.dispose();
            } else {
                // Credenciales incorrectas
                JOptionPane.showMessageDialog(vista, "Usuario o contraseña incorrectos.", "Error de Acceso", JOptionPane.ERROR_MESSAGE);
                ProcesosLogin.LimpiarEntradas(vista);
            }
        }
        
    }
}
