DROP DATABASE IF EXISTS schedule_db;
CREATE DATABASE schedule DEFAULT CHARSET = utf8 COLLATE =utf8_bin;

USE schedule;
DROP TABLE IF EXISTS user;
CREATE TABLE `user` (
                        `id` int NOT NULL AUTO_INCREMENT,
                        `email` varchar(255) DEFAULT NULL,
                        `name` varchar(45) DEFAULT NULL,
                        `created_At` date DEFAULT NULL,
                        `modified_At` date DEFAULT NULL,
                        PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

DROP TABLE IF EXISTS schedule;
CREATE TABLE `schedule` (
                            `id` int NOT NULL AUTO_INCREMENT,
                            `password` varchar(255) DEFAULT NULL,
                            `user_id` int DEFAULT NULL,
                            `detail` varchar(255) DEFAULT NULL,
                            `created_At` date DEFAULT NULL,
                            `modified_At` date DEFAULT NULL,
                            PRIMARY KEY (`id`),
                            KEY `userID_idx` (`user_id`),
                            CONSTRAINT `userID` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

DROP TABLE IF EXISTS comment;
CREATE TABLE `comment` (
                           `created_at` datetime(6) DEFAULT NULL,
                           `id` bigint NOT NULL AUTO_INCREMENT,
                           `modified_at` datetime(6) DEFAULT NULL,
                           `schedule_id` bigint DEFAULT NULL,
                           `user_id` bigint DEFAULT NULL,
                           `detail` varchar(255) DEFAULT NULL,
                           PRIMARY KEY (`id`),
                           KEY `scheduleID_idx` (`schedule_id`),
                           KEY `userID_idx` (`user_id`),
                           CONSTRAINT `userID_idx` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
                           CONSTRAINT `scheduleID_idx` FOREIGN KEY (`schedule_id`) REFERENCES `schedule` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci