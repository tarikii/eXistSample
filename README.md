# Descripción

Este es un código Java que utiliza una base de datos XML existente para almacenar y administrar información de personajes de videojuegos. La aplicación utiliza el controlador ExistController para conectarse a la base de datos y realizar operaciones como listar, buscar, modificar y eliminar personajes.

La aplicación también utiliza una clase Menu para proporcionar una interfaz de línea de comandos al usuario. El usuario puede seleccionar diferentes opciones del menú para realizar diferentes operaciones en la base de datos.

# Imprescindible

Para ejecutar la aplicación, necesitarás tener instalado Java y una base de datos existente de XML como eXist-db.

# Ejecución

    Descarga el código fuente y ábrelo en tu entorno de desarrollo Java preferido.
    Asegúrate de que tienes una base de datos existente de XML como eXist-db y que tienes los detalles de conexión a la base de datos (nombre del servidor, puerto, nombre de usuario y contraseña).
    Actualiza los detalles de conexión en el controlador ExistController en la sección donde se establecen las propiedades del XQDataSource.
    Ejecuta la aplicación desde la clase Main.
    
    
# Uso

Una vez que la aplicación se ejecute, aparecerá un menú en la consola con diferentes opciones. El usuario puede seleccionar diferentes opciones para realizar diferentes operaciones en la base de datos.

Las opciones del menú incluyen:

    Seleccionar todos los characters y listar los atributos.
    Seleccionar todos los characters y listar SOLO sus nombres, ordenados por orden alfabetico.
    Seleccionar todos los characters que contengan "Pea" y listar los atributos.
    Seleccionar characters que contengan 2 habilidades y listar los atributos.
    Seleccionar todos los characters que sean zombie (id character type: 2).
    Seleccionar un character en concreto y listar los atributos.
    Seleccionar un character concreto y modificarle el nombre.
    Modificar varios registros de characters a la vez.
    Eliminar un character en concreto en base a su nombre.
    Eliminar 2 characters a la vez en base a su nombre.
    Salir.
    
Para seleccionar una opción del menú, simplemente ingresa el número de la opción y presiona Enter. En algunos casos, se le pedirá que ingrese información adicional, como el nombre de un personaje a buscar o el nombre de un personaje para eliminar.


# Autor

Tarik Aabouch Azougarth
