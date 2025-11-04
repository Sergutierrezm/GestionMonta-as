Gestión de Montañas en Java

Aplicación desarrollada en Java con conexión a MySQL mediante JDBC.
Permite gestionar un registro de montañas, consultarlas, editarlas o eliminarlas, todo desde un menú en consola.

⸻

Funcionalidades
	•	📋 Menú principal con opciones numeradas.
	•	🏗️ Creación automática de la tabla Montañas.
	•	➕ Inserción de nuevas montañas con:
	•	Nombre
	•	Altura
	•	Año de primera ascensión
	•	Región
	•	País
	•	🔍 Consulta de:
	•	Todas las montañas registradas
	•	Una montaña específica por nombre
	•	✏️ Edición de los datos de una montaña existente
	•	❌ Eliminación de una montaña o de toda la tabla
	•	🚪 Opción de salida segura del programa

⸻

Tecnologías
	•	Java 17
	•	MySQL
	•	JDBC
	•	NetBeans
	•	Git y GitHub

⸻

Cómo ejecutar
	1.	Clonar o descargar el proyecto.
    2.	Abrirlo en tu IDE preferido.
	3.	Configurar la conexión a MySQL en el archivo Main.java:

        String url = "jdbc:mysql://localhost:3306/Geografia";
        String usuario = "root";
        String contraseña = "";
    
    4.	Ejecutar la clase Main.java.
	5.	Seguir las opciones del menú para gestionar las montañas.

👤 Autor

Sergio Gutiérrez    