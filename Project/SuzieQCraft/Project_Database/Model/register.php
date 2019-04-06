<?php

		require 'conn.php';

		$first_name = (filter_input(INPUT_POST, 'first_name', FILTER_SANITIZE_STRING));

        $surname = (filter_input(INPUT_POST, 'surname', FILTER_SANITIZE_STRING));

        $email = (filter_input(INPUT_POST, 'email', FILTER_SANITIZE_STRING));

        $username = (filter_input(INPUT_POST, 'username', FILTER_SANITIZE_STRING));

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
				'Username' => $username,
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
	   