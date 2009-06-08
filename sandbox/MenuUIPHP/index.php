<?php
    /* Starts the session */
	session_start();

    require_once('proxy/courses_proxies.php');
    require_once('proxy/menus_proxies.php');

    /* Proxies initialisation */
    $courseFinderProxy = new CourseFinderProxy ();
    $courseBusinessProxy = new CourseBusinessProxy ();
    $courseCrudProxy = new CourseCRUDProxy();

    $menuFinderProxy = new MenuFinderProxy();
    $menuCrudProxy = new MenuCRUDProxy();
?>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PHP User Interface</title>
    </head>
    <body>
        <h1>Menu Interface</h1>
        <h2>Courses commands</h2>
        <ul>
            <li>
                <a href="index.php?page=clisting">Display all the courses</a>
            </li>
            <li>
                <a href="index.php?page=ccreate">Create a course</a>
            </li>
            <li>
                <a href="index.php?page=cmodify">Modify a course</a>
            </li>
            <li>
                <a href="index.php?page=cdelete">Delete a course</a>
            </li>
        </ul>

        <h2>Menus commands</h2>
        <ul>
            <li>
                <a href="index.php?page=mlisting">Display all the menus</a>
            </li>
            <li>
                <a href="index.php?page=mcreate">Create a menu</a>
            </li>
            <li>
                <a href="index.php?page=mmodify">Modify a menu</a>
            </li>
            <li>
                <a href="index.php?page=mdelete">Delete a menu</a>
            </li>
        </ul>

        <?php
            //Authorised pages
            $pageOK = array('clisting' => 'courses/listing.php',
                            'ccreate' => 'courses/create.php',
                            'cmodify' => 'courses/modify.php',
                            'cdelete' => 'courses/delete.php',
                            'mlisting' => 'menus/listing.php',
                            'mcreate' => 'menus/create.php',
                            'mmodify' => 'menus/modify.php',
                            'mdelete' => 'menus/delete.php');

            if ( (isset($_GET['page'])) )
            {
                $file=$_GET['page'];

                if ( (isset($pageOK[$file])) )
                {
                    include($pageOK[$file]);
                }
            }
        ?>
    </body>
</html>
