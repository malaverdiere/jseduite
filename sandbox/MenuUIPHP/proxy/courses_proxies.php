<?php
    /* Loads the proxy classes */
    require_once('proxy.php');


    /* Utilities */
    function nameMaker($_name)
    {
        return array('name' => $_name);
    }
    function kindMaker($_kind)
    {
        return array('kind' => $_kind);
    }
    function refMaker($_ref)
    {
        return array('ref' => $_ref);
    }


    /**
     * A course object
     */
    class Course
    {
        private $c;

        // Constructor
        public function __construct ($_course) {
            $this->c = array('c' => $_course);
        }

        /**
         * Sets a new kind to the course
         * 
         * @param string $_kind the new kind of the course
         */
        public function setKind ($_kind)
        {
            $this->c['c']['kind'] = $_kind;
        }

        /**
         * Gets a course structure
         *
         * @return  a course structure
         */
        public function getStructure()
        {
            return $this->c;
        }
    }

    /**
     * A course finder proxy object
     *
     */
    class CourseFinderProxy extends Proxy
    {
        // Default constructor
        public function __construct () {
            $this->client = new soapclient('http://localhost:8080/jSeduite/sandbox/MenuService/CourseFinderService?wsdl');
            $this->namespace = "http://restaurant.technical.jSeduite.modalis.i3s.unice.fr/";
        }

        /**
         * Gets all the courses references
         *
         * @return	the courses references
         * @access	public
         */
        public function getAllCoursesReferences ()
        {
            return $this->callWebService("getAllCoursesReferences", array());
        }

        /**
         * Retrieves a course for a given name
         *
         * @param   string  $_name  the name you're looking for
         * @return	the course
         * @access	public
         */
        public function findCourseByName($_name) {
            return $this->callWebService("findCourseByName", nameMaker($_name));
        }

        /**
         * Retrieves courses for a given kind
         *
         * @param   string  $_kind  the kind you're looking for
         * @return	the courses
         * @access	public
         */
        public function findCourseByKind($_kind) {
            return $this->callWebService("findCourseByKind", kindMaker($_kind));
        }
    }

        /**
     * A course business proxy object
     *
     */
    class CourseBusinessProxy extends Proxy
    {
        // Default constructor
        public function __construct () {
            $this->client = new soapclient('http://localhost:8080/jSeduite/sandbox/MenuService/CourseBusinessService?wsdl');
            $this->namespace = "http://restaurant.technical.jSeduite.modalis.i3s.unice.fr/";
        }

        /**
         * Gets all the available kinds
         *
         * @return	a list of the available course kinds
         * @access	public
         */
        public function getAvailableCourseKinds ()
        {
            return $this->callWebService("getAvailableCourseKinds", array());
        }
    }

    /**
     * A course CRUD proxy object
     *
     */
    class CourseCRUDProxy extends CRUDProxy
    {
        // Default constructor
        public function __construct () {
            $this->client = new soapclient('http://localhost:8080/jSeduite/sandbox/MenuService/CourseCRUDService?wsdl');
            $this->namespace = "http://restaurant.technical.jSeduite.modalis.i3s.unice.fr/";
            $this->type = "Course";
        }
    }

?>
