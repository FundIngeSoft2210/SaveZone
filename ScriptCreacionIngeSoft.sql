CREATE TABLE Calificacion (
  ID           int(10) NOT NULL AUTO_INCREMENT, 
  ProductoID   int(10) NOT NULL, 
  Calificacion int(1) NOT NULL, 
  Comentario   varchar(255), 
  PRIMARY KEY (ID));
CREATE TABLE Categoria (
  ID          int(10) NOT NULL AUTO_INCREMENT, 
  Nombre      varchar(255) NOT NULL UNIQUE, 
  Descripcion varchar(255) NOT NULL, 
  PRIMARY KEY (ID));
CREATE TABLE Ciudad (
  ID             int(10) NOT NULL AUTO_INCREMENT, 
  DepartamentoID int(10) NOT NULL, 
  Nombre         varchar(255) NOT NULL, 
  PRIMARY KEY (ID));
CREATE TABLE Departamento (
  ID     int(10) NOT NULL AUTO_INCREMENT, 
  Nombre varchar(255) NOT NULL, 
  PRIMARY KEY (ID));
CREATE TABLE EstadoPago (
  ID          int(10) NOT NULL AUTO_INCREMENT, 
  Nombre      varchar(255) NOT NULL UNIQUE, 
  Descripcion varchar(255) NOT NULL, 
  PRIMARY KEY (ID));
CREATE TABLE EstadoPedido (
  ID          int(10) NOT NULL AUTO_INCREMENT, 
  Nombre      varchar(255) NOT NULL UNIQUE, 
  Descripcion varchar(255) NOT NULL, 
  PRIMARY KEY (ID));
CREATE TABLE EstadoProducto (
  ID         int(10) NOT NULL AUTO_INCREMENT, 
  Nombre     varchar(255) NOT NULL UNIQUE, 
  Disponible bit(1) NOT NULL, 
  PRIMARY KEY (ID));
CREATE TABLE Pedido (
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
CREATE TABLE Producto (
  ID                  int(10) NOT NULL AUTO_INCREMENT, 
  VendedorID          int(10) NOT NULL, 
  Cantidad            int(10) NOT NULL, 
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
CREATE TABLE ProductoFavorito (
  UsuarioID  int(10) NOT NULL, 
  ProductoID int(10) NOT NULL, 
  PRIMARY KEY (UsuarioID, 
  ProductoID));
CREATE TABLE Tarjeta (
  ID             int(10) NOT NULL AUTO_INCREMENT, 
  UsuarioID      int(10) NOT NULL, 
  NumeroTarjeta  int(10) NOT NULL UNIQUE, 
  CVV            int(10), 
  AñoVencimiento int(10) NOT NULL, 
  MesVencimiento int(10) NOT NULL, 
  Activo         bit(1) NOT NULL, 
  PRIMARY KEY (ID));
CREATE TABLE TipoUsuario (
  ID          int(10) NOT NULL AUTO_INCREMENT, 
  Nombre      varchar(255) NOT NULL UNIQUE, 
  Descripcion varchar(255) NOT NULL, 
  Nivel       int(1) NOT NULL, 
  PRIMARY KEY (ID));
CREATE TABLE Usuario (
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
ALTER TABLE Producto ADD CONSTRAINT FKProducto895765 FOREIGN KEY (VendedorID) REFERENCES Usuario (ID);
ALTER TABLE Calificacion ADD CONSTRAINT FKCalificaci835149 FOREIGN KEY (ProductoID) REFERENCES Producto (ID);
ALTER TABLE ProductoFavorito ADD CONSTRAINT FKProductoFa399491 FOREIGN KEY (ProductoID) REFERENCES Producto (ID);
ALTER TABLE ProductoFavorito ADD CONSTRAINT FKProductoFa220754 FOREIGN KEY (UsuarioID) REFERENCES Usuario (ID);
ALTER TABLE Pedido ADD CONSTRAINT FKPedido916166 FOREIGN KEY (EstadoPagoID) REFERENCES EstadoPago (ID);
ALTER TABLE Usuario ADD CONSTRAINT FKUsuario810569 FOREIGN KEY (TipoUsuarioID) REFERENCES TipoUsuario (ID);
ALTER TABLE Pedido ADD CONSTRAINT FKPedido725929 FOREIGN KEY (EstadoPedidoID) REFERENCES EstadoPedido (ID);
ALTER TABLE Producto ADD CONSTRAINT FKProducto904736 FOREIGN KEY (CategoriaID) REFERENCES Categoria (ID);
ALTER TABLE Pedido ADD CONSTRAINT FKPedido529158 FOREIGN KEY (TarjetaID) REFERENCES Tarjeta (ID);
ALTER TABLE Producto ADD CONSTRAINT FKProducto642469 FOREIGN KEY (CiudadID) REFERENCES Ciudad (ID);
ALTER TABLE Usuario ADD CONSTRAINT FKUsuario685879 FOREIGN KEY (CiudadID) REFERENCES Ciudad (ID);
ALTER TABLE Ciudad ADD CONSTRAINT FKCiudad396117 FOREIGN KEY (DepartamentoID) REFERENCES Departamento (ID);
ALTER TABLE Pedido ADD CONSTRAINT FKPedido97630 FOREIGN KEY (ProductoID) REFERENCES Producto (ID);
ALTER TABLE Pedido ADD CONSTRAINT FKPedido62546 FOREIGN KEY (CompradorID) REFERENCES Usuario (ID);
ALTER TABLE Producto ADD CONSTRAINT FKProducto155724 FOREIGN KEY (EstadoProductoID) REFERENCES EstadoProducto (ID);
ALTER TABLE Tarjeta ADD CONSTRAINT FKTarjeta490215 FOREIGN KEY (UsuarioID) REFERENCES Usuario (ID);
