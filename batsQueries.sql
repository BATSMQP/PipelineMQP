INSERT INTO AuthUser (authUserId, firstName, lastName, username, pass, email) values("123", "Example", "User", "exampleUser", "password", "exampleUser@gmail.com");

select * from AuthUser;
select * from Document;
select file from Document where name="api once again";
delete from Document where file=NULL;

select * from Study;
SELECT * FROM Study where authUserId="92200336-c7e4-4056-9043-ecad5847dcd2" order by studyStartDate;

SELECT * FROM Study WHERE studyId = '36fedd99-7d08-484a-84ad-c2bcd3545776';

-- INSERT INTO Study (studyId, institutionsInvolved, studyDescription, studyName, studyShortName, studyContact, studyNotes, visibility, isIrbApproved, studyStartDate, studyEndDate, authUserId) values('','abstract','ts','','','',0,'2021-11-21 23:15:46.968',null,'123',** NOT SPECIFIED **,** NOT SPECIFIED **);