CREATE TABLE comment
(
    id int AUTO_INCREMENT PRIMARY KEY,
    parent_id int NOT NULL,
    type int NOT NULL,
    comment_time long NOT NULL,
    like_count long NOT NULL,
    review_id int NOT NULL
);