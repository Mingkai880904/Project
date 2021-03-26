<?php
$link = mysqli_connect("localhost","root","","rfid") ;

$link -> set_charset ("UTF8"); 

$result = $link -> query("SELECT *FROM rfid");
while ($row = $result->fetch_assoc())
{
$output[]=$row;
}

print(json_encode ($output,JSON_UNESCAPED_UNICODE)) ;

$link -> close(); 
?>