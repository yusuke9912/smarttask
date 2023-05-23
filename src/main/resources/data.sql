INSERT INTO persons(name,email,password) VALUES('山田太郎','yamada@aaa.com','himitu');
INSERT INTO persons(name,email,password) VALUES('田中太郎','tanaka@aaa.com','himitu');
INSERT INTO persons(name,email,password) VALUES('鈴木一郎','suzuki@aaa.com','himitu');

INSERT INTO tags(name) VALUES('仕事');
INSERT INTO tags(name) VALUES('家事');
INSERT INTO tags(name) VALUES('友達');
INSERT INTO tags(name) VALUES('趣味');
INSERT INTO tags(name) VALUES('その他');

INSERT INTO tasks(person_id, tag_id, title, is_completed, content, important, due_datetime, created_datetime ) VALUES(1, 2, '家の掃除をする', false, 'テスト用のタスクです', 0, '2023-05-31 00:00:00', '2023-05-19 00:00:00');
INSERT INTO tasks(person_id, tag_id, title, is_completed, content, important, due_datetime, created_datetime ) VALUES(2, 1, 'ヨガする',  false, 'テスト用のタスクです', 1, '2023-05-25 00:00:00', '2023-05-13 00:00:00');
INSERT INTO tasks(person_id, tag_id, title, is_completed, content, important, due_datetime, created_datetime ) VALUES(3, 2, 'ジョギングする',  false, 'テスト用のタスクです', 2, '2023-05-30 00:00:00', '2023-05-17 12:00:00');
INSERT INTO tasks(person_id, tag_id, title, is_completed, content, important, due_datetime, created_datetime ) VALUES(1, 3, '夕飯を作る',  false, 'テスト用のタスクです', 3, '2023-05-29 00:00:00', '2023-05-14 00:00:00');
INSERT INTO tasks(person_id, tag_id, title, is_completed, content, important, due_datetime, created_datetime ) VALUES(2, 1, 'メールを送る',  false, 'テスト用のタスクです', 4, '2023-05-20 00:00:00', '2023-05-17 00:00:00');
INSERT INTO tasks(person_id, tag_id, title, is_completed, content, important, due_datetime, created_datetime ) VALUES(3, 4, 'プログラミングの勉強をする',  false, 'テスト用のタスクです', 5, '2023-05-29 00:00:00', '2023-05-18 01:00:00');
INSERT INTO tasks(person_id, tag_id, title, is_completed, content, important, due_datetime, created_datetime ) VALUES(1, 4, '車の掃除をする',  false, 'テスト用のタスクです', 0, '2023-05-31 00:00:00', '2023-05-01 00:00:00');
INSERT INTO tasks(person_id, tag_id, title, is_completed, content, important, due_datetime, created_datetime ) VALUES(2, 2, '風呂の掃除をする',  false, 'テスト用のタスクです', 1, '2023-05-18 00:00:00', '2023-05-17 16:00:00');
INSERT INTO tasks(person_id, tag_id, title, is_completed, content, important, due_datetime, created_datetime ) VALUES(3, 5, 'クリーニングに出す',  false, 'テスト用のタスクです', 2, '2023-06-01 00:00:00', '2023-05-18 00:00:00');

