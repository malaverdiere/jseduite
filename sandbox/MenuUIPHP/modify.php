<?php
    if (empty($_GET['action'])) $_GET['action']='';
	$action = $_GET['action'];

    //Course creation
    if($action == 'read')
	{
        if(empty($_POST['rName'])) $_POST['rName'] = "";
        if(empty($rName)) $rName = "";

		// Gets data
		$rName = $_POST['rName'];

        //Reads the course
        $courseRes = $crudProxy->read(refMaker($rName));
        $name = $courseRes['name'];
        $kind = $courseRes['kind'];

        ?>
<h2>Update a course</h2>

<form name="update" method="post" action="index.php?page=modify&action=update">
    <table>
        <tr>
            <td><p>Kind</p></td>
            <td>
                <select name="uKind">
                    <?php
                        $kinds = $businessProxy->getAvailableCourseKinds();

                        for($i=0;$i<sizeof($kinds);$i++)
                        {
                            echo "<option";
                            if($kinds[$i] == $kind)
                            {
                                echo " selected";
                            }
                            echo ">".$kinds[$i]."</option>";
                        }
                    ?>
                </select>
            </td>
        </tr>
        <tr>
            <td><p>Name</p></td>
            <td><input name="uName" type="text" size="30" value="<?php echo $name; ?>" readonly="true"/></td>
        </tr>
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

            if(empty($_POST['uKind'])) $_POST['uKind'] = "";
            if(empty($uKind)) $uKind = "";

            // Gets data
            $uName = $_POST['uName'];
            $uKind = $_POST['uKind'];
            
            /*$courseRes = $crudProxy->read(refMaker($uName));
            $course = new Course($courseRes);
            $course->setKind($uKind);*/
            $course = new Course(array('kind' => $uKind, 'name' => $uName));
            $crudProxy->update($course->getStructure());
        }

        ?>
<h2>Read a course</h2>

<form name="read" method="post" action="index.php?page=modify&action=read">
    <table>
        <tr>
            <td><p>Name</p></td>
            <td>
                <select name="rName">
                    <?php
                        $names = $finderProxy->getAllCoursesReferences();

                        for($i=0;$i<sizeof($names);$i++)
                        {
                            echo "<option>".$names[$i]."</option>";
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
