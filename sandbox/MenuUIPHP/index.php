<?php
    require_once('jseduite_proxies.php');
?>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PHP User Interface</title>
    </head>
    <body>
        <?php
            $finderProxy = new CourseFinderProxy ();
            $crudProxy = new CourseCRUDProxy();

            $course = new Course(array('kind' => "type", 'name' => "znom"));
            $crudProxy->create($course->getStructure());
            $course = new Course(array('kind' => "type", 'name' => "zznom"));
            $crudProxy->create($course->getStructure());

            echo "<pre>";
            print_r($finderProxy->getAllCoursesReferences());
            echo "</pre>-------------<br/>";

            $courseRes = $finderProxy->findCourseByName("znom");
            $course = new Course($courseRes);
            $crudProxy->delete($course->getStructure());

            $courseRes = $crudProxy->read(refMaker("zznom"));
            $course = new Course($courseRes);
            $course->setKind("nouveau_type");
            $crudProxy->update($course->getStructure());

            $courseRes = $finderProxy->findCourseByName("zznom");
            print_r($courseRes);

            echo "<pre>";
            print_r($finderProxy->getAllCoursesReferences());
            echo "</pre>";

        ?>
    </body>
</html>
