<?php
$link = mysqli_connect("localhost","root","","server") ;

$link -> set_charset ("UTF8"); 

$result = $link -> query("SELECT *FROM app01");
while ($row = $result->fetch_assoc())
{
$output[]=$row;
}

print(json_encode ($output,JSON_UNESCAPED_UNICODE)) ;

$link -> close(); 
?>