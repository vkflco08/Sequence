
CREATE DATABASE IF NOT EXISTS db_notice_board;
CREATE USER IF NOT EXISTS 'user2'@'%' IDENTIFIED BY 'pass2';
GRANT ALL PRIVILEGES ON db_notice_board.* TO 'user2'@'%';

FLUSH PRIVILEGES;