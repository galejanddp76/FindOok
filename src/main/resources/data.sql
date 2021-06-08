insert into usuarios(idusuario,username,correo,fecharegistro,password) values(1,"admin","admin@admin.com","2001-11-29","$2a$10$p9oA2jHXCZhhs2CAjd.kmOGDNsndR0GzhcT1QI2.NtayvTgvvKhsu")

insert into roles(nombrerol) values("ROLE_ADMIN")
insert into roles(nombrerol) values("ROLE_USER")

insert into usuariosroles(idrol,idusuario) values(1,1)

insert into publicaciones(idpublicacion,titulo,descripcion,imagenpublicacion,fechacreacion,precio,idusuario) values(1,"Prueba","Esto es una prueba","/images/logo_small.png","2001-11-29",3,1)