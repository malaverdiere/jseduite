#!/bin/bash
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
#  @author Sébastien Mosser <mosser@polytech.unice.fr>
###

#########################################################
## !!!!!!!!!!! DO NOT EDIT AFTER THIS LINE !!!!!!!!!!! ##
#########################################################

source ./config.sh

function wsdeploy() 
{
    NAME=`echo $1 | cut -d . -f 1`
    echo "#   Deploying [$NAME] Web Service using [$1]"
    asadmin deploy --precompilejsp --name $NAME --contextroot /jSeduite/$NAME $1 > /dev/null
}

function deploy-web-services() 
{
    echo "# Deploying jSeduite Web Services from [$1]"
    OLD=`pwd`
    cd $1
    for i in *.war
    do
	wsdeploy $i
    done
    cd $OLD
}

function jbideploy() 
{
    NAME=`echo $1 | cut -d . -f 1`
    echo "#   Deploying [$NAME] assembly using [$1]"
    asadmin deploy-jbi-service-assembly $1 > /dev/null
    asadmin start-jbi-service-assembly $NAME > /dev/null
}

function deploy-jbi-assemblies() 
{
    echo "# Deploying jSeduite BPEL processes from [$1]"
    OLD=`pwd`
    cd $1
    for i in *.zip
    do
	jbideploy $i
    done
    cd $OLD
}

function sqlexec() 
{
    mysql -u $SQL_USER -p$SQL_PASSWORD jSeduite < $1 
}

function sql-init()
{
    echo "# Dropping database content !"
    #echo 'DROP DATABASE IF EXISTS `jSeduite`' | mysql -u $SQL_USER -p$SQL_PASSWORD
    #echo 'CREATE DATABASE `jSeduite`' | mysql -u $SQL_USER -p$SQL_PASSWORD
}

function sql-load() 
{
    echo "# Loading database from [$1]"
    OLD=`pwd`
    cd $1
    for i in *.sql
    do
	echo "#   Running [$i] SQL script"
	sqlexec $i
    done
    cd $OLD
}

function glassfish_connect()
{
    if [ "login" = "$1" ]; then
	echo "# connecting to glassfish application server"
	asadmin login 
    else
	echo "# deleting application server account authorizations"
	rm -f ~/.asadminpass
    fi
}

function main()
{
    if [ "dbinit" = "$1" ]; then
	sql-init
	sql-load ./sql
	sql-load ./sql_fill
    fi
    glassfish_connect login
    deploy-web-services ./ws
    deploy-web-services ./hci
    deploy-jbi-assemblies ./jbi
    glassfish_connect logout
}

main $@