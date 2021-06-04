window.onload = function() {
    var datos = "";

    //crea la tabla
    const tabla = document.createElement("table");
    tabla.classList.add("tabla");
    document.body.appendChild(tabla);

	leerUsuario();
	//lee los usuarios de la base de datos
    async function leerUsuario() {
        const response = await fetch('/usuariosJson');
        const usuarios = await response.json();
        mostrarUsuarios(usuarios);
    }


    //crea las publicaciones
    function mostrarUsuarios(usuarios) {
    datos += `	
    <tr>
        <th>ID</th>
        <th>USERNAME</th>
        <th>CORREO</th>
        <th>FECHA REGISTRO</th>
        <th>EDITAR</th>
        <th>ELIMINAR</th>
 	</tr>
    `;
        for (usuario of usuarios) {
            datos += `
 		<tr>
        	<td>${usuario.idusuario}</td>
        	<td>${usuario.username}</td>
        	<td>${usuario.correo}</td>
        	<td>${usuario.fecharegistro}</td>
        	<td><a href="/modificar"><img alt="modificar" src="/images/editar.png"></a></td>
        	<td><a href="/eliminar?idusuario=${usuario.idusuario}"><img alt="eliminar" src="/images/papelera.png"></a></td> 
      	</tr>
    `
        }
        tabla.innerHTML = datos;
    }
 }