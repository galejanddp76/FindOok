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
            <a href="/verPublicacion?idpublicacion=${publicacion.idpublicacion}" class="boton">Ver mas</a>
        </article>
    `
        }
        publicado.innerHTML = detalle;
    }
 }
 
 function buscarInfo(){
 	const publicado = document.querySelector(".publicaciones");
 	publicado.innerHTML = '';
 	const buscar = document.querySelector("#buscar");
 	const texto = buscar.value.toLowerCase();
 	
 	leerPublicacionTitulo();
 	async function leerPublicacionTitulo() {
    const response = await fetch('/publicacionesJson/'+texto);
    const publicaciones = await response.json();
    filtrar(publicaciones);
    }
 	
 	const filtrar = (publicaciones) =>{
 	for (publicacion of publicaciones) {
 		let titulo = publicacion.titulo.toLowerCase();
 		if(titulo.indexOf(texto) != -1){
 			publicado.innerHTML += `
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
            	<a href="/verPublicacion" class="boton">Ver mas</a>
        	</article>`;
 		}
 	}
}
}
 