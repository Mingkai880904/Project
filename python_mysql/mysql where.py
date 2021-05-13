import mysql.connector

mydb = mysql.connector.connect(
  host="localhost",
  user="root",
  password="",
  database="ksu_database"
)

mycursor = mydb.cursor()

sql = "SELECT * FROM student_detail WHERE std_name LIKE '%Mike%'"

mycursor.execute(sql)

myresult = mycursor.fetchall()

for x in myresult:
  print(x)
