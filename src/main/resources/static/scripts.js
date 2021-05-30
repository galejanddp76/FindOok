window.onload = function() {
    var detalle = "";

    //crea los elementos
    const contenedor = document.createElement("div");
    const publicado = document.createElement("section");
    const carrito = document.createElement("section");

    //crea las clases de los elementos
    contenedor.classList.add("contenedor");
    publicado.classList.add("publicaciones");
    carrito.classList.add("carrito");
    carrito.style.right = "-400px";

    //crea la estructura de hijos y padres
    document.body.appendChild(contenedor);
    contenedor.appendChild(publicado);
    contenedor.appendChild(carrito);

    //crea el header del carrito
    const headerCarrito = document.createElement("header");
    headerCarrito.innerHTML = "<h3>Productos en carrito</h3>";
    carrito.appendChild(headerCarrito);

leerPublicacion();

    async function leerPublicacion() {
        const response = await fetch('https://findookapp.herokuapp.com/publicaciones');
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
    
        const botones = document.querySelectorAll(".boton");

        for (const boton of botones) {
            boton.addEventListener("click", ponerCarrito);

        }
        
        function ponerCarrito(even) {

            let detallesCompra = "";
            let evento = even.target;

            // Crea botón de eliminar
            let itemsCarrito = document.createElement("div");
            itemsCarrito.innerHTML = "<span class='eliminar'>X</span>";
            itemsCarrito.classList.add("elimina-compra");
            carrito.appendChild(itemsCarrito);

            //crea la imagen
            let imgCompra = document.createElement("img");
            imgCompra.classList.add("imagen")
            imgCompra.src = evento.parentNode.children[1].src;
            imgCompra.width = 150;
            itemsCarrito.appendChild(imgCompra);

            // Crea elementos detalles
            let compra = document.createElement("div");
            compra.classList.add("detallesCompra");
            detallesCompra += `
        <div class="detalles">
            <p>${evento.parentNode.children[0].textContent}</p>
            <p>${evento.parentNode.children[2].children[0].textContent}</p>
            <p>${evento.parentNode.children[2].children[1].textContent}</p>
            <p>${evento.parentNode.children[2].children[2].textContent}</p>
        </div>
        `
            compra.innerHTML = detallesCompra;
            itemsCarrito.appendChild(compra);

            // Crea botón de compra
            itemsCarrito.innerHTML += "<span class='comprar'>Comprar</span>";


            // Evento para eliminar compra del carrito
            itemsCarrito.firstChild.addEventListener("click", eliminaCompra);
            itemsCarrito.lastChild.addEventListener("click", eliminaCompra);

            //Función para eliminar compra
            function eliminaCompra(even) {
                let elementoSeleccionado = even.target.parentNode;
                elementoSeleccionado.remove();
            }
        }
        
        document.querySelector(".carro").addEventListener("click", muestraCarrito);
		
		    // funcion que muestra el carrito
    	function muestraCarrito() {
        	if (carrito.style.right == "-400px") {
            	carrito.style.right = 0;
        	} else {
            	carrito.style.right = "-400px";
        }
    }
    }
 }