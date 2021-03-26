import mysql.connector

mydb = mysql.connector.connect(
  host="localhost",
  user="root",
  passwd="",
  database="ksu_database"
)

mycursor = mydb.cursor()

sql = "UPDATE student_detail SET std_name = 'Food Panny' WHERE std_name = 'John Mike'"

mycursor.execute(sql)

mydb.commit()

print(mycursor.rowcount, "record(s) affected")
