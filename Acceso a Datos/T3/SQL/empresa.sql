CREATE database EMPRESA;
USE EMPRESA;

-- CREACION DE TABLAS --

CREATE TABLE departamentos (
dept_no INT(2) NOT NULL,
dnombre VARCHAR(15),
loc VARCHAR(15),
CONSTRAINT pk_dept_no PRIMARY KEY(dept_no)
) ;

CREATE TABLE empleados (
emp_no INT(4) NOT NULL,
apellido VARCHAR(20),
oficio VARCHAR (50),
dir INT(2),
fecha_alt DATE,
salario FLOAT(6,2),
comision FLOAT(6,2),
dept_no INT(2) NOT NULL,
CONSTRAINT pk_emp_no PRIMARY KEY(emp_no),
CONSTRAINT fk_emp_deptno_depart FOREIGN KEY (dept_no) REFERENCES departamentos(dept_no) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO departamentos VALUES(10,'CONTABILIDAD','SEVILLA');
INSERT INTO departamentos VALUES(20,'INVESTIGACION','MADRID');
INSERT INTO departamentos VALUES(30,'VENTAS', 'BARCELONA');
INSERT INTO departamentos VALUES(40,'PRODUCCION','BILBAO');

INSERT INTO empleados (emp_no,apellido,oficio,fecha_alt,salario,comision, dept_no) VALUES(10,'LOPEZ','PROGRAMADOR',sysdate(),1200,100,10);