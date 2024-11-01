#chmod +x ./build.sh
#./build.sh

clear

echo "---------------------------------[ Building environment           ]---------------------------------"
PROYECT_PATH=$(dirname "$(realpath ${BASH_SOURCE:-$0})")
NPM=npm
MVN=mvn

echo "---------------------------------[ Validate environment           ]---------------------------------"
if ! [ -d $PROYECT_PATH ]
then
  echo "$PROYECT_PATH not be found"
fi
if ! command -v NPM 2>&1 >/dev/null
then
    echo "npm not be found"
    exit 1
fi
if ! command -v MVN 2>&1 >/dev/null
then
    echo "mvn not be found"
    exit 1
fi

echo "---------------------------------[ Building the nodejs project    ]---------------------------------"
cd $PROYECT_PATH
$NPM install
$NPM run build

echo "---------------------------------[ Building the maven project     ]---------------------------------"
cd $PROYECT_PATH
$MVN clean install
