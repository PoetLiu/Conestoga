<?php
    /*echo "Hello";
    echo "<h1>Hello Good Morning!</h1>";
    */
    
    // - for comment

    // Variable
    // $firstName = "Peng";
    // $lastName = "Liu";
    // echo $firstName;
    // echo $lastName;

    // echo nl2br("Hello\nWorld");

    // echo $firstName . " " . $lastName;

    // // Constant
    // define("NAME", "Albert");
    // echo NAME;

    // $firstName = 'Albert';
    // echo "My Name is $firstName";

    // echo "My Name is \"$firstName\"";
    // echo 'My Name is "$firstName"';

    // echo strtoupper("this is a lowercase string");
    // echo "<br>";
    // echo strtolower("THIS IS A LOWERCASE STRING");

    // echo strlen($firstName);

    // echo str_replace("i", "I", "This is my string");
    // echo "<br>";

    // // Numbers
    // $radius = 25;
    // $pi = 3.14;
    // // Basic operation: + - * / **
    // echo $pi * $radius;
    // echo "<br>";

    // // Follow BODMAS Rule to evaluate
    // echo 2 * (4 + 9) / 3;
    // echo "<br>";

    // // Increment and Decrement
    // echo ++$radius;
    // echo $radius++;
    // echo "<br>";

    // // Short-hand operators
    // $age = 25;
    // $age += 5;
    // echo $age;
    // echo "<br>";
   
    // // Arrays
    // $people = array(); 
    // $people = ["Bob", "Tom", "Geoge"];
    // echo $people[2];
    // echo "<br>";
    // print_r($people);
    // echo "<br>";
    // var_dump($people);
    // echo "<br>";

    // $ages = [10, 20, 30, 40, 50];
    // print_r($ages);
    // echo "<br>";
    // $ages[] = 60;
    // print_r($ages);
    // echo "<br>";
    // array_push($ages, 70);
    // print_r($ages);
    // echo "<br>";
    // echo count($ages);
    // echo "<br>";
    
    // // Merge Array
    // $people1 = ["Bob1", "Tom1", "Geoge1"];
    // $peopleMerged = array_merge($people, $people1);
    // print_r($peopleMerged);
    // echo "<br>";

    // // Associative Array
    // $data = ["name" => "Bob", "age" => "15"];
    // print_r($data);
    // echo "<br>";
    // echo $data["name"];
    // echo "<br>";

    // // Multi-dimentional array
    // $blogs = [
    //     ['test1', 'test2', 'test3', 'test4'],
    //     ['test5', 'test6', 'test7', 'test8'],
    //     ['test9', 'test10', 'test11', 'test12'],
    // ];
    // echo $blogs[1][1];
    // echo "<br>";

    // // Remove element from the array
    // $people1 = ["Bob1", "Tom1", "Geoge1"];
    // array_pop($people1);
    // print_r($people1);
    // echo "<br>";

    // // loops
    // for ($i = 0; $i < 9; $i++) {
    //     echo "Hello World<br>";
    // }

    // $blogs = ["test1", "test2", "test3"];
    // for ($i = 0; $i < count($blogs); $i++) {
    //     echo $blogs[$i] . "<br>";
    // }

    // foreach($blogs as $blog) {
    //     echo $blog . "<br>";
    // }

    // while loop
    // $i = 0;
    // while ($i < count($blogs)) {
    //     echo $blogs[$i] . "<br>";
    //     $i++;
    // }

    // Boolean and comparisions
    // echo true;
    // echo "<br>";
    // echo false;

    // echo 5 == "5";
    // echo 5 === "5";

    // Conditional Statements
    // $price = 20;
    // if ($price < 10) {
    //     echo "The price is less than 10";
    // } else if ($price < 20) {
    //     echo "The price is between 10 and 20";
    // } else {
    //     echo "The price is bigger or equal than 20";
    // }

    // Continue and break
    // for ($i = 1; $i < 10; $i++) {
    //     if ($i == 6) {
    //         continue;
    //     }
    //     if ($i == 8) {
    //         break;
    //     }
    //     echo $i . ",";
    // }

    // $m1 = 10;
    // $m2 = 20;
    // $m3 = 30;
    // $max = 0;
    // if ($m1 < $m2) {
    //     if ($m3 > $m2) {
    //         $max = $m3;
    //     } else {
    //         $max = $m2;
    //     }
    // } else {
    //     if ($m1 > $m3) {
    //         $max = $m1;
    //     } else {
    //         $max = $m3;
    //     }
    // }
    // echo "The max is $max";

    // Implode and Explode
    // $s1 = "Mon-Tue-Wed-Thu-Fri";
    // $days = explode("-", $s1);
    // print_r($days);
    // echo "<br>";
    
    // $s2 = implode(',', $days);
    // print_r($s2);
    // echo "<br>";

?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Hello World</title>
</head>
<body>
    <h1>
        <?php echo "Hello, World!" ?>
    </h1>
</body>
</html>