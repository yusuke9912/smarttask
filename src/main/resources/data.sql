INSERT INTO persons(name,email,password,is_admin) VALUES('山田太郎','yamada@aaa.com','himitu', false);
INSERT INTO persons(name,email,password,is_admin) VALUES('田中太郎','tanaka@aaa.com','himitu', false);
INSERT INTO persons(name,email,password,is_admin) VALUES('鈴木一郎','suzuki@aaa.com','himitu', false);
INSERT INTO persons(name,email,password,is_admin) VALUES('管理者アカウント','admin@aaa.com','himitu', true);

INSERT INTO tags(name) VALUES('仕事');
INSERT INTO tags(name) VALUES('家庭');
INSERT INTO tags(name) VALUES('交際');
INSERT INTO tags(name) VALUES('趣味');
INSERT INTO tags(name) VALUES('その他');

INSERT INTO tasks(person_id, tag_id, title, is_completed, content, important, due_datetime, created_datetime ) VALUES(1, 2, '家の掃除をする', false, '家の掃除を頑張る', 3, '2023-05-31 00:00:00', '2023-05-19 00:00:00');
INSERT INTO tasks(person_id, tag_id, title, is_completed, content, important, due_datetime, created_datetime ) VALUES(2, 1, 'ヨガする',  false, '赤坂に行く', 1, '2023-05-25 00:00:00', '2023-05-13 00:00:00');
INSERT INTO tasks(person_id, tag_id, title, is_completed, content, important, due_datetime, created_datetime ) VALUES(3, 2, 'ジョギングする',  false, 'ダイエットのため', 2, '2023-05-30 00:00:00', '2023-05-17 12:00:00');
INSERT INTO tasks(person_id, tag_id, title, is_completed, content, important, due_datetime, created_datetime ) VALUES(1, 3, '夕飯を作る',  false, 'カレーを作る', 3, '2023-05-29 00:00:00', '2023-05-14 00:00:00');
INSERT INTO tasks(person_id, tag_id, title, is_completed, content, important, due_datetime, created_datetime ) VALUES(2, 1, 'メールを送る',  false, '田中さんからのメールに返信する', 4, '2023-05-20 00:00:00', '2023-05-17 00:00:00');
INSERT INTO tasks(person_id, tag_id, title, is_completed, content, important, due_datetime, created_datetime ) VALUES(3, 4, 'プログラミングの勉強をする',  false, 'Swiftの勉強を頑張る', 5, '2023-05-29 00:00:00', '2023-05-18 01:00:00');
INSERT INTO tasks(person_id, tag_id, title, is_completed, content, important, due_datetime, created_datetime ) VALUES(1, 4, '車の掃除をする',  false, '早めに掃除', 4, '2023-05-31 00:00:00', '2023-05-01 00:00:00');
INSERT INTO tasks(person_id, tag_id, title, is_completed, content, important, due_datetime, created_datetime ) VALUES(2, 2, '風呂の掃除をする',  false, '風呂掃除めんどくさい', 1, '2023-05-18 00:00:00', '2023-05-17 16:00:00');
INSERT INTO tasks(person_id, tag_id, title, is_completed, content, important, due_datetime, created_datetime ) VALUES(3, 5, 'クリーニングに出す',  false, '忘れないでね', 2, '2023-06-01 00:00:00', '2023-05-18 00:00:00');
INSERT INTO tasks(person_id, tag_id, title, is_completed, content, important, due_datetime, created_datetime ) VALUES(1, 3, '買い物に行く', false, '食材をまとめて買う', 2, '2023-06-02 00:00:00', '2023-05-21 00:00:00');
INSERT INTO tasks(person_id, tag_id, title, is_completed, content, important, due_datetime, created_datetime ) VALUES(2, 4, '報告書を書く', false, 'プロジェクトの進捗をまとめる', 3, '2023-04-26 00:00:00', '2023-05-15 00:00:00');
INSERT INTO tasks(person_id, tag_id, title, is_completed, content, important, due_datetime, created_datetime ) VALUES(3, 1, '友達とランチする', false, '新しいカフェでランチ', 1, '2023-04-27 00:00:00', '2023-05-16 12:00:00');
INSERT INTO tasks(person_id, tag_id, title, is_completed, content, important, due_datetime, created_datetime ) VALUES(1, 5, '散歩する', false, '公園で散策', 4, '2023-05-30 00:00:00', '2023-05-22 00:00:00');
INSERT INTO tasks(person_id, tag_id, title, is_completed, content, important, due_datetime, created_datetime ) VALUES(2, 3, 'プレゼン資料作成', false, '来週のプレゼンのために資料作成', 5, '2023-05-28 00:00:00', '2023-05-23 00:00:00');
INSERT INTO tasks(person_id, tag_id, title, is_completed, content, important, due_datetime, created_datetime ) VALUES(3, 2, '読書する', false, '新刊を読む', 3, '2023-06-03 00:00:00', '2023-05-24 01:00:00');
INSERT INTO tasks(person_id, tag_id, title, is_completed, content, important, due_datetime, created_datetime ) VALUES(1, 1, 'プランを立てる', false, '来週の旅行のプランを考える', 2, '2023-06-04 00:00:00', '2023-05-22 00:00:00');
INSERT INTO tasks(person_id, tag_id, title, is_completed, content, important, due_datetime, created_datetime ) VALUES(2, 5, '家具を買う', false, '新しいソファを購入', 4, '2023-05-29 00:00:00', '2023-04-26 16:00:00');
INSERT INTO tasks(person_id, tag_id, title, is_completed, content, important, due_datetime, created_datetime ) VALUES(3, 4, 'スポーツ観戦する', false, '試合チケットを予約', 1, '2023-05-31 00:00:00', '2023-04-27 00:00:00');
INSERT INTO tasks(person_id, tag_id, title, is_completed, content, important, due_datetime, created_datetime ) VALUES(1, 2, '家具の配置を変える', false, 'リビングの模様替え', 5, '2023-06-01 00:00:00', '2023-04-26 00:00:00');
INSERT INTO tasks(person_id, tag_id, title, is_completed, content, important, due_datetime, created_datetime ) VALUES(2, 1, '映画を観る', false, '最新作を映画館で鑑賞', 3, '2023-06-02 00:00:00', '2023-05-24 00:00:00');
INSERT INTO tasks(person_id, tag_id, title, is_completed, content, important, due_datetime, created_datetime ) VALUES(3, 3, '友達と遊ぶ', false, 'カフェでおしゃべり', 2, '2023-05-30 00:00:00', '2023-05-02 12:00:00');
INSERT INTO tasks(person_id, tag_id, title, is_completed, content, important, due_datetime, created_datetime ) VALUES(1, 4, 'プロジェクトミーティング', false, '進捗報告と次回の計画立案', 4, '2023-06-03 00:00:00', '2023-05-09 00:00:00');
INSERT INTO tasks(person_id, tag_id, title, is_completed, content, important, due_datetime, created_datetime ) VALUES(2, 2, '家族とディナー', false, 'お気に入りのレストランで食事', 1, '2023-06-04 00:00:00', '2023-04-30 00:00:00');
INSERT INTO tasks(person_id, tag_id, title, is_completed, content, important, due_datetime, created_datetime ) VALUES(3, 5, '趣味の習い事', false, '新しい曲の練習', 2, '2023-06-01 00:00:00', '2023-5-20 00:00:00');
INSERT INTO tasks(person_id, tag_id, title, is_completed, content, important, due_datetime, created_datetime ) VALUES(1, 3, '美容院に行く', false, 'カットとカラーリング', 3, '2023-06-02 00:00:00', '2023-03-21 00:00:00');
INSERT INTO tasks(person_id, tag_id, title, is_completed, content, important, due_datetime, created_datetime ) VALUES(2, 4, 'プレゼントを購入', false, '友人の誕生日プレゼント', 5, '2023-06-03 00:00:00', '2023-05-11 00:00:00');


