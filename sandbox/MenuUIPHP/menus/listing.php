<h2>Menus listing</h2>
<table>

<?php
    $references = $menuFinderProxy->getAllMenuReferences();


    //For all the menus
    for($i=0;$i<sizeof($references);$i++)
    {
        $menuRes = $menuFinderProxy->findMenuByDate(dateMaker($references[$i]));
        $menu = new Menu($menuRes);

        $courses = $menu->getCourses();
        $date = $menu->getDate();

        for($j=0;$j<sizeof($courses);$j++)
        {
            echo "<tr>";
            echo "<td>".$date."</td>";
            echo "<td>".$courses[$j]['kind']."</td>";
            echo "<td>".$courses[$j]['name']."</td>";
            echo "</tr>";
        }
    }
?>

</table>
