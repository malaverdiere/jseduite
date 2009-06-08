<?php
	if(empty($_POST['cKind'])) $_POST['cKind'] = "";
	if(empty($cKind)) $cKind = "";
	if(empty($_POST['cName'])) $_POST['cName'] = "";
	if(empty($cName)) $cName = "";

    if (empty($_GET['action'])) $_GET['action']='';
	$action = $_GET['action'];

    //Course creation
    if($action == 'create')
	{
		// Gets data
		$cKind = $_POST['cKind'];
		$cName = $_POST['cName'];

        //Creates the course
        $course = new Course(array('kind' => $cKind, 'name' => $cName));
        $courseCrudProxy->create($course->getStructure());
	}
?>

<h2>Create a course</h2>

<form name="creation" method="post" action="index.php?page=ccreate&action=create">
    <table>
        <tr>
            <td><p>Kind</p></td>
            <td>
                <select name="cKind">
                    <?php
                        $kinds = $courseBusinessProxy->getAvailableCourseKinds();
                        
                        for($i=0;$i<sizeof($kinds);$i++)
                        {
                            echo "<option>".$kinds[$i]."</option>";
                        }
                    ?>
                </select>
            </td>
        </tr>
        <tr>
            <td><p>Name</p></td>
            <td><input name="cName" type="text" size="30" /></td>
        </tr>
    </table>
    <input name="Submit" type="submit" value="Create" />
</form>
