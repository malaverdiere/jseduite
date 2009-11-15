#!/bin/bash
# author sebastien mosser <mosser@polytech.unice.fr>

GOAL=global_arch
OUT=out.png

function usage() {
    echo "Unable to run $0 $@"
    echo "arch_gen.sh OUTPUT_FILE"
    echo "arch_gen.sh OUTPUT_FILE GOAL"
    echo "  GOAL := global_arch, orch_arch, "
}

function transform() {
    cd description
    TMP=`mktemp -t arch_gen.sh.XXXXXX`
    trap "rm $TMP* 2>/dev/null" 0
    
    swipl -s graphviz.pl -g "[jSeduite], run($GOAL), halt." > $TMP
    
    neato -Tpng -o ../$OUT $TMP
    cd ..
}

case $# in
    0) ;;
    1) OUT=$1;;
    2) OUT=$1;
	GOAL=$2;;
    *) usage $@;
	exit 1;;
esac
transform
