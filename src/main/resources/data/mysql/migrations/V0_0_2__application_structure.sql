--
-- Table structure for table `sub_reddit`
--

CREATE TABLE `sub_reddit` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `created_by` varchar(255) NOT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `last_modified_by` varchar(255) NOT NULL,
  `description` varchar(50) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKg732smp3815h4eqvg779xpees` (`user_id`),
  CONSTRAINT `FKg732smp3815h4eqvg779xpees` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `post`
--

CREATE TABLE `post` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `description` longtext,
  `post_name` varchar(50) DEFAULT NULL,
  `url` varchar(800) DEFAULT NULL,
  `vote_count` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK7a2253t8jlohhgpakfmq4g6g1` FOREIGN KEY (`id`) REFERENCES `sub_reddit` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `comment`
--

 CREATE TABLE `comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `text` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FKy8t71okd2skkhqb4mm4do99` FOREIGN KEY (`id`) REFERENCES `post` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `sub_reddit_posts`
--

CREATE TABLE `sub_reddit_posts` (
  `Subreddit_id` bigint(20) NOT NULL,
  `posts_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_fsnikafb3x3htvvmmlrblln9j` (`posts_id`),
  KEY `FKkyidr12c9miufjb0y1s0nnxbh` (`Subreddit_id`),
  CONSTRAINT `FKkyidr12c9miufjb0y1s0nnxbh` FOREIGN KEY (`Subreddit_id`) REFERENCES `sub_reddit` (`id`),
  CONSTRAINT `FKoj8c2b273mmg7er1h8wydbbu8` FOREIGN KEY (`posts_id`) REFERENCES `post` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `vote`
--

CREATE TABLE `vote` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `last_modified_date` datetime DEFAULT NULL,
  `vote_type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FKaa8qetak9jeuh1ub7ielwimu7` FOREIGN KEY (`id`) REFERENCES `post` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Table structure for table `application_settings`
--

CREATE TABLE `application_settings` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) NOT NULL,
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modified_by` varchar(255) NOT NULL,
  `last_modified_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `appKey` varchar(255) NOT NULL,
  `appValue` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;