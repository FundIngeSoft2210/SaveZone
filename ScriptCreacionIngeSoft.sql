-- Creacion de la base de datos

DROP DATABASE IF EXISTS SAFEZONE_DB;
CREATE DATABASE SAFEZONE_DB;
USE SAFEZONE_DB;

-- Eliminando Tablas

DROP TABLE IF EXISTS safezone_db.Calificacion;
DROP TABLE IF EXISTS safezone_db.Categoria;
DROP TABLE IF EXISTS safezone_db.Ciudad;
DROP TABLE IF EXISTS safezone_db.Departamento;
DROP TABLE IF EXISTS safezone_db.EstadoPago;
DROP TABLE IF EXISTS safezone_db.EstadoPedido;
DROP TABLE IF EXISTS safezone_db.EstadoProducto;
DROP TABLE IF EXISTS safezone_db.Pedido;
DROP TABLE IF EXISTS safezone_db.Producto;
DROP TABLE IF EXISTS safezone_db.ProductoFavorito;
DROP TABLE IF EXISTS safezone_db.Tarjeta;
DROP TABLE IF EXISTS safezone_db.TipoUsuario;
DROP TABLE IF EXISTS safezone_db.Usuario;

-- Creación de tablas

CREATE TABLE safezone_db.Calificacion (
  ID           int(10) NOT NULL AUTO_INCREMENT, 
  ProductoID   int(10) NOT NULL, 
  Calificacion int(1) NOT NULL, 
  Comentario   varchar(255), 
  PRIMARY KEY (ID));
  
CREATE TABLE safezone_db.Categoria (
  ID          int(10) NOT NULL AUTO_INCREMENT, 
  Nombre      varchar(255) NOT NULL UNIQUE, 
  Descripcion varchar(255) NOT NULL, 
  PRIMARY KEY (ID));
  
CREATE TABLE safezone_db.Ciudad (
  ID             int(10) NOT NULL AUTO_INCREMENT, 
  DepartamentoID int(10) NOT NULL, 
  Nombre         varchar(255) NOT NULL, 
  PRIMARY KEY (ID));
  
CREATE TABLE safezone_db.Departamento (
  ID     int(10) NOT NULL AUTO_INCREMENT, 
  Nombre varchar(255) NOT NULL, 
  PRIMARY KEY (ID));
  
CREATE TABLE safezone_db.EstadoPago (
  ID          int(10) NOT NULL AUTO_INCREMENT, 
  Nombre      varchar(255) NOT NULL UNIQUE, 
  Descripcion varchar(255) NOT NULL, 
  PRIMARY KEY (ID));
  
CREATE TABLE safezone_db.EstadoPedido (
  ID          int(10) NOT NULL AUTO_INCREMENT, 
  Nombre      varchar(255) NOT NULL UNIQUE, 
  Descripcion varchar(255) NOT NULL, 
  PRIMARY KEY (ID));
  
CREATE TABLE safezone_db.EstadoProducto (
  ID         int(10) NOT NULL AUTO_INCREMENT, 
  Nombre     varchar(255) NOT NULL UNIQUE, 
  Disponible bit(1) NOT NULL, 
  PRIMARY KEY (ID));
  
CREATE TABLE safezone_db.Pedido (
  ID               int(10) NOT NULL AUTO_INCREMENT, 
  CompradorID      int(10) NOT NULL, 
  ProductoID       int(10) NOT NULL, 
  TarjetaID        int(10) NOT NULL, 
  EstadoPedidoID   int(10) NOT NULL, 
  EstadoPagoID     int(10) NOT NULL, 
  DireccionOrigen  varchar(255) NOT NULL, 
  DireccionDestino varchar(255) NOT NULL, 
  GuiaRastreo      varchar(255) NOT NULL, 
  PesoTotal        numeric(19, 0) NOT NULL, 
  Cantidad         int(10) NOT NULL, 
  Subtotal         numeric(19, 0) NOT NULL, 
  Total            numeric(19, 0) NOT NULL, 
  PRIMARY KEY (ID));
  
CREATE TABLE safezone_db.Producto (
  ID                  int(10) NOT NULL AUTO_INCREMENT, 
  VendedorID          int(10) NOT NULL, 
  Cantidad            int(10) NOT NULL, 
  Titulo              varchar(255) NOT NULL,
  Descripcion         varchar(255) NOT NULL, 
  Valor               int(10) NOT NULL, 
  PorcentajeDescuento int(10) NOT NULL, 
  Peso                numeric(19, 0) NOT NULL, 
  Alto                numeric(19, 0) NOT NULL, 
  Largo               numeric(19, 0) NOT NULL, 
  Ancho               numeric(19, 0) NOT NULL, 
  Color               varchar(255) NOT NULL, 
  EstadoProductoID    int(10) NOT NULL, 
  CiudadID            int(10) NOT NULL, 
  CategoriaID         int(10) NOT NULL, 
  PRIMARY KEY (ID));
  
CREATE TABLE safezone_db.ProductoFavorito (
  ID         int(10) NOT NULL AUTO_INCREMENT, 
  UsuarioID  int(10) NOT NULL, 
  ProductoID int(10) NOT NULL, 
  PRIMARY KEY (ID));
  
CREATE TABLE safezone_db.Tarjeta (
  ID             int(10) NOT NULL AUTO_INCREMENT, 
  UsuarioID      int(10) NOT NULL, 
  NumeroTarjeta  int(10) NOT NULL UNIQUE, 
  CVV            int(10), 
  AñoVencimiento int(10) NOT NULL, 
  MesVencimiento int(10) NOT NULL, 
  Activo         bit(1) NOT NULL, 
  PRIMARY KEY (ID));
  
CREATE TABLE safezone_db.TipoUsuario (
  ID          int(10) NOT NULL AUTO_INCREMENT, 
  Nombre      varchar(255) NOT NULL UNIQUE, 
  Descripcion varchar(255) NOT NULL, 
  Nivel       int(1) NOT NULL, 
  PRIMARY KEY (ID));
  
CREATE TABLE safezone_db.Usuario (
  ID                int(10) NOT NULL AUTO_INCREMENT, 
  Nombre            varchar(255) NOT NULL, 
  Apellido          varchar(255) NOT NULL, 
  Usuario           varchar(255) NOT NULL UNIQUE, 
  Contraseña        varchar(500) NOT NULL, 
  CorreoElectronico varchar(255) NOT NULL, 
  FechaNacimiento   date NOT NULL, 
  FechaRegistro     date NOT NULL, 
  Telefono          varchar(255) NOT NULL, 
  Direccion         varchar(255) NOT NULL, 
  CiudadID          int(10) NOT NULL, 
  TipoUsuarioID     int(10) NOT NULL, 
  PRIMARY KEY (ID));

  
-- Foreigns

ALTER TABLE safezone_db.Producto ADD CONSTRAINT FKProducto895765 FOREIGN KEY (VendedorID) REFERENCES Usuario (ID);
ALTER TABLE safezone_db.Calificacion ADD CONSTRAINT FKCalificaci835149 FOREIGN KEY (ProductoID) REFERENCES Producto (ID);
ALTER TABLE safezone_db.ProductoFavorito ADD CONSTRAINT FKProductoFa399491 FOREIGN KEY (ProductoID) REFERENCES Producto (ID);
ALTER TABLE safezone_db.ProductoFavorito ADD CONSTRAINT FKProductoFa220754 FOREIGN KEY (UsuarioID) REFERENCES Usuario (ID);
ALTER TABLE safezone_db.Pedido ADD CONSTRAINT FKPedido916166 FOREIGN KEY (EstadoPagoID) REFERENCES EstadoPago (ID);
ALTER TABLE safezone_db.Usuario ADD CONSTRAINT FKUsuario810569 FOREIGN KEY (TipoUsuarioID) REFERENCES TipoUsuario (ID);
ALTER TABLE safezone_db.Pedido ADD CONSTRAINT FKPedido725929 FOREIGN KEY (EstadoPedidoID) REFERENCES EstadoPedido (ID);
ALTER TABLE safezone_db.Producto ADD CONSTRAINT FKProducto904736 FOREIGN KEY (CategoriaID) REFERENCES Categoria (ID);
ALTER TABLE safezone_db.Pedido ADD CONSTRAINT FKPedido529158 FOREIGN KEY (TarjetaID) REFERENCES Tarjeta (ID);
ALTER TABLE safezone_db.Producto ADD CONSTRAINT FKProducto642469 FOREIGN KEY (CiudadID) REFERENCES Ciudad (ID);
ALTER TABLE safezone_db.Usuario ADD CONSTRAINT FKUsuario685879 FOREIGN KEY (CiudadID) REFERENCES Ciudad (ID);
ALTER TABLE safezone_db.Ciudad ADD CONSTRAINT FKCiudad396117 FOREIGN KEY (DepartamentoID) REFERENCES Departamento (ID);
ALTER TABLE safezone_db.Pedido ADD CONSTRAINT FKPedido97630 FOREIGN KEY (ProductoID) REFERENCES Producto (ID);
ALTER TABLE safezone_db.Pedido ADD CONSTRAINT FKPedido62546 FOREIGN KEY (CompradorID) REFERENCES Usuario (ID);
ALTER TABLE safezone_db.Producto ADD CONSTRAINT FKProducto155724 FOREIGN KEY (EstadoProductoID) REFERENCES EstadoProducto (ID);
ALTER TABLE safezone_db.Tarjeta ADD CONSTRAINT FKTarjeta490215 FOREIGN KEY (UsuarioID) REFERENCES Usuario (ID);

-- Llenado de tablas de referencia

INSERT INTO `safezone_db`.`tipousuario` (`ID`, `Nombre`, `Descripcion`, `Nivel`) VALUES ('1', 'Registrado', 'Usuario registrado', '0');
INSERT INTO `safezone_db`.`tipousuario` (`ID`, `Nombre`, `Descripcion`, `Nivel`) VALUES ('2', 'Administrador', 'Administrador del sistema', '1');

INSERT INTO `safezone_db`.`departamento` (`ID`, `Nombre`) VALUES ('1', 'Cundinamarca');

INSERT INTO `safezone_db`.`ciudad` (`DepartamentoID`, `Nombre`) VALUES ('1', 'Bogotá');

INSERT INTO `safezone_db`.`usuario` (`Nombre`, `Apellido`, `Usuario`, `Contraseña`, `CorreoElectronico`, `FechaNacimiento`, `FechaRegistro`, `Telefono`, `Direccion`, `CiudadID`, `TipoUsuarioID`) VALUES ('NICOLAS', 'CUBILLOS', 'nicolasd-cubillos', 'David123', 'nicolasdcubillos@gmail.com', '2002-12-16', '2022-04-11', '3163009512', 'Cra 64 24', '1', '1');
INSERT INTO `safezone_db`.`usuario` (`Nombre`, `Apellido`, `Usuario`, `Contraseña`, `CorreoElectronico`, `FechaNacimiento`, `FechaRegistro`, `Telefono`, `Direccion`, `CiudadID`, `TipoUsuarioID`) VALUES ('ESTEBAN', 'ROJAS', 'rojases', 'Esteban123', 'estebanrojas@gmail.com', '2001-12-27', '2022-04-11', '3153633556', 'Cra 65 25', '1', '1');
INSERT INTO `safezone_db`.`usuario` (`Nombre`, `Apellido`, `Usuario`, `Contraseña`, `CorreoElectronico`, `FechaNacimiento`, `FechaRegistro`, `Telefono`, `Direccion`, `CiudadID`, `TipoUsuarioID`) VALUES ('ANGELLO', 'JAIMES', 'angello.jaimes', 'Angello123', 'angellojaimes@gmail.com', '2002-05-05', '2022-04-11', '3175889432', 'Cra 9 155', '1', '1');

INSERT INTO `safezone_db`.`estadoproducto` (`Nombre`, `Disponible`) VALUES ('En venta', b'1');
INSERT INTO `safezone_db`.`estadoproducto` (`Nombre`, `Disponible`) VALUES ('Agotado', b'0');
INSERT INTO `safezone_db`.`estadoproducto` (`Nombre`, `Disponible`) VALUES ('Bloqueado', b'0');
INSERT INTO `safezone_db`.`estadoproducto` (`Nombre`, `Disponible`) VALUES ('Oculto', b'0');
INSERT INTO `safezone_db`.`estadoproducto` (`Nombre`, `Disponible`) VALUES ('En promoción', b'1');
INSERT INTO `safezone_db`.`estadoproducto` (`ID`, `Nombre`, `Disponible`) VALUES ('6', 'Eliminado', b'0');

INSERT INTO `safezone_db`.`categoria` (`Nombre`, `Descripcion`) VALUES ('Otros','Productos sin categoria');
INSERT INTO `safezone_db`.`categoria` (`Nombre`, `Descripcion`) VALUES ('Supermercado','Supermercado');
INSERT INTO `safezone_db`.`categoria` (`Nombre`, `Descripcion`) VALUES ('Tecnología','Tecnología');
INSERT INTO `safezone_db`.`categoria` (`Nombre`, `Descripcion`) VALUES ('Electrodomésticos','Electrodomésticos');
INSERT INTO `safezone_db`.`categoria` (`Nombre`, `Descripcion`) VALUES ('Hogar y muebles','Hogar y muebles');
INSERT INTO `safezone_db`.`categoria` (`Nombre`, `Descripcion`) VALUES ('Deportes y Fitness','Deportes y Fitness');
INSERT INTO `safezone_db`.`categoria` (`Nombre`, `Descripcion`) VALUES ('Belleza y Cuidado Personal','Belleza y Cuidado Personal');
INSERT INTO `safezone_db`.`categoria` (`Nombre`, `Descripcion`) VALUES ('Accesorios para vehículos','Accesorios para vehículos');
INSERT INTO `safezone_db`.`categoria` (`Nombre`, `Descripcion`) VALUES ('Herrramientas','Herrramientas');
INSERT INTO `safezone_db`.`categoria` (`Nombre`, `Descripcion`) VALUES ('Construcción','Construcción');
INSERT INTO `safezone_db`.`categoria` (`Nombre`, `Descripcion`) VALUES ('Inmuebles','Inmuebles');
INSERT INTO `safezone_db`.`categoria` (`Nombre`, `Descripcion`) VALUES ('Moda','Moda');
INSERT INTO `safezone_db`.`categoria` (`Nombre`, `Descripcion`) VALUES ('Juguetes','Juguetes');
INSERT INTO `safezone_db`.`categoria` (`Nombre`, `Descripcion`) VALUES ('Salud','Salud');
INSERT INTO `safezone_db`.`categoria` (`Nombre`, `Descripcion`) VALUES ('Industria y Oficinas','Industria y Oficinas');
INSERT INTO `safezone_db`.`categoria` (`Nombre`, `Descripcion`) VALUES ('Servicios','Servicios');

INSERT INTO `safezone_db`.`producto` (`VendedorID`, `Cantidad`, `Titulo`, `Descripcion`, `Valor`, `PorcentajeDescuento`, `Peso`, `Alto`, `Largo`, `Ancho`, `Color`, `EstadoProductoID`, `CiudadID`, `CategoriaID`) VALUES ('1', '50', 'Tapabocas desechables 10 unidades por caja', 'Cajas de tapabocas 10 unidades', '30000', '5', '15', '15', '15', '15', 'Blanco', '1', '1', '14');

INSERT INTO `safezone_db`.`productofavorito` (`UsuarioID`, `ProductoID`) VALUES ('2', '1');
INSERT INTO `safezone_db`.`productofavorito` (`UsuarioID`, `ProductoID`) VALUES ('3', '1');
