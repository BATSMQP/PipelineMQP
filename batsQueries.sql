INSERT INTO AuthUser (authUserId, firstName, lastName, username, pass, email) values("testAuthUser", "Example", "User", "exampleUser", "password", "exampleUser@gmail.com");
Insert into Document (documentId, file, filename, name, dataType, ext, docType) values("5", "data", "nameData5", "Name5", "Facial", "csv", "Data");
Insert into StudyDocument (studyId, documentId) values("a9c30695-28ce-4481-81e5-b240af28e9fd", "5");
Insert into AuthUserDocument (authUserId, documentId) values("testAuthUser", "5");

select * from AuthUser;
select * from Document;
select * from AuthUserDocument;
select * from StudyDocument;
select * from Study;
select file from Document where name="api once again";
delete from Document where file=NULL;

-- SELECT * FROM Document WHERE studyId =  AND docType="Data"; 

select * from Study;
SELECT * FROM Study where authUserId="92200336-c7e4-4056-9043-ecad5847dcd2" order by studyStartDate;

SELECT * FROM Study WHERE studyId = '36fedd99-7d08-484a-84ad-c2bcd3545776';

-- INSERT INTO Study (studyId, institutionsInvolved, studyDescription, studyName, studyShortName, studyContact, studyNotes, visibility, isIrbApproved, studyStartDate, studyEndDate, authUserId) values('','abstract','ts','','','',0,'2021-11-21 23:15:46.968',null,'123',** NOT SPECIFIED **,** NOT SPECIFIED **);