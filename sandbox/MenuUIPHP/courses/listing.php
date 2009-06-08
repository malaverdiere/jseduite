<h2>Course listing</h2>
<table>

<?php
    $references = $courseFinderProxy->getAllCoursesReferences();

    //For all the courses
    for($i=0;$i<sizeof($references);$i++)
    {
        $course = $courseFinderProxy->findCourseByName($references[$i]);
        echo "<tr>";
        echo "<td>".$course['kind']."</td>";
        echo "<td>".$course['name']."</td>";
        echo "</tr>";
    }
?>

</table>
