<?php

$host = "lochnagar.abertay.ac.uk";
$dbname = "sql1605460";
$un = "sql1605460";
$pw = "Laa26L6uWJrz";

try {
	// Open the connection using PDO.
	$pdo = new PDO ("mysql:host=$host;dbname=$dbname;charset=UTF8",$un,$pw);
	// set the PDO error mode to exception
	$pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
	} catch (PDOException $e) {
	// echo $sql . "<br>" . $e->getMessage();
	die();
}

