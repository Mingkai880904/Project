<?php

 
$name=str_replace("'","''",$_REQUEST['name']);
$id=str_replace  ("'","''",$_REQUEST['id']);

 $db_host = "localhost";
 $db_name = "ksu_database";
  $db_table = "ksu_cstd_table";

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
 
 
    
$result = mysqli_query($conn,"SELECT * FROM ksu_std_table where ksu_std_name = '$name' or  ksu_std_id = '$id'");
    



echo "<table border='1'>
<tr>
<th>ksu_std_name</th>
<th>ksu_std_id</th>
<th>ksu_std_age</th>


</tr>";

while($row = mysqli_fetch_array($result))
  {
  
  echo "<tr>";
  
  echo "<td>" . $row['ksu_std_name'] . "</td>";
    echo "<td>" . $row['ksu_std_id'] . "</td>";
	  echo "<td>" . $row['ksu_std_age'] . "</td>";
  
  echo "</tr>";
  }
echo "</table>";



 
      

 
 
 
 
 
 
 
echo "1 record deleted";
?>

<form enctype="multipart/form-data"  method="post" action="ksu_main.html">
<input type="submit" name="sub" value="返回"/>
</form>