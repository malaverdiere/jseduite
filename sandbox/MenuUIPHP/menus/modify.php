<?php
    if (empty($_GET['action'])) $_GET['action']='';
	$action = $_GET['action'];

    $courses = $courseFinderProxy->getAllCoursesReferences();

    //Menu reading
    if($action == 'read')
	{
        if(empty($_POST['rDate'])) $_POST['rDate'] = "";
        if(empty($rDate)) $rDate = "";

		// Gets data
		$rDate = $_POST['rDate'];

        //Reads the menu
        $menuRes = $menuCrudProxy->read(dateRefMaker($rDate));
        $menu = new Menu($menuRes);
        $date = $menu->getDate();
        $selectedCourses = $menu->getCourses();
        ?>
<h2>Update a menu</h2>
<p><?php echo $date; ?></p>

<form name="update" method="post" action="index.php?page=mmodify&action=update">
    <input type="hidden" value="<?php echo $date; ?>" name="uDate" />
    <table>
        <?php
            for($i=0;$i<sizeof($courses);$i++)
            {
                ?>
                <tr>
                    <td>
                        <input type="checkbox"
                               name="cCourse<?php echo $i; ?>"
                               value="<?php echo $courses[$i]; ?>"
                               <?php
                                    for($j=0;$j<sizeof($selectedCourses);$j++)
                                    {
                                        if($selectedCourses[$j]['name'] == $courses[$i])
                                        {
                                            echo "checked";
                                            break;
                                        }
                                    }
                                echo "/>".$courses[$i]; ?>
                    </td>
               </tr>
               <?php
            }
        ?>
    </table>
    <input name="Submit" type="submit" value="Update" />
</form>
        <?php
	}
    else
    {
        if($action == 'update')
        {
            if(empty($_POST['uName'])) $_POST['uName'] = "";
            if(empty($uName)) $uName = "";

            if(empty($_POST['uDate'])) $_POST['uDate'] = "";
            if(empty($uDate)) $uDate = "";

            // Gets data
            $uDate = $_POST['uDate'];

            $menuCreated = false;

            for ($i=0; $i<sizeof($courses); $i++)
            {
                if (!empty($_POST['cCourse'.$i]))
                {
                    $course = $courseCrudProxy->read(refMaker($_POST['cCourse'.$i]));

                    if($menuCreated)
                    {
                        $menu->setCourse($course);
                    }
                    else
                    {
                        $menu = new Menu(array('courses' => $course, 'date' => $uDate));
                        $menuCreated = true;
                    }
                }
            }

            //Updates the menu
            $menuCrudProxy->update($menu->getStructure());
        }
        ?>
<h2>Read a menu</h2>

<form name="read" method="post" action="index.php?page=mmodify&action=read">
    <table>
        <tr>
            <td><p>Date</p></td>
            <td>
                <select name="rDate">
                    <?php
                        $dates = $menuFinderProxy->getAllMenuReferences();

                        for($i=0;$i<sizeof($dates);$i++)
                        {
                            echo "<option>".$dates[$i]."</option>";
                        }
                    ?>
                </select>
            </td>
        </tr>
    </table>
    <input name="Submit" type="submit" value="Read" />
</form>
        <?php

    }
?>
