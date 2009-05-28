<?php
	require_once('util.php');
?>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PHP User Interface</title>
    </head>
    <body>
        <?php
            displayAllCourses();
            deleteCourse('desert_name_166929727');
            echo "----------------------------<br/>";
            displayAllCourses();
        ?>
    </body>
</html>
