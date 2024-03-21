<?php
//session_start();
$host="localhost:3306";
$username="root";
$pass="12345678";
$db="liu32";
 
$conn=mysqli_connect($host,$username,$pass,$db);
if(!$conn){
	die("Database connection error");
}
 
?>