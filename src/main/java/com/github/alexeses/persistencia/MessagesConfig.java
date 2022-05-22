package com.github.alexeses.persistencia;

public class MessagesConfig {
    // Tablas base de datos
    public static final String BD_TABLA = "RESTAURANTES";
    public static final String BD_REGION = "REGION";
    // ----
    public static final String COLUMNNOMBRE = "NOMBRE";
    public static final String COLUMNREGION = "REGION";
    public static final String COLUMNCIUDAD = "CIUDAD";
    public static final String COLUMNDISTINCION = "DISTINCION";
    public static final String COLUMNDIRECCION = "DIRECCION";
    public static final String COLUMNPRMIN = "PRECIO_MIN";
    public static final String COLUMNPR = "PRECIO";
    public static final String COLUMNPRMAX = "PRECIO_MAX";
    public static final String COLUMNCOCINA = "COCINA";
    public static final String COLUMNTELEFONO = "TELEFONO";
    public static final String COLUMNWEB = "WEB";
    public static final String[] REGION = {"Andalucía", "Aragón", "Asturias", "Islas Baleares", "Cantabria",
            "Islas Canarias", "Castilla - La Mancha", "Castilla y León", "Cataluña", "Galicia", "Extremadura",
            "Madrid", "Murcia", "Navarra", "Pais Vasco", "La Rioja", "Comunidad Valenciana"};
    // GESTIÓN BOTONES - MENU GENERAL (NAVBAR)
    public static final String BTN_MENU_GENERAL = "Menu de Gestión";
    public static final String BTN_MENU_SALIR = "Salir";
    public static final String BTN_MENU_CONSULTA = "Consultas";
    public static final String BTN_MENU_REGISTRO = "Registros";
    public static final String BTN_MENU_MODIFICACION = "Modificaciones";

    // GESTIÓN BOTONES - MENÚ DE MODIFICACIÓN
    public static final String BTN_MOD_BUSCAR = "Encontrar";
    public static final String BTN_MOD_MODIFICAR = "Modificar";
    public static final String BTN_MOD_CANCELAR = "Cancelar";

    // GESTION BOTONES - MENÚ DE CONSULTAS
    public static final String BTN_CONS_BUSCAR = "Buscar";
    public static final String BTN_CONS_BORRAR = "Borrar";

    // GESTIÓN BOTONES - MENÚ DE REGISTROS
    public static final String BTN_REG_GUARDAR = "Guardar";
    public static final String BTN_REG_BORRAR = "Limpiar";
}
