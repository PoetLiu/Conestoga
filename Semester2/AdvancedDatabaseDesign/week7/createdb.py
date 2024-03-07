import mysql.connector as sql
mydb = sql.connect(host='localhost',
                   port=int(3306),
                   user='root',
                   passwd='12345678',
                   )
print(mydb)
print('MySQL is connected!')

mycursor = mydb.cursor()
mycursor.execute(
    "drop database if exists mydatabase; CREATE DATABASE mydatabase")
print('Database created')
