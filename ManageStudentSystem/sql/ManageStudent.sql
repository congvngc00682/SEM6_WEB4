--drop database ManageStudentEC
Create database ManageStudentEC
go

use ManageStudentEC
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

--drop table [Profile]
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
create table AcademicYear
(
	id int primary key,
	year_name varchar(50) not null,
	startDate datetime not null,
	endDate datetime not null
)

go
--drop table ExtenuatingCircumstance

create table ExtenuatingCircumstance
(
	id int primary key identity(1,1) not null,
	title varchar(50) not null,
	[description] varchar(100),
	submitted_date datetime not null,
	process_status varchar(50),
	processed_date datetime,
	isActive bit default 1,
	academicYear int foreign key references AcademicYear(id),
	submittedBy int Foreign key references Account(id)
)
go
--drop table Evidence
go
create table Evidence
(
	id int primary key identity(1,1) not null,
	filepath varchar(200) null,
	uploaded_date datetime,
	EC_id int Foreign key references ExtenuatingCircumstance(id)
)
go

create table AssignedCoordinator
(
	id int primary key identity (1,1),
	ec_id int Foreign key references ExtenuatingCircumstance(id),
	coordinator int Foreign key references Account(id)
)

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
insert into Account values('ECCoordinator2', '123', 'coordinator2@gmail.com', 3, 2);

insert into [profile] values ('Admin', 'Admin', null, null, 1);
insert into [profile] values ('EC', 'Manager', null, null, 2);
insert into [profile] values ('EC', 'Coordinator', null, null, 3);
insert into [profile] values ('Student', '1', null, null, 4);
insert into [profile] values ('EC2', 'Coordinator2', null, null, 5);

insert into AcademicYear values (1, '2015/2016', '2015/07/01', '2016/06/30');
insert into AcademicYear values (2, '2016/2017', '2016/07/01', '2017/06/30');
