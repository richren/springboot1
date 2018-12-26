CREATE TABLE `t_authorities` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `Name` tinytext NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

INSERT INTO `t_authorities` VALUES ('1', 'Person.List');
INSERT INTO `t_authorities` VALUES ('2', 'Person.AddNew');
INSERT INTO `t_authorities` VALUES ('3', 'Person.listByAge');
INSERT INTO `t_authorities` VALUES ('4', 'Person.test');


CREATE TABLE `t_roles` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `Name` tinytext NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

INSERT INTO `t_roles` VALUES ('1', 'Teacher');
INSERT INTO `t_roles` VALUES ('2', 'Student');


CREATE TABLE `t_role_authority` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `RoleId` bigint(20) NOT NULL,
  `AuthorityId` bigint(20) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `RoleId` (`RoleId`),
  KEY `AuthorityId` (`AuthorityId`),
  CONSTRAINT `t_role_authority_ibfk_1` FOREIGN KEY (`RoleId`) REFERENCES `t_roles` (`Id`),
  CONSTRAINT `t_role_authority_ibfk_2` FOREIGN KEY (`AuthorityId`) REFERENCES `t_authorities` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

INSERT INTO `t_role_authority` VALUES ('1', '1', '1');
INSERT INTO `t_role_authority` VALUES ('2', '1', '2');
INSERT INTO `t_role_authority` VALUES ('3', '1', '3');
INSERT INTO `t_role_authority` VALUES ('4', '2', '4');


CREATE TABLE `t_users` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `UserName` tinytext NOT NULL,
  `Password` tinytext NOT NULL,
  `Enabled` bit(1) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

INSERT INTO `t_users` VALUES ('1', 'admin', '123', '');
INSERT INTO `t_users` VALUES ('2', 'yzk', '123', '');


DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `UserId` bigint(20) NOT NULL,
  `RoleId` bigint(20) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `UserId` (`UserId`),
  KEY `RoleId` (`RoleId`),
  CONSTRAINT `t_user_role_ibfk_1` FOREIGN KEY (`UserId`) REFERENCES `t_users` (`Id`),
  CONSTRAINT `t_user_role_ibfk_2` FOREIGN KEY (`RoleId`) REFERENCES `t_roles` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

INSERT INTO `t_user_role` VALUES ('1', '1', '2');
INSERT INTO `t_user_role` VALUES ('2', '2', '1');
