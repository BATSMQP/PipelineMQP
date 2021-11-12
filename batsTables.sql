drop table NeuralDocument;
drop table FacialDocument;
drop table SpeechDocument;
drop table StressIndicatorsDocument;
drop table LogDataDocument;
drop table NeuralStudy;
drop table FacialStudy;
drop table SpeechStudy;
drop table StressIndicatorsStudy;
drop table LogDataStudy;
drop table StudyDocument;
drop table Neural;
drop table Facial;
drop table Speech;
drop table StressIndicators;
drop table LogData;
drop table Document;
drop table Participant;
drop table StudyGroup;
drop table Study;
drop table AuthUser;

create table `AuthUser`(
`authUserId` varchar(36),
`firstName` varchar(36),
`lastName` varchar(50),
`username` varchar(36),
`password` varchar(36),
`email` varchar(50),
PRIMARY KEY (`authUserId`)
);

create table `Study`(
`studyId` varchar(36),
`institutionsInvolved`varchar(200),
`studyDescription` varchar(300),
`studyName` varchar(150),
`studyContact` varchar(50),
`studyNotes` varchar(500),
`visibility` varchar(10),
`isIrbApproved` varchar(10),
`studyStartDate` Timestamp,
`studyEndDate` Timestamp,
`authUserId` varchar(36),
PRIMARY KEY (`studyId`),
FOREIGN KEY (`authUserId`) REFERENCES AuthUser(`authUserId`) on delete cascade
);

create table `StudyGroup`(
`studyGroupId` varchar(36),
`studyGroupName` varchar(36),
`studyGroupDescription` varchar(200),
`studyId` varchar(36),
PRIMARY KEY (`studyGroupId`),
FOREIGN KEY (`studyId`) REFERENCES Study(`studyId`) on delete cascade
);

create table `Participant`(
`participantId` varchar(36),
`participantNumber` int,
`studyGroupId` varchar(36),
PRIMARY KEY (`participantId`),
FOREIGN KEY (`studyGroupId`) REFERENCES StudyGroup(`studyGroupId`) on delete cascade
);

create table `Document`(
`documentId` varchar(36),
`filename` varchar(50),
PRIMARY KEY (`documentId`)
);

create table `Neural`(
`neuralId` varchar(36),
`time` Timestamp,
`timeIncr` varchar(30),
`SR` varchar(100),
`logMarker` varchar(100),
`logQuestion` varchar(200),
`logAnswer` varchar(500),
`participantId` varchar(36),
PRIMARY KEY (`neuralId`),
FOREIGN KEY (`participantId`) REFERENCES Participant(`participantId`) on delete cascade
);

create table `Facial`(
`facialId` varchar(36),
`time` Timestamp,
`timeIncr` varchar(30),
`SR` varchar(100),
`video` varchar(100),
`facialPositionQuantification` double,
`participantId` varchar(36),
PRIMARY KEY (`facialId`),
FOREIGN KEY (`participantId`) REFERENCES Participant(`participantId`) on delete cascade
);

create table `Speech`(
`speechId` varchar(36),
`time` Timestamp,
`timeIncr` varchar(30),
`SR` varchar(100),
`waveforms` varchar(100),
`participantId` varchar(36),
PRIMARY KEY (`speechId`),
FOREIGN KEY (`participantId`) REFERENCES Participant(`participantId`) on delete cascade
);

create table `StressIndicators`(
`siId` varchar(36),
`time` Timestamp,
`timeIncr` varchar(30),
`SR` varchar(100),
`heartbeatWaveform` varchar(100),
`bloodPressure` double,
`participantId` varchar(36),
PRIMARY KEY (`siId`),
FOREIGN KEY (`participantId`) REFERENCES Participant(`participantId`) on delete cascade
);

create table `LogData`(
`logDataId` varchar(36),
`changeOfSeverityOverTime` double,
`question` varchar(200),
`answer` varchar(500),
`startOfSymptoms` Timestamp,
`participantId` varchar(36),
PRIMARY KEY (`logDataId`),
FOREIGN KEY (`participantId`) REFERENCES Participant(`participantId`) on delete cascade
);

create table `StudyDocument`(
`studyId` varchar(36),
`documentId` varchar(36),
PRIMARY KEY (`studyId`, `documentId`)
);

create table `NeuralStudy`(
`neuralId` varchar(36),
`studyId` varchar(36),
PRIMARY KEY (`neuralId`, `studyId`)
);

create table `FacialStudy`(
`facialId` varchar(36),
`studyId` varchar(36),
PRIMARY KEY (`facialId`, `studyId`)
);

create table `SpeechStudy`(
`speechId` varchar(36),
`studyId` varchar(36),
PRIMARY KEY (`speechId`, `studyId`)
);

create table `StressIndicatorsStudy`(
`siId` varchar(36),
`studyId` varchar(36),
PRIMARY KEY (`siId`, `studyId`)
);

create table `LogDataStudy`(
`logDataId` varchar(36),
`studyId` varchar(36),
PRIMARY KEY (`logDataId`, `studyId`)
);

create table `NeuralDocument`(
`neuralId` varchar(36),
`documentId` varchar(36),
PRIMARY KEY (`neuralId`, `documentId`)
);

create table `FacialDocument`(
`facialId` varchar(36),
`documentId` varchar(36),
PRIMARY KEY (`facialId`, `documentId`)
);

create table `SpeechDocument`(
`speechId` varchar(36),
`documentId` varchar(36),
PRIMARY KEY (`speechId`, `documentId`)
);

create table `StressIndicatorsDocument`(
`siId` varchar(36),
`documentId` varchar(36),
PRIMARY KEY (`siId`, `documentId`)
);

create table `LogDataDocument`(
`logDataId` varchar(36),
`documentId` varchar(36),
PRIMARY KEY (`logDataId`, `documentId`)
);