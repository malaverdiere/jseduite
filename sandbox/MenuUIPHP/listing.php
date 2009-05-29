<h2>Course listing</h2>
<table>

<?php
    $references = $finderProxy->getAllCoursesReferences();

    //For all the courses
    for($i=0;$i<sizeof($references);$i++)
    {
        $course = $finderProxy->findCourseByName($references[$i]);
        echo "<tr>";
        echo "<td>".$course['kind']."</td>";
        echo "<td>".$course['name']."</td>";
        echo "</tr>";
    }
?>

</table>
