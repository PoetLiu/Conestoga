import mysql.connector as sql
mydb = sql.connect(host='localhost',
                   port=int(3306),
                   user='root',
                   passwd='12345678',
                   database='mydatabase'
                   )
print(mydb)
print('MySQL is connected with mydatabase!')