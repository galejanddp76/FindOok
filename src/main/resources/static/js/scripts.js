window.onload = function() {
    var detalle = "";

    //crea los elementos
    const publicado = document.createElement("div");

    //crea las clases de los elementos
    publicado.classList.add("publicaciones");

    //crea la estructura de hijos y padres
    document.body.appendChild(publicado);

	leerPublicacion();
	//lee las publicaciones de la base de datos
    async function leerPublicacion() {
        const response = await fetch('/publicacionesJson');
        const publicaciones = await response.json();
        mostrarPublicaciones(publicaciones);
    }


    //crea las publicaciones
    function mostrarPublicaciones(publicaciones) {
        for (publicacion of publicaciones) {
            detalle += `
        <article class = "item">
            <div class="titulo">
                <span><strong>${publicacion.titulo}</strong></span>
            </div>
            <img src="${publicacion.imagenpublicacion}" alt="${publicacion.titulo}">
            <div class="info">
                <div class="descripcion"><span><strong>Descripcion:</strong> ${publicacion.descripcion}</span></div>
                <span><strong>Usuario:</strong> ${publicacion.usuario.username}</span>
                <span><strong>Precio:</strong> ${publicacion.precio}€</span>
            </div>
            <a href="#" class="boton">Ver mas</a>
        </article>
    `
        }
        publicado.innerHTML = detalle;
    }
 }