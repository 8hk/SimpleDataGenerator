# SimpleDataGenerator
Most of time, I need at least a million of lines of data to test my projects.  To generate data, coded this simple project with using Java 13.


## Simple usage:

java -jar SimpleDataGenerator.jar -c 5 -l 10000 -ch 20 -sep | -f file.txt 
sample data for following command
sujeeixqbgccntmromno|cptswxrbncjeufbmkesr|ysryvfedaqcqsxeulyrh|xwcpegoezvmmxwxwyhpz|fpkbbprzyoealcuragtr
ydqkyagcopsgtwriolao|khzdfvzpgdwowihlkgzi|jjwxubaiekweokujcprf|eqmzyhmudhvyrluosanq|nxaujpmjzajzmemidhbp


## Usage Parameters

usage: utility-name
 -c,--columns_option <arg>          how many columns for each line
 -ch,--string_length_option <arg>   character size of each columns
 -f,--filename_option <arg>         filename
 -l,--lines_option <arg>            how many lines will be generated
 -sep,--seperator_option <arg>      which seperator will be used between
                                    columns