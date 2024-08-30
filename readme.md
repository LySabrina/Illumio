# How to run

- Ensure JDK is installed
- CD into this folder
- Run "javac *.java" on the terminal
- Run "java Main" on the terminal

# Assumptions

- Flow log data is in default format
- Supported version is version 2
- Program supports protocols specified in the flow log example

Tested using the flow log example provided.

# Approach

I used an enum to hold constant values that I know which are the protocol values. By using an enum, I will utilize it as a key for a hashmap.
I create a LookupTable class that is used to represent the table such that it will be used by other classes.
I used the protocol as the key in the hashmap and its value as a hashmap again. The value as a hashmap will have the port number as a key and tag as the value.
This is because protocol + port = tag.

FlowLog class is used to loop through the flow log and perform the counts by using the LookupTable class.
To count, I use hashmap frequently to keep track of count corresponding to the tag and port/protocol.

FileCreation is a class responsible for creating/writing to the tag_count.txt and port_proto_combo.txt.
