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
delete from Study where studyId="c9ca105a-23c6-4654-9e9a-cbb5e34a1523";
delete from Study where studyId="ce6cf359-1a0f-4f9d-bac5-13158dfc02d6";
delete from Study where studyId="d4d2288c-b2d0-41c2-97be-9d58b687698d";
delete from Study where studyId="e87c2949-740c-448d-9c24-d93b6b6c8207";
delete from Study where studyId="f3f502f0-12ea-45ff-948e-e4f19ab4df0e";

-- SELECT * FROM Document WHERE studyId =  AND docType="Data"; 

select * from Study;
SELECT * FROM Study where authUserId="92200336-c7e4-4056-9043-ecad5847dcd2" order by studyStartDate;

SELECT * FROM Study WHERE studyId = '36fedd99-7d08-484a-84ad-c2bcd3545776';

-- INSERT INTO Study (studyId, institutionsInvolved, studyDescription, studyName, studyShortName, studyContact, studyNotes, visibility, isIrbApproved, studyStartDate, studyEndDate, authUserId) values('','abstract','ts','','','',0,'2021-11-21 23:15:46.968',null,'123',** NOT SPECIFIED **,** NOT SPECIFIED **);