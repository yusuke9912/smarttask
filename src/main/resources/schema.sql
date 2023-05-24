-- 各種テーブル削除
DROP TABLE IF EXISTS tasks;
DROP TABLE IF EXISTS persons;
DROP TABLE IF EXISTS tags;

-- パーソンーテーブル
CREATE TABLE persons
(
id SERIAL PRIMARY KEY,
name TEXT,
email TEXT,
password TEXT,
is_admin BOOLEAN
);

-- タグテーブル
CREATE TABLE tags
(
id SERIAL PRIMARY KEY,
name TEXT
);

-- タスクテーブル
CREATE TABLE tasks
(
id SERIAL PRIMARY KEY,
person_id INTEGER,
FOREIGN KEY(person_id) REFERENCES persons (id),
tag_id INTEGER,
FOREIGN KEY(tag_id) REFERENCES tags (id),
title TEXT,
is_completed BOOLEAN,
important INTEGER,
content TEXT,
due_datetime TIMESTAMP,
created_datetime TIMESTAMP
);

