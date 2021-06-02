window.onload = function() {
    var detalle = "";

    //crea los elementos
    const contenedor = document.createElement("div");
    const publicado = document.createElement("section");

    //crea las clases de los elementos
    contenedor.classList.add("contenedor");
    publicado.classList.add("publicaciones");

    //crea la estructura de hijos y padres
    document.body.appendChild(contenedor);
    contenedor.appendChild(publicado);

	leerPublicacion();

    async function leerPublicacion() {
        const response = await fetch('/publicaciones');
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
                <span><strong>Descripcion:</strong> ${publicacion.descripcion}</span>
                <span><strong>Usuario:</strong> ${publicacion.usuario.username}</span>
                <span><strong>Precio:</strong> ${publicacion.precio}€</span>
            </div>
            <button class="boton">Añadir al carrito</button>
        </article>
    `
        }
        publicado.innerHTML = detalle;
    }
 }