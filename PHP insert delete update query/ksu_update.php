<?php

 $name=str_replace("'","''",$_REQUEST['name']);//欄位name名稱
 $age=str_replace("'","''",$_REQUEST['age']);//欄位age名稱
 $id=str_replace  ("'","''",$_REQUEST['number']);//欄位id名稱
	print ("here!");
/*------------設定變數-------------------*/
 $db_host = "localhost";//資料庫的位置
 $db_name = "ksu_database";//資料庫名稱
 $db_table = "ksu_std_table";//資料表名稱
 $db_user = "root";//登入資訊帳號
 $db_password = "";//登入資訊密碼
 /*--------------------------------------*/
 $conn = mysqli_connect($db_host, $db_user, $db_password);//連接資料庫

 /*-------------檢查資料庫-----------------*/
 if(empty($conn)){				//能否連接
	print  mysqli_error ($conn);
    die ("無法對資料庫連線！" );
	exit;
 } 
 if(!mysqli_select_db( $conn, $db_name)){//資料庫和資料表是否存在
	die("資料庫不存在!");
	exit;
 }
 mysqli_set_charset($conn,'utf8');//設定資料庫使用utf8
/*----------------------------------------*/

 /*----------寫入資料的程式碼---------------*/
 $Update_sql="UPDATE `$db_table` SET
 	 ksu_std_name='$_POST[name]',
	 ksu_std_age='$_POST[age]'
     WHERE ksu_std_id='$_POST[number]'";
 
 if (!mysqli_query($conn,$Update_sql1,$Update_sql2))//sql語法檢查
  { 
  	die("無法修改資料!");
	exit;
  }

 $count=mysql_affected_rows($conn);
 echo "$count";
 
/*----------------------------------------*/
?>

<form enctype="multipart/form-data"  method="post" action="ksu_main.html">
<input type="submit" name="sub" value="返回"/>
</form>