import mysql.connector as sql
mydb = sql.connect(host='localhost',
                   port=int(3306),
                   user='root',
                   passwd='12345678',
                   database='mydatabase'
                   )
mycursor = mydb.cursor()
mycursor.execute(
    "drop table if exists customers; " +
    "CREATE TABLE customers ( " +
    "   id INT AUTO_INCREMENT PRIMARY KEY, " +
    "   name VARCHAR(255), " +
    "   address VARCHAR(255));"
)
print('Table is created')
