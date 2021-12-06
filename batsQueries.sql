INSERT INTO AuthUser (authUserId, firstName, lastName, username, pass, email) values("123", "Example", "User", "exampleUser", "password", "exampleUser@gmail.com");

select * from AuthUser;
select * from Document;
select * from Study;
SELECT * FROM Study where authUserId="5d5ef618-06ef-4667-ad40-34ad4cf02da5" order by studyStartDate;

SELECT * FROM Study WHERE studyId = '36fedd99-7d08-484a-84ad-c2bcd3545776';

-- INSERT INTO Study (studyId, institutionsInvolved, studyDescription, studyName, studyShortName, studyContact, studyNotes, visibility, isIrbApproved, studyStartDate, studyEndDate, authUserId) values('','abstract','ts','','','',0,'2021-11-21 23:15:46.968',null,'123',** NOT SPECIFIED **,** NOT SPECIFIED **);