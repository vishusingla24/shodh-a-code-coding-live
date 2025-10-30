
#!/bin/bash
set -e
cd /workspace || exit 1
MAIN_FILE=$(ls *.java 2>/dev/null | head -n1 || true)
if [ -z "$MAIN_FILE" ]; then
  echo "NO_SOURCE"; exit 2
fi
javac "$MAIN_FILE" 2> compile_err.txt || { cat compile_err.txt; exit 3; }
if [ -f input.txt ]; then
  timeout 5 java ${MAIN_FILE%.java} < input.txt
else
  timeout 5 java ${MAIN_FILE%.java}
fi
EXIT=$?
if [ $EXIT -eq 124 ]; then echo "TIMEOUT"; exit 124; fi
exit $EXIT
