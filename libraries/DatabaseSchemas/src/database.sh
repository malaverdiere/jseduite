#!/bin/bash
###
#  This file is part of jSeduite::Database Schemas
#
#  Copyright (C) 2008-  Sebastien Mosser
#
#  jSeduite::DatabaseSchema is free software; you can redistribute it and/or modify
#  it under the terms of the GNU General Public License as published by
#  the Free Software Foundation; either version 2 of the License, or
#  (at your option) any later version.
#
#  jSeduite::DatabaseSchema is distributed in the hope that it will be useful,
#  but WITHOUT ANY WARRANTY; without even the implied warranty of
#  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
#  GNU General Public License for more details.
#
#  You should have received a copy of the GNU General Public License
#  along with jSeduite::DatabaseSchema; if not, write to the Free Software
#  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
#
#  @author Main Mosser Sebastien [mosser@polytech.unice.fr]
###

# read "Database Username: " USER
# read "Database Password: " PASSWORD

USER=epub
PASSWORD=bupe
MYSQL=/usr/local/mysql/bin/mysql

LOG_FILE=`pwd`"/database.log"

function run() {
    echo "# Executing $1"
    $MYSQL -vvv -u $USER -p$PASSWORD jSeduite < $1 >> $LOG_FILE
}

function run_dir() {
  OLD=`pwd`
  echo "## Executing $1 content"
  cd $1
  for i in  *.sql
    do
    run $i
  done
  echo "##"
  cd $OLD
}

if [ $# -eq 1 ]
    then
    run DatabaseCreation.sql
fi

if [ -e $LOG_FILE ]
    then
    rm -f $LOG_FILE
fi

run_dir Schemas
run_dir Data

