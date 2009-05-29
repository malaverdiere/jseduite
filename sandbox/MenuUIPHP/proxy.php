<?php
    /* Loads the NUSOAP library */
	require_once('nusoap/nusoap.php');

    /**
     * A proxy object
     *
     */
     class Proxy
     {
        protected $client;
        protected $namespace;

        // Constructor
        public function __construct ($_wsdl, $_ns) {
            $this->client = new soapclient($_wsdl);
            $this->namespace = $_ns;
        }

        /**
         * Calls the given method with the given parameters
         *
         * @param	string      $_method	the method name
         * @param	array   	$_params	the parameters
         * @return	SOAP response
         * @access	public
         */
        protected function callWebService($_method, $_params) {
            return $this->client->call($_method, $_params, $this->namespace);
        }
     }

    /**
     * A generic CRUD proxy object
     *
     */
    class CRUDProxy extends Proxy
    {
        protected $type;

        // Constructor
        public function __construct () {
            $this->type = "";
        }

        /**
         * Creates the given object
         *
         * @param   object  $_obj  the object to create
         * @return  the object reference
         * @access	public
         */
        public function create($_obj) {
            return $this->callWebService("create".$this->type, $_obj);
        }

        /**
         * Reads the object matching the given reference
         *
         * @param   string  $_ref  the object reference to read
         * @return  the expected object
         * @access	public
         */
        public function read($_ref) {
            return $this->callWebService("read".$this->type, $_ref);
        }

        /**
         * Updates the given object
         *
         * @param   object  $_obj  the object to update
         * @access	public
         */
        public function update($_obj) {
            return $this->callWebService("update".$this->type, $_obj);
        }

        /**
         * Deletes the given object
         *
         * @param   object  $_obj  the object to delete
         * @access	public
         */
        public function delete($_obj) {
            return $this->callWebService("delete".$this->type, $_obj);
        }
    }
?>
