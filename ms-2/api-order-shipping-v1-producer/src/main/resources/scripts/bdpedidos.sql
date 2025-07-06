-- Creación de la base de datos
CREATE DATABASE IF NOT EXISTS bd_order_shipping;
USE bd_order_shipping;

-- Tabla Clientes
CREATE TABLE client (
    client_id VARCHAR(10) PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    lastname VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone VARCHAR(20),
    date_create DATETIME DEFAULT CURRENT_TIMESTAMP,
    is_active BOOLEAN DEFAULT TRUE
) ENGINE=InnoDB;

-- Tabla Direcciones
CREATE TABLE address (
    address_id VARCHAR(10) PRIMARY KEY,
    client_id VARCHAR(10) NOT NULL,
    street VARCHAR(100) NOT NULL,
    city VARCHAR(50) NOT NULL,
    state VARCHAR(50) NOT NULL,
    zip_code VARCHAR(20) NOT NULL,
    country VARCHAR(50) NOT NULL DEFAULT 'México',
    is_main BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (client_id) REFERENCES client(client_id) ON DELETE CASCADE
) ENGINE=InnoDB;


-- Tabla Pedidos
CREATE TABLE purchase_order (
    purchase_order_id VARCHAR(10) PRIMARY KEY,
    client_id VARCHAR(10) NOT NULL,
    address_shipper_id VARCHAR(10) NOT NULL,
    date_purchase_order DATETIME DEFAULT CURRENT_TIMESTAMP,
    subtotal DECIMAL(10,2) NOT NULL,
    iva DECIMAL(10,2) NOT NULL,
    amounttotal DECIMAL(10,2) NOT NULL,
    status_order ENUM('pendiente', 'procesando', 'enviado', 'completado', 'cancelado') DEFAULT 'pendiente',
    payment_method ENUM('tarjeta', 'transferencia', 'efectivo', 'paypal'),
    FOREIGN KEY (client_id) REFERENCES client(client_id),
    FOREIGN KEY (address_shipper_id) REFERENCES address(address_id)
) ENGINE=InnoDB;


-- Tabla EstadosEnvio
CREATE TABLE status_shipper (
    status_id VARCHAR(10) PRIMARY KEY,
    status_name VARCHAR(50) NOT NULL,
    status_description VARCHAR(255)
) ENGINE=InnoDB;

-- Tabla Envios
CREATE TABLE shipper (
    shipper_id VARCHAR(10) PRIMARY KEY,
    purchase_order_id VARCHAR(10) NOT NULL,
    date_shipper DATETIME,
    date_estimated_delivery DATETIME,
    carrier VARCHAR(50) NOT NULL,
    tracking_number VARCHAR(50),
    amount_shipper DECIMAL(10,2) NOT NULL,
    status_id VARCHAR(10) NOT NULL,
    note_shipper TEXT,
    FOREIGN KEY (purchase_order_id) REFERENCES purchase_order(purchase_order_id),
    FOREIGN KEY (status_id) REFERENCES status_shipper(status_id)
) ENGINE=InnoDB;


-- Índices adicionales para mejorar el rendimiento
CREATE INDEX idx_purchase_order_client ON purchase_order(client_id);
CREATE INDEX idx_shipper_purchase_order ON shipper(purchase_order_id);