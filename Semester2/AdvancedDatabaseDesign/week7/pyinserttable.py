import mysql.connector as sql
mydb = sql.connect(host='localhost',
                   port=int(3306),
                   user='root',
                   passwd='12345678',
                   database='mydatabase'
                   )
mycursor = mydb.cursor()

sql = "INSERT INTO customers (name, address) VALUES (%s, %s)";
val = ("David", "California")
mycursor.execute(sql, val)
mydb.commit()
print(mycursor.rowcount, "record(s) inserted.")
