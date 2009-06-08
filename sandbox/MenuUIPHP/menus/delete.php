<?php
	if(empty($_POST['dDate'])) $_POST['dDate'] = "";
	if(empty($dDate)) $dDate = "";

    if (empty($_GET['action'])) $_GET['action']='';
	$action = $_GET['action'];

    //Menu deletion
    if($action == 'delete')
	{
		// Gets data
		$dDate = $_POST['dDate'];

        //Deletes the menu
        $menuRes = $menuFinderProxy->findMenuByDate(dateMaker($dDate));
        $menu = new Menu($menuRes);
        $menuCrudProxy->delete($menu->getStructure());
	}
?>

<h2>Delete a menu</h2>

<form name="deletion" method="post" action="index.php?page=mdelete&action=delete">
    <table>
        <tr>
            <td><p>Date</p></td>
            <td>
                <select name="dDate">
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
    <input name="Submit" type="submit" value="Delete" />
</form>
