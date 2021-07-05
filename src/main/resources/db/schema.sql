/* タスクテーブル */
CREATE TABLE IF NOT EXISTS task (
  id INT PRIMARY KEY,
  user_id VARCHAR(50),
  priority VARCHAR(10),
  title VARCHAR(50),
  comment VARCHAR(200),
  limitday DATE
);