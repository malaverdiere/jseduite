<?php
	if(empty($_POST['dName'])) $_POST['dName'] = "";
	if(empty($dName)) $dName = "";

    if (empty($_GET['action'])) $_GET['action']='';
	$action = $_GET['action'];

    //Course creation
    if($action == 'delete')
	{
		// Gets data
		$dName = $_POST['dName'];

        //Deletes the course
        $courseRes = $finderProxy->findCourseByName($dName);
        $course = new Course($courseRes);
        $crudProxy->delete($course->getStructure());
	}
?>

<h2>Delete a course</h2>

<form name="deletion" method="post" action="index.php?page=delete&action=delete">
    <table>
        <tr>
            <td><p>Name</p></td>
            <td><input name="dName" type="text" size="30" /></td>
        </tr>
    </table>
    <input name="Submit" type="submit" value="Delete">
</form>
