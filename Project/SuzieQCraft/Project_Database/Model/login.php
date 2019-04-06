<?php
  global $conn;

  if (isset($_POST["Login"]))
  {
    $username = filter_input(INPUT_POST, 'username', FILTER_SANITIZE_STRING);
    $password = filter_input(INPUT_POST, 'password', FILTER_SANITIZE_STRING);
	
    $sql = "SELECT * FROM SQC_Users WHERE Username = :username";

    $stmt = $conn->prepare($sql);
    $success = $stmt->execute([
	'username' => $username
	]);

    if($success && $stmt->rowCount() > 0)
    {
      $result = $stmt->fetch();

      if ($result && password_verify($password, $result['Password']))
      {
		echo "Login Successful";
      }
      else
      {
          echo "Password is invalid";
      }
    }
    else
    {
      echo" Record not found";
    }
  }