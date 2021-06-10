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
        <img src="${oferta.imagenoferta}" alt="{$oferta.titulo}">
        	<div class = "info">
            <div class="asunto"> <a href="/eliminaroferta?idoferta=${oferta.idoferta}" class="boton">x</a><span><strong>${oferta.titulo}</strong></span></div>
            <div><span><strong>Descripcion:</strong> ${oferta.descripcion}</span></div>
            <div><span><strong>Usuario:</strong> ${oferta.nombreusuario}</span></div>
            <div><span><strong>Correo:</strong> ${oferta.contacto}</span></div>
            <div><span><strong>Fecha:</strong> ${oferta.fechaoferta}</span></div>
            </div>
        </div> `;
        }
        document.querySelector(".misOfertas").innerHTML = detalle;
    }
}