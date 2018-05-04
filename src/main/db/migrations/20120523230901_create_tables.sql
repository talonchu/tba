#
# Source for table permission
#

DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `employeeName` varchar(32) DEFAULT 'admin' COMMENT 'the emp name.',
  `role` int(1) DEFAULT '1' COMMENT '1:admin',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=494 DEFAULT CHARSET=utf8 COMMENT='the permission';

#
# Dumping data for table permission
#



#
# Source for table relation
#

DROP TABLE IF EXISTS `relation`;
CREATE TABLE `relation` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `employeeName` varchar(32) CHARACTER SET latin1 DEFAULT NULL COMMENT 'the name of participant',
  `requestId` int(11) DEFAULT NULL COMMENT 'the request id',
  PRIMARY KEY (`Id`),
  KEY `relationRequestForignKey` (`requestId`)
) ENGINE=InnoDB AUTO_INCREMENT=7168 DEFAULT CHARSET=utf8 COMMENT='the relation between the request table and employee';

#
# Dumping data for table relation
#


#
# Source for table request
#

DROP TABLE IF EXISTS `request`;
CREATE TABLE `request` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `requesterName` varchar(32) CHARACTER SET latin1 DEFAULT NULL COMMENT 'the requester name',
  `purpose` varchar(256) CHARACTER SET latin1 DEFAULT NULL COMMENT 'the purpose of request',
  `activity` varchar(256) CHARACTER SET latin1 DEFAULT NULL COMMENT 'the details of activity',
  `totalNumber` int(11) DEFAULT NULL COMMENT 'the participants number',
  `teamBuildingTime` date DEFAULT NULL,
  `totalCost` double(10,2) DEFAULT NULL COMMENT 'the total cost',
  `requestStatus` int(1) DEFAULT NULL COMMENT 'the 0 is "waiting the approve",1 is"approved",2 is "disapproved"',
  `rejectReason` varchar(512) CHARACTER SET latin1 DEFAULT NULL,
  `requestTime` datetime DEFAULT NULL COMMENT 'the date of submiting the request',
  `approver` varchar(32) CHARACTER SET latin1 DEFAULT NULL,
  `approvedTime` datetime DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=12344 DEFAULT CHARSET=utf8 COMMENT='the request table.';

#
# Dumping data for table request
#



#
#  Foreign keys for table relation
#

ALTER TABLE `relation`
ADD CONSTRAINT `relationRequestForignKey` FOREIGN KEY (`requestId`) REFERENCES `request` (`Id`);

