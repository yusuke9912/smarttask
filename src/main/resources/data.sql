INSERT INTO persons(name,email,password) VALUES('山田太郎','yamada@aaa.com','himitu');
INSERT INTO persons(name,email,password) VALUES('田中太郎','tanaka@aaa.com','himitu');
INSERT INTO persons(name,email,password) VALUES('鈴木一郎','suzuki@aaa.com','himitu');

INSERT INTO tasks(person_id, title, is_completed, content, important, due_date, created_date ) VALUES(1, '家の掃除をする', false, 'テスト用のタスクです', 0, '2023-05-31', '2023-05-17');
INSERT INTO tasks(person_id, title, is_completed, content, important, due_date, created_date ) VALUES(2, 'ヨガする',  false, 'テスト用のタスクです', 1, '2023-05-25', '2023-05-13');
INSERT INTO tasks(person_id, title, is_completed, content, important, due_date, created_date ) VALUES(3, 'ジョギングする',  false, 'テスト用のタスクです', 2, '2023-05-30', '2023-05-17');
INSERT INTO tasks(person_id, title, is_completed, content, important, due_date, created_date ) VALUES(1, '夕飯を作る',  false, 'テスト用のタスクです', 3, '2023-05-29', '2023-05-14');
INSERT INTO tasks(person_id, title, is_completed, content, important, due_date, created_date ) VALUES(2, 'メールを送る',  false, 'テスト用のタスクです', 4, '2023-05-20', '2023-05-17');
INSERT INTO tasks(person_id, title, is_completed, content, important, due_date, created_date ) VALUES(3, 'プログラミングの勉強をする',  false, 'テスト用のタスクです', 5, '2023-05-29', '2023-05-18');
INSERT INTO tasks(person_id, title, is_completed, content, important, due_date, created_date ) VALUES(1, '車の掃除をする',  false, 'テスト用のタスクです', 0, '2023-05-31', '2023-05-01');
INSERT INTO tasks(person_id, title, is_completed, content, important, due_date, created_date ) VALUES(2, '風呂の掃除をする',  false, 'テスト用のタスクです', 1, '2023-05-18', '2023-05-17');
INSERT INTO tasks(person_id, title, is_completed, content, important, due_date, created_date ) VALUES(3, 'クリーニングに出す',  false, 'テスト用のタスクです', 2, '2023-06-01', '2023-05-18');