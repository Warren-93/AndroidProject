<?php

		require 'conn.php';

		$first_name = (filter_input(INPUT_POST, 'first_name', FILTER_SANITIZE_STRING));

        $surname = (filter_input(INPUT_POST, 'surname', FILTER_SANITIZE_STRING));

        $email = (filter_input(INPUT_POST, 'email', FILTER_SANITIZE_STRING));

        $username = (filter_input(INPUT_POST, 'username', FILTER_SANITIZE_STRING));

        $pwd = (filter_input(INPUT_POST, 'pwd', FILTER_SANITIZE_STRING));
		
		$confpwd = (filter_input(INPUT_POST, 'confpwd', FILTER_SANITIZE_STRING));
			
    $Error = false;
    $nameError;
    $emailError;
    $usernameError;
    $pwdError;
    $confpwdError;

    if (!preg_match("/^[a-zA-Z ]*$/",$first_name) || !preg_match("/^[a-zA-Z ]*$/",$surname)) // First & Surname must be Letters
    {
      $Error = true;
      $nameError = "Your name can only contain letters";
    }

    if (!filter_var($email, FILTER_VALIDATE_EMAIL)) // Email Must be Valid
    {
      $Error = true;
      $emailError = "Invalid email format";
    }

    if(!preg_match("/^[a-zA-Z0-9]*$/", $username))//Username Must be letters & Numbers
    {
      $Error = true;
      $usernameError = "Username Must Contain only letters and numbers";
    }

    if(!empty($pwd) && $pwd == $confpwd) // pwd & confpwd Must Match
    {
      if(strlen($pwd) <= '8')// Passowrd must be Atleast 8 characters
      {
        $Error = true;
        $pwdError = "pwd Must be Atleast 8 characters Long";
      }
      elseif(!preg_match("#[0-9]+#",$pwd)) // pwd must contain a number
      {
        $Error = true;
        $pwdError = "Your pwd Must Contain At Least 1 Number!";
      }
      elseif(!preg_match("#[A-Z]+#",$pwd)) // pwd Must contain an Uppercase letter
      {
        $Error = true;
        $pwdError = "Your pwd Must Contain At Least 1 Capital Letter!";
      }
      elseif(!preg_match("#[a-z]+#",$pwd))// pwd Must Conatain a Lowercase letter
      {
        $Error = true;
        $pwdError = "Your pwd Must Contain At Least 1 Lowercase Letter!";
      }
      else// No pwd errors have Occured
      {
        $pwdError = "pwd Is Acceptable";
      }
    }
  
  if(!empty($pwd) && $pwd != $confpwd) // pwd and confpwd do NOT Match
  {
    $Error = true;
    $confpwdError = "Please Check You've confpwdirmed Your pwd!";
  }
  if(empty($pwd)) // pwd Is Empty
  {
    $Error = true;
    $pwdError = "Please enter a pwd";
  }

  if($Error == true) // An Error Has Occured
  {
    echo"'$nameError' </br> '$emailError' </br> '$usernameError' </br> '$pwdError' </br> '$confpwdError'";
  }
  else // Continue with the Registration
  {
    // Hash the pwd
    $pwd = pwd_hash($pwd, pwd_DEFAULT);
    $confpwd ="";
		

			//SQL Query for inserting new user registration details
            $query = "INSERT INTO SQC_Users(First_Name, Surname, Email, Username, pwd)
                    VALUES (:First_Name, :Surname, :Email, :Username, :pwd)";

                //  Prepare the statement
                $stmt = $pdo->prepare($query);
			
                //  Bind values and execute
                $success = $stmt->execute([
				'First_Name' => $first_name,
				'Surname' => $surname,
				'Email' => $email,
				'Username' => $username,
				'pwd' => $pwdhash
				]);
				
            //Check for successful execution
            $count = $stmt->rowCount();
            if($success)
               {
                    echo "Registration successful";
               }
           else
                {
                    echo $stmt->errorInfo()[5];
                }		
  }		
	   