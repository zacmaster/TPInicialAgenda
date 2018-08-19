USE agenda;

CREATE TABLE `tipo_contacto`
(
    `idTipoContacto` int(2) NOT NULL AUTO_INCREMENT,
    `Tipo_Contacto` varchar(45) NOT NULL,
    PRIMARY KEY (`idTipoContacto`)
);

CREATE TABLE `localidad`
(
    `idLocalidad` int(4) NOT NULL AUTO_INCREMENT,
    `Localidad` varchar(45) NOT NULL,
    PRIMARY KEY (`idLocalidad`)
);

CREATE TABLE `personas`
(
    `idPersona` int(11) NOT NULL AUTO_INCREMENT,
    `Nombre` varchar(45) NOT NULL,
    `Fecha_Nacimiento` date, 
    `Correo` varchar(45),
    `Telefono` varchar(20) NOT NULL,
    `Calle` varchar(45),
    `Altura` int(4),
    `Piso` int(2),
    `Departamento` varchar(2),
    `idLocalidad` int(4),
    `idTipoContacto` int(2),
    PRIMARY KEY (`idPersona`),
    FOREIGN KEY (`idLocalidad`) REFERENCES `localidad`(`idLocalidad`),
    FOREIGN KEY (`idTipoContacto`) REFERENCES `tipo_contacto`(`idTipoContacto`)
);
