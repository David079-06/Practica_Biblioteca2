# Colección Bruno — GameVault API

Esta colección contiene las peticiones preparadas para videojuegos, socios y préstamos. Abre esta carpeta con **Open Collection** y selecciona el entorno **Local**.

Antes de enviar peticiones, arranca la aplicación y comprueba que MySQL está encendido y tiene la base `biblioteca` de la práctica. El archivo SQL de ejemplo elimina y vuelve a crear esa base, así que ejecútalo solo si puedes reemplazar sus datos. Si tus IDs son distintos, cambia las variables de `environments/Local.bru`.

Las peticiones de alta, devolución y borrado cambian datos: revisa el cuerpo y el ID antes de enviarlas. Con el SQL de ejemplo, `socioSinPrestamosId` sirve para comprobar el borrado correcto; `socioId` sirve para comprobar el conflicto por préstamos. La petición `Error 422` usa a Paula (menor) y una copia disponible de un juego PEGI 18. La `Error 409` de devolución usa un préstamo ya devuelto.