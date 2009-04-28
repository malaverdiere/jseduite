#!/bin/bash

DIR=contracts
URL=$1
FILE=`basename $URL`

TMP_BAD_WSDL=`mktemp -t add_contract.sh.XXXXXX`
trap "rm $TMP_BAD_WSDL* 2>/dev/null" 0
TMP_GOOD_WSDL=`mktemp -t add_contract.sh.XXXXXX`
trap "rm $TMP_GOOD_WSDL* 2>/dev/null" 0

TMP_XSD=`mktemp -t add_contract.sh.XXXXXX`
trap "rm $TMP_XSD* 2>/dev/null" 0

echo "# Retrieving remote files"
wget -nv -O $TMP_BAD_WSDL $URL?wsdl 
wget -nv -O $TMP_XSD  $URL?xsd=1 

echo "# Formatting  XSD file"
xmllint --format $TMP_XSD > $DIR/$FILE.xsd
file contracts/$FILE.xsd

echo "# Replacing schemaLocation attribute in WSDL file"
cat $TMP_BAD_WSDL | sed s/schemaLocation=\".*?xsd=1\"/schemaLocation=\"$FILE.xsd\"/g  > $TMP_GOOD_WSDL

echo "# Formating WSDL file"
xmllint --format $TMP_GOOD_WSDL > $DIR/$FILE.wsdl
file contracts/$FILE.wsdl