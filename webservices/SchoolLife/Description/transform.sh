#!/bin/bash
source ~/.bash_profile

dot -Tpng -o arch.png -Nfontname=Courier \
    -Gfontpath=/System/Library/Fonts arch.dot