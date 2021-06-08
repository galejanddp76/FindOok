window.onload = function() {
	const id = getQueryVariable("idpublicacion");
    
    //crea los elementos
    const publicado = document.createElement("div");
    const comentado = document.createElement("div");

    //crea las clases de los elementos
    publicado.classList.add("publicacion");
    comentado.classList.add("comentarios");

    //crea la estructura de hijos y padres
    document.body.appendChild(publicado);
    document.body.appendChild(comentado);

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
        	<div class="usu"><img src="/images/perfilDefault.png" alt="usuario"><strong>${publicacion.usuario.username}</strong></div>
        </div>
            <div class="contenedor">
                <div class="titulo"><h2>${publicacion.titulo}</h2></div>
                <div class="info">
                	<div class="descripcion"><p>${publicacion.descripcion}</p></div>
                	<div class="precio"><strong>Precio:</strong>${publicacion.precio}€</div>
                	<div class="botones">
            			<a href="/intercambio">Intercambiar</a>
            			<a href="/pago">Comprar</a>
           			</div>
            	</div>
            </div>`;
            publicado.innerHTML = detalles;
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