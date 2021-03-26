import mysql.connector

mydb = mysql.connector.connect(
  host="localhost",
  user="root",
  password="",
  database="ksu_database"
)

mycursor = mydb.cursor()

mycursor.execute("SELECT std_city_id,std_city_name FROM city_detail")

myresult = mycursor.fetchall()

for x in myresult:
  print(x)
