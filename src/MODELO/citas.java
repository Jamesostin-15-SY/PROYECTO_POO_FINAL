package MODELO;
import java.time.LocalDateTime;
public class citas {
    int id_cita;
    int fk_id_mascota;
    String fk_dni_veterinario;
    LocalDateTime fecha_hora;
    int fk_id_servicio;
    int fk_id_estado;
    String motivo_cita;
    
}
