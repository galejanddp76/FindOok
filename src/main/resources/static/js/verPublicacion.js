window.onload = function() {
	const id = getQueryVariable("idpublicacion");
    var datoscomentarios = "";
    //crea los elementos
   
	leerPublicacion();
	//lee la publicacion de la base de datos
    async function leerPublicacion() {
        const response = await fetch('/publicacionesJson/id/'+id);
        const publicacion = await response.json();
        mostrarDetalles(publicacion);
    }


    //crea detalles de la publicacion
    function mostrarDetalles(publicacion) {
        let detalles = `
        <div class="usuimg">
        	<img src="${publicacion.imagenpublicacion}" alt="${publicacion.titulo}"/>
        	<div class="contacto">
        		<div><strong>CONTACTO</strong></div>
        		<div><strong>Usuario:</strong> ${publicacion.usuario.username}</div>
        		<div><strong>Correo:</strong> ${publicacion.usuario.correo}</div>
        	</div>
        </div>
            <div class="detalles">
                <div class="titulo"><h2>${publicacion.titulo}</h2></div>
                <div class="info">
                	<div class="descripcion"><p>${publicacion.descripcion}</p></div>
                	<div class="precio"><strong>Precio:</strong> ${publicacion.precio}€</div>
                	<div class="botones">
            			<a href="/intercambio">Intercambiar</a>
            			<a href="/pago">Comprar</a>
           			</div>
            	</div>
            </div>`;
            document.querySelector(".publicacion").innerHTML = detalles;
            document.querySelector(".comentar").innerHTML += `<input type="hidden" name="publicacion" id="publicacion" value="${publicacion.idpublicacion}"/>`;
        }
        
        leerComentarios();
	//lee los comentarios de la publicacion en la base de datos
    async function leerComentarios() {
        const response = await fetch('/comentariosJson/id/'+id);
        const comentarios = await response.json();
        mostrarComentarios(comentarios);
    }

    //crea los comentarios
    function mostrarComentarios(comentarios) {
        for (comentario of comentarios) {
            datoscomentarios += `
 		<div class="comentario">
        	<h2>${comentario.usuario.username}</h2>
        	<h3>${comentario.asunto}</h3>
        	<p>${comentario.contenido}</p>
        	<p>${comentario.fecha}</p>
      	</div>
    `
        }
        document.querySelector(".comentarios").innerHTML += datoscomentarios;
    }
        
    }
 
 function getQueryVariable(variable)
{
       var query = window.location.search.substring(1);
       var vars = query.split("&");
       for (var i=0;i<vars.length;i++) {
               var pair = vars[i].split("=");
               if(pair[0] == variable){
               	return pair[1];
               }
       }
       return(false);
       
}