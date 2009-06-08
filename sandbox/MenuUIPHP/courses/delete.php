<?php
	if(empty($_POST['dName'])) $_POST['dName'] = "";
	if(empty($dName)) $dName = "";

    if (empty($_GET['action'])) $_GET['action']='';
	$action = $_GET['action'];

    //Course deletion
    if($action == 'delete')
	{
		// Gets data
		$dName = $_POST['dName'];

        //Deletes the course
        $courseRes = $courseFinderProxy->findCourseByName($dName);
        $course = new Course($courseRes);
        $courseCrudProxy->delete($course->getStructure());
	}
?>

<h2>Delete a course</h2>

<form name="deletion" method="post" action="index.php?page=cdelete&action=delete">
    <table>
        <tr>
            <td><p>Name</p></td>
            <td>
                <select name="dName">
                    <?php
                        $names = $courseFinderProxy->getAllCoursesReferences();

                        for($i=0;$i<sizeof($names);$i++)
                        {
                            echo "<option>".$names[$i]."</option>";
                        }
                    ?>
                </select>
            </td>
        </tr>
    </table>
    <input name="Submit" type="submit" value="Delete" />
</form>
