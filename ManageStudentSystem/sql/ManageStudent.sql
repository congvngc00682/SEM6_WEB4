Create database ManageStudent
go

use ManageStudent
go

create table Faculty
(
	id int primary key identity(1,1) not null,
	name varchar(50) not null,
	 
)
go

create table [Role]
(
	id int primary key identity(1,1) not null,
	[role] varchar(50) not null,
)
go

create table Account
(
	id int primary key identity(1,1) not null,
	username varchar(50) not null,
	[password] varchar(50) not null,
	email varchar(50),
	[role] int Foreign key references [Role](id),
	faculty int Foreign key references Faculty(id)
)
go

drop table [Profile]
create table [Profile]
(
	id int primary key identity(1,1) not null,
	firstname nvarchar(50) not null,
	lastname nvarchar(50) not null,
	middlename nvarchar(50),
	dob varchar(50),
	unique(firstname, lastname, middlename, dob),
	accountId int Foreign key references Account(id)
)

go

drop table ExtenuatingCircumstance

create table ExtenuatingCircumstance
(
	id int primary key identity(1,1) not null,
	title varchar(50),
	[description] varchar(100),
	submitted_date varchar(20),
	process_status varchar(50),
	processed_date varchar(20),
	studentId int Foreign key references Account(id),
	assignedCoordinator int Foreign key references Account(id)
)
go
if exists(select * from Evidence) drop table Evidence
go
create table Evidence
(
	id int primary key identity(1,1) not null,
	files varchar(200) null,
	evidence_date varchar (20),
	EC_id int Foreign key references ExtenuatingCircumstance(id)
)
go


insert into Faculty values('FPT')
insert into Faculty values('Faculty 2')

insert into [Role] values ('Admin')
insert into [Role] values ('EC Manager')
insert into [Role] values ('EC Coordinator')
insert into [Role] values ('Student')

insert into Account values('Admin', '123', 'wsadGroup@gmail.com', 1, 1);
insert into Account values('ECManager', '123', 'manager@gmail.com', 2, 1);
insert into Account values('ECCoordinator', '123', 'coordinator@gmail.com', 3, 1);
insert into Account values('Student', '123', 'Student@fpt.edu.vn', 4, 1);

insert into [profile] values ('E', 'C', null, null, 50);

insert into ExtenuatingCircumstance values('Feedback', 'Class', '10/3/2017', 'submitted', '12/3/2017', 41,50);
insert into ExtenuatingCircumstance values('sdfdsf', 'sdfdsf', '10/3/2017', 'submitted', '12/3/2017', 44,50);

insert into Evidence values('/sdfds/sdf', '15/3/20167', 1);
insert into Evidence values('/1234/1234', '15/3/20167', 1);

