import mysql.connector

mydb = mysql.connector.connect(
  host="localhost",
  user="root",
  password="",
  database="ksu_database"
)

mycursor = mydb.cursor()

sql = "INSERT INTO customer (First_Name,Last_Name,Addr,City,Country,Birth_Date) VALUES (%s,%s,%s,%s,%s,%s)"
val = ('Kobe','Bryant','Ocean blvd 2','Los Angles','USA','2004-08-24')
mycursor.execute(sql, val)

mydb.commit()

print("1 record inserted, ID:", mycursor.lastrowid)
