CREATE DATABASE  IF NOT EXISTS `facebook` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `facebook`;
-- MySQL dump 10.13  Distrib 5.5.35, for debian-linux-gnu (i686)
--
-- Host: localhost    Database: facebook
-- ------------------------------------------------------
-- Server version	5.5.35-0ubuntu0.12.04.2

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Comment`
--

DROP TABLE IF EXISTS `Comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Comment` (
  `commentID` int(11) NOT NULL AUTO_INCREMENT,
  `profileId` int(11) DEFAULT NULL,
  `description` varchar(300) DEFAULT NULL,
  `postId` int(11) DEFAULT NULL,
  `commentTime` datetime DEFAULT NULL,
  PRIMARY KEY (`commentID`),
  KEY `fk_Comment_1` (`profileId`),
  KEY `fk_Comment_2` (`postId`),
  CONSTRAINT `fk_Comment_1` FOREIGN KEY (`profileId`) REFERENCES `Profile` (`profileId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Comment_2` FOREIGN KEY (`postId`) REFERENCES `Post` (`postId`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=115 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `RelationshipStatus`
--

DROP TABLE IF EXISTS `RelationshipStatus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `RelationshipStatus` (
  `relationshipStatusId` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`relationshipStatusId`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `RSVPStatus`
--

DROP TABLE IF EXISTS `RSVPStatus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `RSVPStatus` (
  `rsvpStatusId` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`rsvpStatusId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Messages`
--

DROP TABLE IF EXISTS `Messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Messages` (
  `messageId` int(11) NOT NULL AUTO_INCREMENT,
  `sender` int(11) DEFAULT NULL,
  `fromValidity` tinyint(1) DEFAULT NULL,
  `receiver` int(11) DEFAULT NULL,
  `toValidity` tinyint(1) DEFAULT NULL,
  `text` varchar(400) DEFAULT NULL,
  `sentAt` datetime DEFAULT NULL,
  `seenAt` datetime DEFAULT NULL,
  PRIMARY KEY (`messageId`),
  KEY `fk_Messages_1` (`receiver`),
  KEY `fk_Messages_2` (`sender`),
  CONSTRAINT `fk_Messages_1` FOREIGN KEY (`receiver`) REFERENCES `Profile` (`profileId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Messages_2` FOREIGN KEY (`sender`) REFERENCES `Profile` (`profileId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `School`
--

DROP TABLE IF EXISTS `School`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `School` (
  `schoolId` int(11) NOT NULL AUTO_INCREMENT,
  `schoolName` varchar(200) NOT NULL,
  `graduationYear` varchar(45) DEFAULT NULL,
  `concentration` varchar(45) DEFAULT NULL,
  `profileId` int(11) DEFAULT NULL,
  PRIMARY KEY (`schoolId`),
  KEY `fk_School_1` (`profileId`),
  CONSTRAINT `fk_School_1` FOREIGN KEY (`profileId`) REFERENCES `Profile` (`profileId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `UserStatus`
--

DROP TABLE IF EXISTS `UserStatus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `UserStatus` (
  `userStatusId` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(500) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `profileId` int(11) DEFAULT NULL,
  PRIMARY KEY (`userStatusId`),
  KEY `fk_UserStatus_1` (`profileId`),
  CONSTRAINT `fk_UserStatus_1` FOREIGN KEY (`profileId`) REFERENCES `Profile` (`profileId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Interest`
--

DROP TABLE IF EXISTS `Interest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Interest` (
  `interestId` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`interestId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Login`
--

DROP TABLE IF EXISTS `Login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Login` (
  `loginId` int(11) NOT NULL AUTO_INCREMENT,
  `emailId` varchar(45) DEFAULT NULL,
  `userName` varchar(45) DEFAULT NULL,
  `phoneNumber` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`loginId`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `PhotoAlbum`
--

DROP TABLE IF EXISTS `PhotoAlbum`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PhotoAlbum` (
  `photoAlbumId` int(11) NOT NULL AUTO_INCREMENT,
  `createdOn` datetime DEFAULT NULL,
  `coverPhotoId` int(11) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `location` varchar(45) DEFAULT NULL,
  `modifiedOn` datetime DEFAULT NULL,
  `size` int(11) DEFAULT NULL,
  `owner` int(11) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`photoAlbumId`),
  KEY `fk_PhotoAlbum_1` (`owner`),
  KEY `fk_PhotoAlbum_2` (`coverPhotoId`),
  CONSTRAINT `fk_PhotoAlbum_1` FOREIGN KEY (`owner`) REFERENCES `Profile` (`profileId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_PhotoAlbum_2` FOREIGN KEY (`coverPhotoId`) REFERENCES `Photo` (`photoId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Friendship`
--

DROP TABLE IF EXISTS `Friendship`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Friendship` (
  `friendshipId` int(11) NOT NULL AUTO_INCREMENT,
  `areFriends` varchar(1) DEFAULT 'N',
  `userId1` int(11) DEFAULT NULL,
  `userId2` int(11) DEFAULT NULL,
  PRIMARY KEY (`friendshipId`),
  KEY `fk_Friendship_1` (`userId1`),
  KEY `fk_Friendship_2` (`userId2`),
  CONSTRAINT `fk_Friendship_1` FOREIGN KEY (`userId1`) REFERENCES `Profile` (`profileId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Friendship_2` FOREIGN KEY (`userId2`) REFERENCES `Profile` (`profileId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Events`
--

DROP TABLE IF EXISTS `Events`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Events` (
  `eventId` int(11) NOT NULL AUTO_INCREMENT,
  `eventName` varchar(45) DEFAULT NULL,
  `profileId` int(11) DEFAULT NULL,
  `description` varchar(300) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `startTime` datetime DEFAULT NULL,
  `endTime` datetime DEFAULT NULL,
  `host` varchar(45) DEFAULT NULL,
  `location` varchar(45) DEFAULT NULL,
  `tagLine` varchar(45) DEFAULT NULL,
  `eventPicId` int(11) DEFAULT NULL,
  PRIMARY KEY (`eventId`),
  KEY `fk_Events_1` (`profileId`),
  KEY `fk_Events_2` (`eventPicId`),
  CONSTRAINT `fk_Events_1` FOREIGN KEY (`profileId`) REFERENCES `Profile` (`profileId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ResetPassword`
--

DROP TABLE IF EXISTS `ResetPassword`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ResetPassword` (
  `resetPasswordId` int(11) NOT NULL AUTO_INCREMENT,
  `loginId` int(11) NOT NULL,
  `password` varchar(45) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`resetPasswordId`),
  KEY `fk_ResetPassword_1` (`loginId`),
  CONSTRAINT `fk_ResetPassword_1` FOREIGN KEY (`loginId`) REFERENCES `Login` (`loginId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Profile`
--

DROP TABLE IF EXISTS `Profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Profile` (
  `profileId` int(11) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(45) NOT NULL,
  `lastName` varchar(45) DEFAULT NULL,
  `interests` varchar(100) DEFAULT NULL,
  `birthDate` date DEFAULT NULL,
  `aboutMe` varchar(45) DEFAULT NULL,
  `profilePicAlbumId` int(11) DEFAULT NULL,
  `religion` varchar(45) DEFAULT NULL,
  `coverPicAlbumId` int(11) DEFAULT NULL,
  `schoolCount` int(11) DEFAULT NULL,
  `gender` varchar(45) DEFAULT NULL,
  `updateTime` varchar(45) DEFAULT NULL,
  `homeTown` varchar(45) DEFAULT NULL,
  `currentCity` varchar(45) DEFAULT NULL,
  `loginId` int(11) NOT NULL,
  `workPlaceCount` int(11) DEFAULT NULL,
  `profilePicId` int(11) DEFAULT NULL,
  `coverPicId` int(11) DEFAULT NULL,
  `relationshipWith` varchar(500) DEFAULT NULL,
  `relationshipChangeDate` date DEFAULT NULL,
  `relationship` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`profileId`),
  KEY `fk_Profile_1` (`profilePicAlbumId`),
  KEY `fk_Profile_2` (`coverPicAlbumId`),
  KEY `fk_Profile_3` (`loginId`),
  KEY `fk_Profile_5` (`profilePicId`),
  KEY `fk_Profile_6` (`coverPicId`),
  CONSTRAINT `fk_Profile_3` FOREIGN KEY (`loginId`) REFERENCES `Login` (`loginId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Profile_1` FOREIGN KEY (`profilePicAlbumId`) REFERENCES `PhotoAlbum` (`photoAlbumId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Profile_2` FOREIGN KEY (`coverPicAlbumId`) REFERENCES `PhotoAlbum` (`photoAlbumId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `EventMembership`
--

DROP TABLE IF EXISTS `EventMembership`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `EventMembership` (
  `eventMembershipId` int(11) NOT NULL AUTO_INCREMENT,
  `profileId` int(11) DEFAULT NULL,
  `rsvpStatusId` int(11) DEFAULT NULL,
  `eventId` int(11) DEFAULT NULL,
  PRIMARY KEY (`eventMembershipId`),
  KEY `fk_EventMembership_1` (`profileId`),
  KEY `fk_EventMembership_2` (`eventId`),
  KEY `fk_EventMembership_3` (`rsvpStatusId`),
  CONSTRAINT `fk_EventMembership_1` FOREIGN KEY (`profileId`) REFERENCES `Profile` (`profileId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_EventMembership_2` FOREIGN KEY (`eventId`) REFERENCES `Events` (`eventId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_EventMembership_3` FOREIGN KEY (`rsvpStatusId`) REFERENCES `RSVPStatus` (`rsvpStatusId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `PostOnWall`
--

DROP TABLE IF EXISTS `PostOnWall`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PostOnWall` (
  `postOnWallId` int(11) NOT NULL AUTO_INCREMENT,
  `time` datetime NOT NULL,
  `description` varchar(400) NOT NULL,
  `fromId` int(11) NOT NULL,
  `toId` int(11) NOT NULL,
  PRIMARY KEY (`postOnWallId`),
  KEY `fk_from_1` (`fromId`),
  KEY `fk_to_2` (`toId`),
  CONSTRAINT `fk_from_1` FOREIGN KEY (`fromId`) REFERENCES `Profile` (`profileId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_to_2` FOREIGN KEY (`toId`) REFERENCES `Profile` (`profileId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Conversations`
--

DROP TABLE IF EXISTS `Conversations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Conversations` (
  `conversationId` int(11) NOT NULL AUTO_INCREMENT,
  `user1Id` int(11) NOT NULL,
  `user2Id` int(11) NOT NULL,
  `lastMessageId` int(11) NOT NULL,
  PRIMARY KEY (`conversationId`),
  KEY `user1Id_idx` (`user1Id`),
  KEY `user2Id_idx` (`user2Id`),
  KEY `lastMessageId_idx` (`lastMessageId`),
  CONSTRAINT `lastMessageId` FOREIGN KEY (`lastMessageId`) REFERENCES `Messages` (`messageId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user1Id` FOREIGN KEY (`user1Id`) REFERENCES `Profile` (`profileId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `user2Id` FOREIGN KEY (`user2Id`) REFERENCES `Profile` (`profileId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ForgotPasswordCode`
--

DROP TABLE IF EXISTS `ForgotPasswordCode`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ForgotPasswordCode` (
  `forgotPassowrdCodeId` int(11) NOT NULL AUTO_INCREMENT,
  `loginId` int(11) DEFAULT NULL,
  `code` int(11) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`forgotPassowrdCodeId`),
  KEY `fk_ForgotPasswordCode_1` (`loginId`),
  CONSTRAINT `fk_ForgotPasswordCode_1` FOREIGN KEY (`loginId`) REFERENCES `Login` (`loginId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Birthday`
--

DROP TABLE IF EXISTS `Birthday`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Birthday` (
  `birthdayId` int(11) NOT NULL AUTO_INCREMENT,
  `time` datetime NOT NULL,
  `message` varchar(45) NOT NULL,
  `fromId` int(11) NOT NULL,
  `toId` int(11) NOT NULL,
  PRIMARY KEY (`birthdayId`),
  KEY `fk_Birthday_1` (`fromId`),
  KEY `fk_Birthday_2` (`toId`),
  CONSTRAINT `fk_Birthday_1` FOREIGN KEY (`fromId`) REFERENCES `Profile` (`profileId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Birthday_2` FOREIGN KEY (`toId`) REFERENCES `Profile` (`profileId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `WorkPlace`
--

DROP TABLE IF EXISTS `WorkPlace`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `WorkPlace` (
  `workPlaceId` int(11) NOT NULL AUTO_INCREMENT,
  `companyName` varchar(45) DEFAULT NULL,
  `jobProfile` varchar(45) DEFAULT NULL,
  `startDate` varchar(45) DEFAULT NULL,
  `endDate` varchar(45) DEFAULT NULL,
  `profileId` int(11) DEFAULT NULL,
  PRIMARY KEY (`workPlaceId`),
  KEY `fk_WorkPlace_1` (`profileId`),
  CONSTRAINT `fk_WorkPlace_1` FOREIGN KEY (`profileId`) REFERENCES `Profile` (`profileId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Photo`
--

DROP TABLE IF EXISTS `Photo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Photo` (
  `photoId` int(11) NOT NULL AUTO_INCREMENT,
  `albumId` int(11) DEFAULT NULL,
  `caption` varchar(45) DEFAULT NULL,
  `createdOn` varchar(45) DEFAULT NULL,
  `owner` int(11) DEFAULT NULL,
  `location` varchar(45) DEFAULT NULL,
  `photo` longblob NOT NULL,
  PRIMARY KEY (`photoId`),
  KEY `fk_Photo_1` (`albumId`),
  KEY `fk_Photo_2` (`owner`),
  CONSTRAINT `fk_Photo_1` FOREIGN KEY (`albumId`) REFERENCES `PhotoAlbum` (`photoAlbumId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Photo_2` FOREIGN KEY (`owner`) REFERENCES `Profile` (`profileId`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=113 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `placeMovement`
--

DROP TABLE IF EXISTS `placeMovement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `placeMovement` (
  `place_id` int(11) NOT NULL AUTO_INCREMENT,
  `WhereTo` varchar(50) NOT NULL,
  `address` varchar(50) DEFAULT NULL,
  `story` varchar(1000) DEFAULT NULL,
  `date` varchar(20) DEFAULT NULL,
  `fromPlace` varchar(50) DEFAULT NULL,
  `profileId` int(11) DEFAULT NULL,
  PRIMARY KEY (`place_id`),
  KEY `profileId` (`profileId`),
  CONSTRAINT `placeMovement_ibfk_1` FOREIGN KEY (`profileId`) REFERENCES `Profile` (`profileId`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Post`
--

DROP TABLE IF EXISTS `Post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Post` (
  `postId` int(11) NOT NULL AUTO_INCREMENT,
  `owner` int(11) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `statusId` int(11) DEFAULT NULL,
  `photoId` int(11) DEFAULT NULL,
  `postOnWallId` int(11) DEFAULT NULL,
  PRIMARY KEY (`postId`),
  KEY `fk_Post_2` (`photoId`),
  KEY `fk_Post_3` (`statusId`),
  KEY `fk_Post_4` (`owner`),
  KEY `fk_Post_6` (`postOnWallId`),
  CONSTRAINT `fk_Post_2` FOREIGN KEY (`photoId`) REFERENCES `Photo` (`photoId`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_Post_3` FOREIGN KEY (`statusId`) REFERENCES `UserStatus` (`userStatusId`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_Post_4` FOREIGN KEY (`owner`) REFERENCES `Profile` (`profileId`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_Post_6` FOREIGN KEY (`postOnWallId`) REFERENCES `PostOnWall` (`postOnWallId`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=116 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Poll`
--

DROP TABLE IF EXISTS `Poll`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Poll` (
  `pollId` int(11) NOT NULL AUTO_INCREMENT,
  `question` varchar(45) DEFAULT NULL,
  `owner` int(11) DEFAULT NULL,
  PRIMARY KEY (`pollId`),
  KEY `fk_Poll_1` (`owner`),
  CONSTRAINT `fk_Poll_1` FOREIGN KEY (`owner`) REFERENCES `Profile` (`profileId`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `PollOption`
--

DROP TABLE IF EXISTS `PollOption`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PollOption` (
  `optionId` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(45) NOT NULL,
  `listOfUsersVoted` varchar(45) DEFAULT NULL,
  `countOfUsersVoted` int(11) DEFAULT NULL,
  `pollId` int(11) DEFAULT NULL,
  PRIMARY KEY (`optionId`),
  KEY `fk_PollOption_222` (`pollId`),
  CONSTRAINT `fk_PollOption_222` FOREIGN KEY (`pollId`) REFERENCES `Poll` (`pollId`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Likes`
--

DROP TABLE IF EXISTS `Likes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Likes` (
  `likeId` int(11) NOT NULL AUTO_INCREMENT,
  `profileId` int(11) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `commentId` int(11) DEFAULT NULL,
  `postId` int(11) DEFAULT NULL,
  `likeTime` datetime DEFAULT NULL,
  PRIMARY KEY (`likeId`),
  KEY `fk_Likes_1` (`profileId`),
  KEY `fk_Likes_2` (`postId`),
  KEY `fk_Likes_3` (`commentId`),
  CONSTRAINT `fk_Likes_1` FOREIGN KEY (`profileId`) REFERENCES `Profile` (`profileId`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_Likes_2` FOREIGN KEY (`postId`) REFERENCES `Post` (`postId`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `fk_Likes_3` FOREIGN KEY (`commentId`) REFERENCES `Comment` (`commentID`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=308 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-05-02  1:13:44
