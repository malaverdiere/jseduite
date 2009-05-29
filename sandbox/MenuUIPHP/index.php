<?php
    /* Starts the session */
	session_start();

    require_once('jseduite_proxies.php');

    /* Proxies initialisation */
    $finderProxy = new CourseFinderProxy ();
    $crudProxy = new CourseCRUDProxy();
?>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PHP User Interface</title>
    </head>
    <body>
        <h1>Course Interface</h1>
        <h2>Commands</h2>
        <ul>
            <li>
                <a href="index.php?page=listing">Display all the courses</a>
            </li>
            <li>
                <a href="index.php?page=create">Create a course</a>
            </li>
            <li>
                <a href="index.php?page=modify">Modify a course</a>
            </li>
            <li>
                <a href="index.php?page=delete">Delete a course</a>
            </li>
        </ul>

        <?php
            //Authorised pages
            $pageOK = array('listing' => 'listing.php',
                            'create' => 'create.php',
                            'modify' => 'modify.php',
                            'delete' => 'delete.php');

            if ( (isset($_GET['page'])) )
            {
                $file=$_GET['page'];

                if ( (isset($pageOK[$file])) )
                    include($pageOK[$file]);
            }
        ?>
    </body>
</html>
