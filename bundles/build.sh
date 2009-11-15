#!/bin/sh
###
#  This file is part of jSeduiteBundler
# 
#  Copyright (C) 2008-  Sebastien Mosser
# 
#  jSeduiteBundler is free software; you can redistribute it and/or modify
#  it under the terms of the GNU General Public License as published by
#  the Free Software Foundation; either version 2 of the License, or
#  (at your option) any later version.
# 
#  jSeduiteBundler is distributed in the hope that it will be useful,
#  but WITHOUT ANY WARRANTY; without even the implied warranty of
#  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
#  GNU General Public License for more details.
# 
#  You should have received a copy of the GNU General Public License
#  along with jSeduiteBundler; if not, write to the Free Software
#  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
# 
#  @author Sebastien Mosser <mosser@polytech.unice.fr>
###

function extract_sql() 
{
    echo "## Extracting SQL"
    mkdir sql
    cd sql
    cp -v ../../../libraries/DatabaseSchemas/src/Schemas/*.sql .
    cd ..
}

function extract_data()
{
    echo "## Extracting initial data"
    mkdir sql_fill
    cd sql_fill
    cp -v ../../../libraries/DatabaseSchemas/src/Data/*.sql .
    cd ..
}

function extract_webservices() 
{
    echo "## Extracting Web Services"
    mkdir ws
    cd ws
    build_dir ../../../webservices war
    cd ..
}

function extract_hci()
{    
    echo "## Extracting Web Interfaces"
    mkdir hci
    cd hci
    build_dir ../../../hci war
    cd ..
}

function extract_jbi()
{    
    echo "## Extracting EXISTING business processes"
    mkdir jbi
    cd jbi
    cp -v ../../../jSeduiteBpelComponent/dist/jSeduiteBpelComponent.zip .
    cd ..
}


function build_dir() {
    OLD=`pwd`
    cd $1
    for f in *; do
	if [ -d $f ]; then
	    cd $f
	    ant -q dist > /dev/null
	    if [ -f dist/$f.$2 ]; then
		cp -v dist/$f.$2 $OLD/.
	    fi
	    cd ..
	fi
    done
    cd $OLD
}

function bundle() 
{
    zip -q -r tmp $1
    mv tmp.zip ./$1.zip
    rm -rf $1
}

function main()
{
    VERSION=$1
    DIR=jSeduite_$VERSION
    BUNDLE=$DIR.zip
    if [ -f $BUNDLE ]; then
	echo "Error: Unable to generate [$BUNDLE]"
	echo "The reason was: [$BUNDLE] exists"
	exit 1
    fi
    echo "### building jSeduite bundle "
    mkdir -v $DIR
    cd $DIR
    extract_sql
    extract_data
    extract_webservices
    extract_hci
    extract_jbi
    cd ..
    cp marble/* $DIR/.
    bundle $DIR
}

if [ $# -eq 0 ]; then
    echo "Error: You MUST specify a version number"
    exit 1
fi

main $@