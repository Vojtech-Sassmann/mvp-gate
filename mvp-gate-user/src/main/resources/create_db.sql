/*sudo -i -u postgres*/
CREATE DATABASE mvp_user;
/*For now we use mvp user from the mvp_party*/
/*CREATE USER mvp;*/
/*ALTER USER mvp WITH ENCRYPTED PASSWORD 'mvp';*/
ALTER DATABASE mvp_user OWNER TO mvp;
GRANT ALL PRIVILEGES ON DATABASE mvp_user TO mvp;
/*exit*/
