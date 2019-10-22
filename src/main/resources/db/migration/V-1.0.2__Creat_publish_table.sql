CREATE TABLE publish
(
    id int AUTO_INCREMENT PRIMARY KEY,
    title varchar(100) NOT NULL,
    body TEXT NOT NULL,
    tag varchar(256) NOT NULL,
    uid int NOT NULL,
    love_count int DEFAULT 0,
    answer_count int DEFAULT 0,
    view_count int DEFAULT 0
);