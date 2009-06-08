<?php
    /* Loads the proxy classes */
    require_once('proxy.php');


    /* Utilities */
    function dateMaker($_date)
    {
        return array('date' => $_date);
    }
    function dateRefMaker($_date)
    {
        return array('ref' => $_date);
    }


    /**
     * A menu object
     */
    class Menu
    {
        private $date;
        private $courses;

        // Constructor
        public function __construct ($_menu) {
            $this->date = $_menu['date'];
            $this->courses = $_menu['courses'];
        }

        /**
         * Gets the list of courses
         *
         * @return  a list of courses
         */
        public function getCourses()
        {
            if(empty($this->courses['0']))
            {
                return array('0' => $this->courses);
            }
            
            return $this->courses;
        }

        /**
         * Sets the given course
         *
         * @param course the course to add
         * @return
         */
        public function setCourse($_course)
        {
            $this->courses = $this->getCourses();
            array_push($this->courses, $_course);
        }


        /**
         * Gets the date
         *
         * @return  the date
         */
        public function getDate()
        {
            return $this->date;
        }


        /**
         * Gets a menu structure
         *
         * @return  a menu structure
         */
        public function getStructure()
        {
            return array('m' => array ('date' => $this->date,
                                       'courses' => $this->courses));
        }
    }

    /**
     * A menu finder proxy object
     *
     */
    class MenuFinderProxy extends Proxy
    {
        // Default constructor
        public function __construct () {
            $this->client = new soapclient('http://localhost:8080/jSeduite/sandbox/MenuService/MenuFinderService?wsdl');
            $this->namespace = "http://restaurant.technical.jSeduite.modalis.i3s.unice.fr/";
        }

        /**
         * Gets all the menus references
         *
         * @return	the menus references
         * @access	public
         */
        public function getAllMenuReferences ()
        {
            return $this->callWebService("getAllMenuReferences", array());
        }

        /**
         * Retrieves a menu for a given date
         *
         * @param   string  $_date  the menu you're looking for
         * @return	the menu
         * @access	public
         */
        public function findMenuByDate($_date) {
            return $this->callWebService("findMenuByDate", $_date);
        }
    }

    /**
     * A menu business proxy object
     *
     */
    class MenuBusinessProxy extends Proxy
    {
        // Default constructor
        public function __construct () {
            $this->client = new soapclient('http://localhost:8080/jSeduite/sandbox/MenuService/MenuBusinessService?wsdl');
            $this->namespace = "http://restaurant.technical.jSeduite.modalis.i3s.unice.fr/";
        }

        /**
         * Gets today menu
         *
         * @return	the today menu
         * @access	public
         */
        public function getTodayMenu ()
        {
            return $this->callWebService("getTodayMenu", array());
        }
    }

    /**
     * A menu CRUD proxy object
     *
     */
    class MenuCRUDProxy extends CRUDProxy
    {
        // Default constructor
        public function __construct () {
            $this->client = new soapclient('http://localhost:8080/jSeduite/sandbox/MenuService/MenuCRUDService?wsdl');
            $this->namespace = "http://restaurant.technical.jSeduite.modalis.i3s.unice.fr/";
            $this->type = "Menu";
        }
    }

?>
