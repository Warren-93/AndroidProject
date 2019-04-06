<?php
	
		
		require 'conn.php';

		$first_name = (filter_input(INPUT_POST, 'first_name', FILTER_SANITIZE_STRING));

        $surname = (filter_input(INPUT_POST, 'surname', FILTER_SANITIZE_STRING));

        $email = (filter_input(INPUT_POST, 'email', FILTER_SANITIZE_STRING));

        $un = (filter_input(INPUT_POST, 'username', FILTER_SANITIZE_STRING));

        $pwd = (filter_input(INPUT_POST, 'password', FILTER_SANITIZE_STRING));
        //Password Hashing function
		$pwdhash = password_hash($pwd, PASSWORD_DEFAULT);
		
        //SQL Query for inserting new user registration details
            $query = "INSERT INTO SQC_Users(First_Name, Surname, Email, Username, Password)
                    VALUES (:First_Name, :Surname, :Email, :Username, :Password)";

                //  Prepare the statement
                $stmt = $pdo->prepare($query);
			
                //  Bind values and execute
                $success = $stmt->execute([
				'First_Name' => $first_name,
				'Surname' => $surname,
				'Email' => $email,
				'Username' => $un,
				'Password' => $pwdhash
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
	   


/* function UserLogin()
{
  global $conn;

  if (isset($_POST["Login"]))
  {
    $username = filter_input(INPUT_POST, 'username', FILTER_SANITIZE_STRING);
    $password = filter_input(INPUT_POST, 'password', FILTER_SANITIZE_STRING);

    $sql = "SELECT * FROM SQC_Users WHERE Username = $username";

    $stmt = $conn->prepare($sql);
    $success = $stmt->execute(['username' => $username]);

    if($success && $stmt->rowCount() > 0)
    {
      $result = $stmt->fetch();

      if ($result && password_verify($password, $result['Password']))
      {
        $_SESSION['LoggedIn'] = true;
        $_SESSION['userid'] = $result['User_ID'];
        $_SESSION['username'] = $result['Username'];
        $_SESSION['firstname'] = $result['First_Name'];
        $_SESSION['user_type'] = $result['User_Type'];
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
}
 */
// function CreateNewUser()
// {
//   global $conn;
//
//   if (isset($_POST["registerUser"]))
//   {
//     $firstname = (filter_input(INPUT_POST, 'firstname', FILTER_SANITIZE_STRING));
//     $surname = (filter_input(INPUT_POST, 'surname', FILTER_SANITIZE_STRING));
//     $email = (filter_input(INPUT_POST, 'email', FILTER_SANITIZE_EMAIL));
//     $username = (filter_input(INPUT_POST, 'username', FILTER_SANITIZE_STRING));
//     $password = (filter_input(INPUT_POST, 'password', FILTER_SANITIZE_STRING));
//     $confPassword = (filter_input(INPUT_POST, 'confPassword', FILTER_SANITIZE_STRING));
//
//     $Error = false;
//     $nameError;
//     $emailError;
//     $usernameError;
//     $passwordError;
//     $confPasswordError;
//
//     if (!preg_match("/^[a-zA-Z ]*$/",$firstname) || !preg_match("/^[a-zA-Z ]*$/",$surname)) // First & Surname must be Letters
//     {
//       $Error = true;
//       $nameError = "Your name can only contain letters";
//     }
//
//     if (!filter_var($email, FILTER_VALIDATE_EMAIL)) // Email Must be Valid
//     {
//       $Error = true;
//       $emailError = "Invalid email format";
//     }
//
//     if(!preg_match("/^[a-zA-Z0-9]*$/", $username))//Username Must be letters & Numbers
//     {
//       $Error = true;
//       $usernameError = "Username Must Contain only letters and numbers";
//     }
//
//     if(!empty($password) && $password == $confPassword) // Password & confPassword Must Match
//     {
//       if(strlen($password) <= '8')// Passowrd must be Atleast 8 characters
//       {
//         $Error = true;
//         $passwordError = "Password Must be Atleast 8 characters Long";
//       }
//       elseif(!preg_match("#[0-9]+#",$password)) // Password must contain a number
//       {
//         $Error = true;
//         $passwordError = "Your Password Must Contain At Least 1 Number!";
//       }
//       elseif(!preg_match("#[A-Z]+#",$password)) // Password Must contain an Uppercase letter
//       {
//         $Error = true;
//         $passwordError = "Your Password Must Contain At Least 1 Capital Letter!";
//       }
//       elseif(!preg_match("#[a-z]+#",$password))// Password Must Conatain a Lowercase letter
//       {
//         $Error = true;
//         $passwordError = "Your Password Must Contain At Least 1 Lowercase Letter!";
//       }
//       else// No password errors have Occured
//       {
//         $PasswordError = "Password Is Acceptable";
//       }
//     }
//   }
//   if(!empty($password) && $password != $confPassword) // Password and confPassword do NOT Match
//   {
//     $Error = true;
//     $confPasswordError = "Please Check You've Confirmed Your Password!";
//   }
//   if(empty($password)) // Password Is Empty
//   {
//     $Error = true;
//     $passwordError = "Please enter a password";
//   }
//
//   if($Error == true) // An Error Has Occured
//   {
//     echo"'$nameError' </br> '$emailError' </br> '$usernameError' </br> '$passwordError' </br> '$confPasswordError'";
//   }
//   else // Continue with the Registration
//   {
//     // Hash the password
//     $password = password_hash($password, PASSWORD_DEFAULT);
//     $confPassword ="";
//
//     // Create SQL Template
//     $query = $pdo->prepare
//     ("
//
//     INSERT INTO SQC_Users (First_Name, Surname, Email, Username, Password)
//     VALUES( :firstname, :surname, :email, :username, :password)
//
//     ");
//
//     // Runs and executes the query
//     $success = $query->execute
//     ([
//       'firstname' => $firstname,
//       'surname' => $surname,
//       'email' => $email,
//       'username' => $username,
//       'password' => $password
//     ]);
//
//     // If rows returned is more than 0 Let us know if it inserted or not.
//     $count = $query->rowCount();
//     if($count > 0)
//     {
//       echo "Insert Successful";
//     }
//     else
//     {
//       echo "Insert Failed";
//       echo $query -> errorInfo()[2];
//     }
//   }
// }
