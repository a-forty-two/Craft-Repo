#SQLite Setup
import sqlite3
con = sqlite3.connect('web.db')
cur = con.cursor()

#Create the Tables
cur.execute("CREATE TABLE movie_table (movie_id text, movie_title text, movie_year text);")
cur.execute("CREATE TABLE genres_table (movie_id text, genre_name text);")
cur.execute("CREATE TABLE ratings_table (user_id text, movie_id text,rating tinyint,time_stamp text);")
cur.execute("CREATE TABLE user_table (user_id text, gender text, age_id text, occupation_id text, zip_code text);")
cur.execute("CREATE TABLE occupation_table (occupation_id text, occupation_description text);")
cur.execute("CREATE TABLE age_table (age_id text, age_description text);")

#input data into the movie _table
file_movies = open('movies.dat', encoding = 'latin-1')
for line in file_movies:
    line = line.strip()
    line = line.split('::')
    movie_id = line[0]
    movie_title_year = line[1]
    movie_title = movie_title_year[0:-7]
    year = movie_title_year[-5:-1]
    genre = line[2]
    genre = genre.split('|')
    movie_record = [movie_id, movie_title, year]
    con.execute("INSERT INTO movie_table VALUES (?,?,?);", movie_record)

    #input data into the genre table
    for genre_item in genre:
        genre_record = [movie_id, genre_item]
        con.execute("INSERT INTO genres_table VALUES (?,?);", genre_record)
        
#create the ratings table
file_ratings = open('ratings.dat', encoding = 'latin-1')
for line in file_ratings:
    item = line.split('::')
    con.execute("INSERT INTO ratings_table VALUES (?,?,?,?);", (item))

#create the users table
file_users = open('users.dat', encoding = 'latin-1')
for line in file_users:
    item = line.split('::')
    con.execute("INSERT INTO user_table VALUES (?,?,?,?,?);", (item))

#insert into the occupation table
con.execute("INSERT INTO occupation_table VALUES ('0', 'other');")
con.execute("INSERT INTO occupation_table VALUES ('1', 'academic/educator');")
con.execute("INSERT INTO occupation_table VALUES ('2', 'artist');")
con.execute("INSERT INTO occupation_table VALUES ('3', 'clerical/admin');")
con.execute("INSERT INTO occupation_table VALUES ('4', 'college/grad student');")
con.execute("INSERT INTO occupation_table VALUES ('5', 'customer service');")
con.execute("INSERT INTO occupation_table VALUES ('6', 'doctor/health care');")
con.execute("INSERT INTO occupation_table VALUES ('7', 'executive/managerial');")
con.execute("INSERT INTO occupation_table VALUES ('8', 'farmer');")
con.execute("INSERT INTO occupation_table VALUES ('9', 'homemaker');")
con.execute("INSERT INTO occupation_table VALUES ('10', 'K-12 student');")
con.execute("INSERT INTO occupation_table VALUES ('11', 'lawyer');")
con.execute("INSERT INTO occupation_table VALUES ('12', 'programmer');")
con.execute("INSERT INTO occupation_table VALUES ('13', 'retired');")
con.execute("INSERT INTO occupation_table VALUES ('14', 'sales/marketing');")
con.execute("INSERT INTO occupation_table VALUES ('15', 'scientist');")
con.execute("INSERT INTO occupation_table VALUES ('16', 'self-employed');")
con.execute("INSERT INTO occupation_table VALUES ('17', 'technician/engineer');")
con.execute("INSERT INTO occupation_table VALUES ('18', 'tradesman/craftsman');")
con.execute("INSERT INTO occupation_table VALUES ('19', 'unemployed');")
con.execute("INSERT INTO occupation_table VALUES ('20', 'writer');")

#insert into the age table
con.execute("INSERT INTO age_table VALUES ('1', 'Under 18');")
con.execute("INSERT INTO age_table VALUES ('18', '18-24');")
con.execute("INSERT INTO age_table VALUES ('25', '25-34');")
con.execute("INSERT INTO age_table VALUES ('35', '35-44');")
con.execute("INSERT INTO age_table VALUES ('45', '45-49');")
con.execute("INSERT INTO age_table VALUES ('50', '50-55');")
con.execute("INSERT INTO age_table VALUES ('56', '56+');")

con.commit()
print('done')


    
            
    










