CREATE TABLE `sys_permission`
(
    `id`   int NOT NULL,
    `name` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL,
    `code` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `sys_role`
(
    `id`   int NOT NULL,
    `name` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL,
    `code` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `sys_role_permission`
(
    `id`            int NOT NULL,
    `role_id`       int NOT NULL,
    `permission_id` int NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `sys_user`
(
    `id`       int NOT NULL,
    `username` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL,
    `password` varchar(32) COLLATE utf8mb4_general_ci DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `sys_user_role`
(
    `id`      int NOT NULL,
    `user_id` int NOT NULL,
    `role_id` int NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `study`.`sys_permission`(`id`, `name`, `code`) VALUES (1, '获取信息', 'user:info');

INSERT INTO `study`.`sys_role`(`id`, `name`, `code`) VALUES (1, '管理员', 'admin');

INSERT INTO `study`.`sys_role_permission`(`id`, `role_id`, `permission_id`) VALUES (1, 1, 1);

INSERT INTO `study`.`sys_user`(`id`, `username`, `password`) VALUES (1, 'aaa', '123');

INSERT INTO `study`.`sys_user_role`(`id`, `user_id`, `role_id`) VALUES (1, 1, 1);