window.onload = function() {
    var detalle = "";

	leerOfertas();
	//lee las ofertas de la base de datos
    async function leerOfertas() {
        const response = await fetch('/ofertasJson/usuario');
        const ofertas = await response.json();
        mostrarOfertas(ofertas);
    }

    //crea las publicaciones
    function mostrarOfertas(ofertas) {
        for (oferta of ofertas) {
            detalle += `
        <div class = "oferta">
            <div class="titulo">
                <span><strong>${oferta.titulo}</strong></span>
            </div>
            <img src="${oferta.imagenoferta}" alt="{$oferta.titulo}">
            <div class="descripcion">
            	<span><strong>Descripcion:</strong> ${oferta.descripcion}</span>
            </div>
            <span><strong>Usuario:</strong> ${oferta.nombreusuario}</span>
            <span><strong>Correo:</strong> ${oferta.contacto}€</span>
            <span><strong>Fecha:</strong> ${oferta.fechaoferta}€</span>
            <a href="/eliminaroferta?idoferta=${oferta.idoferta}" class="boton">Eliminar</a>
        </div> `;
        }
        document.querySelector(".misOfertas").innerHTML = detalle;
    }
}