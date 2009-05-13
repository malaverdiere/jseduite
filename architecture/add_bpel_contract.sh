#!/bin/bash

ORCH_NAME=$1
DIR=contracts

WSDL=../orchestrations/$ORCH_NAME/src/$ORCH_NAME.wsdl
XSD=../orchestrations/$ORCH_NAME/src/$ORCH_NAME.xsd

echo "# Retrieving XSD data model [Partners => '']"
echo "$XSD -> $DIR/$ORCH_NAME.xsd"
cat $XSD | sed s/Partners\\/// > $DIR/$ORCH_NAME.xsd

echo "# Retrieving WSDL descripton [HttpDefaultPort => 9080]"
echo "$WSDL -> $DIR/$ORCH_NAME.wsdl "
cat $WSDL | sed s/\${HttpDefaultPort}/9080/ > $DIR/$ORCH_NAME.wsdl

 