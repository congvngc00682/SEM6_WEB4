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

create table Account
(
	id int primary key identity(1,1) not null,
	username varchar(50) not null,
	[password] varchar(50) not null,
	email varchar(50),
	[role] int not null,
	faculty int Foreign key references Faculty(id)
)
go


create table ExtenuatingCircumstance
(
	id int primary key identity(1,1) not null,
	title varchar(50),
	[description] varchar(100),
	submitted_date datetime,
	process_status varchar(50),
	account int Foreign key references Account(id)
)
go

create table Evidence
(
	id int primary key identity(1,1) not null,
	files varchar(200) null,
	evidence_date datetime,
	account int Foreign key references Account(id)
)
go


insert into Faculty values('Information Technology')

insert into Account values('Cong', '123', 'congvngc00682@fpt.edu.vn', 1, 1);

select * from Account