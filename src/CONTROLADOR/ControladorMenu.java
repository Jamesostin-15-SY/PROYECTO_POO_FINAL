package CONTROLADOR;

import DAO.ProcesosPermisos;
import MODELO.UsuariosCredenciales;
import VISTA.frmMenusVeterinaria;

public class ControladorMenu {

    private final frmMenusVeterinaria vistaMenu;
    private final UsuariosCredenciales usuarioLogueado;

    // Recibe la ventana del menú y el objeto del usuario que inició sesión
    public ControladorMenu(frmMenusVeterinaria vistaMenu, UsuariosCredenciales usuarioLogueado) {
        this.vistaMenu = vistaMenu;
        this.usuarioLogueado = usuarioLogueado;
    }

    // Muestra el panel principal configurando títulos y permisos de rol
    public void iniciar() {
        vistaMenu.setTitle("Sistema de Gestión Veterinaria - Panel Principal");
        vistaMenu.setLocationRelativeTo(null);
        
        // Llama a tu clase ProcesosPermisos para habilitar/deshabilitar los componentes correspondientes
        ProcesosPermisos.AplicarRestricciones(vistaMenu, usuarioLogueado.getFk_id_role());
        
        vistaMenu.setVisible(true);
    }
}