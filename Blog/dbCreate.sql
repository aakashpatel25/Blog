create table Users(
	user_id INT NOT NULL PRIMARY KEY IDENTITY(1,1),
	username varchar(24) NOT NULL UNIQUE,
	password varchar(50) NOT NULL,
	first_name varchar(50) NOT NULL,
	last_name varchar(50) NOT NULL,
	email varchar(255) NOT NULL UNIQUE,
	signupDate Date NOT NULL
);

create table Blog(
	blog_id int NOT NULL PRIMARY KEY IDENTITY(1,1),
	title varchar(255) NOT NULL,
	post nvarchar(max) NOT NULL,
	pubDate date NOT NULL,
	user_id int FOREIGN KEY REFERENCES Users(user_id)
);

create table Comments(
	comment_id INT NOT NULL PRIMARY KEY IDENTITY(1,1),
	time DATE NOT NULL,
	comment nvarchar(2000) NOT NULL,
	blog_id int FOREIGN KEY REFERENCES Blog(blog_id),
	user_id int FOREIGN KEY REFERENCES Users(user_id)
);

create table Likes(
	like_id INT NOT NULL PRIMARY KEY IDENTITY(1,1),
	time DATE NOT NULL,
	blog_id int FOREIGN KEY REFERENCES Blog(blog_id),
	user_id int FOREIGN KEY REFERENCES Users(user_id)
);