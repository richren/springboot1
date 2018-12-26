SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_authors
-- ----------------------------
DROP TABLE IF EXISTS `t_authors`;
CREATE TABLE `t_authors`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_authors
-- ----------------------------
INSERT INTO `t_authors` VALUES (1, '李白');
INSERT INTO `t_authors` VALUES (2, '鲁迅');

-- ----------------------------
-- Table structure for t_books
-- ----------------------------
DROP TABLE IF EXISTS `t_books`;
CREATE TABLE `t_books`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `authorid` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_books
-- ----------------------------
INSERT INTO `t_books` VALUES (1, '孔乙己', 2);
INSERT INTO `t_books` VALUES (2, '鲁迅文集', 2);
INSERT INTO `t_books` VALUES (3, '呐喊', 2);
INSERT INTO `t_books` VALUES (4, '彷徨', 2);
INSERT INTO `t_books` VALUES (5, '太白诗集', 1);

SET FOREIGN_KEY_CHECKS = 1;