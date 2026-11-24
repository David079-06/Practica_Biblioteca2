# Colección Bruno — GameVault API

Esta colección contiene las peticiones preparadas para las partes terminadas: videojuegos y socios. Abre esta carpeta con **Open Collection** y selecciona el entorno **Local**.

Antes de enviar una petición, arranca la aplicación desde IntelliJ o VS Code y comprueba que MySQL está encendido y tiene la base de datos de la práctica anterior. Si los IDs no coinciden, cambia las variables de `environments/Local.bru` por IDs que existan en tu base.

Prueba las peticiones numeradas. La petición de error 404 usa un videojuego que no existe; la de error 409 necesita que `emailDuplicado` ya pertenezca a un socio. Las altas pueden cambiar los datos de la base, así que revisa el cuerpo antes de enviarlas.