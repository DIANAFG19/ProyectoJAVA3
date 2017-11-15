/*
 * Author:  YOO
 * Created: 15/11/2017
 */
create table mensajito(id integer primary key auto_increment,titulo varchar(50),cuerpo varchar(250));

describe mensajito;

select *from mensajito;

create table usuario(cuenta integer primary key, nombre varchar(40), email varchar(40));

create table direccion(id integer primary key auto_increment, cuenta integer, foreign key(cuenta) references usuario(cuenta), 
calle varchar(100), cp integer, municipio varchar(40));

describe usuario;
describe direccion;

select * from usuario;
select * from direccion;

