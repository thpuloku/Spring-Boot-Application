
    CREATE TABLE product_category
    (
        id               INT AUTO_INCREMENT PRIMARY KEY,
        name             VARCHAR(1000)  NOT NULL,
        description        VARCHAR(500)  DEFAULT NULL,
    );


    CREATE TABLE product
    (
        id                  INT AUTO_INCREMENT PRIMARY KEY,
        name                VARCHAR(100)  NOT NULL,
        description         VARCHAR(500)  DEFAULT NULL,
        price               DECIMAL(10,2) NOT NULL,
        product_category_id INT  NOT NULL,
        status              CHAR(1),
        launched_date       DATE NOT NULL,
        UNIQUE KEY(name),
        FOREIGN KEY (product_category_id) REFERENCES product_category (id)
    );

    CREATE TABLE product_comment
    (
        id                  INT AUTO_INCREMENT PRIMARY KEY,
        comment             VARCHAR(300)  NOT NULL,
        product_id          INT NOT NULL ,
        created_at          TIMESTAMP  DEFAULT NULL,
        FOREIGN KEY (product_id) REFERENCES product (id)
    );


    INSERT INTO `product_category`(id,name,description) VALUES(1,"sweets","includes butter,sugar");
