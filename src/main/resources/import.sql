/*Productos*/
INSERT INTO productos(nombre,precio,create_at) VALUES('Panasonic Pantalla',25145,NOW());
INSERT INTO productos(nombre,precio,create_at) VALUES('Sony camara',1258,NOW());
INSERT INTO productos(nombre,precio,create_at) VALUES('Modem',2145,NOW());
INSERT INTO productos(nombre,precio,create_at) VALUES('Teclado',25145,NOW());
INSERT INTO productos(nombre,precio,create_at) VALUES('Mouse',140,NOW());
INSERT INTO productos(nombre,precio,create_at) VALUES('Impresora',3145,NOW());

/*Ventas*/

INSERT INTO ventas(create_at) VALUES(NOW());
INSERT INTO detalle_ventas(cantidad,venta_id,producto_id) VALUES(2,1,1);
INSERT INTO detalle_ventas(cantidad,venta_id,producto_id) VALUES(3,1,2);
INSERT INTO detalle_ventas(cantidad,venta_id,producto_id) VALUES(4,1,4);
INSERT INTO detalle_ventas(cantidad,venta_id,producto_id) VALUES(5,1,3);

INSERT INTO ventas(create_at) VALUES(NOW());
INSERT INTO detalle_ventas(cantidad,venta_id,producto_id) VALUES(1,2,4);
INSERT INTO detalle_ventas(cantidad,venta_id,producto_id) VALUES(3,2,5);
INSERT INTO detalle_ventas(cantidad,venta_id,producto_id) VALUES(2,2,1);
INSERT INTO detalle_ventas(cantidad,venta_id,producto_id) VALUES(1,2,3);

/*Creamos algunos usuarios con roles*/
INSERT INTO users(username,password,enabled) VALUES('user','$2a$10$1r9JTbsY9UkV6d3yq3pVtuLcg9b8RCPDFPcK0rDlRk8bKZ1UcYt.a',1);
INSERT INTO users(username,password,enabled) VALUES('admin','$2a$10$JlTI2pEm.PTUfWLwHq.qeen.7K3C4N6Hx8TSZ5kEAO/SD98gMqc4y',1);

INSERT INTO authorities(user_id,authority) VALUES(1,'ROLE_USER');
INSERT INTO authorities(user_id,authority) VALUES(2,'ROLE_USER');
INSERT INTO authorities(user_id,authority) VALUES(2,'ROLE_ADMIN');