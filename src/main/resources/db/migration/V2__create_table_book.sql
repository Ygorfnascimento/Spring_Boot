CREATE TABLE IF NOT EXISTS book (
                                    id INT AUTO_INCREMENT PRIMARY KEY,
                                    name VARCHAR(255) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    status VARCHAR(50) DEFAULT 'ATIVO',
    customer_id INT NOT NULL,
    CONSTRAINT fk_book_customer FOREIGN KEY (customer_id) REFERENCES customer(id)
    );
