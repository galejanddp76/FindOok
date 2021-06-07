window.onload = function() {
    var datosusuarios = "";
    var datospublicaciones = "";

    //crea la tabla usuarios
    const tablausuarios = document.createElement("table");
    tablausuarios.classList.add("tablausuarios");
    document.querySelector(".contenedortablas").appendChild(tablausuarios);
    //crea la tabla publicaciones
    const tablapublicaciones = document.createElement("table");
    tablapublicaciones.classList.add("tablapublicaciones");
    document.querySelector(".contenedortablas").appendChild(tablapublicaciones);
    
        datosusuarios += `	
    <tr>
        <th>ID</th>
        <th>USERNAME</th>
        <th>CORREO</th>
        <th>FECHA REGISTRO</th>
        <th>EDITAR</th>
        <th>ELIMINAR</th>
 	</tr>
    `;
    tablausuarios.innerHTML = datosusuarios;
    
    datospublicaciones += `	
    <tr>
        <th>ID</th>
        <th>TITULO</th>
        <th>USUARIO</th>
        <th>FECHA CREACION</th>
        <th>EDITAR</th>
        <th>ELIMINAR</th>
 	</tr>
    `;
    
    tablapublicaciones.innerHTML = datospublicaciones;

	leerUsuario();
	//lee los usuarios de la base de datos
    async function leerUsuario() {
        const response = await fetch('/usuariosJson');
        const usuarios = await response.json();
        mostrarUsuarios(usuarios);
    }

    //crea los usuarios
    function mostrarUsuarios(usuarios) {
        for (usuario of usuarios) {
            datosusuarios += `
 		<tr>
        	<td>${usuario.idusuario}</td>
        	<td>${usuario.username}</td>
        	<td>${usuario.correo}</td>
        	<td>${usuario.fecharegistro}</td>
        	<td><a href="/editarusuario?idusuario=${usuario.idusuario}"><img alt="modificar" src="/images/editar.png"></a></td>
        	<td><a href="/eliminarusuario?idusuario=${usuario.idusuario}"><img alt="eliminar" src="/images/papelera.png"></a></td> 
      	</tr>
    `
        }
        tablausuarios.innerHTML = datosusuarios;
    }
    
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
            datospublicaciones += `
 		<tr>
        	<td>${publicacion.idpublicacion}</td>
        	<td>${publicacion.titulo}</td>
        	<td>${publicacion.usuario.username}</td>
        	<td>${publicacion.fechacreacion}</td>
        	<td><a href="/editarpublicacion?idpublicacion=${publicacion.idpublicacion}"><img alt="modificar" src="/images/editar.png"></a></td>
        	<td><a href="/eliminarpublicacion?idpublicacion=${publicacion.idpublicacion}"><img alt="eliminar" src="/images/papelera.png"></a></td> 
      	</tr>
    `
        }
        tablapublicaciones.innerHTML = datospublicaciones;
    }
 }
 
     
    function cambiaTablaUsuario(){
    		document.querySelector(".tablapublicaciones").style.display = "none";
            document.querySelector(".tablausuarios").style.display = "table";
    }
    
        function cambiaTablaPublicaciones(){
    		 document.querySelector(".tablausuarios").style.display = "none";
            document.querySelector(".tablapublicaciones").style.display = "table";
    }