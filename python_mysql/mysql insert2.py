import mysql.connector

mydb = mysql.connector.connect(
  host="localhost",
  user="root",
  password="",
  database="ksu_database"
)

mycursor = mydb.cursor()

sql = "INSERT INTO customer (First_Name,Last_Name,Addr,City,Country,Birth_Date) VALUES (%s, %s,%s,%s,%s,%s)"
val = [
  ('John','Mike','Lowstreet 4','Tainan','Taiwan','1999-09-09'),
  ('Wom','Try','Apple st 652','NewYork','USA','1998-12-31'),
  ('Wuber','Eat','Mountain 21','Tokyo','Japan','2000-02-28'),
  ('Food','Panny','Valley 345','Seoul','Korea','1978-11-11'),
  ('Sandy','Fire','Ocean blvd 2','Beijing','China','2007-08-03')
]

mycursor.executemany(sql, val)

mydb.commit()

print(mycursor.rowcount, "was inserted.")
