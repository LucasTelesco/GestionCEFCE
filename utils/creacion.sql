create table Becarios(nombre char(12) PRIMARY KEY,
                                                 pass char(40),
                                                 total_vendido float);
                                                 
create table Producto(nombre char(36) PRIMARY KEY,
                                                   precio float,
                                                   cantdis decimal(8,0),
                                                   minimo_alerta decimal(8,0),
                                                   cantpromo decimal(8,0),
                                                   preciopromo float);
                                                   
create table caja(dia timestamp PRIMARY KEY,
                                       becario char(20),
                                       total float);
                                       
create table mate(dni char(20) PRIMARY KEY,
                                         termo char(5),
                                         mate char(5));
                                         
create table venta(codigo int auto_increment PRIMARY KEY,
                                           precio float,
                                           dia timestamp);
                                            
 create table datoventa(codigo int auto_increment ,
                                                      nombre char(36),
                                                      cantidad int,
                                                      dia timestamp);