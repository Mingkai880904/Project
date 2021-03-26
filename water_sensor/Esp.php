<?php 
ini_set('date.timezone','Asia/Taipei');
DEFINE ('DBServer', '127.0.0.1'); 
DEFINE ('DBName', 'ksu_database');
DEFINE ('DBTable', 'sensor');
DEFINE ('DBUser', 'root'); 
DEFINE ('DBPw', '');  
//date_default_timezone_set("Asia/Taipei")
$conDb = mysqli_connect(DBServer,DBUser,DBPw);
if (!$conDb) {
    die("Can not connect to DB: " . mysqli_error($conDb));
    exit();
}

$selectDb = mysqli_select_db($conDb, DBName);
if (!$selectDb) {
    die("Database selection failed: " . mysqli_error($conDb));
    exit(); 
}

$w = mysqli_real_escape_string($conDb,@$_GET['w']);
$m = mysqli_real_escape_string($conDb,@$_GET['m']);
$datetime = date('Y-m-d H:i:s');


$query = "INSERT INTO sensor (w, m,  datetime) values ('$w', '$m', '$datetime')";

$result = mysqli_query($conDb, $query) or trigger_error("query error " . mysqli_error($conDb)); 

mysqli_close($conDb); 

?>