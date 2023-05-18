-- 各種テーブル削除
DROP TABLE IF EXISTS tasks;
DROP TABLE IF EXISTS persons;

-- パーソンーテーブル
CREATE TABLE persons
(
id SERIAL PRIMARY KEY,
name TEXT,
email TEXT,
password TEXT
);

-- タスクテーブル
CREATE TABLE tasks
(
id SERIAL PRIMARY KEY,
person_id INTEGER,
FOREIGN KEY(person_id) REFERENCES persons (id),
title TEXT,
is_completed BOOLEAN,
important INTEGER,
content TEXT,
due_date DATE,
created_date DATE
);