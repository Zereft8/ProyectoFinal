CREATE DATABASE Reposteria;

USE Reposteria;


CREATE TABLE `Empleados`
(
 `ID_Empleados`            int AUTO_INCREMENT NOT NULL ,
 `Nombre`                  varchar(45) NOT NULL ,
 `Apellidos`               varchar(45) NOT NULL ,
 `Cedula`                  varchar(13) NOT NULL ,
 `Sueldo`                  float NOT NULL ,
 `Télefono`                varchar(15) NOT NULL ,
 `Cargo`                   varchar(45) NOT NULL ,
 `ID_Empleados_Supervisor` int NULL ,

PRIMARY KEY (`ID_Empleados`),
KEY `Index_ID_Emp_Sup` (`ID_Empleados_Supervisor`),
CONSTRAINT `FK_Empleados_Supervisor` FOREIGN KEY `Index_ID_Emp_Sup` (`ID_Empleados_Supervisor`) REFERENCES `Empleados` (`ID_Empleados`)
);


CREATE TABLE `Usuarios`
(
 `ID_Usuarios`    int AUTO_INCREMENT NOT NULL ,
 `Contraseña`     varchar(45) NOT NULL ,
 `Nombre_Usuario` varchar(45) NOT NULL ,
 `Tipo_Usuario`   varchar(45) NOT NULL ,
 `Fecha_Registro` date NOT NULL ,
 `ID_Empleados`   int NOT NULL ,

PRIMARY KEY (`ID_Usuarios`),
KEY `Index_ID_Usuarios` (`ID_Empleados`),
CONSTRAINT `FK_Empleados_en_Usuarios` FOREIGN KEY `Index_ID_Usuarios` (`ID_Empleados`) REFERENCES `Empleados` (`ID_Empleados`)
);


CREATE TABLE `Clientes`
(
 `ID_Clientes` int AUTO_INCREMENT NOT NULL ,
 `Nombre`      varchar(45) NOT NULL ,
 `Apellidos`   varchar(45) NOT NULL ,
 `Cedula`      varchar(13) NOT NULL ,
 `Teléfono`    varchar(15) NULL ,

PRIMARY KEY (`ID_Clientes`)
);


CREATE TABLE `Empleado_Atiende_Cliente`
(
 `ID_Emp_Cli`         int AUTO_INCREMENT NOT NULL ,
 `ID_Clientes`        int NOT NULL ,
 `ID_Empleados`       int NOT NULL ,
 `Fecha_Cli_Atendido` date NOT NULL ,

PRIMARY KEY (`ID_Emp_Cli`),
KEY `Index_ID_Emp_Cli` (`ID_Clientes`),
CONSTRAINT `FK_Clientes_Emp_Cli` FOREIGN KEY `Index_ID_Emp_Cli` (`ID_Clientes`) REFERENCES `Clientes` (`ID_Clientes`),
KEY `Index_ID_Cli_Emp` (`ID_Empleados`),
CONSTRAINT `FK_Empleados_Emp_Cli` FOREIGN KEY `Index_ID_Cli_Emp` (`ID_Empleados`) REFERENCES `Empleados` (`ID_Empleados`)
);

CREATE TABLE `Productos`
(
 `ID_Productos` int AUTO_INCREMENT NOT NULL ,
 `Nombre`       varchar(45) NOT NULL ,
 `Precio`       float NOT NULL ,

PRIMARY KEY (`ID_Productos`)
);


CREATE TABLE `Pedidos`
(
 `ID_Pedidos`    int AUTO_INCREMENT NOT NULL ,
 `Descripcion`   varchar(100) NOT NULL ,
 `Fecha_Entrega` date NOT NULL ,
 `Fecha_Pedido`  date NOT NULL ,
 `ID_Clientes`   int NOT NULL ,
 `ID_Productos`  int NOT NULL ,

PRIMARY KEY (`ID_Pedidos`),
KEY `Index_ID_Clientes_Pedidos` (`ID_Clientes`),
CONSTRAINT `FK_Clientes_en_Pedidos` FOREIGN KEY `Index_ID_Clientes_Pedidos` (`ID_Clientes`) REFERENCES `Clientes` (`ID_Clientes`),
KEY `Index_ID_Pedidos_Productos` (`ID_Productos`),
CONSTRAINT `FK_Productos_en_Pedidos` FOREIGN KEY `Index_ID_Pedidos_Productos` (`ID_Productos`) REFERENCES `Productos` (`ID_Productos`)
);


INSERT INTO `Empleados` (`Nombre`, `Apellidos`, `Cedula`, `Sueldo`, `Télefono`, `Cargo`, `ID_Empleados_Supervisor`)
VALUES ('John', 'Doe', '1234567890', 5000, '555-555-5555', 'Gerente', NULL);

INSERT INTO `Usuarios` (`Contraseña`, `Nombre_Usuario`, `Tipo_Usuario`, `Fecha_Registro`, `ID_Empleados`)
VALUES ('clave123', 'usuario1', 'Admin', '2023-08-18', 1);
    
