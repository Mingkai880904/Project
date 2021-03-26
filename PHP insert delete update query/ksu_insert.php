<?php

 $name=str_replace("'","''",$_REQUEST['name']);
 $id=str_replace  ("'","''",$_REQUEST['id']);
 $age=str_replace ("'","''",$_REQUEST['age']);
	print ("here!");

 $db_host = "localhost";
 $db_name = "ksu_database";
 $db_table = "ksu_std_table";
 $db_user = "root";
 $db_password = "";
 $conn = mysqli_connect($db_host, $db_user, $db_password);
 
 if(empty($conn)){
	print  mysqli_error ($conn);
    die ("無法對資料庫連線！" );
	exit;
 } 


 if(!mysqli_select_db( $conn, $db_name)){
	die("資料庫不存在!");
	exit;
 }
 mysqli_set_charset($conn,'utf8');	
 
 $insert_sql="INSERT INTO $db_table (ksu_std_id,ksu_std_name,ksu_std_age) values('$id','$name','$age')";
 if (!mysqli_query($conn,$insert_sql))
  {  die("無法新增資料!");
	 exit;
   
  }
echo "1 record added";
?>

<form enctype="multipart/form-data"  method="post" action="ksu_main.html">
<input type="submit" name="sub" value="返回"/>
</form>