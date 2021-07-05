DELETE FROM task;

/* タスクテーブルのデータ */
INSERT INTO task (id, user_id, priority, title, comment, limitday) VALUES (1, 'user', 'HIGH','a', 'これやる', '2020-03-23');
INSERT INTO task (id, user_id, priority, title, comment, limitday) VALUES (2, 'master', 'MIDDLE','b', 'あれやる', '2020-03-24');
INSERT INTO task (id, user_id, priority, title, comment, limitday) VALUES (3, 'tester', 'LOW','c', 'それやる', '2020-03-31');
INSERT INTO task (id, user_id, priority, title, comment, limitday) VALUES (4, 'owner', 'LOW','d', 'どれやる', '2020-03-25');
INSERT INTO task (id, user_id, priority, title, comment, limitday) VALUES (5, 'checker', 'LOW','e', 'もっとやる', '2020-04-20');