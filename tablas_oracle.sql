-- ================================================================
-- SCRIPT DE CONFIGURACION COMPLETA - AMBOS MICROSERVICIOS
-- Microservicios: servicio-pedidos (8081) y servicio-citas (8082)
-- ================================================================
-- PASO 1: Ejecutar este bloque conectado como SYSTEM
-- ================================================================

CREATE USER CUIDADOMASCOTAS IDENTIFIED BY Mascotas123;
GRANT CONNECT, RESOURCE, UNLIMITED TABLESPACE TO CUIDADOMASCOTAS;

-- ================================================================
-- PASO 2: Ejecutar el resto conectado como CUIDADOMASCOTAS
-- ================================================================

-- ----------------------------------------------------------------
-- MICROSERVICIO: servicio-pedidos
-- ----------------------------------------------------------------

CREATE SEQUENCE PEDIDOS_SEQ
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

CREATE TABLE PEDIDOS (
    ID                  NUMBER          NOT NULL,
    NOMBRE_PRODUCTO     VARCHAR2(150)   NOT NULL,
    CATEGORIA_PRODUCTO  VARCHAR2(50)    NOT NULL,
    CANTIDAD            NUMBER          NOT NULL,
    PRECIO_UNITARIO     NUMBER(10,2)    NOT NULL,
    PRECIO_TOTAL        NUMBER(10,2)    NOT NULL,
    ESTADO              VARCHAR2(30)    NOT NULL,
    NOMBRE_CLIENTE      VARCHAR2(100)   NOT NULL,
    FECHA_PEDIDO        VARCHAR2(20)    NOT NULL,
    CONSTRAINT PK_PEDIDOS PRIMARY KEY (ID)
);

INSERT INTO PEDIDOS (ID, NOMBRE_PRODUCTO, CATEGORIA_PRODUCTO, CANTIDAD, PRECIO_UNITARIO, PRECIO_TOTAL, ESTADO, NOMBRE_CLIENTE, FECHA_PEDIDO)
VALUES (PEDIDOS_SEQ.NEXTVAL, 'Alimento Premium Perro 15kg', 'Alimento', 2, 25990.00, 51980.00, 'Pendiente', 'Maria Lopez', '2026-03-20');

INSERT INTO PEDIDOS (ID, NOMBRE_PRODUCTO, CATEGORIA_PRODUCTO, CANTIDAD, PRECIO_UNITARIO, PRECIO_TOTAL, ESTADO, NOMBRE_CLIENTE, FECHA_PEDIDO)
VALUES (PEDIDOS_SEQ.NEXTVAL, 'Arena Sanitaria Gato 10kg', 'Higiene', 3, 8990.00, 26970.00, 'Enviado', 'Carlos Diaz', '2026-03-18');

INSERT INTO PEDIDOS (ID, NOMBRE_PRODUCTO, CATEGORIA_PRODUCTO, CANTIDAD, PRECIO_UNITARIO, PRECIO_TOTAL, ESTADO, NOMBRE_CLIENTE, FECHA_PEDIDO)
VALUES (PEDIDOS_SEQ.NEXTVAL, 'Collar Antipulgas Perro', 'Salud', 1, 12990.00, 12990.00, 'Entregado', 'Ana Torres', '2026-03-15');

INSERT INTO PEDIDOS (ID, NOMBRE_PRODUCTO, CATEGORIA_PRODUCTO, CANTIDAD, PRECIO_UNITARIO, PRECIO_TOTAL, ESTADO, NOMBRE_CLIENTE, FECHA_PEDIDO)
VALUES (PEDIDOS_SEQ.NEXTVAL, 'Juguete Mordedor Resistente', 'Juguete', 4, 5990.00, 23960.00, 'Pendiente', 'Pedro Sanchez', '2026-03-22');

INSERT INTO PEDIDOS (ID, NOMBRE_PRODUCTO, CATEGORIA_PRODUCTO, CANTIDAD, PRECIO_UNITARIO, PRECIO_TOTAL, ESTADO, NOMBRE_CLIENTE, FECHA_PEDIDO)
VALUES (PEDIDOS_SEQ.NEXTVAL, 'Cama Ortopedica Perro Grande', 'Accesorios', 1, 34990.00, 34990.00, 'Entregado', 'Jorge Martinez', '2026-03-10');

-- ----------------------------------------------------------------
-- MICROSERVICIO: servicio-citas
-- ----------------------------------------------------------------

CREATE SEQUENCE CITAS_SEQ
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

CREATE TABLE CITAS (
    ID              NUMBER          NOT NULL,
    NOMBRE_MASCOTA  VARCHAR2(100)   NOT NULL,
    TIPO_MASCOTA    VARCHAR2(50)    NOT NULL,
    NOMBRE_DUENO    VARCHAR2(100)   NOT NULL,
    TELEFONO_DUENO  VARCHAR2(20),
    SERVICIO        VARCHAR2(100)   NOT NULL,
    VETERINARIO     VARCHAR2(100)   NOT NULL,
    FECHA_HORA      VARCHAR2(30)    NOT NULL,
    ESTADO          VARCHAR2(30)    NOT NULL,
    CONSTRAINT PK_CITAS PRIMARY KEY (ID)
);

INSERT INTO CITAS (ID, NOMBRE_MASCOTA, TIPO_MASCOTA, NOMBRE_DUENO, TELEFONO_DUENO, SERVICIO, VETERINARIO, FECHA_HORA, ESTADO)
VALUES (CITAS_SEQ.NEXTVAL, 'Rocky', 'Perro', 'Maria Lopez', '+56912345678', 'Consulta general', 'Dr. Ramirez', '2026-04-01 10:00', 'Programada');

INSERT INTO CITAS (ID, NOMBRE_MASCOTA, TIPO_MASCOTA, NOMBRE_DUENO, TELEFONO_DUENO, SERVICIO, VETERINARIO, FECHA_HORA, ESTADO)
VALUES (CITAS_SEQ.NEXTVAL, 'Michi', 'Gato', 'Carlos Diaz', '+56923456789', 'Vacunacion', 'Dra. Soto', '2026-04-01 11:30', 'Programada');

INSERT INTO CITAS (ID, NOMBRE_MASCOTA, TIPO_MASCOTA, NOMBRE_DUENO, TELEFONO_DUENO, SERVICIO, VETERINARIO, FECHA_HORA, ESTADO)
VALUES (CITAS_SEQ.NEXTVAL, 'Luna', 'Perro', 'Ana Torres', '+56934567890', 'Cirugia menor', 'Dr. Ramirez', '2026-03-28 09:00', 'Completada');

INSERT INTO CITAS (ID, NOMBRE_MASCOTA, TIPO_MASCOTA, NOMBRE_DUENO, TELEFONO_DUENO, SERVICIO, VETERINARIO, FECHA_HORA, ESTADO)
VALUES (CITAS_SEQ.NEXTVAL, 'Simba', 'Gato', 'Pedro Sanchez', '+56945678901', 'Desparasitacion', 'Dra. Munoz', '2026-04-02 15:00', 'Programada');

INSERT INTO CITAS (ID, NOMBRE_MASCOTA, TIPO_MASCOTA, NOMBRE_DUENO, TELEFONO_DUENO, SERVICIO, VETERINARIO, FECHA_HORA, ESTADO)
VALUES (CITAS_SEQ.NEXTVAL, 'Thor', 'Perro', 'Sofia Herrera', '+56978901234', 'Vacunacion', 'Dra. Munoz', '2026-03-30 16:00', 'Cancelada');

COMMIT;

-- ================================================================
-- VERIFICACION: ejecutar esto para confirmar que todo quedó bien
-- ================================================================
-- SELECT COUNT(*) AS TOTAL_PEDIDOS FROM PEDIDOS;
-- SELECT COUNT(*) AS TOTAL_CITAS   FROM CITAS;
-- SELECT * FROM PEDIDOS;
-- SELECT * FROM CITAS;
