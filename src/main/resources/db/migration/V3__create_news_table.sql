CREATE TABLE news
(
    id             BINARY(16) PRIMARY KEY,
    journalist_id  BINARY(16)   NOT NULL,
    news_type_id   BINARY(16)   NOT NULL,
    title          VARCHAR(50)  NOT NULL,
    description    VARCHAR(100) NOT NULL,
    news_body      TEXT         NOT NULL,
    featured_image VARCHAR(255),
    FOREIGN KEY (journalist_id) REFERENCES journalists (id),
    FOREIGN KEY (news_type_id) REFERENCES news_types (id)
);