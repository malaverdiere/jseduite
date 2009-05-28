<?php
    /* Loads the NUSOAP library */
	require_once('nusoap/nusoap.php');  


    /**
	 * Calls the given method on the given SOAP client with the given parameters
	 *
     * @param	soapclient	$_client	the SOAP client
     * @param	string      $_method	the method name
     * @param	array   	$_params	the parameters
     * @param	string   	$_namespace	the namespace for the call
	 * @return	SOAP response
	 * @access	public
	 */
    function callWebService($_client, $_method, $_params, $_namespace=null) {
        return $_client->call($_method, $_params, $_namespace);
    }

    /**
	 * Calls the given method on the given SOAP client with the given parameters
	 *
     * @param	soapclient	$_client	the SOAP client
     * @param	string      $_method	the method name
     * @param	array   	$_params	the parameters
     * @param	string   	$_namespace	the namespace for the call
	 * @return	SOAP response
	 * @access	public
	 */
    function simpleCall($_client, $_method, $_params, $_namespace) {
        //XML creation
        //Method + namespace
        $xml = "<ns2:".$_method." xmlns:ns2=\"".$_namespace."\">";

        //Parameters
        foreach($_params as $key=>$value)
        {
            $xml .= "<".$key.">";
            $xml .= $value;
            $xml .= "</".$key.">";
        }

        $xml .= "</ns2:".$_method.">";

        return $_client->call($_method, $xml);
    }

    /**
     * Gets all the courses references
     *
     * @return	the courses references
     * @access	public
     */
    function getAllCoursesReferences() {
        $client = new soapclient('http://localhost:8080/jSeduite/sandbox/MenuService/CourseFinderService?wsdl');
        $method = "getAllCoursesReferences";
        $params = array();
        $namespace = "http://restaurant.technical.jSeduite.modalis.i3s.unice.fr/";
        return callWebService($client, $method, $params, $namespace);
    }


    /**
     * Retrieves a course for a given name
     *
     * @param   string  $_name  the name you're looking for
     * @return	the course
     * @access	public
     */
    function findCourseByName($_name) {
        $client = new soapclient('http://localhost:8080/jSeduite/sandbox/MenuService/CourseFinderService?wsdl');
        $method = "findCourseByName";
        $params = array('name' => $_name);
        $namespace = "http://restaurant.technical.jSeduite.modalis.i3s.unice.fr/";
        return callWebService($client, $method, $params, $namespace);
    }

    /**
     * Deletes the course matching the given name
     *
     * @param   string  $_name  the name of the course to delete
     * @access	public
     */
    function deleteCourse($_name) {
        $client = new soapclient('http://localhost:8080/jSeduite/sandbox/MenuService/CourseCRUDService?wsdl');
        $method = "deleteCourse";
        $params = findCourseByName($_name);
        $params = array('c' => $params);
        $namespace = "http://restaurant.technical.jSeduite.modalis.i3s.unice.fr/";
        return callWebService($client, $method, $params, $namespace);
    }


    /**
     * Displays all the courses
     *
     * @access	public
     */
    function displayAllCourses() {
        $references = getAllCoursesReferences();

        echo "<table>";
        
        //For all the courses
        for($i=0;$i<sizeof($references);$i++)
        {
            $course = findCourseByName($references[$i]);
            echo "<tr>";
            echo "<td>".$course['kind']."</td>";
            echo "<td>".$course['name']."</td>";
            echo "</tr>";
        }

        echo "</table>";
    }


    /* Initialisations and calls */
    /*$result = deleteCourse("xhxhhc");
    //$result = findCourseByName("main_name_1671897227");
    echo "<pre>";
    print_r($result);
    echo "</pre>";*/



    // Check for an error
/*    $err = $client->getError();
    if ($err) {
        // Display the error
        echo '<h2>Constructor error</h2><pre>' . $err . '</pre>';
    }

    // Check for a fault
    if ($client->fault) {
        echo '<h2>Fault</h2><pre>';
        print_r($result);
        echo '</pre>';
    } else {
        // Check for errors
        $err = $client->getError();
        if ($err) {
            // Display the error
            echo '<h2>Error</h2><pre>' . $err . '</pre>';
        } else {
            // Display the result
            echo '<h2>Result</h2><pre>';
            print_r($result);
        echo '</pre>';
        }
    }
    // Display the request and response
    echo '<h2>Request</h2>';
    echo '<pre>' . htmlspecialchars($client->request, ENT_QUOTES) . '</pre>';
    echo '<h2>Response</h2>';
    echo '<pre>' . htmlspecialchars($client->response, ENT_QUOTES) . '</pre>';
    // Display the debug messages
    echo '<h2>Debug</h2>';
    echo '<pre>' . htmlspecialchars($client->debug_str, ENT_QUOTES) . '</pre>';*/
?>