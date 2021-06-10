window.onload = function() {
    var detalle = "";

	leerPublicacion();
	//lee las publicaciones de la base de datos
    async function leerPublicacion() {
        const response = await fetch('/publicacionesJson/usuario');
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
            <a href="/verPublicacion?idpublicacion=${publicacion.idpublicacion}" class="boton">Ver mas</a>
            <a href="/editarpublicacion?idpublicacion=${publicacion.idpublicacion}" class="boton">Editar</a>
        	<a href="/eliminarpublicacion?idpublicacion=${publicacion.idpublicacion}" class="boton">Eliminar</a>
            
        </article>
    `
        }
        document.querySelector(".misPublicaciones").innerHTML = detalle;
    }
}