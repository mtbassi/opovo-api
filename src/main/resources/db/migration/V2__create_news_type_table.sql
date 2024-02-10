CREATE TABLE news_types
(
    id            BINARY(16) PRIMARY KEY,
    journalist_id BINARY(16)  NOT NULL,
    type_name     VARCHAR(50) NOT NULL,
    FOREIGN KEY (journalist_id) REFERENCES journalists (id)
);