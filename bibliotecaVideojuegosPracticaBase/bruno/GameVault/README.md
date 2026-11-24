# Colección Bruno — GameVault API

Esta colección contiene las peticiones preparadas para videojuegos, socios, préstamos, desarrolladoras y estadísticas. La colección está en `bruno/GameVault`; abre esa carpeta con **Open Collection** y selecciona el entorno **Local**.

Antes de enviar peticiones, arranca la aplicación y comprueba que MySQL está encendido y tiene la base `biblioteca` de la práctica. El script SQL que acompaña la práctica elimina y vuelve a crear esa base, así que ejecútalo solo si puedes reemplazar sus datos. Si tus IDs son distintos, cambia las variables de `environments/Local.bru`.

Las peticiones de alta, devolución y borrado cambian datos: revisa el cuerpo y el ID antes de enviarlas. Con el SQL de ejemplo, `socioSinPrestamosId` sirve para comprobar el borrado correcto; `socioId` sirve para comprobar el conflicto por préstamos. La petición `Error 422` usa a Paula (menor) y una copia disponible de un juego PEGI 18. La petición `Error 409` de devolución usa un préstamo ya devuelto. Las carpetas **Desarrolladoras** y **Estadísticas** cubren los recursos auxiliares de lectura.
