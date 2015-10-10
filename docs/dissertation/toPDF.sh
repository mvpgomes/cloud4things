BASENAME=dissertation
NAME=dissertation.tex
pdflatex -shell-escape $NAME
bibtex $BASENAME
makeglossaries $BASENAME
pdflatex -shell-escape $NAME
pdflatex -shell-escape $NAME
