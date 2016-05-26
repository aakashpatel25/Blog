IF NOT EXISTS ( SELECT * FROM sys.database_principals WHERE name = N'app_user' )
BEGIN
	CREATE USER [app_user] FOR LOGIN [app_user]
END
GO
ALTER ROLE [db_datareader] ADD MEMBER [app_user]
GO
ALTER ROLE [db_datawriter] ADD MEMBER [app_user]
GO