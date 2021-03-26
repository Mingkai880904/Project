import mysql.connector

mydb = mysql.connector.connect(
  host="localhost",
  user="root",
  password="",
  database="ksu_database"
)

mycursor = mydb.cursor()

sql = "INSERT INTO city_detail (std_city_id,std_city_name) VALUES (%s, %s)"
val = ("A5", "Changhua")
mycursor.execute(sql, val)

mydb.commit()

print(mycursor.rowcount, "record inserted.")
