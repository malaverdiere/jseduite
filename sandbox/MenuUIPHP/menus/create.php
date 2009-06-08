<?php
	if(empty($_POST['cDate'])) $_POST['cDate'] = "";
	if(empty($cDate)) $cDate = "";

    if (empty($_GET['action'])) $_GET['action']='';
	$action = $_GET['action'];

    $courses = $courseFinderProxy->getAllCoursesReferences();

    //Menu creation
    if($action == 'create')
	{
		// Gets data
		$cDate = $_POST['cDate'];

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
                    $menu = new Menu(array('courses' => $course, 'date' => $cDate));
                    $menuCreated = true;
                }
            }
        }

        //Creates the menu
        $menuCrudProxy->create($menu->getStructure());
	}
?>

<h2>Create a menu</h2>

<form name="creation" method="post" action="index.php?page=mcreate&action=create">
    <table>
        <tr>
            <td><p>Date</p></td>
            <td><input name="cDate" type="text" size="30" /></td>
        </tr>
        <?php
            for($i=0;$i<sizeof($courses);$i++)
            {
                ?>
                <tr>
                    <td>
                        <input type="checkbox" name="cCourse<?php echo $i; ?>" value="<?php echo $courses[$i]; ?>" />
                <?php echo $courses[$i]; ?>
                    </td>
               </tr>
               <?php
            }
        ?>
    </table>
    <input name="Submit" type="submit" value="Create" />
</form>
