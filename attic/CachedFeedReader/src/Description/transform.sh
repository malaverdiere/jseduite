source ~/.bash_profile

dot -Tpng -o arch.png -Nfontname=Courier \
    -Gfontpath=/System/Library/Fonts arch.dot
dot -Tpng -o business.png -Nfontname=Courier \
    -Gfontpath=/System/Library/Fonts business.dot